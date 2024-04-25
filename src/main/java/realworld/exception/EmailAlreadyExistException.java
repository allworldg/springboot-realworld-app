package realworld.exception;

import realworld.common.HttpCommon;

public class EmailAlreadyExistException extends  RuntimeException {
    public EmailAlreadyExistException(){
        super(HttpCommon.EMAIL_ALREADY_EXISTED);
    }
}
