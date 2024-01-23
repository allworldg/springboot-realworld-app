package realworld.exception;


import realworld.common.HttpCommon;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException() {
        super(HttpCommon.IS_INVALID);
    }
}
