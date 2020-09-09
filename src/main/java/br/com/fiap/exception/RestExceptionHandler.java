package br.com.fiap.exception;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.fiap.business.BusinessException;

@RestControllerAdvice
public class RestExceptionHandler {

	private static final String UNEXPECTED_ERROR = "Um erro inesperado aconteceu, contato o suporte em suporte-api@fiap.com.br";
	private static final String RESOURCE_NOT_FOUND = "Recurso n�o encontrado";
	private static final String INVALID_JSON = "Json inv�lido";
	private static final String METHOD_NOT_SUPPORTED = "M�todo n�o suportado";
	private static final String INVALID_ARGUMENTS = "Argumentos inv�lidos";
	private static final String INVALID_PATH_PARAM = "Tipo de par�metro inv�lido, verifique a documenta��o";

	
	@ExceptionHandler(value = {NoHandlerFoundException.class, NoSuchElementException.class, EmptyResultDataAccessException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseException notFoundException(HttpServletRequest request, Exception exception) {
		return new ResponseException(request, RESOURCE_NOT_FOUND, exception.getMessage());
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseException badRequestException(HttpServletRequest request, HttpMessageNotReadableException exception) {
		return new ResponseException(request, INVALID_JSON, exception.getMessage());
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseException badRequestException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
		return new ResponseException(request, METHOD_NOT_SUPPORTED, exception.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseException badRequestException(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
		return new ResponseException(request, INVALID_PATH_PARAM, exception.getMessage());
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseException badRequestException(HttpServletRequest request, MethodArgumentNotValidException exception) {
		
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		
		HashMap<String, String> detail = new HashMap<String, String>();
		
		for (FieldError fieldError : errors) {
			detail.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return new ResponseException(request, INVALID_ARGUMENTS, detail);
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseException handleException(HttpServletRequest request, Exception exception) {
		return new ResponseException(request, UNEXPECTED_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(value = BusinessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseException handleBusinessException(HttpServletRequest request, Exception exception) {
		return new ResponseException(request, INVALID_ARGUMENTS, exception.getMessage());
	}
}
