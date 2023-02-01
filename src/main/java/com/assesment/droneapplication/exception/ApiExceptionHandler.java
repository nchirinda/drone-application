package com.assesment.droneapplication.exception;

import com.assesment.droneapplication.model.payload.ApiErrorResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nyasha Chirinda - 31/01/2023
 */

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResp> handleResourceNotFoundException(ResourceNotFoundException rnfe, ServletWebRequest request) {
        log.error("Exception: ResourceNotFound - ", rnfe);
        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), rnfe.getMessage(), request.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResp);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException mave, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Exception: Request Validation - ", mave);

        Map<String, String> errors = new HashMap<>();
        mave.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.BAD_REQUEST.value(), "Request fields validation failed", errors, request.getContextPath());
        return ResponseEntity.badRequest().body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = InsufficientBatteryCapacityException.class)
    public ResponseEntity<ApiErrorResp> handleInsufficientBatteryCapacityException(InsufficientBatteryCapacityException ibce, ServletWebRequest request) {
        log.error("Exception: Insufficient BatteryCapacity - ", ibce);
        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Drone Battery capacity too low", ibce.getMessage(), request.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiErrorResp);
    }

    @ResponseBody
    @ExceptionHandler(value = MedicationWeightOverloadException.class)
    public ResponseEntity<ApiErrorResp> handleMedicationWeightOverloadException(MedicationWeightOverloadException mwoe, ServletWebRequest request) {
        log.error("Exception: Medication WeightOverload - ", mwoe);
        ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.BAD_REQUEST.value(), "Medication weight is beyond drone capacity", mwoe.getMessage(), request.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResp);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleUncaughtException(final Exception ex, final ServletWebRequest request) {
        log.error("Exception: ", ex);

        final ApiErrorResp apiErrorResp = new ApiErrorResp(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while processing request", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), request.getRequest().getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResp);
    }

}
