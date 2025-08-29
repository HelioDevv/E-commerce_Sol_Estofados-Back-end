package com.fabestofados.api_sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabestofados.api_sistema.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
