package com.froad.beans;

import java.io.Serializable;

/**
 * Created by zhou on 2015/3/30.
 */
public class PayPK implements Serializable {
    private String seqNo;
    private short payStep;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public short getPayStep() {
        return payStep;
    }

    public void setPayStep(short payStep) {
        this.payStep = payStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayPK payPK = (PayPK) o;

        if (payStep != payPK.payStep) return false;
        if (seqNo != null ? !seqNo.equals(payPK.seqNo) : payPK.seqNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = seqNo != null ? seqNo.hashCode() : 0;
        result = 31 * result + (int) payStep;
        return result;
    }
}
