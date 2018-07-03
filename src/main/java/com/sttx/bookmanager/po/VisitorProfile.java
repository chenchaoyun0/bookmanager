 package com.sttx.bookmanager.po;

import java.io.Serializable;

import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name="t_visitor_profile")
public class VisitorProfile implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private long id;
  private String ip;
  private String address;
  private String appName;
  private String platform;
  private String appVersion;
  private String userAgent;
  private String product;
  private String cookieEnabled;
  private String cpuClass;
  private String oscpu;
  private String productSub;
  private String userProfile;
  private String createTime;
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

}
