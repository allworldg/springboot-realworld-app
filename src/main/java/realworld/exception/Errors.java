package realworld.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
import java.util.Map;

@JsonRootName("errors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors {
    private Map<String, List<String>> errorsMap;

    public Errors() {
    }

    public Errors(Map<String, List<String>> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, List<String>> getErrorsMap() {
        return errorsMap;
    }

    public void setErrorsMap(Map<String, List<String>> errorsMap) {
        this.errorsMap = errorsMap;
    }
}
