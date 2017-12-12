*********************图书城-项目简介*******************************

修订于2016年7月15日

1 前期需求
公司内部图书管理，员工私有或公司公有书籍管理，电子书籍、资料共享。

2 主要软件版本
mysql-5.1.28
spring-3.2.0.RELEASE
mybatis-3.2.7
druid-1.0.23
log4j-1.2.17
junit-4.11
dubbo-2.5.3

3 项目实体类
User-用户
Book-图书
EBook-电子书
OrderItem-借书单条目
Address-地址

4 功能概述(括号后为对应dao层接口名称)

用户功能
1、  用户注册[insertSelective]----SUCCESS
    （1） Ajax验证邮箱与用户名不可重，表单格式验证[existUserEmail，existUserName] --SUCCESS
    （2） 注册即跳转邮箱激活，字段status为1[findByCode, activeUserStatus] --SUCCESS
    （3） 密码加密采用SHA

2、  用户登录[userLoginSubmit] --SUCCESS
    （1） 凭借用户名/手机号/邮箱均可登录--SUCCESS
    （2） 未激活用户不可登陆--SUCCESS
    
3、  用户个人中心
    （1） 更新个人信息，如：头像，密码，联系方式等
    （2） 我借出的、未归还、已归还、未借出的图书列表分页
    （3） 列表上的CURD 

4、  实物书籍管理
    （1） 上架实物书籍[insertSelective] --SUCCESS
    （2） 所有实物书籍的列表分页[selectBookPages] --SUCCESS
    （3） 实物书列表的多条件查询[selectBookPages] --SUCCESS
    （4） 实物书籍详情页面[selectBookDetailMes]
    （5） 用户个人书籍的信息变更[updateByPrimaryKeySelective]    [**]
    
5、  电子书与资料管理
    （1） 上传电子书籍或文档[insertSelective] --SUCCESS
    （2） 所有电子书籍的列表分页[selectEBookPages] --SUCCESS
    （3） 电子书多条件查询[selectEBookPages] [yxy]
    （4） 电子书或文档的在线预览与下载，目前支持pdf，word，excel三种格式--SUCCESS
    （5） 电子书下载量统计[updateByPrimaryKeySelective]

6、  订单操作[wyl]
    （1） 用户选择书籍加入借书单,此时并向管理员发送邮件提示审核[insertSelective]- [wyl] --SUCCESS
    （2） 管理员选择同意借出或者拒绝[updateByPrimaryKeySelective] --SUCCESS
    （3） 用户申请还书，等待管理员确认[updateByPrimaryKeySelective] --SUCCESS
    （4） 管理员确认收到图书，流程结束[updateByPrimaryKeySelective]
    item_Status解释：[selectOrderItemPages] 
    显示已借书籍，显示0 1 2 -3 的值 即不是 -1 3的值
    0：用户向管理员发出借书请求，请求中, 等待书主人确认
    -1：管理员拒绝请求，请求失败
    1：管理员同意请求，请求成功，书籍未归还
    2：借用方申请还书请求，请求中，等待书主人确认
    3：管理员确认收到图书，书籍归还成功
    -3：管理员未收到图书，书籍归还失败
    (0, 1, 2 ,- 3) 查看列表的时候




管理员功能
7、  后台管理(暂不做)
  Ps：原本计划普通用户上传的图书信息需要管理员审核才可，为了便于测试。这个功能留在最后做，在时间足够的情况下，采用spring security做权限管理。
    （1） 用户上传书籍包括电子书的将发送邮件通知管理员
    （2） 管理员登录邮箱点击链接可查看图书详情，并决定内否入库
    （3） 管理员分配普通用户权限，ps：角色尚未拟定
    （4） 管理员界面所有待入库图书列表分页
8、  管理员功能[wyl]
    （1） 审核普通用户借书的请求，决定接受或拒绝[insertSelective] [**]
    （2） 管理员操作所有普通用户列表[selectUserPages]
    （3） 管理员可查看-所有正在外借书籍，已借出书籍，未归还书籍，已归还书籍，用户借书申请列表，用户申请还书列表。[selectBookPages] [**]
    （4） 公司公有图书的CURD，book_type：0-为用户图书，1-为公司图书[insertSelective]
    （5） 分配普通用户管理员角色[updateByPrimaryKeySelective]
