package br.com.oak.aluraflix.api.config;

import br.com.oak.aluraflix.api.model.contract.response.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

  @Bean
  public Docket apiDocket() {

    TypeResolver typeResolver = new TypeResolver();

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
        .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
        .ignoredParameterTypes(UriComponentsBuilder.class)
        .ignoredParameterTypes(HttpServletRequest.class)
        .additionalModels(typeResolver.resolve(ErrorResponse.class))
        .apiInfo(apiInfo())
        .tags(
            new Tag("Consultas", "Agrupa as consultas da API"),
            new Tag("Video", "Gerencia os vídeos"),
            new Tag("Categoria", "Gerencia as categorias"));
  }

  private List<ResponseMessage> globalPostPutResponseMessages() {
    return Arrays.asList(
        new ResponseMessageBuilder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message("Requisição inválida (erro do cliente)")
            .responseModel(new ModelRef("ErrorResponse"))
            .build(),
        new ResponseMessageBuilder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Erro interno no servidor")
            .responseModel(new ModelRef("ErrorResponse"))
            .build(),
        new ResponseMessageBuilder()
            .code(HttpStatus.NOT_ACCEPTABLE.value())
            .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
            .build(),
        new ResponseMessageBuilder()
            .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
            .message("Requisição recusada porque o corpo está em um formato não suportado")
            .responseModel(new ModelRef("ErrorResponse"))
            .build());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Vídeos API")
        .description("API para controle de cadastro de vídeos")
        .version("1")
        .build();
  }
}
