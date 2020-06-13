package br.com.compasso.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8081/swagger-ui.html


@Configuration
@EnableSwagger2
public class SwaggerConfig {    
	
	@Bean
	public LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));	
	}
	 
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(this.informacoesApi().build());
    }

	private ApiInfoBuilder informacoesApi() {
		
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		 
		apiInfoBuilder.title("Apis Cidades");
		apiInfoBuilder.description("Apis para contemplar o desafio referente a https://github.com/gustavodallanora/spring-boot-interview");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Estudo de caso");
		apiInfoBuilder.license("Licença - Open Source");		
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
	}
	
	private Contact contato() {
		 
		return new Contact(
				"Vitor Cavalcante Hora",
				 null, 
				"vitorhora@hotmail.com");
	}
    
}

