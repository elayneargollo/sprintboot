package br.com.solutis.votacao.config.mapper;

import br.com.solutis.votacao.model.dto.AssociadoDto;
import br.com.solutis.votacao.model.dto.PautaDto;
import br.com.solutis.votacao.model.dto.SessaoDto;
import br.com.solutis.votacao.model.dto.VotoDto;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.entity.Sessao;
import br.com.solutis.votacao.model.entity.Voto;

public class Mapper {

	public static VotoDto ConverteParaVotoDto(Voto voto)
	{
		return new VotoDto(voto.getDescricao(), voto.getAssociadoId(), voto.getPautaId());
	}
	
	public static Voto ConverteParaVoto(VotoDto votoDto)
	{
		return new Voto(votoDto.getDescricao(), votoDto.getAssociadoId(), votoDto.getPautaId());
	}
	
	public static AssociadoDto converterByAssociadoDto(Associado associado)
	{
		return new AssociadoDto(associado.getEmail(), associado.getEmail());
	}
	
	public static Associado converterByAssociado(AssociadoDto associadoDto)
	{
		return new Associado(associadoDto.getNome(), associadoDto.getEmail());
	}
	
	public static SessaoDto converterBySessaoDto(Sessao sessao)
	{
		return new SessaoDto(sessao.getDescricao(), sessao.getTempoDuracao(), sessao.getTipo());
	}
	
	public static Sessao converterBySessao(SessaoDto sessaoDto)
	{
		return new Sessao(sessaoDto.getDescricao(), sessaoDto.getTempoDuracao(), sessaoDto.getTipo());
	}
	
	public static PautaDto converterByPautaDto(Pauta pauta)
	{
		return new PautaDto(pauta.getStatus(), converterBySessaoDto(pauta.getSessao()));
	}
	
	public static Pauta converterByPauta(PautaDto pautaDto)
	{	
		return new Pauta(pautaDto.getStatus(), converterBySessao(pautaDto.getSessao()));
	}	
}
