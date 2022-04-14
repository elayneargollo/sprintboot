package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.viewModel.PautaViewModel;

public class PautaMapper {
	
	public static PautaDto converterByPautaDto(Pauta pauta)
	{
		return new PautaDto(pauta.getStatus(), SessaoMapper.converterBySessaoDto(pauta.getSessao()));
	}
	
	public static Pauta converterByPauta(PautaDto pautaDto)
	{	
		return new Pauta(pautaDto.getStatus(), SessaoMapper.converterBySessao(pautaDto.getSessao()));
	}	
	
	public static PautaViewModel converterByPautaViewModel(Pauta pauta)
	{	
		return new PautaViewModel(pauta.getId(), pauta.getStatus(), SessaoMapper.converterBySessaoViewModel(pauta.getSessao()));
	}	
}
