package br.com.compasso.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.business.repository.CidadeRepository;
import br.com.compasso.model.Cidade;
import br.com.compasso.model.dto.CidadeDTO;
import br.com.compasso.model.dto.Estado;

@Service
public class CidadeBusiness {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public CidadeDTO salvar(CidadeDTO cidadeDTO) {
		
		Cidade cidade = cidadeRepository.save(cidadeDTO.transformarEntity());
		
		CidadeDTO cidadeSalvaDTO = new CidadeDTO();
		cidadeSalvaDTO.setEstado(cidade.getEstado());
		cidadeSalvaDTO.setId(cidade.getId());
		cidadeSalvaDTO.setNome(cidade.getNome());
		
		return cidadeSalvaDTO;
	}
	
	
	public List<CidadeDTO> consultarPorNome(String nome){
		
		List<Cidade> listaCidades = cidadeRepository.findByNome(nome);
		
		List<CidadeDTO> listaCidadesDTO = new ArrayList<>();
		
		for (Cidade cidade : listaCidades) {			
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setEstado(cidade.getEstado());
			cidadeDTO.setId(cidade.getId());
			cidadeDTO.setNome(cidade.getNome());
			
			listaCidadesDTO.add(cidadeDTO);			
		}
		
		return listaCidadesDTO;		
	}
	
	public List<CidadeDTO> consultarPorEstado(Estado uf){
		
		List<Cidade> listaCidades = cidadeRepository.findByEstado(uf);
		
		List<CidadeDTO> listaCidadesDTO = new ArrayList<>();
		
		for (Cidade cidade : listaCidades) {			
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setEstado(cidade.getEstado());
			cidadeDTO.setId(cidade.getId());
			cidadeDTO.setNome(cidade.getNome());
			
			listaCidadesDTO.add(cidadeDTO);			
		}
		
		return listaCidadesDTO;		
	}
	
		
	public List<CidadeDTO> consultarTodos() {		
		
		List<Cidade> listaCidades = cidadeRepository.findAll();
		List<CidadeDTO> listaCidadesDTO = new ArrayList<>();
		
		for (Cidade cidade : listaCidades) {
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setEstado(cidade.getEstado());
			cidadeDTO.setId(cidade.getId());
			cidadeDTO.setNome(cidade.getNome());
			
			listaCidadesDTO.add(cidadeDTO);			
		}
		
		return listaCidadesDTO;		
	}	

}
