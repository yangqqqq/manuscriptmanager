package com.yang.software.mm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yang.software.mm.data.session.SessionCache;

@RequestMapping
public class SessionInterceptor implements HandlerInterceptor {

    public SessionInterceptor() {
        // TODO Auto-generated constructor stub
    }

    private String mappingURL;// 利用正则映射到需要拦截的路径

    public void setMappingURL(String mappingURL) {
        this.mappingURL = mappingURL;
    }

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p/>
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getSession().getId();
        SessionCache.sessionId.set(sessionId);
        if (!SessionCache.isSessionExist(sessionId)) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
            return false;

        }
        return true;
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("123123");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * <p/>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("64536546");
    }

}
