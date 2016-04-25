package squire.Users;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by brandon on 4/25/16.
 */
public class PasswordHashTest {
    ArrayList<String> hashes = new ArrayList<>();

    @Test
    public void createHash() throws Exception {
        //Generate 20 hashes, all should be unique
        HashSet<String> hashes = new HashSet<>();
        for (int i = 0; i < 19; i++) {
            String hash = PasswordHash.createHash(new Integer(i).toString());
            assertFalse("failure - duplicate password hashes", hashes.contains(hash));
            hashes.add(hash);
        }
    }

    @Test
    public void validatePassword() throws Exception {
        String password = "password123";
        String hash = PasswordHash.createHash(password);
        assertTrue("failure - correct password not accepted", PasswordHash.validatePassword(password, hash));
        assertFalse("failure - incorrect password accepted", PasswordHash.validatePassword("notThePassword", hash));
    }
}