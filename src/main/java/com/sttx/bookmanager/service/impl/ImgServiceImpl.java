package com.sttx.bookmanager.service.impl;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Service("imgService")
public class ImgServiceImpl extends BaseServiceImpl<TImg> implements IImgService {
    private static Logger log = DdpLoggerFactory.getLogger(ImgServiceImpl.class);

}
