package com.yl.swagger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(SwaggerConfig.class);
	
	@Autowired
	private ApiDocConfigReader apiDocConfigReader;
	
	@Value("${swagger.show}")
	private boolean swaggerShow;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.enable(swaggerShow)//是否显示，swagger功能在生产环境可以不需要使用
					.apiInfo(apiInfo())//指定swagger的基本信息
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.yl.web"))//RequestHandlerSelectors.basePackage("com.didispace.web")
					.paths(PathSelectors.any())//指定swagger暴露的连接
					.build();
	}

	private ApiInfo apiInfo() {
		LOGGER.error("swagger para:{},{},{},{},{}",
				apiDocConfigReader.getName(),apiDocConfigReader.getDesc(),
				apiDocConfigReader.getTos(), apiDocConfigReader.getAuthor(),apiDocConfigReader.getVersion());
		ApiInfo apiInfo = new ApiInfo(
				apiDocConfigReader.getName(),
				apiDocConfigReader.getDesc(), apiDocConfigReader.getVersion(),
				apiDocConfigReader.getTos(), apiDocConfigReader.getAuthor(),
				apiDocConfigReader.getLicense(), apiDocConfigReader.getLicenseURL());
		return apiInfo;
	}
	
}