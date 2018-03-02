package com.sttx.bookmanager.cas;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.sttx.bookmanager.po.User;
import com.sttx.bookmanager.service.IUserService;

/**
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 * 
 * @author ChengLi
 * 
 */
public class CustomUserDetailsService
        implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token)
            throws UsernameNotFoundException {
        String name = token.getName();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>当前的用户名是：{}" , name);
        /* 这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息 */
        User userInfo = userService.userLoginSubmit("chenchaoyun", "111111");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest req = sra.getRequest();
        req.getSession().setAttribute("userLogin", userInfo);
//        Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
//        AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
//        authorities.add(authorityInfo);
//        userInfo.setAuthorities(authorities);
        return userInfo;
    }

}
