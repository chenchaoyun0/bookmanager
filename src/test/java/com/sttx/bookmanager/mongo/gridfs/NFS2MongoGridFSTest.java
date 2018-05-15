package com.sttx.bookmanager.mongo.gridfs;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.sttx.bookmanager.dao.EBookMapper;
import com.sttx.bookmanager.dao.UserMapper;
import com.sttx.bookmanager.po.EBook;
import com.sttx.bookmanager.po.GridfsImg;
import com.sttx.bookmanager.po.TImg;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IBaseMongoRepository;
import com.sttx.bookmanager.service.IImgService;
import com.sttx.bookmanager.util.file.NfsFileUtils;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml", "classpath:spring/applicationContext-service.xml",
    "classpath:spring/applicationContext-transation.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest
public class NFS2MongoGridFSTest {
  private static final Logger logger = LoggerFactory.getLogger(NFS2MongoGridFSTest.class);
  @Resource
  private MongoTemplate mongoTemplate;
  @Resource
  private IBaseMongoRepository baseMongoRepository;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private IImgService imgService;
  @Autowired
  private EBookMapper ebookMapper;

  @Test
  public void updateBook() throws Exception {

    List<TImg> imgList = imgService.selectList(new TImg());
    logger.info("============imgList:{}", JSONObject.toJSONString(imgList));
    for (TImg tImg : imgList) {
      String imgPath = tImg.getImgPath();
      InputStream inputStream = NfsFileUtils.readNfsFile2Stream(NfsFileUtils.getNfsUrl() + imgPath);
      GridfsImg gridfsImg = new GridfsImg();
      gridfsImg.setIn(inputStream);
      gridfsImg.setAliases(tImg.getImgId());
      String fileName = StringUtils.substringAfterLast(imgPath, "/");
      gridfsImg.setFileName(fileName);
      //
      String url = baseMongoRepository.saveImg(gridfsImg);
      tImg.setImgPath(url);
      imgService.updateByPrimaryKeySelective(tImg);
    }

  }

  @Test
  public void updateUser() throws Exception {
    List<User> list = userMapper.selectUserPages(new User());
    for (User user : list) {
      String userHead = user.getUserHead();
      InputStream inputStream = NfsFileUtils.readNfsFile2Stream(NfsFileUtils.getNfsUrl() + userHead);
      GridfsImg gridfsImg = new GridfsImg();
      gridfsImg.setIn(inputStream);
      gridfsImg.setAliases(user.getUserId());
      String fileName = StringUtils.substringAfterLast(userHead, "/");
      gridfsImg.setFileName(fileName);
      //
      String url = baseMongoRepository.saveImg(gridfsImg);
      user.setUserHead(url);
      userMapper.updateByPrimaryKeySelective(user);
    }
  }

  @Test
  public void updateEBook() throws Exception {
    List<EBook> selectEBookPages = ebookMapper.selectEBookPages(new EBook());
    for (EBook eBook : selectEBookPages) {
      String imgPath = eBook.getEbookPath();
      InputStream inputStream = null;
      try {
        inputStream = NfsFileUtils.readNfsFile2Stream(NfsFileUtils.getNfsUrl() + imgPath);
      } catch (Exception e) {
        continue;
      }
      GridfsImg gridfsImg = new GridfsImg();
      gridfsImg.setIn(inputStream);
      gridfsImg.setAliases(eBook.getEbookId());
      String fileName = StringUtils.substringAfterLast(imgPath, "/");
      gridfsImg.setFileName(fileName);
      //
      String url = baseMongoRepository.saveImg(gridfsImg);
      eBook.setEbookPath(url);
      ebookMapper.updateByPrimaryKeySelective(eBook);
    }
  }
}
