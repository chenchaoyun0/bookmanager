package com.sttx.bookmanager.po;

import java.io.InputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class GridfsImg implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private InputStream in;
  
  private String aliases;
  
  private String fileName;

}
