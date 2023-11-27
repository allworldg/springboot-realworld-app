package allworldg.api.exception;

import static allworldg.common.HttpCommon.NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super(NOT_FOUND);
    }
}
