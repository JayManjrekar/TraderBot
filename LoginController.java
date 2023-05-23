import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String userid, @RequestParam String name) {
        // Place your authentication logic here.
        System.out.println("User ID: " + userid);
        System.out.println("Name: " + name);

        // If user authenticated successfully
        return "redirect:/welcome"; // This should be the URL of your welcome page

        // If authentication failed
        // return "redirect:/login?error"; // Use this if you want to redirect back to the login page
    }
}
