package com.sttx.bookmanager.dao;

import java.util.List;

import com.sttx.bookmanager.po.VisitorProfile;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

public interface VisitorProfileMapper extends Mapper<VisitorProfile> {
  
  public List<VisitorProfile> visitors();
}