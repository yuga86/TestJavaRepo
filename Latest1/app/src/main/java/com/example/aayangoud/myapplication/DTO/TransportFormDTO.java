package com.example.aayangoud.myapplication.DTO;

/**
 * Created by Aayan Goud on 8/21/2016.
 */
public class TransportFormDTO {
    String id;
    String transportDate;
    String busNo;
    String driverName;
    String plannerName;
    String from;
    String fromTime;
    String to;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(String transportDate) {
        this.transportDate = transportDate;
    }

    public String getBusNo() {
        return busNo;
    }

    public void setBusNo(String busNo) {
        this.busNo = busNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlannerName() {
        return plannerName;
    }

    public void setPlannerName(String plannerName) {
        this.plannerName = plannerName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getAcftRegin() {
        return acftRegin;
    }

    public void setAcftRegin(String acftRegin) {
        this.acftRegin = acftRegin;
    }

    public String getMdcTimeIn() {
        return mdcTimeIn;
    }

    public void setMdcTimeIn(String mdcTimeIn) {
        this.mdcTimeIn = mdcTimeIn;
    }

    public String getMdcTimeOut() {
        return mdcTimeOut;
    }

    public void setMdcTimeOut(String mdcTimeOut) {
        this.mdcTimeOut = mdcTimeOut;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(int transport_type) {
        this.transport_type = transport_type;
    }

    String toTime;
    String acftRegin;
    String mdcTimeIn;
    String mdcTimeOut;
    String remark;
    String sign;
    String team;
    int transport_type;


}
