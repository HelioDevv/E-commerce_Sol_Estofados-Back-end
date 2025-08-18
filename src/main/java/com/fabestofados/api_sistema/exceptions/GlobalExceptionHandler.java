package com.fabestofados.api_sistema.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Diz ao Spring que esta classe vai interceptar e tratar exceções de toda a aplicação
public class GlobalExceptionHandler {

	// Tratamento para quando um recurso não é encontrado (ex: Usuario inexistente)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		//Cria um objeto padrão de erro
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				"Erro de validação",
				"Dados invádidos"
		);
		
		return ResponseEntity.status(status).body(err);
	}
	
	// Tratamento para erros de validação de DTOs (ex: @NotBlank, @Email, etc)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> handleValidationErrors(MethodArgumentNotValidException e){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		//Objeto de erro especíco para validação
		ValidationError err = new ValidationError(
				Instant.now(),
				status.value(),
				"Erro de validação",
				"Dados inválidos"
				
		);
		
		//Percorre os erros de validação e adiciona no objeto
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
	
	//Tratamento genérico para qualquer exceção não mapeada
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> handleGenericException(Exception e){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				"Erro inesperado",
				e.getMessage()
		);
		
		return ResponseEntity.status(status).body(err);
	}
	
}
