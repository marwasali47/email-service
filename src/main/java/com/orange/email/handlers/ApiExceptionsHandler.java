package com.orange.email.handlers;

import com.orange.email.handlers.exceptions.ApiBaseException;
import com.orange.email.handlers.exceptions.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetails> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {

        ErrorDetails err = new ErrorDetails(messageSource.getMessage("max.upload.size", null, LocaleContextHolder.getLocale()));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> handleApiExceptions(ApiBaseException ex, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < fieldErrors.size(); i ++){

            if (i != 0)
                sb.append(",");

            sb.append(messageSource.getMessage(fieldErrors.get(i).getDefaultMessage(), null, LocaleContextHolder.getLocale()));
        }

        return new ResponseEntity<>(new ErrorDetails(sb.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolation(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        Iterator<ConstraintViolation<?>> iterator = violations.iterator();

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (iterator.hasNext()){

            if (i != 0)
                sb.append(",");

            ConstraintViolation<?> constraintViolation = iterator.next();
            sb.append(messageSource.getMessage(constraintViolation.getMessage()
                    , new Object[]{constraintViolation.getInvalidValue()}, LocaleContextHolder.getLocale()));

            i++;
        }

        return new ResponseEntity<>(new ErrorDetails(sb.toString()), HttpStatus.BAD_REQUEST);
    }

}