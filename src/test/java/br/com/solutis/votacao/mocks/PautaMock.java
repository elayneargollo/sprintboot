package br.com.solutis.votacao.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.enumeracao.Status;

public final class PautaMock {

	public static List<Pauta> ObterPautas()
	{
		List<Pauta> pautas = new ArrayList<Pauta>();
		
		pautas.add(new Pauta(1, Status.ABERTO, SessaoMock.ObterSessao()));
		pautas.add(new Pauta(2, Status.CRIADO, SessaoMock.ObterSessao()));
		pautas.add(new Pauta(3, Status.FECHADO,SessaoMock.ObterSessao()));
		
		return pautas;
	}
	
	public static Optional<Pauta> ObterPauta()
	{		
		return Optional.of(new Pauta(1, Status.ABERTO, SessaoMock.ObterSessao()));
	}
}
