package com.froad.beans;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhou on 2015/3/30.
 */
@Entity
public class Bill {
    private String seqNo;

    @Id
    @javax.persistence.Column(name = "seq_no", nullable = false, insertable = true, updatable = true, length = 64)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    private String createSeqNo;

    @Basic
    @javax.persistence.Column(name = "create_seq_no", nullable = false, insertable = true, updatable = true, length = 100)
    public String getCreateSeqNo() {
        return createSeqNo;
    }

    public void setCreateSeqNo(String createSeqNo) {
        this.createSeqNo = createSeqNo;
    }

    private String creator;

    @Basic
    @javax.persistence.Column(name = "creator", nullable = false, insertable = true, updatable = true, length = 200)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    private String payer;

    @Basic
    @javax.persistence.Column(name = "payer", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    private String payee;

    @Basic
    @javax.persistence.Column(name = "payee", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    private String appPara;

    @Basic
    @javax.persistence.Column(name = "app_para", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAppPara() {
        return appPara;
    }

    public void setAppPara(String appPara) {
        this.appPara = appPara;
    }

    private String billType;

    @Basic
    @javax.persistence.Column(name = "bill_type", nullable = false, insertable = true, updatable = true, length = 30)
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    private String currency;

    @Basic
    @javax.persistence.Column(name = "currency", nullable = false, insertable = true, updatable = true, length = 30)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private BigDecimal money;

    @Basic
    @javax.persistence.Column(name = "money", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    private String spec;

    @Basic
    @javax.persistence.Column(name = "spec", nullable = true, insertable = true, updatable = true, length = 255)
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    private String state;

    @Basic
    @javax.persistence.Column(name = "state", nullable = false, insertable = true, updatable = true, length = 30)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String pzState;

    @Basic
    @javax.persistence.Column(name = "pz_state", nullable = true, insertable = true, updatable = true, length = 30)
    public String getPzState() {
        return pzState;
    }

    public void setPzState(String pzState) {
        this.pzState = pzState;
    }

    private BigDecimal rate;

    @Basic
    @javax.persistence.Column(name = "rate", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    private Date createTime;

    @Basic
    @javax.persistence.Column(name = "create_time", nullable = false, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private Date payTime;

    @Basic
    @javax.persistence.Column(name = "pay_time", nullable = true, insertable = true, updatable = true)
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    private Date pzTime;

    @Basic
    @javax.persistence.Column(name = "pz_time", nullable = true, insertable = true, updatable = true)
    public Date getPzTime() {
        return pzTime;
    }

    public void setPzTime(Date pzTime) {
        this.pzTime = pzTime;
    }

    private Date expires;

    @Basic
    @javax.persistence.Column(name = "expires", nullable = true, insertable = true, updatable = true)
    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    private String notifyCmd;

    @Basic
    @javax.persistence.Column(name = "notify_cmd", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNotifyCmd() {
        return notifyCmd;
    }

    public void setNotifyCmd(String notifyCmd) {
        this.notifyCmd = notifyCmd;
    }

    private String notifyState;

    @Basic
    @javax.persistence.Column(name = "notify_state", nullable = true, insertable = true, updatable = true, length = 30)
    public String getNotifyState() {
        return notifyState;
    }

    public void setNotifyState(String notifyState) {
        this.notifyState = notifyState;
    }

    private String auth;

    @Basic
    @javax.persistence.Column(name = "auth", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    private Date cancelTime;

    @Basic
    @javax.persistence.Column(name = "cancel_time", nullable = true, insertable = true, updatable = true)
    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    private Date paybackTime;

    @Basic
    @javax.persistence.Column(name = "payback_time", nullable = true, insertable = true, updatable = true)
    public Date getPaybackTime() {
        return paybackTime;
    }

    public void setPaybackTime(Date paybackTime) {
        this.paybackTime = paybackTime;
    }

    private Date refuseTime;

    @Basic
    @javax.persistence.Column(name = "refuse_time", nullable = true, insertable = true, updatable = true)
    public Date getRefuseTime() {
        return refuseTime;
    }

    public void setRefuseTime(Date refuseTime) {
        this.refuseTime = refuseTime;
    }

    private String payPath1;

    @Basic
    @javax.persistence.Column(name = "pay_path1", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayPath1() {
        return payPath1;
    }

    public void setPayPath1(String payPath1) {
        this.payPath1 = payPath1;
    }

    private String payPath2;

    @Basic
    @javax.persistence.Column(name = "pay_path2", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayPath2() {
        return payPath2;
    }

    public void setPayPath2(String payPath2) {
        this.payPath2 = payPath2;
    }

    private String payPath3;

    @Basic
    @javax.persistence.Column(name = "pay_path3", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayPath3() {
        return payPath3;
    }

    public void setPayPath3(String payPath3) {
        this.payPath3 = payPath3;
    }

    private String payPath4;

    @Basic
    @javax.persistence.Column(name = "pay_path4", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayPath4() {
        return payPath4;
    }

    public void setPayPath4(String payPath4) {
        this.payPath4 = payPath4;
    }

    private String payPath5;

    @Basic
    @javax.persistence.Column(name = "pay_path5", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPayPath5() {
        return payPath5;
    }

    public void setPayPath5(String payPath5) {
        this.payPath5 = payPath5;
    }

    private String merReturnUrl;

    @Basic
    @javax.persistence.Column(name = "merReturnUrl", nullable = true, insertable = true, updatable = true, length = 256)
    public String getMerReturnUrl() {
        return merReturnUrl;
    }

    public void setMerReturnUrl(String merReturnUrl) {
        this.merReturnUrl = merReturnUrl;
    }

    private String merNotifyUrl;

    @Basic
    @javax.persistence.Column(name = "merNotifyUrl", nullable = true, insertable = true, updatable = true, length = 256)
    public String getMerNotifyUrl() {
        return merNotifyUrl;
    }

    public void setMerNotifyUrl(String merNotifyUrl) {
        this.merNotifyUrl = merNotifyUrl;
    }

    private String mobilePhone;

    @Basic
    @javax.persistence.Column(name = "mobilePhone", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    private String merchantId;

    @Basic
    @javax.persistence.Column(name = "merchantId", nullable = true, insertable = true, updatable = true, length = 32)
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    private String bankGroup;

    @Basic
    @javax.persistence.Column(name = "bankGroup", nullable = true, insertable = true, updatable = true, length = 3)
    public String getBankGroup() {
        return bankGroup;
    }

    public void setBankGroup(String bankGroup) {
        this.bankGroup = bankGroup;
    }

    private String channelId;

    @Basic
    @javax.persistence.Column(name = "channelId", nullable = true, insertable = true, updatable = true, length = 2)
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    private String merGoods;

    @Basic
    @javax.persistence.Column(name = "merGoods", nullable = true, insertable = true, updatable = true, length = 256)
    public String getMerGoods() {
        return merGoods;
    }

    public void setMerGoods(String merGoods) {
        this.merGoods = merGoods;
    }

    private String md5;

    @Basic
    @javax.persistence.Column(name = "md5", nullable = true, insertable = true, updatable = true, length = 500)
    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    private String role;

    @Basic
    @javax.persistence.Column(name = "role", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String opStep;

    @Basic
    @javax.persistence.Column(name = "op_step", nullable = true, insertable = true, updatable = true, length = 6)
    public String getOpStep() {
        return opStep;
    }

    public void setOpStep(String opStep) {
        this.opStep = opStep;
    }

    private BigDecimal canBackMoney;

    @Basic
    @javax.persistence.Column(name = "can_back_money", nullable = true, insertable = true, updatable = true, precision = 2)
    public BigDecimal getCanBackMoney() {
        return canBackMoney;
    }

    public void setCanBackMoney(BigDecimal canBackMoney) {
        this.canBackMoney = canBackMoney;
    }

    private String orderSupplier;

    @Basic
    @javax.persistence.Column(name = "order_supplier", nullable = true, insertable = true, updatable = true, length = 256)
    public String getOrderSupplier() {
        return orderSupplier;
    }

    public void setOrderSupplier(String orderSupplier) {
        this.orderSupplier = orderSupplier;
    }

    private String payerIdentity;

    @Basic
    @javax.persistence.Column(name = "payer_identity", nullable = true, insertable = true, updatable = true, length = 8)
    public String getPayerIdentity() {
        return payerIdentity;
    }

    public void setPayerIdentity(String payerIdentity) {
        this.payerIdentity = payerIdentity;
    }

    private String payeeIdentity;

    @Basic
    @javax.persistence.Column(name = "payee_identity", nullable = true, insertable = true, updatable = true, length = 8)
    public String getPayeeIdentity() {
        return payeeIdentity;
    }

    public void setPayeeIdentity(String payeeIdentity) {
        this.payeeIdentity = payeeIdentity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (appPara != null ? !appPara.equals(bill.appPara) : bill.appPara != null) return false;
        if (auth != null ? !auth.equals(bill.auth) : bill.auth != null) return false;
        if (bankGroup != null ? !bankGroup.equals(bill.bankGroup) : bill.bankGroup != null) return false;
        if (billType != null ? !billType.equals(bill.billType) : bill.billType != null) return false;
        if (canBackMoney != null ? !canBackMoney.equals(bill.canBackMoney) : bill.canBackMoney != null) return false;
        if (cancelTime != null ? !cancelTime.equals(bill.cancelTime) : bill.cancelTime != null) return false;
        if (channelId != null ? !channelId.equals(bill.channelId) : bill.channelId != null) return false;
        if (createSeqNo != null ? !createSeqNo.equals(bill.createSeqNo) : bill.createSeqNo != null) return false;
        if (createTime != null ? !createTime.equals(bill.createTime) : bill.createTime != null) return false;
        if (creator != null ? !creator.equals(bill.creator) : bill.creator != null) return false;
        if (currency != null ? !currency.equals(bill.currency) : bill.currency != null) return false;
        if (expires != null ? !expires.equals(bill.expires) : bill.expires != null) return false;
        if (md5 != null ? !md5.equals(bill.md5) : bill.md5 != null) return false;
        if (merGoods != null ? !merGoods.equals(bill.merGoods) : bill.merGoods != null) return false;
        if (merNotifyUrl != null ? !merNotifyUrl.equals(bill.merNotifyUrl) : bill.merNotifyUrl != null) return false;
        if (merReturnUrl != null ? !merReturnUrl.equals(bill.merReturnUrl) : bill.merReturnUrl != null) return false;
        if (merchantId != null ? !merchantId.equals(bill.merchantId) : bill.merchantId != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(bill.mobilePhone) : bill.mobilePhone != null) return false;
        if (money != null ? !money.equals(bill.money) : bill.money != null) return false;
        if (notifyCmd != null ? !notifyCmd.equals(bill.notifyCmd) : bill.notifyCmd != null) return false;
        if (notifyState != null ? !notifyState.equals(bill.notifyState) : bill.notifyState != null) return false;
        if (opStep != null ? !opStep.equals(bill.opStep) : bill.opStep != null) return false;
        if (orderSupplier != null ? !orderSupplier.equals(bill.orderSupplier) : bill.orderSupplier != null)
            return false;
        if (payPath1 != null ? !payPath1.equals(bill.payPath1) : bill.payPath1 != null) return false;
        if (payPath2 != null ? !payPath2.equals(bill.payPath2) : bill.payPath2 != null) return false;
        if (payPath3 != null ? !payPath3.equals(bill.payPath3) : bill.payPath3 != null) return false;
        if (payPath4 != null ? !payPath4.equals(bill.payPath4) : bill.payPath4 != null) return false;
        if (payPath5 != null ? !payPath5.equals(bill.payPath5) : bill.payPath5 != null) return false;
        if (payTime != null ? !payTime.equals(bill.payTime) : bill.payTime != null) return false;
        if (paybackTime != null ? !paybackTime.equals(bill.paybackTime) : bill.paybackTime != null) return false;
        if (payee != null ? !payee.equals(bill.payee) : bill.payee != null) return false;
        if (payeeIdentity != null ? !payeeIdentity.equals(bill.payeeIdentity) : bill.payeeIdentity != null)
            return false;
        if (payer != null ? !payer.equals(bill.payer) : bill.payer != null) return false;
        if (payerIdentity != null ? !payerIdentity.equals(bill.payerIdentity) : bill.payerIdentity != null)
            return false;
        if (pzState != null ? !pzState.equals(bill.pzState) : bill.pzState != null) return false;
        if (pzTime != null ? !pzTime.equals(bill.pzTime) : bill.pzTime != null) return false;
        if (rate != null ? !rate.equals(bill.rate) : bill.rate != null) return false;
        if (refuseTime != null ? !refuseTime.equals(bill.refuseTime) : bill.refuseTime != null) return false;
        if (role != null ? !role.equals(bill.role) : bill.role != null) return false;
        if (seqNo != null ? !seqNo.equals(bill.seqNo) : bill.seqNo != null) return false;
        if (spec != null ? !spec.equals(bill.spec) : bill.spec != null) return false;
        if (state != null ? !state.equals(bill.state) : bill.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = seqNo != null ? seqNo.hashCode() : 0;
        result = 31 * result + (createSeqNo != null ? createSeqNo.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (payer != null ? payer.hashCode() : 0);
        result = 31 * result + (payee != null ? payee.hashCode() : 0);
        result = 31 * result + (appPara != null ? appPara.hashCode() : 0);
        result = 31 * result + (billType != null ? billType.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (spec != null ? spec.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (pzState != null ? pzState.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (pzTime != null ? pzTime.hashCode() : 0);
        result = 31 * result + (expires != null ? expires.hashCode() : 0);
        result = 31 * result + (notifyCmd != null ? notifyCmd.hashCode() : 0);
        result = 31 * result + (notifyState != null ? notifyState.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        result = 31 * result + (cancelTime != null ? cancelTime.hashCode() : 0);
        result = 31 * result + (paybackTime != null ? paybackTime.hashCode() : 0);
        result = 31 * result + (refuseTime != null ? refuseTime.hashCode() : 0);
        result = 31 * result + (payPath1 != null ? payPath1.hashCode() : 0);
        result = 31 * result + (payPath2 != null ? payPath2.hashCode() : 0);
        result = 31 * result + (payPath3 != null ? payPath3.hashCode() : 0);
        result = 31 * result + (payPath4 != null ? payPath4.hashCode() : 0);
        result = 31 * result + (payPath5 != null ? payPath5.hashCode() : 0);
        result = 31 * result + (merReturnUrl != null ? merReturnUrl.hashCode() : 0);
        result = 31 * result + (merNotifyUrl != null ? merNotifyUrl.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (bankGroup != null ? bankGroup.hashCode() : 0);
        result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
        result = 31 * result + (merGoods != null ? merGoods.hashCode() : 0);
        result = 31 * result + (md5 != null ? md5.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (opStep != null ? opStep.hashCode() : 0);
        result = 31 * result + (canBackMoney != null ? canBackMoney.hashCode() : 0);
        result = 31 * result + (orderSupplier != null ? orderSupplier.hashCode() : 0);
        result = 31 * result + (payerIdentity != null ? payerIdentity.hashCode() : 0);
        result = 31 * result + (payeeIdentity != null ? payeeIdentity.hashCode() : 0);
        return result;
    }
}
