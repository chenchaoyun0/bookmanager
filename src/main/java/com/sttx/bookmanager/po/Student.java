package com.sttx.bookmanager.po;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Student implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;

  private String name;

  private BigDecimal score;

}