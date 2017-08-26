package com.sttx.bookmanager.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;

import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.properties.PropertiesUtil;
import com.sttx.ddp.logger.DdpLoggerFactory;
import com.sun.xfile.XFile;
import com.sun.xfile.XFileInputStream;
import com.sun.xfile.XFileOutputStream;

import sun.misc.BASE64Encoder;

/**
 * 
 * @Description 操作nfs文件
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年6月23日 上午10:12:37
 */
public class NfsFileUtils {
    private static Logger log = DdpLoggerFactory.getLogger(NfsFileUtils.class);
    private static String jspImgSrc = "data:image/jpg;base64,";
    private static String nfsUrl = null;
    private static String[] imgTypes = null;
    static {
        nfsUrl = PropertiesUtil.getFilePath("properties/nfs.properties", "nfsUrl");
        imgTypes = new String[] { "jpg", "png", "jpeg", "gif", "bmp", "jpe", "tif", "tiff" };
    }

    public static String[] getImgTypes() {
        return imgTypes;
    }

    public static String getJspImgSrc() {
        return jspImgSrc;
    }

    public static String getNfsUrl() {
        return nfsUrl;
    }

    /**
     * 上传文件到NFS
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径名,如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @param localFileName
     *            本地文件绝对路径,如:/u01/app/ccbcusr/test/ad.jpg
     * @return 文件大小
     * @throws UserException
     */
    public static int uploadFile(String nfsFileName, String localFileName) throws UserException {
        int start = 0;
        mkdirFile(nfsFileName);
        try {
            File file = new File(localFileName);
            if (!file.exists()) {
                throw new UserException("USPS0104", "本地文件不存在");
            }
            FileInputStream in = FileUtils.openInputStream(file);
            XFileOutputStream out = new XFileOutputStream(nfsFileName);
            start = uploadFile(in, out);
        } catch (Exception e) {
            log.error("上传文件至NFS异常:{}", e);
            throw new UserException("上传文件至NFS异常", e);
        }
        return start;
    }

    /**
     * 上传文件到NFS
     * 
     * @Description
     * @param in
     *            输入流
     * @param out
     *            输出流
     * @return 文件大小
     * @throws UserException
     */
    public static int uploadFile(InputStream in, XFileOutputStream out) throws UserException {
        int start = 0;
        try {
            byte[] fileByte = IOUtils.toByteArray(in);
            int bufSize = 1024;
            int left = fileByte.length;
            while (left > 0) {
                int count = left > bufSize ? bufSize : left;
                out.write(fileByte, start, count);
                out.flush();
                start += count;
                left -= count;
            }
            out.close();

        } catch (Exception e) {
            log.error("上传文件至NFS异常:{}", e);
            throw new UserException("上传文件至NFS异常", e);
        }
        return start;
    }

    /**
     * 复制nfs文件到本地
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @param localFileName
     *            本地文件绝对路径 如:/u01/app/ccbcusr/test/ad.jpg
     * @return 文件大小
     * @throws UserException
     */
    public static int copyNfsFile2Local(String nfsFileName, String localFileName) throws UserException {
        int start = 0;
        try {
            InputStream in = readNfsFile2Stream(nfsFileName);
            //out
            XFileOutputStream out = new XFileOutputStream(localFileName);
            //copy
            start = IOUtils.copy(in, out);
        } catch (Exception e) {
            log.error("复制nfs文件到本地", e);
            throw new UserException("复制nfs文件到本地", e);
        }
        return start;
    }

    /**
     * 读取nfs文件 进流
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return
     * @throws UserException
     */
    public static InputStream readNfsFile2Stream(String nfsFileName) throws UserException {
        if (!existsNfsFile(nfsFileName)) {
            log.error("读取nfs文件进流:{}", "文件不存在");
            throw new UserException("USPS0104", "文件不存在");
        }
        InputStream in = null;
        //stream
        try {
            in = new XFileInputStream(nfsFileName);
        } catch (Exception e) {
            log.error("读取nfs文件 进流异常", e);
            throw new UserException("USPS0104", "读取nfs文件 进流异常");
        }
        return in;
    }

