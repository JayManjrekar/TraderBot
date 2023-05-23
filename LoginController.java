import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("userid") String userId, @RequestParam("name") String name) {
        // Perform login logic here
        if (isValidLogin(userId, name)) {
            return "success"; // Redirect to success page
        } else {
            return "error"; // Redirect to error page
        }
    }
