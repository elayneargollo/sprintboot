package br.com.solutis.votacao.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api11() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("votacao-api-1.1")
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("br.com.solutis.votacao.controller"))
	        .paths(PathSelectors.ant("/api/**/v1.1/**"))
	        .build()
	        .apiInfo(metaInfo("v1.1"));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.solutis.votacao.controller"))
				.paths(PathSelectors.ant("/api/**/v1.0/**"))
				.build()
				.apiInfo(metaInfo("v1.0"));
	}
	
	private ApiInfo metaInfo(String versao) {
		String descricao = "Votação API REST ";
		
        return new ApiInfoBuilder()
        			.version(versao)
        			.title("API REST Sessão de Votação")
        			.description((descricao.concat(versao)))
        			.contact(new Contact("Repositório GitHub", "https://github.com/elayneargollo/votacao_solutis", null))
        			.build();
    }
}
