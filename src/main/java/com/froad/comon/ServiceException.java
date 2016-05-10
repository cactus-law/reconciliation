package com.froad.comon;

/**
 * @author choky
 *         自定义服务异常
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = 3292081983807822003L;
    /**
     * 模块
     */
    private String module;

    /**
     * 业务异常
     *
     * @param module  模块，通过Constant取
     * @param message 错误信息
     * @param e       异常源
     */
    public ServiceException(String module, String message, Throwable e) {
        super(message, e);
        this.module = module;
    }

    /**
     * 业务异常
     *
     * @param module  模块，通过Constant取
     * @param message 错误信息
     */
    public ServiceException(String module, String message) {
        super(message);
        this.module = module;
    }

    public ServiceException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable e) {
        super(message, e);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public ServiceException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public ServiceException(Throwable e) {
        super(e);
        // TODO Auto-generated constructor stub
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
