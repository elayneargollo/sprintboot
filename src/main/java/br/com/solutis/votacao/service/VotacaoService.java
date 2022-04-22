package br.com.solutis.votacao.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.repository.IVotacaoRepository;
import br.com.solutis.votacao.service.interfaces.IVotacaoService;
import br.com.solutis.votacao.service.interfaces.IVotoService;

@Service
public class VotacaoService implements IVotacaoService {

	@Autowired
	IVotacaoRepository votacaoRepository;
	@Autowired
	IVotoService votoService;
	Logger logger = Logger.getLogger(VotacaoService.class.getName());

	@Override
	public Votacao add(Votacao votacao) {
		logger.info("Método Add");
		
		Voto voto = votoService.add(votacao.getVoto());
		votacao.setVoto(voto);

		return votacaoRepository.save(votacao);
	}
}
