package br.com.compasso.model.dto;

import br.com.compasso.model.Cidade;
import br.com.compasso.model.Cliente;

public class CidadeDTO {	
    
	private Long id;   
	private String nome;    
	private Estado estado;   
    
    public CidadeDTO() {    	
    }
    
	public CidadeDTO(Long id, String nome, Estado estado) {	
		this.id = id;
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
	
	public Cidade transformarEntity(){
	    return new Cidade(nome, estado);
	}
	
}
