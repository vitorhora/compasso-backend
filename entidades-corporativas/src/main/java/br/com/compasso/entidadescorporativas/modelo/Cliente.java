package br.com.compasso.entidadescorporativas.modelo;

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

import br.com.compasso.entidadescorporativas.dto.Sexo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude="id")
@EqualsAndHashCode
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter private Long id;
    
    @Column(name = "nome_completo", nullable = false)
    @Getter @Setter private String nomeCompleto;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    @Getter @Setter private Sexo sexo;    
    
    @Column(name = "data_nascimento", nullable = false)
    @Getter @Setter private Date dataNascimento;
    
    @Column(name = "idade", nullable = false)
    @Getter @Setter private Integer idade;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")     
    @Getter @Setter private Cidade cidadeResidente;
	
}
