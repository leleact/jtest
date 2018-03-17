package com.lele.test.mybatis.generator.bean.dto;

public class QrsRouteInfo {
    private String routeId;

    private String mchtId;

    private String prodId;

    private String acctType;

    private String pagyNo;

    private Integer priority;

    private String status;

    private String routeDesc;

    private String crtTlr;

    private String crtDateTime;

    private String lastUpdTlr;

    private String lastUpdDateTime;

    private String remark1;

    private String remark2;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getMchtId() {
        return mchtId;
    }

    public void setMchtId(String mchtId) {
        this.mchtId = mchtId == null ? null : mchtId.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getPagyNo() {
        return pagyNo;
    }

    public void setPagyNo(String pagyNo) {
        this.pagyNo = pagyNo == null ? null : pagyNo.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc == null ? null : routeDesc.trim();
    }

    public String getCrtTlr() {
        return crtTlr;
    }

    public void setCrtTlr(String crtTlr) {
        this.crtTlr = crtTlr == null ? null : crtTlr.trim();
    }

    public String getCrtDateTime() {
        return crtDateTime;
    }

    public void setCrtDateTime(String crtDateTime) {
        this.crtDateTime = crtDateTime == null ? null : crtDateTime.trim();
    }

    public String getLastUpdTlr() {
        return lastUpdTlr;
    }

    public void setLastUpdTlr(String lastUpdTlr) {
        this.lastUpdTlr = lastUpdTlr == null ? null : lastUpdTlr.trim();
    }

    public String getLastUpdDateTime() {
        return lastUpdDateTime;
    }

    public void setLastUpdDateTime(String lastUpdDateTime) {
        this.lastUpdDateTime = lastUpdDateTime == null ? null : lastUpdDateTime.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}