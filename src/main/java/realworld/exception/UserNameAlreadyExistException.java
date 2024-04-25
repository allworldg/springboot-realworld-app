package realworld.exception;


import realworld.common.HttpCommon;

public class UserNameAlreadyExistException extends RuntimeException {
    public UserNameAlreadyExistException() {
        super(HttpCommon.USERNAME_ALREADY_EXISTED);
    }
}
