package com.wsminitor.hisexampleserver.entity;

import java.util.Date;

public class Report {
    private Integer rid;
    private String workerId;
    private String workerName;
    private String gender;
    private Date dateOfBirth;
    private String department;
    private String position;
    /**健康状况，是否有影响工作的疾病或手术描述*/
    private String healthStatus;
    private Date statusDate;
    /**心率*/
    private Integer heartRate;

    /**血压.舒张压DBP 60mmHg~90mmHg之间为正常*/
    private Integer DBP;

    /**血压.收缩压SBP 90mmHg~140mmHg之间为正常*/
    private Integer SBP;

    /**血氧饱和度SaO2 ＞94%为正常*/
    private Integer SaO2;

    /**体温*/
    private Double temperature;

    /**
     *体征状态
     * status = 0 正常
     * status = 1 异常
     * */
    private Integer status;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getDBP() {
        return DBP;
    }

    public void setDBP(Integer DBP) {
        this.DBP = DBP;
    }

    public Integer getSBP() {
        return SBP;
    }

    public void setSBP(Integer SBP) {
        this.SBP = SBP;
    }

    public Integer getSaO2() {
        return SaO2;
    }

    public void setSaO2(Integer saO2) {
        SaO2 = saO2;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
