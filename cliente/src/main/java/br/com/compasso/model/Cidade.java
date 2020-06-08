package br.com.compasso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.compasso.model.dto.Estado;

@Entity
@Table(name = "cidade")
public class Cidade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @Column(name = "nome", nullable = false)
	private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
	private Estado estado;   
    
    public Cidade() {
    	
    }
    
	public Cidade(String nome, Estado estado) {	
		this.nome = nome;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
