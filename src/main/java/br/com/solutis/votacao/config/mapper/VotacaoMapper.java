package br.com.solutis.votacao.config.mapper;

import java.util.logging.Logger;

import br.com.solutis.votacao.model.dto.VotacaoDto;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.viewModel.VotacaoViewModel;

public class VotacaoMapper {
	
	static Logger logger = Logger.getLogger(VotacaoMapper.class.getName());
	
	public static Votacao ConverteParaVotacao(VotacaoDto votacaoDto)
	{
		logger.info("Método ConverteParaVotacao converte uma VotacaoDto para uma Votacao");
		return new Votacao(VotoMapper.ConverteParaVoto(votacaoDto.getVoto()));
	}
	
	public static VotacaoViewModel converterByVotacaoViewModel(Votacao votacao)
	{
		logger.info("Método converterByVotacaoViewModel converte uma Votacao para uma VotacaoViewModel");
		return new VotacaoViewModel(votacao.getId(), VotoMapper.converterByVotoViewModel(votacao.getVoto()), votacao.getDataVotacao());
	}
}
