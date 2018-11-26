//package com.howtodoinjava.app.controller;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//import org.springframework.web.servlet.view.UrlBasedViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan
//public class MvcConfiguration extends WebMvcConfigurerAdapter
//{
//    @Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//		resolver.setPrefix("/WEB-INF/view/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		registry.viewResolver(resolver);
//	}
//}
