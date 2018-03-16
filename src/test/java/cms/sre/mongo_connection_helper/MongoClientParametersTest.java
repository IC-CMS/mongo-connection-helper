package cms.sre.mongo_connection_helper;

import static org.junit.Assert.*;

import org.junit.Test;

public class MongoClientParametersTest {
    @Test
    public void constructorTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;
        assertNotNull("Constructor not working properly", test);
        assertTrue("Test and Other are not initially equal", test == other);
    }

    @Test
    public void trustStorePasswordTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String trustStorePassword = "trustStorePassword";
        other = test.setTrustStorePassword(trustStorePassword);
        assertTrue("TrustStorePassword Setter creates new instances", test == other);
        assertEquals("Setter did not set TrustStorePassword correctly", trustStorePassword, test.getTrustStorePassword());
    }

    @Test
    public void trustStoreLocationTest(){
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String trustStoreLocation = "trustStoreLocation";
        other = test.setTrustStoreLocation(trustStoreLocation);
        assertTrue("TrustStoreLocation Setter creates new instances", test == other);
        assertEquals("Setter did not set TrustStoreLocation correctly", trustStoreLocation, test.getTrustStoreLocation());
    }

    @Test
    public void keyStorePasswordTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String keyStorePassword = "keyStorePassword";
        other = test.setKeyStorePassword(keyStorePassword);
        assertTrue("KeyStorePassword Setter creates new instances", test == other);
        assertEquals("Setter did not set KeyStorePassword correctly", keyStorePassword, test.getKeyStorePassword());
    }

    @Test
    public void keyStoreKeyPasswordTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String keyStoreKeyPassword = "keyStoreKeyPassword";
        other = test.setKeyStoreKeyPassword(keyStoreKeyPassword);
        assertTrue("KeyStoreKeyPassword Setter creates new instances", test == other);
        assertEquals("Setter did not set KeyStorePassword correctly", keyStoreKeyPassword, test.getKeyStoreKeyPassword());
    }

    @Test
    public void keyStoreLocationTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String keyStoreLocation = "keyStoreLocation";
        other = test.setKeyStoreLocation(keyStoreLocation);
        assertTrue("KeyStoreLocation Setter creates new instances", test == other);
        assertEquals("Setter did not set KeyStoreLocation correctly", keyStoreLocation, test.getKeyStoreLocation());
    }

    @Test
    public void mongoUserNameTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String mongoUsername = "mongoUsername";
        other = test.setMongoUsername(mongoUsername);
        assertTrue("MongoUsername Setter creates new instances", test == other);
        assertEquals("Setter did not set MongoUsername correctly", mongoUsername, test.getMongoUsername());
    }

    @Test
    public void mongoPasswordTest() {
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String mongoPassword = "mongoPassword";
        other = test.setMongoPassword(mongoPassword);
        assertTrue("MongoPassword Setter creates new instances", test == other);
        assertEquals("Setter did not set MongoPassword correctly", mongoPassword, test.getMongoPassword());
    }

    @Test
    public void replicaSetLocationTest(){
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String[] replicaSetLocations = new String[]{"replica", "set", "locations"};
        other = test.setReplicaSetLocations(replicaSetLocations);
        assertTrue("ReplicaSetLocations Setter creates new instances", test == other);

        assertNotNull("Setter did not set ReplicaSetLocations correctly -- object is null", test.getReplicaSetLocations());
        assertEquals("Setter did not set ReplicaSetLocations correctly -- sizes are different", replicaSetLocations.length, test.getReplicaSetLocations().length);
        for(int i = 0, len = replicaSetLocations.length; i < len; i++){
            assertEquals("Setter did not set ReplicaSetLocations correctly -- item " + i + " is not equal", replicaSetLocations[i], test.getReplicaSetLocations()[i]);
        }
    }

    @Test
    public void replicaSetNameTest(){
        MongoClientParameters test = new MongoClientParameters();
        MongoClientParameters other = test;

        String replicaSetName = "replicaSetName";
        other = test.setReplicaSetName(replicaSetName);
        assertTrue("ReplicaSetLocations Setter creates new instances", test == other);
        assertEquals("Setter did not set ReplicaSetName correctly", replicaSetName, test.getReplicaSetName());
    }

}
