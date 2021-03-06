package com.sttx.bookmanager.util.tts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class XunfeiLib {
  private static Map<String, Boolean> vioceFile = new HashMap<String, Boolean>();

  /**
   * 设置生成文件队列
   * 
   * @param name
   * @param have
   */
  public static void setVioce(String name, Boolean have) {
    XunfeiLib.vioceFile.put(name, have);
  }

  /**
   * 查看文件是否在队列中
   * 
   * @param name
   * @return
   */
  public static Boolean checkDone(String name) {
    Boolean don = XunfeiLib.vioceFile.get(name);
    if (don == null) {
      return false;
    }
    return don;
  }

  /**
   * 清除队列中的信息
   * 
   * @param name
   */
  public static void delDone(String name) {
    XunfeiLib.vioceFile.remove(name);
  }

  /**
   * 返回合成监视器
   * 
   * @return
   */

  /**
   * 获取文件名
   */
  public static String getFileName(String name) {
    // 获取文件名
    String fileName="/u01/app/bookmanager/"+name;
    return fileName;
  }

  /**
   * @param fileLeng 转换文件长度
   * @param srate 采样率 - 8000,16000等
   * @param channel 通道数量 - 单声道= 1，立体声= 2等。
   * @param format 每个样本的位数（这里是16）
   * @throws IOException
   */

  public static byte[] getWAVHeader(long fileLeng, int srate, int channel, int format) {

    byte[] header = new byte[44];
    long totalDataLen = fileLeng + 36;
    long bitrate = srate * channel * format;

    header[0] = 'R';
    header[1] = 'I';
    header[2] = 'F';
    header[3] = 'F';
    header[4] = (byte)(totalDataLen & 0xff);
    header[5] = (byte)((totalDataLen >> 8) & 0xff);
    header[6] = (byte)((totalDataLen >> 16) & 0xff);
    header[7] = (byte)((totalDataLen >> 24) & 0xff);
    header[8] = 'W';
    header[9] = 'A';
    header[10] = 'V';
    header[11] = 'E';
    header[12] = 'f';
    header[13] = 'm';
    header[14] = 't';
    header[15] = ' ';
    header[16] = (byte)format;
    header[17] = 0;
    header[18] = 0;
    header[19] = 0;
    header[20] = 1;
    header[21] = 0;
    header[22] = (byte)channel;
    header[23] = 0;
    header[24] = (byte)(srate & 0xff);
    header[25] = (byte)((srate >> 8) & 0xff);
    header[26] = (byte)((srate >> 16) & 0xff);
    header[27] = (byte)((srate >> 24) & 0xff);
    header[28] = (byte)((bitrate / 8) & 0xff);
    header[29] = (byte)(((bitrate / 8) >> 8) & 0xff);
    header[30] = (byte)(((bitrate / 8) >> 16) & 0xff);
    header[31] = (byte)(((bitrate / 8) >> 24) & 0xff);
    header[32] = (byte)((channel * format) / 8);
    header[33] = 0;
    header[34] = 16;
    header[35] = 0;
    header[36] = 'd';
    header[37] = 'a';
    header[38] = 't';
    header[39] = 'a';
    header[40] = (byte)(fileLeng & 0xff);
    header[41] = (byte)((fileLeng >> 8) & 0xff);
    header[42] = (byte)((fileLeng >> 16) & 0xff);
    header[43] = (byte)((fileLeng >> 24) & 0xff);

    return header;
  }
}
