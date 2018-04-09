package cms.sre.mongo_connection_helper;

import com.mongodb.*;
import com.mongodb.connection.Server;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MongoClientFactory {

    private static final ServerAddress DEFAULT_LOCALHOST_ADDRESS_AND_PORT = new ServerAddress("localhost", 27017);

    private static boolean isNotEmptyOrNull(String str){
        return str != null && str.length() > 0;
    }

    private static MongoClientOptions mongoClientOptions(MongoClientParameters mongoClientParameters){
        File trustStore = null;
        if(isNotEmptyOrNull(mongoClientParameters.getTrustStoreLocation()) && isNotEmptyOrNull(mongoClientParameters.getTrustStorePassword())){
            trustStore = Paths.get(mongoClientParameters.getTrustStoreLocation())
                .toFile();
        }

        File keyStore = null;
        if(isNotEmptyOrNull(mongoClientParameters.getKeyStoreLocation()) && isNotEmptyOrNull(mongoClientParameters.getKeyStorePassword())&& isNotEmptyOrNull(mongoClientParameters.getKeyStoreKeyPassword())){
            keyStore = Paths.get(mongoClientParameters.getKeyStoreLocation())
                    .toFile();
        }

        SSLContext sslContext = null;
        try{
            if(keyStore != null || trustStore != null){
                SSLContextBuilder custom = SSLContexts.custom()
                        .setProtocol("TLSv1.2");

                if(keyStore != null){
                    custom = custom.loadKeyMaterial(keyStore,
                            mongoClientParameters.getKeyStorePassword().toCharArray(),
                            mongoClientParameters.getKeyStoreKeyPassword().toCharArray(),
                            (aliases, socket) -> {
                                return aliases.keySet()
                                        .iterator()
                                        .next();
                    });
                }

                if(trustStore != null){
                    custom = custom.loadTrustMaterial(trustStore, mongoClientParameters.getTrustStorePassword().toCharArray());
                }

                sslContext = custom.build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        if(sslContext != null){
            builder.sslContext(sslContext)
                    .sslEnabled(true)
                    .sslInvalidHostNameAllowed(true);
        }

        if(mongoClientParameters.getReplicaSetName() != null){
            builder.requiredReplicaSetName(mongoClientParameters.getReplicaSetName());
        }

        return sslContext == null && isNotEmptyOrNull(mongoClientParameters.getReplicaSetName()) ? null : builder.build();
    }

    private static List<ServerAddress> serverAddresses(MongoClientParameters params){
        List<ServerAddress> ret = new LinkedList<ServerAddress>();
        if(params.getReplicaSetLocations().length > 0){
            Arrays.asList(params.getReplicaSetLocations())
                    .forEach(arg -> ret.add(new ServerAddress(arg)));
        }
        return ret.size() > 0 ? ret : null;
    }

    private static MongoCredential mongoCredential(MongoClientParameters mongoClientParameters){
        MongoCredential ret = null;
        if(isNotEmptyOrNull(mongoClientParameters.getDatabaseName()) && isNotEmptyOrNull(mongoClientParameters.getMongoUsername()) && isNotEmptyOrNull(mongoClientParameters.getMongoPassword())){
            ret = MongoCredential.createCredential(mongoClientParameters.getMongoUsername(), mongoClientParameters.getDatabaseName(), mongoClientParameters.getMongoPassword().toCharArray());
        }
        return ret;
    }

    public static MongoClient getMongoClient(MongoClientParameters mongoClientParameters){
        MongoClientOptions options = mongoClientOptions(mongoClientParameters);
        List<ServerAddress> addresses = serverAddresses(mongoClientParameters);
        MongoCredential creds = mongoCredential(mongoClientParameters);

        MongoClient ret = null;

        if(options != null && addresses != null && creds != null){
            ret = new MongoClient(addresses, creds, options);
        }else if(options != null && addresses != null && creds == null) {
            ret = new MongoClient(addresses, options);
        }else if(addresses != null && options == null && creds == null) {
            ret = new MongoClient(addresses);
        }else if(addresses != null && creds != null && options == null){
            ret = new MongoClient(addresses, creds, MongoClientOptions.builder().build());
        }else {
            ret = new MongoClient();
        }

        return ret == null ? new MongoClient() : ret;
    }

    public static MongoClient getLocalhostMongoClient(){
        return new MongoClient();
    }

    public static MongoClient getLocalhostMongoClient(String databaseName, String username, String password){
        MongoCredential credential = MongoCredential.createCredential(username, databaseName, password.toCharArray());
        return new MongoClient(DEFAULT_LOCALHOST_ADDRESS_AND_PORT, credential, MongoClientOptions.builder().build());
    }

}
