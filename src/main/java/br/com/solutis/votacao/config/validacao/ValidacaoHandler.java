package br.com.solutis.votacao.config.validacao;

import java.util.ArrayList;
import java.util.List;
import javax.el.MethodNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.solutis.votacao.exception.AssociadoNaoExiste;

@RestControllerAdvice
public class ValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<VotacaoException> handle(MethodArgumentNotValidException exception) {

		List<VotacaoException> erros = new ArrayList<VotacaoException>();
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		
		fieldErros.stream().forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			VotacaoException erro = new VotacaoException(e.getField(),mensagem);
			erros.add(erro);
		});
		
		return erros;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(MethodNotFoundException.class)
	public AssociadoNaoExiste handle(MethodNotFoundException exception) {
		return new AssociadoNaoExiste(exception.getMessage());
	}
}
