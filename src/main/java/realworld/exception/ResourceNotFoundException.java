package realworld.exception;

import static realworld.common.HttpCommon.NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super(NOT_FOUND);
    }
}
