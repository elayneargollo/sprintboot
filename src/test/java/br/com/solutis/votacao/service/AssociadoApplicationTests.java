package br.com.solutis.votacao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.solutis.votacao.mocks.AssociadoMock;
import br.com.solutis.votacao.model.entity.Associado;
import br.com.solutis.votacao.repository.IAssociadoRepository;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = AssociadoService.class)
class AssociadoServiceApplicationTests {
	
	@MockBean
	IAssociadoRepository associadoRepository;
	
	@Autowired
	private AssociadoService associadoService;


	@Test
	void GetById() throws Exception {

		Associado associadoMock = AssociadoMock.GetAssociado().get();

		when(associadoRepository.findById(associadoMock.getId())).thenReturn(AssociadoMock.GetAssociado());

		Optional<Associado> associadoReturn = associadoService.getById(associadoMock.getId());

		assertNotNull(associadoReturn.toString());
		assertThat(associadoReturn.get().getNome()).isEqualTo(associadoMock.getNome());
		assertThat(associadoReturn.get().getEmail()).isEqualTo(associadoMock.getEmail());
		assertThat(associadoReturn.get().getId()).isEqualTo(associadoMock.getId());
	}

	@Test
	void GetAll() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoRepository.findAll()).thenReturn(associadosMock);

		List<Associado> associadosReturn = associadoService.getAll();

		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).hasSameClassAs(associadosMock.size());
	}

	@Test
	void GetAllv11() throws Exception {

		List<Associado> associadosMock = AssociadoMock.GetAssociados();

		when(associadoRepository.findAll()).thenReturn(associadosMock);

		List<Associado> associadosReturn = associadoService.getAll();

		assertNotNull(associadosReturn);
		assertThat(associadosReturn).isEqualTo(associadosMock);
		assertThat(associadosReturn.size()).hasSameClassAs(associadosMock.size());
	}

}
