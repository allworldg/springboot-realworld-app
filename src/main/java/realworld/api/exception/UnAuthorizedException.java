package realworld.api.exception;


import realworld.common.*;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(HttpCommon.UNAUTHORIZED);
    }
}
