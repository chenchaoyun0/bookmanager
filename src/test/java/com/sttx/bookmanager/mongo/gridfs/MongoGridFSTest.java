package com.sttx.bookmanager.mongo.gridfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoGridFSTest {
  static GridFS gridFS = null;
  static Mongo client=null;
  static {
    /* 
     * 1、创建数据库连接 
     */
    client = new Mongo("ccy004", 27017);
    // 取得数据库对象
    DB db = client.getDB("bookmanager");
    String collectionName = "fs";
    // 创建数据库对象中GridFS集合
    gridFS = new GridFS(db, collectionName);
  }

  @Test
  public void save() throws Exception {
    /* 
     * 2、上传文件 
     */
    // 创建测试文件，mongo 默认存在该文件
    InputStream inputStream = IOUtils.toInputStream("‪E:\\job\\photo\\33.jpg");
    // 创建gridFS文件数据流
    GridFSInputFile createFile = gridFS.createFile(inputStream);
    // 设置文件属性
    createFile.put("filename", "33.jpg");
    createFile.save();
    Object id = createFile.getId();
    System.out.println(id);
    GridFSDBFile findOne = gridFS.findOne(new BasicDBObject("_id", id));
    findOne.writeTo( new FileOutputStream("E:\\chenchaoyun\\Desktop\\test.jpg"));
    System.out.print(findOne);
  }

  @Test
  public void find() {
    /* 
     * 3、根据id查询上传文件 
     */
    String id = "5afa59e14dc15823906b16f1";
    GridFSDBFile findOne = gridFS.findOne(new BasicDBObject("_id", id));
    System.out.print(findOne);
  }
  @Test
  public void findList(){
    /* 
     * 4、查询所有文件列表 
     * DBCursor 数据库游标 
     */  
    DBCursor fileList=gridFS.getFileList();  
    while(fileList.hasNext())  
    {  
        System.out.println(fileList.next());  
    } 
  }
  
  @Test
  public void delete(){
    /* 
     *5、 删除文件 
     */  
    String id = "5afa59e14dc15823906b16f1";
    gridFS.remove(new  BasicDBObject("_id",id));  
    client.close(); 
  }
  
  @Test
  public void read() throws Exception{
    File file = new File("E:\\job\\photo\\15.jpg");
    System.out.println(file.exists());
  }
  
}
