package com.example.demo.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// For ObjectMapper
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.TimeZone;
//import org.springframework.context.annotation.Bean;

@ComponentScan(basePackages = {"springBootInitialDemo"})
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }
    
    /*
     * recordatorio: mirar object mapper 
     * de objeto con mas atributos a json con menos atributos
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        return mapper;
    }*/
}