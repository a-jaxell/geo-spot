package com.laboration2.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    //This is for DEBUGGING only
    /*
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setMaxPayloadLength(10000);
        loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
        return loggingFilter;
    }
    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> loggingFilter() {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        OncePerRequestFilter filter = new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // The stream here causes the Request Body to become consumed. Ie a stream can only be read once.
            //  .readAllBytes() consumes all data and leaves nothing for Springs dispatcher servlet.
                String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println("Direct request body logging: " + body);
                filterChain.doFilter(request, response);
            }
        };
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
    */
}