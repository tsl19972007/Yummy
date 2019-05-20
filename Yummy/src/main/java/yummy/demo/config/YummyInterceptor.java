package yummy.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class YummyInterceptor implements HandlerInterceptor {
    private final static String SESSION_KEY_CST = "cstId";
    private final static String SESSION_KEY_RST = "rstId";
    private final static String SESSION_KEY_MNG = "mngId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling");
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_KEY_CST) != null){
            return true;
        }
        if(session.getAttribute(SESSION_KEY_RST) != null){
            return true;
        }
        if(session.getAttribute(SESSION_KEY_MNG) != null){
            return true;
        }
        System.out.println("no login");
        String url = "/yummy";
        response.sendRedirect(url);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        //System.out.println("Request and Response is completed");
    }
}

