package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.viewModel.SessaoViewModel;

public class SessaoMapper {
	
	public static SessaoDto converterBySessaoDto(Sessao sessao)
	{
		return new SessaoDto(sessao.getDescricao(), sessao.getTempoDuracao(), sessao.getTipo());
	}
	
	public static Sessao converterBySessao(SessaoDto sessaoDto)
	{
		return new Sessao(sessaoDto.getDescricao(), sessaoDto.getTempoDuracao(), sessaoDto.getTipo());
	}
	
	public static SessaoViewModel converterBySessaoViewModel(Sessao sessao)
	{
		return new SessaoViewModel(sessao.getId(), sessao.getDescricao(), sessao.getTempoDuracao(), sessao.getTipo());
	}
}
