package com.margotekstil.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer{

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new Java8TimeDialect());
      //  templateEngine.addDialect(new SiuvsDialect());
        return templateEngine;
    }

}
