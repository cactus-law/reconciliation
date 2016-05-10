package com.froad.beans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by zhou on 2015/3/30.
 */
@Entity
public class Pay {
    private String paySeqNo;
    private String seqNo;
    private String payTrackNo;
    private String payType;
    private String payer;
    private String payee;
    private String mobilephone;
    private String panNum;
    private String iDcardNum;
    private BigDecimal money;
    private String payResult;
    private String state;
    private String payCredential;
    private Date payTime;
    private short payStep;
    private String createSeqNo;
    private String czState;
    private Date czTime;
    private Integer checkTimer;
    private Date lastTime;
    private String accountingStatus;

    @Basic
    @Column(name = "pay_seq_no", nullable = true, insertable = true, updatable = true, length = 64)
    public String getPaySeqNo() {
        return paySeqNo;
    }

    public void setPaySeqNo(String paySeqNo) {
        this.paySeqNo = paySeqNo;
    }

    @Id
    @Column(name = "seq_no", nullable = false, insertable = true, updatable = true, length = 64)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @Basic
    @Column(name = "pay_track_no", nullable = true, insertable = true, updatable = true, length = 32)
    public String getPayTrackNo() {
        return payTrackNo;
    }

    public void setPayTrackNo(String payTrackNo) {
        this.payTrackNo = payTrackNo;
    }

    @Basic
    @Column(name = "pay_type", nullable = false, insertable = true, updatable = true, length = 30)
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "payer", nullable = false, insertable = true, updatable = true, length = 200)
    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    @Basic
    @Column(name = "payee", nullable = false, insertable = true, updatable = true, length = 200)
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Basic
    @Column(name = "mobilephone", nullable = true, insertable = true, updatable = true, length = 18)
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    @Basic
    @Column(name = "pan_num", nullable = true, insertable = true, updatable = true, length = 32)
    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    @Basic
    @Column(name = "IDcard_num", nullable = true, insertable = true, updatable = true, length = 18)
    public String getiDcardNum() {
        return iDcardNum;
    }

    public void setiDcardNum(String iDcardNum) {
        this.iDcardNum = iDcardNum;
    }

    @Basic
    @Column(name = "money", nullable = false, insertable = true, updatable = true, precision = 2)
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Basic
    @Column(name = "pay_result", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "pay_credential", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayCredential() {
        return payCredential;
    }

    public void setPayCredential(String payCredential) {
        this.payCredential = payCredential;
    }

    @Basic
    @Column(name = "pay_time", nullable = false, insertable = true, updatable = true)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Id
    @Column(name = "pay_step", nullable = false, insertable = true, updatable = true)
    public short getPayStep() {
        return payStep;
    }

    public void setPayStep(short payStep) {
        this.payStep = payStep;
    }

    @Basic
    @Column(name = "create_seq_no", nullable = false, insertable = true, updatable = true, length = 64)
    public String getCreateSeqNo() {
        return createSeqNo;
    }

    public void setCreateSeqNo(String createSeqNo) {
        this.createSeqNo = createSeqNo;
    }

    @Basic
    @Column(name = "cz_state", nullable = true, insertable = true, updatable = true, length = 10)
    public String getCzState() {
        return czState;
    }

    public void setCzState(String czState) {
        this.czState = czState;
    }

    @Basic
    @Column(name = "cz_time", nullable = true, insertable = true, updatable = true)
    public Date getCzTime() {
        return czTime;
    }

    public void setCzTime(Date czTime) {
        this.czTime = czTime;
    }

    @Basic
    @Column(name = "check_timer", nullable = true, insertable = true, updatable = true)
    public Integer getCheckTimer() {
        return checkTimer;
    }

    public void setCheckTimer(Integer checkTimer) {
        this.checkTimer = checkTimer;
    }

    @Basic
    @Column(name = "last_time", nullable = true, insertable = true, updatable = true)
    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "accounting_status", nullable = true, insertable = true, updatable = true, length = 3)
    public String getAccountingStatus() {
        return accountingStatus;
    }

    public void setAccountingStatus(String accountingStatus) {
        this.accountingStatus = accountingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pay pay = (Pay) o;

        if (payStep != pay.payStep) return false;
        if (accountingStatus != null ? !accountingStatus.equals(pay.accountingStatus) : pay.accountingStatus != null)
            return false;
        if (checkTimer != null ? !checkTimer.equals(pay.checkTimer) : pay.checkTimer != null) return false;
        if (createSeqNo != null ? !createSeqNo.equals(pay.createSeqNo) : pay.createSeqNo != null) return false;
        if (czState != null ? !czState.equals(pay.czState) : pay.czState != null) return false;
        if (czTime != null ? !czTime.equals(pay.czTime) : pay.czTime != null) return false;
        if (iDcardNum != null ? !iDcardNum.equals(pay.iDcardNum) : pay.iDcardNum != null) return false;
        if (lastTime != null ? !lastTime.equals(pay.lastTime) : pay.lastTime != null) return false;
        if (mobilephone != null ? !mobilephone.equals(pay.mobilephone) : pay.mobilephone != null) return false;
        if (money != null ? !money.equals(pay.money) : pay.money != null) return false;
        if (panNum != null ? !panNum.equals(pay.panNum) : pay.panNum != null) return false;
        if (payCredential != null ? !payCredential.equals(pay.payCredential) : pay.payCredential != null) return false;
        if (payResult != null ? !payResult.equals(pay.payResult) : pay.payResult != null) return false;
        if (paySeqNo != null ? !paySeqNo.equals(pay.paySeqNo) : pay.paySeqNo != null) return false;
        if (payTime != null ? !payTime.equals(pay.payTime) : pay.payTime != null) return false;
        if (payTrackNo != null ? !payTrackNo.equals(pay.payTrackNo) : pay.payTrackNo != null) return false;
        if (payType != null ? !payType.equals(pay.payType) : pay.payType != null) return false;
        if (payee != null ? !payee.equals(pay.payee) : pay.payee != null) return false;
        if (payer != null ? !payer.equals(pay.payer) : pay.payer != null) return false;
        if (seqNo != null ? !seqNo.equals(pay.seqNo) : pay.seqNo != null) return false;
        if (state != null ? !state.equals(pay.state) : pay.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paySeqNo != null ? paySeqNo.hashCode() : 0;
        result = 31 * result + (seqNo != null ? seqNo.hashCode() : 0);
        result = 31 * result + (payTrackNo != null ? payTrackNo.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (payer != null ? payer.hashCode() : 0);
        result = 31 * result + (payee != null ? payee.hashCode() : 0);
        result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
        result = 31 * result + (panNum != null ? panNum.hashCode() : 0);
        result = 31 * result + (iDcardNum != null ? iDcardNum.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (payResult != null ? payResult.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (payCredential != null ? payCredential.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (int) payStep;
        result = 31 * result + (createSeqNo != null ? createSeqNo.hashCode() : 0);
        result = 31 * result + (czState != null ? czState.hashCode() : 0);
        result = 31 * result + (czTime != null ? czTime.hashCode() : 0);
        result = 31 * result + (checkTimer != null ? checkTimer.hashCode() : 0);
        result = 31 * result + (lastTime != null ? lastTime.hashCode() : 0);
        result = 31 * result + (accountingStatus != null ? accountingStatus.hashCode() : 0);
        return result;
    }
}
