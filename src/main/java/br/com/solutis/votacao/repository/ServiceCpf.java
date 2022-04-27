package br.com.solutis.votacao.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.solutis.votacao.model.dto.CpfDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceCpf {

	@Resource
	private WebClient webClient;
	Logger logger = Logger.getLogger(ServiceCpf.class.getName());

	public CpfDto validarCpf(final String cpf) {
	
		try
		{
			Mono<CpfDto> monoCpf = webClient.get().uri("/cpf/{cpf}", cpf).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.retrieve().bodyToMono(CpfDto.class);
			
			return monoCpf.block();
		}catch (Exception e) {
			logger.log(Level.INFO, "Ocorreu um erro durante a validação do cpf:: {0} ", e.getMessage());
			return validar(cpf);
		}
	}
	
	private CpfDto validar(String cpf)
	{
		CPFValidator cpfValidador = new CPFValidator(); 
		List<ValidationMessage> erros = cpfValidador.invalidMessagesFor(cpf); 
		
		if(erros.isEmpty())
			return new CpfDto(cpf, true);
	
		return new CpfDto(cpf, false);
	}
}
