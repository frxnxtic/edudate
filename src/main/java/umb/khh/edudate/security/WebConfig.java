//package umb.khh.edudate.security;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import org.springframework.http.HttpHeaders;
//import java.util.Arrays;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig {
//
//    private static final long MAX_AGE_SECS = 3600L;
//
//    private static final int CORS_FILTER_ORDER = -102;
//
//    @Bean
//    public FilterRegistrationBean filterBean() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowCredentials(true);
////        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("http://localhost:3000/login");
//        config.addAllowedHeader(String.valueOf(Arrays.asList(
//                HttpHeaders.AUTHORIZATION,
//                HttpHeaders.CONTENT_TYPE,
//                HttpHeaders.ACCEPT)));
//        config.addAllowedMethod(String.valueOf(Arrays.asList(
//                HttpMethod.GET,
//                HttpMethod.POST,
//                HttpMethod.PUT,
//                HttpMethod.DELETE)));
//        config.setMaxAge(MAX_AGE_SECS);
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//
//        bean.setOrder(CORS_FILTER_ORDER);
//        return bean;
//    }
//}
package umb.khh.edudate.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebConfig {

    private static final long MAX_AGE_SECS = 3600L;

    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean<CorsFilter> filterBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000"); // Frontend URL
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        config.setMaxAge(MAX_AGE_SECS);
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(CORS_FILTER_ORDER);
        return bean;
    }
}


