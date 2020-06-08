package br.com.compasso.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.compasso.model.Cidade;
import br.com.compasso.model.Cliente;

public class ClienteDTO {	

	private Long id;
	private String nomeCompleto;
	private Sexo sexo; 
	@JsonFormat(pattern="dd/MM/yyy")
	private Date dataNascimento;
	private Integer idade;    
	private Cidade cidadeResidente;
	
	public ClienteDTO() {		
	}
	
	public ClienteDTO(Long id, String nomeCompleto, Sexo sexo, Date dataNascimento, Integer idade, Cidade cidadeResidente) {		
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidadeResidente = cidadeResidente;		
	}		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}	
		
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
		
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
		
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
		
	public Cidade getCidadeResidente() {
		return cidadeResidente;
	}
	
	public void setCidadeResidente(Cidade cidadeResidente) {		
		this.cidadeResidente = cidadeResidente;
	}		
	
	public Cliente transformarEntity(){
	    return new Cliente(id, nomeCompleto, sexo, dataNascimento, idade, cidadeResidente);
	}
	
}