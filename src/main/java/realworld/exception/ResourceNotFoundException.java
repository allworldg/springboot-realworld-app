package realworld.exception;

import static realworld.common.HttpCommon.RESOURCE_NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super(RESOURCE_NOT_FOUND);
    }

}
