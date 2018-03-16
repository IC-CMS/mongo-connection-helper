package cms.sre.mongo_connection_helper;

public class MongoClientParameters {
    private String trustStorePassword;
    private String trustStoreLocation;
    private String keyStorePassword;
    private String keyStoreKeyPassword;
    private String keyStoreLocation;
    private String mongoUsername;
    private String mongoPassword;
    private String[] replicaSetLocations;
    private String replicaSetName;
    private String databaseName;

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public MongoClientParameters setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
        return this;
    }

    public String getTrustStoreLocation() {
        return trustStoreLocation;
    }

    public MongoClientParameters setTrustStoreLocation(String trustStoreLocation) {
        this.trustStoreLocation = trustStoreLocation;
        return this;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public MongoClientParameters setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
        return this;
    }

    public String getKeyStoreKeyPassword() {
        return keyStoreKeyPassword;
    }

    public MongoClientParameters setKeyStoreKeyPassword(String keyStoreKeyPassword) {
        this.keyStoreKeyPassword = keyStoreKeyPassword;
        return this;
    }

    public String getKeyStoreLocation() {
        return keyStoreLocation;
    }

    public MongoClientParameters setKeyStoreLocation(String keyStoreLocation) {
        this.keyStoreLocation = keyStoreLocation;
        return this;
    }

    public String getMongoUsername() {
        return mongoUsername;
    }

    public MongoClientParameters setMongoUsername(String mongoUsername) {
        this.mongoUsername = mongoUsername;
        return this;
    }

    public String getMongoPassword() {
        return mongoPassword;
    }

    public MongoClientParameters setMongoPassword(String mongoPassword) {
        this.mongoPassword = mongoPassword;
        return this;
    }

    public String[] getReplicaSetLocations() {
        return replicaSetLocations;
    }

    public MongoClientParameters setReplicaSetLocations(String[] replicaSetLocations) {
        this.replicaSetLocations = replicaSetLocations;
        return this;
    }

    public String getReplicaSetName() {
        return replicaSetName;
    }

    public MongoClientParameters setReplicaSetName(String replicaSetName) {
        this.replicaSetName = replicaSetName;
        return this;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public MongoClientParameters setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }
}
