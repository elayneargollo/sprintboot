package br.com.solutis.votacao.mocks;

import br.com.solutis.votacao.model.dto.VotacaoDto;
import br.com.solutis.votacao.model.entity.Votacao;

public class VotacaoMock {
		
	public static VotacaoDto ObterVotacaoDto() 
	{		
		return new VotacaoDto(VotoMock.ObterVotoDto());
	}
	
	public static Votacao ObterVotacao() 
	{		
		return new Votacao(1, VotoMock.ObterVotos().get(0));
	}
}
