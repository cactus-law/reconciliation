package com.froad.comon.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Silence
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 5882510654970114287L;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected ServletContext context;
    protected String basePath;
    /**
     * IP
     */
    protected String from_ip;
    /**
     * 错误码
     */
    protected String errorcode;
    /**
     * 异常后跳转地址
     */
    protected String targetUrlApp;
    /**
     * 有错误时跳转至错误提示页面
     */
    protected final String ERRMESSAGE = "errmessage";
    /**
     * 有错误时跳转至指定页面
     */
    protected final String REDIRECTPAGE = "redirectPage";

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        session = request.getSession();
        context = session.getServletContext();
        from_ip = request.getRemoteAddr();
    }

    public String getBasePath() {
        String path = request.getContextPath();
        this.basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "";
        return basePath;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getERRMESSAGE() {
        return ERRMESSAGE;
    }

    public String getREDIRECTPAGE() {
        return REDIRECTPAGE;
    }

    public String getTargetUrlApp() {
        return targetUrlApp;
    }

    public void setTargetUrlApp(String targetUrlApp) {
        this.targetUrlApp = targetUrlApp;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpSession getSession() {
        return session;
    }

    public ServletContext getContext() {
        return context;
    }

    public String getFrom_ip() {
        return from_ip;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setFrom_ip(String fromIp) {
        from_ip = fromIp;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

}
