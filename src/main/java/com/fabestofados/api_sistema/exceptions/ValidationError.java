package com.fabestofados.api_sistema.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

//Classe específica para representar erros de validação de DTO
public class ValidationError extends StandardError{
	
	private Map<String, String> fieldErrors = new HashMap<>();
	// Map com os campos inválidos e suas mensagens de erro

	public ValidationError(Instant timestamp, Integer status, String erro, String message) {
		super(timestamp, status, erro, message);
	}

	//Adiciona um erro de campo no Map
	public void addError(String fieldName, String errorMessage) {
		fieldErrors.put(fieldName, errorMessage);
	}
	
	public Map<String, String> getFieldErrors(){
		return fieldErrors;
	}
}
