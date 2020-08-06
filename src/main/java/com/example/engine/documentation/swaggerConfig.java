package com.example.engine.documentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import static com.google.common.base.Predicates.or;
import java.util.Arrays;
@Configuration
@EnableSwagger2
public class swaggerConfig {

	@Bean
	public Docket postsApi() {
	
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build().pathMapping("").globalOperationParameters( Arrays.asList(new ParameterBuilder()
            .name("header")
            .description("Description of header")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(true)
            .build()));
	}

	private Predicate<String> postPaths() {
		return or(regex("/api.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Engine API")
				.description("RestAPI with spring boot")
				.version("1.0").build();
	}

}