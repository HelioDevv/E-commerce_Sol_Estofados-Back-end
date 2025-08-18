package com.fabestofados.api_sistema.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabestofados.api_sistema.dtos.UsuarioDTO;
import com.fabestofados.api_sistema.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	// GET - Buscar todos os usuários
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<UsuarioDTO> usuarios = service.findAll();
		return ResponseEntity.ok(usuarios);
	}
	
	// GET - Buscar usuário por ID
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
		UsuarioDTO usuario = service.findById(id);
		return ResponseEntity.ok(usuario);
	}
	
	// POST - criar um novo usuário
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO dto){
		UsuarioDTO created = service.insert(dto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	// PUT - atualizar um usuário existente
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto){
		UsuarioDTO updated = service.update(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	// DELETE - deletar usuário
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
