package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.model.viewModel.AssociadoViewModel;

public class AssociadoMapper{
	
	public static AssociadoDto converterByAssociadoDto(Associado associado)
	{
		return new AssociadoDto(associado.getEmail(), associado.getEmail());
	}
	
	public static Associado converterByAssociado(AssociadoDto associadoDto)
	{
		return new Associado(associadoDto.getNome(), associadoDto.getEmail());
	}
	
	public static AssociadoViewModel converterByAssociadoViewModel(Associado associado)
	{
		return new AssociadoViewModel(associado.getId(), associado.getNome(), associado.getEmail());
	}
}
