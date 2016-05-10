package com.froad.flow;

import com.froad.beans.Rckflowdetail;
import com.froad.comon.ServiceException;

/**
 * @author silence
 *         流异常
 */
public class FlowException extends Exception {
    private static final long serialVersionUID = -3261448359500206013L;
    private Rckflowdetail rckflowdetail;

    public FlowException() {
        super();
    }

    /**
     * @param e
     */
    public FlowException(Throwable e) {
        super(e);
    }

    /**
     * @param message
     */
    public FlowException(String message) {
        super(message);
    }

    public FlowException(Rckflowdetail rckflowdetail, ServiceException serviceException) {
        super(serviceException);
        this.rckflowdetail = rckflowdetail;
    }

    /**
     * @param message
     * @param e
     */
    public FlowException(String message, Throwable e) {
        super(message, e);
    }

    public FlowException(Rckflowdetail rckflowdetail, Throwable e) {
        super(e);
        this.rckflowdetail = rckflowdetail;
    }

    public FlowException(Rckflowdetail rckflowdetail, String message, Throwable e) {
        super(e);
        this.rckflowdetail = rckflowdetail;
    }

    public FlowException(Rckflowdetail rckflowdetail, String message) {
        super(message);
        this.rckflowdetail = rckflowdetail;
    }

    public Rckflowdetail getRckflowdetail() {
        return rckflowdetail;
    }

    public void setRckflowdetail(Rckflowdetail rckflowdetail) {
        this.rckflowdetail = rckflowdetail;
    }
}
