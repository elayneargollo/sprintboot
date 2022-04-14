package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.VotoDto;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.viewModel.VotoViewModel;

public class VotoMapper {
	
	public static VotoDto ConverteParaVotoDto(Voto voto)
	{
		return new VotoDto(voto.getDescricao(), voto.getAssociadoId(), voto.getPautaId());
	}
	
	public static Voto ConverteParaVoto(VotoDto votoDto)
	{
		return new Voto(votoDto.getDescricao(), votoDto.getAssociadoId(), votoDto.getPautaId());
	}
	
	public static VotoViewModel converterByVotoViewModel(Voto voto)
	{
		return new VotoViewModel(voto.getAssociadoId() ,voto.getDescricao(), voto.getAssociadoId(), voto.getPautaId());
	}
}
