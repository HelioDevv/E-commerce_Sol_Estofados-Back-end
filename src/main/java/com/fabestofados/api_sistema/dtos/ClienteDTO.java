package com.fabestofados.api_sistema.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
		Long cliente_id,
		
		@NotBlank(message = "O telefone é obrigatório")
		@Size(max = 20)
		String telefone,
		
		Long usuario_id) {}
