package com.kameleoon.exception.handler;

import com.kameleoon.exception.KameleoonTrialTaskException;
import com.kameleoon.exception.QuoteNoteFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class KameleoonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KameleoonTrialTaskException.class)
    public ProblemDetail handleKameloonTrialTaskException(KameleoonTrialTaskException ex) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.I_AM_A_TEAPOT, ex.getLocalizedMessage());
        pd.setType(URI.create("http://localhost:8080/errors/user-account"));
        pd.setTitle(HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
        return pd;
    }

    @ExceptionHandler(QuoteNoteFoundException.class)
    public ProblemDetail handleQuoteNoteFoundException(QuoteNoteFoundException ex) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        pd.setType(URI.create("http://localhost:8080/errors/quote"));
        pd.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        return pd;
    }
}