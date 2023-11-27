package allworldg.api.exception;


import allworldg.common.*;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super(HttpCommon.UNAUTHORIZED);
    }
}
