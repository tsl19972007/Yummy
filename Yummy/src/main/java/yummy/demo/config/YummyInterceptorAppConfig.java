package yummy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class YummyInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    YummyInterceptor yummyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(yummyInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/","/test","/test2","/static/**","/cstLogin","/rstLogin", "/mngLogin",
                        "/cstRegister","/rstRegister","/cstRegister/**","/rstRegister/**","/loginAndRegister/**");
    }
}
