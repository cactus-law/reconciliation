package com.froad.beans;

import javax.persistence.*;

/**
 * Created by zhou on 2015/5/13.
 */
@Entity
@Table(name = "trans_order",  catalog = "openapi")
public class TransOrder {
    private String orderNo;
    private String orderId;
    private String orderType;
    private String orderAmount;
    private String orderCurrency;
    private String orderDisplay;
    private String orderSubmitTime;
    private String orderFailureTime;
    private String orderRemark;
    private String stateCode;
    private String orderAcquiringTime;
    private String orderCompleteTime;
    private String acquiringTime;
    private String completeTime;
    private String billNo;
    private String partnerNo;
    private String trackNo;
    private String createTime;
    private String updateTime;
    private String orderSupplier;
    private String refundableAmount;
    private String payerMark;
    private String payeeMark;
    private String exchFlag;

    @Id
    @Column(name = "order_no", nullable = false, insertable = true, updatable = true, length = 40)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "order_ID", nullable = false, insertable = true, updatable = true, length = 40)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_type", nullable = false, insertable = true, updatable = true, length = 4)
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "order_amount", nullable = false, insertable = true, updatable = true, length = 19)
    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "order_currency", nullable = false, insertable = true, updatable = true, length = 2)
    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    @Basic
    @Column(name = "order_display", nullable = false, insertable = true, updatable = true, length = 384)
    public String getOrderDisplay() {
        return orderDisplay;
    }

    public void setOrderDisplay(String orderDisplay) {
        this.orderDisplay = orderDisplay;
    }

    @Basic
    @Column(name = "order_submit_time", nullable = false, insertable = true, updatable = true, length = 14)
    public String getOrderSubmitTime() {
        return orderSubmitTime;
    }

    public void setOrderSubmitTime(String orderSubmitTime) {
        this.orderSubmitTime = orderSubmitTime;
    }

    @Basic
    @Column(name = "order_failure_time", nullable = true, insertable = true, updatable = true, length = 14)
    public String getOrderFailureTime() {
        return orderFailureTime;
    }

    public void setOrderFailureTime(String orderFailureTime) {
        this.orderFailureTime = orderFailureTime;
    }

    @Basic
    @Column(name = "order_remark", nullable = true, insertable = true, updatable = true, length = 1024)
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    @Basic
    @Column(name = "state_code", nullable = false, insertable = true, updatable = true, length = 32)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Basic
    @Column(name = "order_acquiring_time", nullable = false, insertable = true, updatable = true, length = 14)
    public String getOrderAcquiringTime() {
        return orderAcquiringTime;
    }

    public void setOrderAcquiringTime(String orderAcquiringTime) {
        this.orderAcquiringTime = orderAcquiringTime;
    }

    @Basic
    @Column(name = "order_complete_time", nullable = true, insertable = true, updatable = true, length = 14)
    public String getOrderCompleteTime() {
        return orderCompleteTime;
    }

    public void setOrderCompleteTime(String orderCompleteTime) {
        this.orderCompleteTime = orderCompleteTime;
    }

    @Basic
    @Column(name = "acquiring_time", nullable = false, insertable = true, updatable = true, length = 14)
    public String getAcquiringTime() {
        return acquiringTime;
    }

    public void setAcquiringTime(String acquiringTime) {
        this.acquiringTime = acquiringTime;
    }

    @Basic
    @Column(name = "complete_time", nullable = true, insertable = true, updatable = true, length = 14)
    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    @Basic
    @Column(name = "bill_no", nullable = true, insertable = true, updatable = true, length = 64)
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    @Basic
    @Column(name = "partner_no", nullable = false, insertable = true, updatable = true, length = 32)
    public String getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }

    @Basic
    @Column(name = "track_no", nullable = false, insertable = true, updatable = true, length = 32)
    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false, insertable = true, updatable = true, length = 32)
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "order_supplier", nullable = true, insertable = true, updatable = true, length = 256)
    public String getOrderSupplier() {
        return orderSupplier;
    }

    public void setOrderSupplier(String orderSupplier) {
        this.orderSupplier = orderSupplier;
    }

    @Basic
    @Column(name = "refundable_amount", nullable = false, insertable = true, updatable = true, length = 19)
    public String getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(String refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    @Basic
    @Column(name = "payer_mark", nullable = true, insertable = true, updatable = true, length = 32)
    public String getPayerMark() {
        return payerMark;
    }

    public void setPayerMark(String payerMark) {
        this.payerMark = payerMark;
    }

    @Basic
    @Column(name = "payee_mark", nullable = true, insertable = true, updatable = true, length = 32)
    public String getPayeeMark() {
        return payeeMark;
    }

    public void setPayeeMark(String payeeMark) {
        this.payeeMark = payeeMark;
    }

    @Basic
    @Column(name = "exch_flag", nullable = true, insertable = true, updatable = true, length = 19)
    public String getExchFlag() {
        return exchFlag;
    }

    public void setExchFlag(String exchFlag) {
        this.exchFlag = exchFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransOrder that = (TransOrder) o;

        if (acquiringTime != null ? !acquiringTime.equals(that.acquiringTime) : that.acquiringTime != null)
            return false;
        if (billNo != null ? !billNo.equals(that.billNo) : that.billNo != null) return false;
        if (completeTime != null ? !completeTime.equals(that.completeTime) : that.completeTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (exchFlag != null ? !exchFlag.equals(that.exchFlag) : that.exchFlag != null) return false;
        if (orderAcquiringTime != null ? !orderAcquiringTime.equals(that.orderAcquiringTime) : that.orderAcquiringTime != null)
            return false;
        if (orderAmount != null ? !orderAmount.equals(that.orderAmount) : that.orderAmount != null) return false;
        if (orderCompleteTime != null ? !orderCompleteTime.equals(that.orderCompleteTime) : that.orderCompleteTime != null)
            return false;
        if (orderCurrency != null ? !orderCurrency.equals(that.orderCurrency) : that.orderCurrency != null)
            return false;
        if (orderDisplay != null ? !orderDisplay.equals(that.orderDisplay) : that.orderDisplay != null) return false;
        if (orderFailureTime != null ? !orderFailureTime.equals(that.orderFailureTime) : that.orderFailureTime != null)
            return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (orderRemark != null ? !orderRemark.equals(that.orderRemark) : that.orderRemark != null) return false;
        if (orderSubmitTime != null ? !orderSubmitTime.equals(that.orderSubmitTime) : that.orderSubmitTime != null)
            return false;
        if (orderSupplier != null ? !orderSupplier.equals(that.orderSupplier) : that.orderSupplier != null)
            return false;
        if (orderType != null ? !orderType.equals(that.orderType) : that.orderType != null) return false;
        if (partnerNo != null ? !partnerNo.equals(that.partnerNo) : that.partnerNo != null) return false;
        if (payeeMark != null ? !payeeMark.equals(that.payeeMark) : that.payeeMark != null) return false;
        if (payerMark != null ? !payerMark.equals(that.payerMark) : that.payerMark != null) return false;
        if (refundableAmount != null ? !refundableAmount.equals(that.refundableAmount) : that.refundableAmount != null)
            return false;
        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null) return false;
        if (trackNo != null ? !trackNo.equals(that.trackNo) : that.trackNo != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNo != null ? orderNo.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderAmount != null ? orderAmount.hashCode() : 0);
        result = 31 * result + (orderCurrency != null ? orderCurrency.hashCode() : 0);
        result = 31 * result + (orderDisplay != null ? orderDisplay.hashCode() : 0);
        result = 31 * result + (orderSubmitTime != null ? orderSubmitTime.hashCode() : 0);
        result = 31 * result + (orderFailureTime != null ? orderFailureTime.hashCode() : 0);
        result = 31 * result + (orderRemark != null ? orderRemark.hashCode() : 0);
        result = 31 * result + (stateCode != null ? stateCode.hashCode() : 0);
        result = 31 * result + (orderAcquiringTime != null ? orderAcquiringTime.hashCode() : 0);
        result = 31 * result + (orderCompleteTime != null ? orderCompleteTime.hashCode() : 0);
        result = 31 * result + (acquiringTime != null ? acquiringTime.hashCode() : 0);
        result = 31 * result + (completeTime != null ? completeTime.hashCode() : 0);
        result = 31 * result + (billNo != null ? billNo.hashCode() : 0);
        result = 31 * result + (partnerNo != null ? partnerNo.hashCode() : 0);
        result = 31 * result + (trackNo != null ? trackNo.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (orderSupplier != null ? orderSupplier.hashCode() : 0);
        result = 31 * result + (refundableAmount != null ? refundableAmount.hashCode() : 0);
        result = 31 * result + (payerMark != null ? payerMark.hashCode() : 0);
        result = 31 * result + (payeeMark != null ? payeeMark.hashCode() : 0);
        result = 31 * result + (exchFlag != null ? exchFlag.hashCode() : 0);
        return result;
    }
}
