package com.fabestofados.api_sistema.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usuario_id;
	
	private String nome;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	public Usuario() {}

	public Usuario(Long usuario_id, String nome, String email) {
		super();
		this.usuario_id = usuario_id;
		this.nome = nome;
		this.email = email;
	}

	public Long getUsuario_id() {return usuario_id;}
	public void setUsuario_id(Long usuario_id) {this.usuario_id = usuario_id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public static long getSerialversionuid() {return serialVersionUID;}

	@Override
	public int hashCode() {return Objects.hash(usuario_id);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;Usuario other = (Usuario) obj;
		return Objects.equals(usuario_id, other.usuario_id);
	}
}
