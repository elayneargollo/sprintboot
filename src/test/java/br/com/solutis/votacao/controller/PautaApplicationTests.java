package br.com.solutis.votacao.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.mocks.PautaMock;
import br.com.solutis.votacao.model.entity.Pauta;
import br.com.solutis.votacao.repository.ISessaoRepository;
import br.com.solutis.votacao.service.interfaces.IPautaService;

@WebMvcTest(controllers = PautaController.class)
class PautaControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IPautaService pautaService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ISessaoRepository sessaoRepository;

	private final String BASE_URL = "/api/pauta/";

	@Test
	void GetById() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaService.getById(pautaMock.get().getId())).thenReturn(pautaMock);

		mock.perform(get(BASE_URL + "v1.0/" + "1").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautaMock)));

		Optional<Pauta> pautaReturn = pautaService.getById(pautaMock.get().getId());

		assertNotNull(pautaReturn.toString());
	}

	@Test
	void GetAll() throws Exception {

		List<Pauta> pautasMock = PautaMock.ObterPautas();

		when(pautaService.getAll()).thenReturn(pautasMock);

		mock.perform(get(BASE_URL + "v1.0/").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautasMock)));

		List<Pauta> pautasReturn = pautaService.getAll();

		assertNotNull(pautasReturn);
		assertThat(pautasReturn).isEqualTo(pautasMock);
		assertThat(pautasReturn.size()).hasSameClassAs(pautasMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Pauta> pautasMock = PautaMock.ObterPautas();

		when(pautaService.getAll()).thenReturn(pautasMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(pautasMock)));

		List<Pauta> pautasReturn = pautaService.getAll();

		assertNotNull(pautasReturn);
		assertThat(pautasReturn).isEqualTo(pautasMock);
		assertThat(pautasReturn.size()).hasSameClassAs(pautasMock.size());
	}

	@Test
	void IniciarPauta() throws Exception {

		Optional<Pauta> pautaMock = PautaMock.ObterPauta();

		when(pautaService.iniciarPauta(pautaMock.get().getId())).thenReturn("Pauta aberta !");

		mock.perform(put(BASE_URL + "/v1.0/").content(objectMapper.writeValueAsString(pautaMock.get()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));

		String pautasReturn = pautaService.iniciarPauta(1);

		assertNotNull(pautasReturn);
	}
}
