package com.sttx.bookmanager.nfsimg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.slf4j.Logger;

import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.ddp.logger.DdpLoggerFactory;
import com.sun.xfile.XFileOutputStream;

/**
 * 
 * @Description 共享文件路径测试
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年6月22日 下午5:03:47
 */
public class TestNFSFile {
    private static Logger log = DdpLoggerFactory.getLogger(TestNFSFile.class);
    private static String nfsUrl = null;
    static {
        nfsUrl = PropertiesUtil.getFilePath("properties/nfs.properties", "nfsUrl");
    }
    @Test
    public void read() {
        /**
         * read
         */
        log.info("nfsUrl:{}", nfsUrl);
        String nfsFileName = nfsUrl + "test.txt";
        String localFileName = "H:\\test.txt";
        NfsFileUtils.copyNfsFile2Local(nfsFileName, localFileName);
    }

    @Test
    public void readImg() throws UserException {
        /**
         * read
         */
        log.info("nfsUrl:{}", nfsUrl);
        String nfsFileName = nfsUrl + "ad.jpg";
        byte[] bs = NfsFileUtils.readNfsFile2Byte(nfsFileName);
        log.info("bs length:{}", bs.length);
        String imageBase64Str = NfsFileUtils.getImageBase64Str(bs);
        log.info("imageBase64Str:{}", imageBase64Str);
    }

    @Test
    public void writeImg() throws Exception {
        InputStream input = new FileInputStream(new File("H:\\ad.jpg"));
        boolean b = NfsFileUtils.mkdirFile("nfs://39.108.0.229:/u01/upload/bookImg/chenchaoyun/2017-08-26/564810052569910/1/");
        log.info("b:{}", b);
        int i = NfsFileUtils.uploadFile(input, new XFileOutputStream(
                "nfs://39.108.0.229:/u01/upload/bookImg/chenchaoyun/2017-08-26/564810052569910/1/564810052569910-defaultBookImg--1.jpg"));
        log.info("i:{}", i);
    }

}
