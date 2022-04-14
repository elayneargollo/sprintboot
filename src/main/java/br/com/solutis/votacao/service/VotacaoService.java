package br.com.solutis.votacao.service;

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

	@Override
	public Votacao Add(Votacao votacao) {
		Voto voto = votoService.Add(votacao.getVoto());
		votacao.setVoto(voto);

		return votacaoRepository.save(votacao);
	}
}
