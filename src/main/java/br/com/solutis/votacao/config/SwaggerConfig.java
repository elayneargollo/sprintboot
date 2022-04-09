package br.com.solutis.votacao.config;

import java.util.ArrayList;
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
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.solutis.votacao"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
        		"Votação API REST",
        		"API REST sessão de votação",
                "1.0",
                "Terms of Service",
                new Contact("Elayne Natália", "https://www.linkedin.com/in/elayne-nat%C3%A1lia/","elayne.natalia@solutis.com.br"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
