package br.com.compasso.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.business.repository.CidadeRepository;
import br.com.compasso.entidadescorporativas.dto.CidadeDTO;
import br.com.compasso.entidadescorporativas.dto.Estado;
import br.com.compasso.entidadescorporativas.modelo.Cidade;

@Service
public class CidadeBusiness {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public CidadeDTO salvar(CidadeDTO cidadeDTO) {			
		Cidade cidade = cidadeRepository.save(new Cidade(cidadeDTO.getId(), cidadeDTO.getNome(), cidadeDTO.getEstado()));	
		
		return transformarDTO(cidade);
	}	
	
	public List<CidadeDTO> consultarPorNome(String nome){		
		return criarListaCidades(cidadeRepository.findByNome(nome));	
	}
	
	public List<CidadeDTO> consultarPorEstado(Estado uf){				
		return criarListaCidades(cidadeRepository.findByEstado(uf));	
	}	
		
	public List<CidadeDTO> consultarTodos() {			
		return criarListaCidades(cidadeRepository.findAll());		
	}	

	private List<CidadeDTO> criarListaCidades(List<Cidade> listaCidades) {
		List<CidadeDTO> listaCidadesDTO = new ArrayList<>();
		
		for (Cidade cidade : listaCidades) {
			CidadeDTO cidadeDTO = transformarDTO(cidade);			
			listaCidadesDTO.add(cidadeDTO);			
		}
		
		return listaCidadesDTO;
	}

	private CidadeDTO transformarDTO(Cidade cidade) {
		CidadeDTO cidadeDTO = new CidadeDTO();
		cidadeDTO.setEstado(cidade.getEstado());
		cidadeDTO.setId(cidade.getId());
		cidadeDTO.setNome(cidade.getNome());
		return cidadeDTO;
	}	
}
