package online.morn.itime;

import online.morn.itime.DO.UserDO;
import online.morn.itime.service.UserService;
import online.morn.itime.util.MyException;
import online.morn.itime.util.SessionKey;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局主过滤器
 * @auther Horner 2017/11/27 20:42
 */
public class MainFilter extends HttpServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==>MainFilter启动");
        //System.setProperty("entityExpansionLimit", "6400000");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**将请求转换成HttpServletRequest 请求*/
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        HttpSession session = request.getSession(true);

        UserDO userDO = (UserDO)session.getAttribute(SessionKey.USER);//获得用户实例
        /**做Session用户验证*/
        if(userDO == null){
            /**通过上下文获得用户服务*/
            ServletContext context = request.getServletContext();
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
            UserService userService = ctx.getBean(UserService.class);
            /**获得Cookie中的userId*/
            Map<String,String> cookieMap = this.getCookieMap(request);
            String userId = cookieMap.get(SessionKey.COOKIE_USER_ID);
            try {
                if(userId != null){
                    userDO = userService.findUserById(userId);
                    session.setAttribute(SessionKey.USER, userDO);//设置用户实例
                }
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 获得CookieMap
     * @auther Horner 2017/11/27 20:48
     * @param request
     * @return
     */
    private Map<String,String> getCookieMap(HttpServletRequest request){
        Map<String,String> cookieMap = new HashMap<>();
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                cookieMap.put(cookie.getName(),cookie.getValue());
            }
        }
        return cookieMap;
    }
}
