package com.sttx.bookmanager.service;

import java.util.List;

import com.sttx.bookmanager.po.VisitorProfile;

public interface IVisitorProfileService extends IBaseService<VisitorProfile> {
  
  public List<VisitorProfile> visitors();
}
