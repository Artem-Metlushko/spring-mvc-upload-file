package com.metlushko.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"com.metlushko"})
public class SpringWebMvcConfig implements WebMvcConfigurer {

    //@Bean(name = "multipartResolver")
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

/*    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSizePerFile(1000); //bytes
        return commonsMultipartResolver;
    }*/

/*    @Bean
    public StandardServletMultipartResolver multipartResolver() {

        return new StandardServletMultipartResolver();
    }*/


}
