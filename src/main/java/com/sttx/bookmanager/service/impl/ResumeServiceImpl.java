package com.sttx.bookmanager.service.impl;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.po.ResumeVo;
import com.sttx.bookmanager.service.IResumeService;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Service("resumeService")
public class ResumeServiceImpl implements IResumeService {
    private static final Logger logger = DdpLoggerFactory.getLogger(ResumeServiceImpl.class);

    @Override
    public ResumeVo findResumeVo() {
        // TODO Auto-generated method stub
        return null;
    }

}
