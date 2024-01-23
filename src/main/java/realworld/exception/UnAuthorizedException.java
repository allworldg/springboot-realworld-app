package realworld.exception;


import realworld.common.*;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(HttpCommon.UNAUTHORIZED);
    }
}
