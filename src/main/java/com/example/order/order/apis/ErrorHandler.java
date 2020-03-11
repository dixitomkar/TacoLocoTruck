package com.example.order.order.apis;


import com.example.order.order.exceptions.ApplicationError;
import com.example.order.order.exceptions.InvalidOrderError;
import com.example.order.order.exceptions.ItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<ApplicationError> handleError(ItemNotFound exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode(404);
        applicationError.setMessage(exception.getMessage());

        return new ResponseEntity<>(applicationError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOrderError.class)
    public ResponseEntity<ApplicationError> handleError(InvalidOrderError exception, WebRequest webRequest) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode(400);
        applicationError.setMessage(exception.getMessage());

        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }
}
