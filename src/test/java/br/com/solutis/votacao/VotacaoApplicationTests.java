package br.com.solutis.votacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.solutis.votacao.controller.AssociadoController;
import br.com.solutis.votacao.model.Associado;
import br.com.solutis.votacao.service.interfaces.IAssociadoService;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(controllers = AssociadoController.class)
class AssociadorControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private IAssociadoService associadoService;
	
	@Autowired
    private ObjectMapper objectMapper;

	@Test
	public void GetById() throws Exception {
			
		Associado associadoMock = AssociadoMock.GetAssociado().get();
		
		when(associadoService.GetById(associadoMock.getId())).thenReturn(AssociadoMock.GetAssociado());
		
		mock.perform(get("/api/associado/1")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(associadoMock)));
		
		Optional<Associado> associadoReturn = associadoService.GetById(associadoMock.getId());
		
		assertNotNull(associadoReturn);
	    assertThat(associadoReturn.get().getNome()).isEqualTo(associadoMock.getNome());
	    assertThat(associadoReturn.get().getEmail()).isEqualTo(associadoMock.getEmail());
	    assertThat(associadoReturn.get().getId()).isEqualTo(associadoMock.getId());
	}
	
	@Test
	public void GetAll() throws Exception {
		
		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoService.GetAll()).thenReturn(associadosMock);
		
		mock.perform(get("/api/associado/")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(associadosMock)));
		
		List<Associado> associadosReturn = associadoService.GetAll();
		
		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).isEqualTo(associadosMock.size());
	}
}
