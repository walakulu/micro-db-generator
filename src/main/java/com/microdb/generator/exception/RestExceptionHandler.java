package com.microdb.generator.exception;

import com.microdb.generator.dto.ErrorDto;
import com.microdb.generator.dto.ErrorsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(SchemaPropertyValidationException.class)
	public ResponseEntity<ErrorsDto> handlePropertyValidationException(SchemaPropertyValidationException ex) {
		LOGGER.info("Invalid Property:", ex);
		ErrorsDto errorsDto = ErrorsDto.builder().withError(ErrorDto.builder()
				.withCode(ApiError.SCHEMA_PROPERTY_VALIDATION_FAILURE.getCode()).withMessage(ex.getMessage()).build())
				.build();

		return new ResponseEntity<>(errorsDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorsDto> badRequestException(HttpMessageNotReadableException ex) {
		LOGGER.info("Bad Request:", ex);
		ErrorsDto errorsDto = ErrorsDto.builder().withError(ErrorDto.builder().withCode(ApiError.BAD_REQUEST.getCode())
				.withMessage(ApiError.BAD_REQUEST.getMessage()).build()).build();

		return new ResponseEntity<>(errorsDto, HttpStatus.BAD_REQUEST);
	}

}
