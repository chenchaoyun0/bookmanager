package com.sttx.bookmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.service.IImgService;

@Service("imgService")
public class ImgServiceImpl extends BaseServiceImpl<TImg> implements IImgService {
  private static final Logger log = LoggerFactory.getLogger(ImgServiceImpl.class);

}
