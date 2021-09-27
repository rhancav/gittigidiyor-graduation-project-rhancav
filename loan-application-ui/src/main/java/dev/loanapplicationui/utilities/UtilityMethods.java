package dev.loanapplicationui.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class UtilityMethods {
    public static String logErrors(BindingResult bindingResult, String form){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach((e)-> errors.put(e.getField(),e.getDefaultMessage()));
            errors.forEach((e, c) -> log.warn(e+" "+c));
            return form;
        }
        return null;
    }
}
