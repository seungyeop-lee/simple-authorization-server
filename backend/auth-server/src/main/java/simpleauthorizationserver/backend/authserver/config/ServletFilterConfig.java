package simpleauthorizationserver.backend.authserver.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simpleauthorizationserver.backend.authserver.config.servlet.CachedBodyHttpServletRequestFilter;

@Configuration
public class ServletFilterConfig {

    @Bean
    public FilterRegistrationBean<CachedBodyHttpServletRequestFilter> contentCachingWrapFilter() {
        FilterRegistrationBean<CachedBodyHttpServletRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CachedBodyHttpServletRequestFilter());
        registrationBean.setOrder(Integer.MIN_VALUE);
        return registrationBean;
    }
}
