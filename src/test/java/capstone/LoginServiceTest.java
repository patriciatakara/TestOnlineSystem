package capstone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.capstone.serviceClasses.AuthenticationService;

/**
 * This file is testing whether the AuthenticationService correctly handles valid and invalid login credentials.
 */
public class LoginServiceTest{

	AuthenticationService authenticate01 = new AuthenticationService();
  AuthenticationService authenticate02 = new AuthenticationService();
  AuthenticationService authenticate03 = new AuthenticationService();

		// This checks whether valid credentials are accepted
	 @Test
	    public void testLogin1() throws Exception {
	      // Call the authentication method here.
        boolean result = authenticate01.authenticate("nao@gmail.com", "123", "STUDENTS");
        // Assert that the login succeeds. 
        assertEquals(true, result);
      }

    @Test
      public void testLogin2() throws Exception {
	      // Call the authentication method here.
        boolean result = authenticate02.authenticate("dean@gmail.com", "123", "STUDENTS");
        // Assert that the login succeeds. 
        assertEquals(true, result);
      }

    @Test
      public void testLogin3() throws Exception {
	      // Call the authentication method here.
        boolean result = authenticate03.authenticate("nao@edu.com", "123", "STUDENTS");
        // Assert that the login succeeds. 
        assertEquals(false, result);
      }
}
