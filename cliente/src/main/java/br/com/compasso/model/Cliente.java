package br.com.compasso.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.compasso.model.dto.Sexo;

@Entity
@Table(name = "cliente")
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Long id;
    
    @Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
	private Sexo sexo;    
    
    @Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;
    
    @Column(name = "idade", nullable = false)
	private Integer idade;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")     
	private Cidade cidadeResidente;
	
	public Cliente() {		
	}
	
	public Cliente(Long id, String nomeCompleto, Sexo sexo, Date dataNascimento, Integer idade, Cidade cidadeResidente) {		
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
}
