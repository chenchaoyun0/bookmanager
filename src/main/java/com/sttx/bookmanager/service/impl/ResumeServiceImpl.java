package com.sttx.bookmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sttx.bookmanager.po.ResumeVo;
import com.sttx.bookmanager.service.IResumeService;
import com.sttx.bookmanager.util.ResumeUtils;

@Service("resumeService")
public class ResumeServiceImpl implements IResumeService {
  private static final Logger log = LoggerFactory.getLogger(ResumeServiceImpl.class);

    @Override
    public ResumeVo findResumeVo() {
        ResumeVo resumeVo = null;
        try {
            resumeVo = ResumeUtils.getResumeVo();
            if (resumeVo.getImageVo() == null) {
                ResumeUtils.initResumeVo();
            }
        } catch (Exception e) {
            return new ResumeVo();
        }
        return resumeVo;
    }

}
