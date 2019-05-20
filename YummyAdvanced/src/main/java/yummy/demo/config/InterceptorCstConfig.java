package yummy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorCstConfig extends WebMvcConfigurerAdapter {
    @Autowired
    InterceptorCst cstInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cstInterceptor).addPathPatterns("/cstHome","/cstStatistics","/cstOrder","/cstOrder/**",
                "/cstOrderList","/cstOrderListUnpaid","/cstOrderListPaid","/cstOrderListCompleted","/cstOrderListReturned",
                "/cstOrderDetail/**","/cstInfo","/customer/**");
    }
}
