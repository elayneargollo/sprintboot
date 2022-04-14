package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.VotacaoDto;
import br.com.solutis.votacao.model.entity.Votacao;
import br.com.solutis.votacao.model.viewModel.VotacaoViewModel;

public class VotacaoMapper {
	
	public static Votacao ConverteParaVotacao(VotacaoDto votacaoDto)
	{
		return new Votacao(VotoMapper.ConverteParaVoto(votacaoDto.getVoto()));
	}
	
	public static VotacaoViewModel converterByVotacaoViewModel(Votacao votacao)
	{
		return new VotacaoViewModel(votacao.getId(), VotoMapper.converterByVotoViewModel(votacao.getVoto()), votacao.getDataVotacao());
	}
}
