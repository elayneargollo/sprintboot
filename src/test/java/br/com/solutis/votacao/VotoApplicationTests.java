package br.com.solutis.votacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.controller.VotoController;
import br.com.solutis.votacao.mocks.VotoMock;
import br.com.solutis.votacao.model.entity.Voto;
import br.com.solutis.votacao.service.interfaces.IVotoService;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

@WebMvcTest(controllers = VotoController.class)
class VotoControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IVotoService votoService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String BASE_URL = "/api/voto/";
	
	@Test
	void GetAllv11() throws Exception {

		List<Voto> votosMock = VotoMock.ObterVotos();

		when(votoService.getAll()).thenReturn(votosMock);

		mock.perform(get(BASE_URL + "v1.1/").contentType("application/json")
				.content(objectMapper.writeValueAsString(votosMock)));

		List<Voto> votosReturn = votoService.getAll();

		assertNotNull(votosReturn);
		assertThat(votosReturn).isEqualTo(votosMock);
		assertThat(votosReturn.size()).hasSameClassAs(votosMock.size());
	}
}
