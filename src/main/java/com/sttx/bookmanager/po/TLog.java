package com.sttx.bookmanager.po;

import java.io.Serializable;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="t_log")
public class TLog extends TLogKey implements Serializable {
  private static final long serialVersionUID = 1L;

  private String userName;

  private String userNickName;

  private String userAddress;

  private String userJwd;

  private String module;

  private String action;

  private Long actionTime;

  private String operTime;

  private Long count;

  private String browserType;
  // 浏览器名称和版本
  private String browserAndVersion;
  // 浏览器厂商
  private String manufacturer;
  // 浏览器引擎
  private String renderingEngine;
  // 系统名称
  private String sysName;
  // 产品系列
  private String operatingSystem;
  // 生成厂商
  private String sysManufacturer;
  // 设备类型
  private String deviceType;

  public TLog() {
    super();
    // TODO Auto-generated constructor stub
  }

  public TLog(String userName, String userNickName, String userAddress, String userJwd, String module, String action,
    Long actionTime, String operTime, Long count) {
    super();
    this.userName = userName;
    this.userNickName = userNickName;
    this.userAddress = userAddress;
    this.userJwd = userJwd;
    this.module = module;
    this.action = action;
    this.actionTime = actionTime;
    this.operTime = operTime;
    this.count = count;
  }
}