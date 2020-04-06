package pl.bsobieski.crudlibrary.utils.errors;

import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private Integer errorCode;
    private List<String> errors;
    private SimpleDateFormat timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public ApiError(HttpStatus status, Integer errorCode, List<String> errors) {
        this.status = status;
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, Integer errorCode, String error){
        this.status = status;
        this.errorCode = errorCode;
        this.errors = Collections.singletonList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getTimestamp() {
        Date date = new Date();
        return timestamp.format(date);
    }
}