    /**
     * 读取文件字节
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return 字节
     * @throws UserException
     */
    public static byte[] readNfsFile2Byte(String nfsFileName) throws UserException {
        InputStream in = readNfsFile2Stream(nfsFileName);
        byte[] byteArray = null;
        try {
            byteArray = IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("读取nfs文件为字节异常", e);
            throw new UserException("USPS0104", "读取nfs文件为字节异常");
        }
        return byteArray;
    }

    /**
     * 读取文件字节
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return 字节
     * @throws UserException
     */
    public static byte[] readNfsStream2Byte(InputStream in) throws UserException {
        byte[] byteArray = null;
        try {
            byteArray = IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("读取nfs文件为字节异常", e);
            throw new UserException("USPS0104", "读取nfs文件为字节异常");
        }
        return byteArray;
    }

    /**
     * 删除nfs文件
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 如:nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return true-成功删除，false-失败
     * @throws UserException
     */
    public static boolean deleteNfsFile(String nfsFileName) throws UserException {

        if (!existsNfsFile(nfsFileName)) {
            log.error("删除nfs文件异常:{}", "文件不存在");
            throw new UserException("USPS0104", "文件不存在");
        }
        return new XFile(nfsFileName).delete();
    }

    /**
     * nfs mkdir
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return true-存在，false-不存在
     */
    public static boolean mkdirFile(String nfsFileName) {
        String dirName = nfsFileName.substring(0, nfsFileName.lastIndexOf("/"));
        log.info("mkdir创建文件夹begin...dirName:{}", dirName);
        XFile xFile = new XFile(dirName);
        boolean mkdirs = xFile.mkdirs();
        log.info("mkdir创建文件夹end...mkdirs:{}", mkdirs);
        return mkdirs;
    }

    /**
     * nfs 文件是否存在
     * 
     * @Description
     * @param nfsFileName
     *            远程文件绝对路径 nfs://192.168.1.xxx:/u01/app/image/ad/ad.jpg
     * @return true-存在，false-不存在
     */
    public static boolean existsNfsFile(String nfsFileName) {
        return new XFile(nfsFileName).exists();
    }

    /**
     * 将图片读取为base64 字符串
     * 
     * @Description
     * @param nfsFileName
     * @return
     * @throws UserException
     */
    @SuppressWarnings("restriction")
    public static String getImageBase64Str(String nfsFileName) throws UserException {
        byte[] b = readNfsFile2Byte(nfsFileName);
        return jspImgSrc + new BASE64Encoder().encode(b);
    }

    /**
     * 将图片读取为base64 字符串
     * 
     * @Description
     * @param nfsFileName
     * @return
     * @throws UserException
     */
    @SuppressWarnings("restriction")
    public static String getImageBase64Str(InputStream in) throws UserException {
        byte[] b = readNfsStream2Byte(in);
        return jspImgSrc + new BASE64Encoder().encode(b);
    }

    /**
     * 将图片读取为base64 字符串
     * 
     * @Description
     * @param nfsFileName
     * @return
     * @throws UserException
     */
    @SuppressWarnings("restriction")
    public static String getImageBase64Str(byte[] b) throws UserException {
        return jspImgSrc + new BASE64Encoder().encode(b);
    }

    public static String getImgIfNullReturnDefault(String imgPath) {
        String[] imgType = new String[] { ".jpg", ".png", ".JPG", ".PNG", "JEPG" };
        String imageBase64Str = null;
        if (!ArrayUtils.contains(imgType, imgPath) || !existsNfsFile(imgPath)) {
            InputStream inputStream = NfsFileUtils.class.getClassLoader().getResourceAsStream("defaultBookImg.jpg");
            imageBase64Str = getImageBase64Str(inputStream);
        } else {
            imageBase64Str = getImageBase64Str(imgPath);
        }
        return imageBase64Str;
    }

    public static List<String> getImageBase64StrList(List<TImg> imgList) {
        List<String> base64StrList = new ArrayList<>();
        for (TImg tImg : imgList) {
            String imgPath = tImg.getImgPath();
            String nfsImgPath=nfsUrl+imgPath;
            log.info("NFS服务器图片路径:{}", nfsImgPath);
            String imageBase64Str = getImageBase64Str(nfsImgPath);
            base64StrList.add(imageBase64Str);
        }
        return base64StrList;
    }

    public static boolean isImgFile(String fileName){
        if(ArrayUtils.contains(imgTypes, fileName.toLowerCase())){
            return true;
        }
        return false;
    }
}
