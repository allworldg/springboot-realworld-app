package realworld.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomErrors {
    private Map<String, List<String>> errors;

    public CustomErrors() {
        this.errors = new HashMap<>();
    }

    public CustomErrors(Map<String, List<String>> errorsMap) {
        this.errors = errorsMap;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
