package com.sttx.bookmanager.dao;

import java.util.List;

import com.sttx.bookmanager.po.VisitorProfile;

import tk.mybatis.mapper.common.BaseMapper;

public interface VisitorProfileMapper extends BaseMapper<VisitorProfile> {
  
  public List<VisitorProfile> visitors();
}