package br.com.solutis.votacao.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.solutis.votacao.model.dto.VotoDto;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.enumeracao.OpcaoVoto;

public class VotoMock {

	public static List<Voto> ObterVotos()
	{
		List<Voto> votos = new ArrayList<Voto>(); 
		
		votos.add(new Voto(OpcaoVoto.NAO, AssociadoMock.GetAssociado().get().getId(), PautaMock.ObterPauta().get().getId()));
		votos.add(new Voto(OpcaoVoto.SIM, AssociadoMock.GetAssociado().get().getId(), PautaMock.ObterPauta().get().getId()));
		votos.add(new Voto(OpcaoVoto.SIM, AssociadoMock.GetAssociado().get().getId(), PautaMock.ObterPauta().get().getId()));
		
		return votos;
	}
	
	public static VotoDto ObterVotoDto()
	{		
		return new VotoDto(OpcaoVoto.NAO, AssociadoMock.GetAssociado().get().getId(), PautaMock.ObterPauta().get().getId());
	}
}
