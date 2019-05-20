package yummy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorRstConfig extends WebMvcConfigurerAdapter {
    @Autowired
    InterceptorRst rstInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rstInterceptor).addPathPatterns("/rstHome","/rstStatistics","/rstInfo","/rstMenu",
                "/rstOrderListPaid","/rstOrderListCompleted","/rstOrderListReturned","/rstOrderDetail/**",
                "/restaurant/**");
    }
}