package realworld.user;

public class ProfileVo {
    private Profile profile;


    public ProfileVo(Profile profile) {
        this.profile = profile;
    }

    public ProfileVo() {
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
