 package com.sttx.bookmanager.web.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TodayCountVo implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  private long todayCount;
  
  private long todayVisitorCount;

}
