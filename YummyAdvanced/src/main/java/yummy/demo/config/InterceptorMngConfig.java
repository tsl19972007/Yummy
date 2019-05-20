package yummy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorMngConfig extends WebMvcConfigurerAdapter {
    @Autowired
    InterceptorMng mngInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mngInterceptor).addPathPatterns("/mngHome","/mngCstInfo","/mngRstInfo",
                "/mngBalance","/mngApprove","/mngApprove/**","/mngStatistics",
                "/manager/**");
    }
}