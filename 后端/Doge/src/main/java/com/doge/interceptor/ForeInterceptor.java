package com.doge.interceptor;


import org.springframework.lang.Nullable;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 拦截前台请求的拦截器
 * @Author: chenjun
 * @Date: 2018/11/29 16:22
 */
public class ForeInterceptor implements HandlerInterceptor {
//    @Autowired
//    SystemService systemService;
//
//    private Log log = new Log();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //访问者IP
//        String ip = request.getRemoteAddr();
//        //请求地址
//        String url = request.getRequestURL().toString();
//        //浏览器名
//        String browser = BrowserUtil.getOsAndBrowserInfo(request);
//
//        log.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
//        log.setUrl(StringUtils.isEmpty(url) ? "获取请求地址失败" : url);
//        log.setBrowser(StringUtils.isEmpty(browser) ? "获取浏览器失败" : browser);
//
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        if(handler instanceof HandlerMethod){
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = handlerMethod.getMethod();
//            //保存日志信息
//            log.setMethod(method.getName());
//            systemService.insertLog(log);
//        } else {
//            String uri = request.getRequestURI();
//            log.setMethod(uri);
//            systemService.insertLog(log);
//        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
