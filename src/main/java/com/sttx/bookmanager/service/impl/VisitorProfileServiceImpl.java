package com.sttx.bookmanager.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.dao.VisitorProfileMapper;
import com.sttx.bookmanager.po.VisitorProfile;
import com.sttx.bookmanager.service.IVisitorProfileService;

@Service("visitorProfileService")
public class VisitorProfileServiceImpl extends BaseServiceImpl<VisitorProfile>  implements IVisitorProfileService {
  private static final Logger log = LoggerFactory.getLogger(VisitorProfileServiceImpl.class);

  @Autowired
  private VisitorProfileMapper visitorProfileMapper;
  
  @Override
  public List<VisitorProfile> visitors() {
     return visitorProfileMapper.visitors();
  }

}
