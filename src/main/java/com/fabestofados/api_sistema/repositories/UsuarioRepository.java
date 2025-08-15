package com.fabestofados.api_sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabestofados.api_sistema.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
