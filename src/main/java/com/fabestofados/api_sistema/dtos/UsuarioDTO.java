package com.fabestofados.api_sistema.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
		Long usuario_id,
		
		@NotBlank(message = "O nome é obrigatório")
		@Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caracteres")
		String nome,
		
		@NotBlank(message = "O e-mail é obrigatório")
		@Email(message = "E-mail inválido")
		String email) {}
