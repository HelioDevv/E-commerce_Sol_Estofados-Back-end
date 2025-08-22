package com.fabestofados.api_sistema.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long endereco_id;
	
	@Column(nullable = false, length = 100)
	private String rua;
	
	@Column(nullable = false, length = 100)
	private String bairro;
	
	@Column(nullable = false, length = 100)
	private String cidade;
	
	@Column(nullable = false, length = 50)
	private String estado;
	
	@Column(nullable = false, length = 50)
	private String pais;
	
	@Column(nullable = false, length = 20)
	private String tipo;
	
	//Relacionamento *:1
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	public Endereco() {}

	public Endereco(
			Long endereco_id, String rua, String bairro, String cidade, String estado, String pais, String tipo,
			Cliente cliente) {
		super();
		this.endereco_id = endereco_id;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	public Long getEndereco_id() {return endereco_id;}
	public void setEndereco_id(Long endereco_id) {this.endereco_id = endereco_id;}

	
	public String getRua() {return rua;}
	public void setRua(String rua) {this.rua = rua;}

	
	public String getBairro() {return bairro;}
	public void setBairro(String bairro) {this.bairro = bairro;}

	
	public String getCidade() {return cidade;}
	public void setCidade(String cidade) {this.cidade = cidade;}

	
	public String getEstado() {return estado;}
	public void setEstado(String estado) {this.estado = estado;}

	
	public String getPais() {return pais;}
	public void setPais(String pais) {this.pais = pais;}

	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}

	
	public Cliente getCliente() {return cliente;}
	public void setCliente(Cliente cliente) {this.cliente = cliente;}

	
	public static long getSerialversionuid() {return serialVersionUID;}

	@Override
	public int hashCode() {return Objects.hash(endereco_id);}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		Endereco other = (Endereco) obj;return Objects.equals(endereco_id, other.endereco_id);
	}	
}
