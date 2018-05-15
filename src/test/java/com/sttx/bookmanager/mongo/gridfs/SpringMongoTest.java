package com.sttx.bookmanager.mongo.gridfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sttx.bookmanager.po.GridfsImg;
import com.sttx.bookmanager.service.IBaseMongoRepository;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
  "classpath:spring/applicationContext-service.xml", "classpath:spring/applicationContext-transation.xml"})
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest
public class SpringMongoTest {
  private static final Logger logger = LoggerFactory.getLogger(SpringMongoTest.class);
  @Resource
  private MongoTemplate mongoTemplate;
  @Resource
  private IBaseMongoRepository baseMongoRepository;
  
  @Test
  public void save() throws Exception {
    logger.info("================");
    DB db = mongoTemplate.getDb();
    // 存储fs的根节点
    GridFS gridFS = new GridFS(db, "fs");
    GridFSInputFile gfs = gridFS.createFile(new File("E:\\job\\photo\\33.jpg"));
    gfs.put("aliases", "face++");
    gfs.put("filename", "33.jpg");
    gfs.put("contentType", "jpg");
    gfs.save();
    Object id = gfs.getId();
    System.out.println(JSONObject.toJSONString(id));
  }

  @Test
  public void find() throws Exception {
    DB db = mongoTemplate.getDb();
    // 获取fs的根节点
    GridFS gridFS = new GridFS(db, "fs");
    String fileName = "873_B.jpg";
    GridFSDBFile dbfile = gridFS.findOne(fileName);
    long writeTo = dbfile.writeTo(new FileOutputStream("E:\\chenchaoyun\\Desktop\\test.jpg"));
  }
  @Test
  public void saveService() throws Exception {
    GridfsImg gridfsImg=new GridfsImg();
    InputStream in= new FileInputStream(new File("E:\\job\\photo\\face.jpg"));
    gridfsImg.setIn(in);
    gridfsImg.setAliases("tttt");
    gridfsImg.setFileName("face.jpg");
    String url = baseMongoRepository.saveImg(gridfsImg);
    logger.info("===================url:{}",url);
  }
}
