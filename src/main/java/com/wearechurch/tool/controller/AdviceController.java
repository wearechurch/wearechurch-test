package com.wearechurch.tool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.codec.CodecException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wearechurch.tool.dto.Response;
import com.wearechurch.tool.enumerator.Reply;
import com.wearechurch.tool.exception.RarityException;

@ControllerAdvice
public class AdviceController {

	private final Logger logger = LoggerFactory.getLogger(AdviceController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exception(final Exception exception) {
		return logResponse(exception, Reply.EXCEPTION);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Response> httpMessageNotReadableException(final HttpMessageNotReadableException exception) {
		return logResponse(exception, Reply.MESSAGE_READABLE);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<Response> httpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException exception) {
		return logResponse(exception, Reply.REQUEST_SUPPORTED);
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseEntity<Response> indexOutOfBoundsException(final IndexOutOfBoundsException exception) {
		return logResponse(exception, Reply.INDEX_BOUNDS);
	}

	private ResponseEntity<Response> logResponse(final Exception exception, final Reply reply) {
		logger.error("Code: {} | Class: {} | Message: {}", reply.getCode(), exception.getClass().getSimpleName(),
				exception.getLocalizedMessage());
		return ResponseEntity.ok(new Response(reply));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> methodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		return logResponse(exception, Reply.METHOD_ARGUMENT);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Response> numberFormatException(final NumberFormatException exception) {
		return logResponse(exception, Reply.NUMBER_FORMAT);
	}

	@ExceptionHandler(CodecException.class)
	public ResponseEntity<Response> rarity(final CodecException exception) {
		return ResponseEntity.ok(new Response(Reply.CODEC));
	}

	@ExceptionHandler(RarityException.class)
	public ResponseEntity<Response> rarity(final RarityException exception) {
		return ResponseEntity.ok(new Response(exception.getReply()));
	}
}
