package com.sttx.bookmanager.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sttx.bookmanager.util.exception.UserException;

/**
 * java通过模拟post方式提交表单实现图片上传功能实例
 */
public class HttpUploadFile {
  private static final Logger log = LoggerFactory.getLogger(HttpUploadFile.class);

    /**
     * 上传图片
     * 
     * @param urlStr
     *            faceId通讯地址
     * @param textMap
     *            文本请求参数
     * @param fileMap
     *            文件请求参数
     * @param contentType
     *            文件类型 没有传入文件类型默认采用application/octet-stream
     *            contentType非空采用filename匹配默认的图片类型
     * @param byteFile
     *            二进制文件
     * @return
     * @throws UserException
     */
    // 方法说明：1.建立连接2.上传文本textMap（姓名、身份证号等）3.上传文件fileMap（照片等）
    @SuppressWarnings("rawtypes")
    public String formUpload(String urlStr, Map<String, String> textMap, Map<String, Object> fileMap,
            String contentType, byte[] byteFile) throws UserException {
        String res = "";
        HttpURLConnection conn = null;
        // boundary就是request头和上传文件内容的分隔符
        String BOUNDARY = "---------------------------123821742118716";
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {//如果textMap不为空，上传文本textMap（姓名、身份证号等）
                StringBuffer strBuf = new StringBuffer();
                Iterator iter = textMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }
            // file
            if (fileMap != null) {//如果fileMap不为空，上传文件fileMap（照片等）
                Iterator iter = fileMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String inputName = (String) entry.getKey();
                    File file = (File) entry.getValue();
                    if (file == null) {
                        continue;
                    }
                    String filename = file.getName();
                    // 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                    contentType = new MimetypesFileTypeMap().getContentType(file);
                    // contentType非空采用filename匹配默认的图片类型
                    if (!"".equals(contentType)) {//判断文件类型
                        if (filename.endsWith(".png")) {
                            contentType = "image/png";
                        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
                                || filename.endsWith(".jpe")) {
                            contentType = "image/jpeg";
                        } else if (filename.endsWith(".gif")) {
                            contentType = "image/gif";
                        } else if (filename.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {// 没有传入文件类型
                        contentType = "application/octet-stream";
                    }
                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                    out.write(strBuf.toString().getBytes());
                    out.write(byteFile);
                }
            }
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // 读取返回数据
                StringBuffer strBuf = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    strBuf.append(line).append("\n");
                }
                res = strBuf.toString();
                reader.close();
                reader = null;
            } else {
                StringBuffer error = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line1 = null;
                while ((line1 = bufferedReader.readLine()) != null) {
                    error.append(line1).append("\n");
                }
                res = error.toString();
                bufferedReader.close();
                bufferedReader = null;
            }
        } catch (Exception e) {
            log.error("USPB1050", e);
            throw new UserException("USPB1050", e);// 发送POST请求失败
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }
}
