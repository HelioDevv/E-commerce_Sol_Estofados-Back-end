package com.fabestofados.api_sistema.dtos;

public record EnderecoDTO(
		Long id,
        String rua,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String tipo
) {}
