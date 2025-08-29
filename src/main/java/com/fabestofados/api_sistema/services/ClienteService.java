package com.fabestofados.api_sistema.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabestofados.api_sistema.dtos.ClienteDTO;
import com.fabestofados.api_sistema.dtos.EnderecoDTO;
import com.fabestofados.api_sistema.entities.Cliente;
import com.fabestofados.api_sistema.entities.Endereco;
import com.fabestofados.api_sistema.entities.Usuario;
import com.fabestofados.api_sistema.exceptions.ResourceNotFoundException;
import com.fabestofados.api_sistema.repositories.ClienteRepository;
import com.fabestofados.api_sistema.repositories.UsuarioRepository;

@Service
public class ClienteService {

	private ClienteRepository repository;
	private UsuarioRepository usuarioRepository;
	
	public ClienteService(ClienteRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}
	
	// Listar todos os clientes
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar cliente por ID
    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
        return toDTO(cliente);
    }

    // Inserir novo cliente
    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setTelefone(dto.telefone());

        // Buscar o usuário pelo ID que veio no DTO
        Usuario usuario = usuarioRepository.findById(dto.usuario_id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.usuario_id()));
        cliente.setUsuario(usuario);

        Cliente saved = repository.save(cliente);
        return toDTO(saved);
    }

    // Atualizar cliente existente
    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));

        cliente.setTelefone(dto.telefone());

        Usuario usuario = usuarioRepository.findById(dto.usuario_id())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.usuario_id()));
        cliente.setUsuario(usuario);

        Cliente updated = repository.save(cliente);
        return toDTO(updated);
    }

    // Deletar cliente
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

 // converte Cliente -> ClienteDTO (com lista de EnderecoDTO)
    private ClienteDTO toDTO(Cliente cliente) {
        // evita NullPointer se a lista for null
        List<EnderecoDTO> enderecosDto = cliente.getEnderecos() == null
                ? Collections.emptyList()
                : cliente.getEnderecos()
                         .stream()
                         .map(this::toEnderecoDTO) // map por método evita inferência ambígua
                         .collect(Collectors.toList());

        Long usuarioId = cliente.getUsuario() != null ? cliente.getUsuario().getUsuario_id() : null;

        return new ClienteDTO(
                cliente.getCliente_id(),         
                cliente.getTelefone(),
                usuarioId,
                enderecosDto
        );
    }

    // converte Endereco -> EnderecoDTO
    private EnderecoDTO toEnderecoDTO(Endereco e) {
        return new EnderecoDTO(
                e.getEndereco_id(),
                e.getRua(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getPais(),
                e.getTipo()
        );
    }
}
