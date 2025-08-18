package com.fabestofados.api_sistema.exceptions;

import java.time.Instant;

// Classe padrão para representar um erro genérico
public class StandardError {

	private Instant timestamp;	//Momento em que o erro aconteceu
	private Integer status;		//Código HTTP (404, 400, 500, etc.)
	private String erro; 		//Tipo do erro (Recurso não encontrado, Erro de validação, etc.)
	private String message; 	//Mensagem detalhada do erro;
	
	
	public StandardError(Instant timestamp, Integer status, String erro, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.message = message;
	}


	public Instant getTimestamp() {return timestamp;}
	public void setTimestamp(Instant timestamp) {this.timestamp = timestamp;}

	
	public Integer getStatus() {return status;}
	public void setStatus(Integer status) {this.status = status;}

	
	public String getErro() {return erro;}
	public void setErro(String erro) {this.erro = erro;}

	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}

}
