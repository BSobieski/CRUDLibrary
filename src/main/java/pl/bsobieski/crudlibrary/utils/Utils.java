package pl.bsobieski.crudlibrary.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> getErrorMessage(BindingResult bindingResult) {
        List<String> errorMsg = new ArrayList<>();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.add(error.getObjectName() + " - " + error.getCode());
        }
        return errorMsg;
    }
}
