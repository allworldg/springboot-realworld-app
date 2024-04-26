package realworld.exception;

import realworld.common.HttpCommon;

public class TitleAlreadyExistException extends RuntimeException {
    public TitleAlreadyExistException() {
        super(HttpCommon.ALREADY_EXISTED);
    }
}
