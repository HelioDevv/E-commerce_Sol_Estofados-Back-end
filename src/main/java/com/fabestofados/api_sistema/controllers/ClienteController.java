package com.fabestofados.api_sistema.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabestofados.api_sistema.dtos.ClienteDTO;
import com.fabestofados.api_sistema.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	//Listar todos os clientes
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	//Buscar cliente por ID
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
		ClienteDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	//Inserir novo cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO dto){
		ClienteDTO created = service.insert(dto);
		return ResponseEntity.ok(created);
	}
	
	//Atualizar cliente existente
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id,@RequestBody ClienteDTO dto) {
		ClienteDTO updated = service.update(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	//Deletar cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
