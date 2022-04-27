package br.com.solutis.votacao.config.mapper;

import java.util.logging.Logger;
import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.model.viewModel.AssociadoViewModel;

public class AssociadoMapper{
	
	static Logger logger = Logger.getLogger(AssociadoMapper.class.getName());
	
	 private AssociadoMapper() {
		 throw new IllegalStateException("Classe de utilidade");
	 }

	public static AssociadoDto converterByAssociadoDto(Associado associado)
	{
		logger.info("Método converterByAssociadoDto converte um Associado para um AssociadoDto");
		return new AssociadoDto(associado.getCpf(), associado.getEmail(), associado.getEmail());
	}
	
	public static Associado converterByAssociado(AssociadoDto associadoDto)
	{
		logger.info("Método converterByAssociado converte um AssociadoDto para um Associado");
		return new Associado(associadoDto.getNome(), associadoDto.getEmail(), associadoDto.getCpf());
	}
	
	public static AssociadoViewModel converterByAssociadoViewModel(Associado associado)
	{
		logger.info("Método converterByAssociadoViewModel converte um Associado para um AssociadoViewModel");
		return new AssociadoViewModel(associado.getId(), associado.getNome(), associado.getEmail(), associado.getCpf());
	}
}
