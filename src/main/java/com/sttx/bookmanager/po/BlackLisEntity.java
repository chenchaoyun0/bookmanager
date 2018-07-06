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
}