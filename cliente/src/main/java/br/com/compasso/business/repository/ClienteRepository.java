package br.com.compasso.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compasso.entidadescorporativas.modelo.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNomeCompleto(String nomeCompleto);	

}
