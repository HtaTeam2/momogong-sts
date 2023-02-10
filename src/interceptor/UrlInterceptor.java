package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UrlInterceptor extends HandlerInterceptorAdapter {
	 
@Override
public boolean preHandle(HttpServletRequest request, 
    HttpServletResponse response,
    Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    if(session.getAttribute("loginVO") == null) {
    
     String uri = request.getRequestURI();
     String query = request.getQueryString();
        
        if(query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }
        
        if(request.getMethod().equals("GET")) {
            request.getSession().setAttribute("URL", uri + query);
        }
    }    
    return true;
}
 
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
          ModelAndView modelAndView) throws Exception {
      super.postHandle(request, response, handler, modelAndView);
  } 
   
}