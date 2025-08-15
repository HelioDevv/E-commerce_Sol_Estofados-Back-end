package com.fabestofados.api_sistema.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabestofados.api_sistema.dtos.UsuarioDTO;
import com.fabestofados.api_sistema.entities.Usuario;
import com.fabestofados.api_sistema.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	//Listar todos os usuários
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
		return repository.findAll()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	//Buscar usuário por ID
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID" + id));
		return toDTO(usuario);
	}
	
	//Inserir novo usuário
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.nome());
		usuario.setEmail(dto.email());
		Usuario saved = repository.save(usuario);
		return toDTO(saved);
	}
	
	//Atualizar usuário existente
	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID" + id));
		usuario.setNome(dto.nome());
		usuario.setEmail(dto.email());
		Usuario updated = repository.save(usuario);
		return toDTO(updated);
	}
	
	//Deletar usuário 
	
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new RuntimeException("Usuário não encotrado com ID:" + id);
		}
		repository.deleteById(id);
	}
	
	
	// Método auxiliar para converter entidade em DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getUsuario_id(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
