package br.com.solutis.votacao.mocks;

import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.entity.Voto;

public class VotacaoMock {
	
	public static Votacao ObterVotacao() 
	{		
		return new Votacao(1, new Voto());
	}
}
