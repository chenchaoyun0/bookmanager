package com.sttx.bookmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sttx.bookmanager.dao.UserMapper;
import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IUserService;
import com.sttx.bookmanager.util.exception.UserException;
import com.sttx.bookmanager.util.file.NfsFileUtils;
import com.sttx.bookmanager.util.pages.BeanUtil;
import com.sttx.bookmanager.util.pages.PagedResult;
import com.sttx.bookmanager.util.passwd.SHA;
import com.sttx.bookmanager.util.tuling.Aes;
import com.sttx.bookmanager.util.tuling.Md5;
import com.sttx.bookmanager.util.tuling.PostServer;
import com.sttx.ddp.logger.DdpLoggerFactory;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    private static Logger log = DdpLoggerFactory.getLogger(UserServiceImpl.class);
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public int deleteByPrimaryKey(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    public List<Book> findUserBookListByUserId(String userId) {
        return userMapper.findUserBookListByUserId(userId);
    }

    public boolean existUserEmail(String userEmail) {
        if (userMapper.existUserEmail(userEmail) > 0) {

            return true;
        }
        return false;
    }

    public int insertSelective(User record) {
        try {
            return userMapper.insertSelective(record);
        } catch (Exception e) {
            throw new UserException("注册失败");
        }
    }

    public boolean existUserName(String loginName) {
        if (userMapper.existUserName(loginName) > 0) {
            return true;
        }
        return false;
    }

    public Integer activeUserStatus(Integer userStatus, String userCode) {

        User user = userMapper.findByCode(userCode);
        if (user == null) {
            throw new UserException("您的操作不当，激活码无效！3秒后跳转到主页。如若您的操作不当行为超过3次，您将无法访问本网站!谢谢合作");
        }
        if (user.getUserStatus() == 1) {
            throw new UserException("您已经成功激活，不可再次点击。3秒后跳转到登录页面。");
        }
        return userMapper.activeUserStatus(userStatus, userCode);
    }

    public User userLoginSubmit(String loginName, String userPwd) {

        User user = userMapper.userLoginSubmit(loginName);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        if (!user.getUserPwd().equals(SHA.getSHA256(userPwd))) {
            throw new UserException("密码有误");
        }
        if (user.getUserStatus() == 0) {
            throw new UserException(user.getLoginName() + ",您尚未激活，请前往邮箱" + user.getUserEmail() + "激活");
        }

        String userHead = user.getUserHead();
        String imageBase64Str = NfsFileUtils.getImageBase64Str(NfsFileUtils.getNfsUrl() + userHead);
        user.setUserHead(imageBase64Str);
        return user;
    }

    public PagedResult<User> selectUserPages(User user, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? (Integer) 1 : pageNo;
        pageSize = pageSize == null ? (Integer) 8 : pageSize;
        PageHelper.startPage(pageNo, pageSize);// 告诉插件开始分页

        List<User> list = userMapper.selectUserPages(user);

        log.info("list:{}", JSONObject.toJSON(list));
        List<User> arrayList = new ArrayList<User>();
        for (User user2 : list) {
            String userHead = user2.getUserHead();
            String imageBase64Str = NfsFileUtils.getImageBase64Str(NfsFileUtils.getNfsUrl() + userHead);
            user2.setUserHead(imageBase64Str);
            arrayList.add(user2);
        }

        PagedResult<User> bookPagedResult = BeanUtil.toPagedResult(list);

        return bookPagedResult;
    }

    public int updatePermission(String userId) {
        return userMapper.updatePermission(userId);
    }

    public int updatePwd(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public String chatWithRobot(String worlds) {
        // 图灵网站上的secret
        String secret = "6010dd4e5210b874";
        // 图灵网站上的apiKey
        String apiKey = "cd6707a4c47d43dcb8d73fb7f4ca57bd";
        // 待加密的json数据
        String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + worlds + "\"}";
        // 获取时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 生成密钥
        String keyParam = secret + timestamp + apiKey;
        String key = Md5.MD5(keyParam);

        // 加密
        Aes mc = new Aes(key);
        data = mc.encrypt(data);

        // 封装请求参数
        JSONObject json = new JSONObject();
        json.put("key", apiKey);
        json.put("timestamp", timestamp);
        json.put("data", data);
        // 请求图灵api
        String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
        return result;
    }

}
