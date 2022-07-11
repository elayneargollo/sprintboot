package br.com.solutis.votacao.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.solutis.votacao.exception.NotFoundException;
import br.com.solutis.votacao.exception.VotoNaoUnicoExcepiton;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.model.enumeracao.Status;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import br.com.solutis.votacao.repository.IPautaRepository;
import br.com.solutis.votacao.repository.IVotoRepository;
import br.com.solutis.votacao.service.interfaces.IVotoService;

@Service
public class VotoService implements IVotoService {

	@Autowired
	IVotoRepository votoRepository;
	@Autowired
	IAssociadoRepository associadoRepository;
	@Autowired
	IPautaRepository pautaRepository;
	Logger logger = Logger.getLogger(VotoService.class.getName());

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Voto add(Voto voto) {
		logger.info("Método Add");

		Optional<Pauta> pauta = pautaRepository.findById(voto.getPautaId());
		
		if (pauta.isEmpty() || !Status.ABERTO.toString().equals(pauta.get().getStatus())) 
			throw new NotFoundException("Pauta não encontrado ou não encontra-se 'ABERTA' ");		

		if (associadoRepository.existsById(voto.getAssociadoId())) 
			throw new NotFoundException("Associado não encontrado");

		getJaVotou(voto.getAssociadoId(), pauta.get().getId());
		
		return votoRepository.save(voto);
	}

	private void getJaVotou(Integer associadoId, Integer pautaId) {
		logger.info("Método GetJaVotou");

		Optional<Voto> votoAssociado = votoRepository.findAll().stream()
				.filter(x -> x.getAssociadoId().equals(associadoId) && x.getPautaId().equals(pautaId)).findFirst();
		
		if (votoAssociado.isEmpty()) 
			throw new VotoNaoUnicoExcepiton("Votos devem ser únicos por pauta.");
		
	}

	@Override
	public Optional<Voto> getById(Integer id) {
		logger.log(Level.INFO, "Método GetById com id:: {0} ", id);
		
		var voto = votoRepository.findById(id);
				
		if(voto.isEmpty())
			throw new NotFoundException("Voto não encontrada");
		
		return voto;
	}

	@Override
	public Page<Voto> getAll(Pageable paginacao) {
		logger.info("Método GetAll com paginacao");
		return votoRepository.findAll(paginacao);
	}

	@Override
	public List<Voto> getAll() {
		return votoRepository.findAll();
	}
}
