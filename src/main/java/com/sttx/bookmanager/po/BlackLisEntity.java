package com.sttx.bookmanager.po;

import java.io.Serializable;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "t_blacklist")
public class BlackLisEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String ip;
  private String path;
  private String lasttime;
  private Integer isblock = 0;
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
}