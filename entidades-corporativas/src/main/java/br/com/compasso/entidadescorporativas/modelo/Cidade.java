package br.com.compasso.entidadescorporativas.modelo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.compasso.entidadescorporativas.dto.Estado;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cidade")
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude="id")
@EqualsAndHashCode
public class Cidade {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;
    
    @Column(name = "nome", nullable = false)
    @Getter @Setter private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    @Getter @Setter private Estado estado;   
    
}
