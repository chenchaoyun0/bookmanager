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

}
