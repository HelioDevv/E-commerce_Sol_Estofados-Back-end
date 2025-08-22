package com.fabestofados.api_sistema.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cliente_id;
	
	private String telefone;
	
	//Relacionamento 1:1 com Usuario
	@OneToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
	private Usuario usuario;
	
	public Cliente() {}

	public Cliente(Long cliente_id, String telefone, Usuario usuario) {
		super();
		this.cliente_id = cliente_id;
		this.telefone = telefone;
		this.usuario = usuario;
	}

	public Long getCliente_id() {return cliente_id;}
	public void setCliente_id(Long cliente_id) {this.cliente_id = cliente_id;}

	
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}

	
	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}

	
	public static long getSerialversionuid() {return serialVersionUID;}

	@Override
	public int hashCode() {return Objects.hash(cliente_id);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Cliente other = (Cliente) obj;return Objects.equals(cliente_id, other.cliente_id);
	}
	
}
