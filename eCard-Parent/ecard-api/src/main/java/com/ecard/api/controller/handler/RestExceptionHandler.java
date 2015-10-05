/*
 * RestExceptionHandler
 */
package com.ecard.api.controller.handler;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author vinhla
 */
public abstract class RestExceptionHandler {
    public static final String OK_RESPONSE = "OK";
    
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
    
    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String handleLoginException(Exception e) 
    {
        LOG.info("Returning HTTP " + HttpServletResponse.SC_UNAUTHORIZED, e); // The cause of the error is logged upstream at the error level so dial down the log level here
        return e.getMessage();
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value=HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleAccessDeniedException(Exception e) 
    {
        LOG.warn("Returning HTTP " + HttpServletResponse.SC_FORBIDDEN, e);
        return e.getMessage();
    }
    
    @ExceptionHandler(value={NullPointerException.class,IllegalArgumentException.class})
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleClientException(Exception e)
    {
        LOG.warn("Returning HTTP " + HttpServletResponse.SC_BAD_REQUEST, e);
        return e.getMessage();
    }
    
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleServerException(Exception e)
    {
        LOG.error("Returning HTTP " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
        return e.getMessage();
    }
    
    @ExceptionHandler(value={HttpMessageNotReadableException.class,HttpMessageNotWritableException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleServerExceptionSilently(Exception e)
    {
        LOG.warn("Returning HTTP " + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e);
        return e.getMessage();
    }
    
    @ExceptionHandler(value={TransientDataAccessException.class,UnexpectedRollbackException.class,DataIntegrityViolationException.class})
    @ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public String handleTransientException(Exception e, HttpServletResponse response)
    {
        LOG.warn("Returning HTTP " + HttpServletResponse.SC_SERVICE_UNAVAILABLE, e);
        response.setHeader(HttpHeaders.RETRY_AFTER, "5"); // Indicate that client can retry after 5 seconds
        return e.getMessage();
    }
    
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleAccountNotFoundException(Exception e)
    {
        LOG.warn(e.getMessage());
        return e.getMessage();
    }
}
