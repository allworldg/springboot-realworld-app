package realworld.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import realworld.user.LoginUser;
import realworld.user.ProfileVo;
import realworld.user.service.ProfileService;

@RestController()
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{username}")
    public ProfileVo getProfile(@PathVariable("username") String username, @AuthenticationPrincipal
    LoginUser user) {
        return new ProfileVo(profileService.getProfileByUserName(username, user));
    }

    @PostMapping("{username}/follow")
    public ProfileVo addFollow(@PathVariable("username") String username,
                               @AuthenticationPrincipal LoginUser user) {
        profileService.addFollow(username, user);
        return new ProfileVo(profileService.getProfileByUserName(username, user));
    }

    @DeleteMapping("{username}/follow")
    public ProfileVo unFollow(@PathVariable("username") String username,@AuthenticationPrincipal LoginUser user) {
        profileService.removeFollow(username,user);
        return new ProfileVo(profileService.getProfileByUserName(username,user));
    }
}
