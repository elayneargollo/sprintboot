package br.com.solutis.votacao.config.mapper;

import java.util.logging.Logger;

import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.viewModel.SessaoViewModel;

public class SessaoMapper {
	
	static Logger logger = Logger.getLogger(SessaoMapper.class.getName());
	
	public static SessaoDto converterBySessaoDto(Sessao sessao)
	{
		logger.info("Método converterBySessaoDto converte uma Sessao para uma SessaoDto");
		return new SessaoDto(sessao.getDescricao(), sessao.getTempoDuracao(), sessao.getTipo());
	}
	
	public static Sessao converterBySessao(SessaoDto sessaoDto)
	{
		logger.info("Método converterBySessao converte ums SessaoDto para uma Sessao");
		return new Sessao(sessaoDto.getDescricao(), sessaoDto.getTempoDuracao(), sessaoDto.getTipo());
	}
	
	public static SessaoViewModel converterBySessaoViewModel(Sessao sessao)
	{
		logger.info("Método converterBySessaoViewModel converte ums Sessao para uma SessaoViewModel");
		return new SessaoViewModel(sessao.getId(), sessao.getDescricao(), sessao.getTempoDuracao(), sessao.getTipo());
	}
}
