package com.sttx.bookmanager.service;

import java.util.List;

import com.sttx.bookmanager.po.Book;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.util.pages.PagedResult;

public interface IUserService {
    /**
     * 
     * @Title: selectByPrimaryKey
     * @Description: 根据id查找用户详细信息
     * @param userId
     * @return
     * @return: User
     */
    User selectByPrimaryKey(String userId);

    /**
     * 
     * @Title: deleteByPrimaryKey
     * @Description: 逻辑删除用户
     * @param userId
     * @return
     * @return: int
     */
    int deleteByPrimaryKey(String userId);

    /**
     * 
     * @Title: findUserBookListByUserId
     * @Description: 根据id查找用户图书
     * @param userId
     * @return
     * @return: List<Book>
     */
    List<Book> findUserBookListByUserId(String userId);

    /**
     * ajax判断邮箱是否存在
     * 
     * @param email
     * @return
     */
    public boolean existUserEmail(String userEmail);

    /**
     * 用户注册
     */
    int insertSelective(User record);

    /**
     * ajax判断用户名是否存在
     * 
     * @param loginName
     * @return
     */
    boolean existUserName(String loginName);

    /**
     * @Title: activeUserStatus
     * @Description: 用户激活
     * @param userStatus
     *            用户状态
     * @param userCode
     *            用户激活码
     * @return
     * @return: Integer
     */
    Integer activeUserStatus(Integer userStatus, String userCode);

    /**
     * 
     * @Title: userLoginSubmit
     * @Description: 用户登录
     * @param loginName
     *            可以是用户名，邮箱，手机号
     * @return
     * @return: User
     */
    User userLoginSubmit(String loginName, String userPwd);

    /**
     * 管理员根据条件查找人员
     * 
     * @param user
     * @param pageNo
     * @param pageSize
     * @return
     */
    PagedResult<User> selectUserPages(User user, Integer pageNo, Integer pageSize);

    /**
     * 分配管理员权限
     * 
     * @param userId
     * @return
     */
    int updatePermission(String userId);

    /**
     * 修改密码
     * 
     * @param user
     * @return
     */
    int updatePwd(User user);

    public String chatWithRobot(String worlds);
}
