package br.com.compasso.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.entidadescorporativas.dto.Estado;
import br.com.compasso.entidadescorporativas.modelo.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	List<Cidade> findByNome(String nome);
	
	List<Cidade> findByEstado(Estado uf);
}
