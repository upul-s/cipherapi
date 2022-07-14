package com.test.cipherapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/** Exception handler for api. */
@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {



  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public AppErrors handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    log.info("Method argument not valid", e);
    return new AppErrors()
            .withErrors(
                    e.getBindingResult().getAllErrors().stream()
                            .map(oe -> new AppErrors.ErrorMessage(oe.getCodes()[0], oe.getDefaultMessage()))
                            .collect(toList()));
  }


  @ExceptionHandler(CipherNotFoundException.class)
  @ResponseBody
  public ResponseEntity<CustomError> handleCipherNotFoundException(
      CipherNotFoundException e, WebRequest request) {
    log.info("Bad request", e);
    CustomError customError = new CustomError();
    customError.setStatusCode(HttpStatus.NOT_FOUND.value());
    customError.setMessage(e.getMessage());
    customError.setTimesTamp(new Date());
    return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AppErrorsException.class)
  @ResponseBody
  public AppErrors handleAppErrors(AppErrorsException e) {
    log.info("Bad request", e);
    return e.getAppErrors();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  public AppErrors handleIllegalArgument(IllegalArgumentException e) {
    log.info("Invalid argument", e);
    return new AppErrors("invalid.argument", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseBody
  public AppErrors handleIllegalArgument(MethodArgumentTypeMismatchException e) {
    log.info("Invalid argument", e);
    return new AppErrors(
        "invalid.argument." + e.getName(),
        format("'%s' is illegal value for %s", e.getValue(), e.getName()));
  }



  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MultipartException.class)
  @ResponseBody
  public AppErrors handleNotMultipartFile(MultipartException e) {
    log.info("File upload failed", e);
    return new AppErrors("file.upload", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseBody
  public AppErrors handleTypeNotSupported(Exception e) {
    log.info("Invalid content type", e);
    return new AppErrors("invalid.content.type", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseBody
  public AppErrors handleMethodNotSupported(Exception e) {
    log.info("Invalid method", e);
    return new AppErrors("invalid.method", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  public AppErrors handleMissingParameter(MissingServletRequestParameterException e) {
    log.info("Missing parameter", e);
    return new AppErrors("missing.parameter", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(PropertyReferenceException.class)
  @ResponseBody
  public AppErrors handleInvalidProperty(PropertyReferenceException e) {
    log.info("Invalid property", e);
    return new AppErrors("invalid.property", e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public AppErrors handleMessageNotReadable(HttpMessageNotReadableException e) {
    log.info("Not readable request body", e);
    return new AppErrors("invalid.request.body", "Request body is not readable");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConnectException.class)
  @ResponseBody
  public AppErrors handleAlwaysOnInsightsException(ConnectException e) {
    log.info(e.getMessage(), e);
    return new AppErrors(e);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseBody
  public AppErrors handleResourceNotFound(ResourceNotFoundException e) {
    log.info("Resource not found", e);
    return new AppErrors("resource.not.found", "Resource does not exist");
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public AppErrors handleInternalServerError(Exception e) {
    UUID uuid = UUID.randomUUID();
    log.error("Internal Server Error. " + uuid, e);
    return new AppErrors("internal-server-error", e.getMessage() + ". Correlation ID: " + uuid);
  }
}
