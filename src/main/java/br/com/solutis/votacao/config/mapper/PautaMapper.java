package br.com.solutis.votacao.config.mapper;

import java.util.logging.Logger;

import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.viewModel.PautaViewModel;

public class PautaMapper {
	
	static Logger logger = Logger.getLogger(PautaMapper.class.getName());
	
	 private PautaMapper() {
		 throw new IllegalStateException("Classe de utilidade");
	 }
	 
	public static PautaDto converterByPautaDto(Pauta pauta)
	{
		logger.info("Método converterByPautaDto converte um Pauta para uma PautaDto");
		return new PautaDto(pauta.getStatus(), SessaoMapper.converterBySessaoDto(pauta.getSessao()));
	}
	
	public static Pauta converterByPauta(PautaDto pautaDto)
	{	
		logger.info("Método converterByPauta converte um PautaDto para uma PautaDtoPauta");
		return new Pauta(pautaDto.getStatus(), SessaoMapper.converterBySessao(pautaDto.getSessao()));
	}	
	
	public static PautaViewModel converterByPautaViewModel(Pauta pauta)
	{	
		logger.info("Método converterByPauta converte um Pauta para uma PautaViewModel");
		return new PautaViewModel(pauta.getId(), pauta.getStatus(), SessaoMapper.converterBySessaoViewModel(pauta.getSessao()));
	}	
}
