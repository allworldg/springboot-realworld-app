package realworld.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import realworld.user.LoginUser;
import realworld.user.Profile;
import realworld.user.service.ProfileService;

@RestController()
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{username}")
    public Profile getProfile(@PathVariable("username") String username, @AuthenticationPrincipal
    LoginUser user) {
        return profileService.getProfile(username, user);
    }

    @PostMapping("{username}/follow")
    public Profile addFollow(@PathVariable("username") String username) {
        return null;
    }

    @DeleteMapping("{username}/follow")
    public Profile unFollow(@PathVariable("username") String username) {
        return null;
    }
}
