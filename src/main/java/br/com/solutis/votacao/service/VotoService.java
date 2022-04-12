package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.solutis.votacao.exception.AssociadoNaoExiste;
import br.com.solutis.votacao.exception.PautaNaoAbertaException;
import br.com.solutis.votacao.exception.VotoNaoUnicoExcepiton;
import br.com.solutis.votacao.model.Pauta;
import br.com.solutis.votacao.model.Voto;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import br.com.solutis.votacao.service.interfaces.IVotoService;

@Service
public class VotoService implements IVotoService{

	@Autowired
	IVotoRepository votoRepository;
	@Autowired
	IAssociadoRepository associadoRepository;
	@Autowired
	IPautaRepository pautaRepository;
	
	@Override
	public Voto Add(Voto voto){

		boolean associadoEncontrado = associadoRepository.existsById(voto.getAssociadoId());
		Pauta pauta = pautaRepository.getById(voto.getPautaId());
		
		if(!associadoEncontrado) 
			throw new AssociadoNaoExiste("Associado não encontrado");
		
		if(pauta.getStatus() != (Status.ABERTO))
			throw new PautaNaoAbertaException("Para votar é necessário que a pauta esteja aberta !");
		
		if(!GetJaVotou(voto.getAssociadoId(), pauta.getId()))
			throw new VotoNaoUnicoExcepiton("Votos devem ser únicos por pauta.");
		
		return votoRepository.save(voto);
	}
	
	private Boolean GetJaVotou(Integer associadoId, Integer pautaId)
	{
		Optional<Voto> votoAssociado = votoRepository.findAll().stream().filter(x -> x.getAssociadoId() == associadoId && x.getPautaId() == pautaId).findFirst();
		return votoAssociado.isEmpty();
	}
	
	@Override
	public Optional<Voto> GetById(Integer id) {
		return votoRepository.findById(id);
	}

	@Override
	public Page<Voto> GetAll(Pageable paginacao) {
		return votoRepository.findAll(paginacao);
	}

	@Override
	public List<Voto> GetAll() {
		return votoRepository.findAll();
	}
}
