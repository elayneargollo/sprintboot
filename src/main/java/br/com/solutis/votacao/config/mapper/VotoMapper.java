package br.com.solutis.votacao.config.mapper;

import java.util.logging.Logger;

import br.com.solutis.votacao.model.dto.VotoDto;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.viewModel.VotoViewModel;

public class VotoMapper {
	
	static Logger logger = Logger.getLogger(VotoMapper.class.getName());
	
	public static VotoDto ConverteParaVotoDto(Voto voto)
	{
		logger.info("Método ConverteParaVotoDto converte uma Voto para uma VotoDto");
		return new VotoDto(voto.getDescricao(), voto.getAssociadoId(), voto.getPautaId());
	}
	
	public static Voto ConverteParaVoto(VotoDto votoDto)
	{
		logger.info("Método ConverteParaVoto converte uma VotoDto para uma Voto");
		return new Voto(votoDto.getDescricao(), votoDto.getAssociadoId(), votoDto.getPautaId());
	}
	
	public static VotoViewModel converterByVotoViewModel(Voto voto)
	{
		logger.info("Método converterByVotoViewModel converte uma  voto para uma VotoViewModel");
		return new VotoViewModel(voto.getAssociadoId() ,voto.getDescricao(), voto.getAssociadoId(), voto.getPautaId());
	}
}
