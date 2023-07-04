package com.changgou.oauth.config;

import com.changgou.oauth.util.UserJwt;
import com.health.items.feign.UserFeign;
import com.health.items.pojo.Role;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserFeign userFeign;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /***
         * 客户端信息认证
         */
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if (authentication == null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if (clientDetails != null) {
                //秘钥
                String clientSecret = clientDetails.getClientSecret();

                /* 静态方式*/
                //return new User(username, // 客户端 ID
                // new BCryptPasswordEncoder().encode(clientSecret), // 客户端密钥
                // AuthorityUtils.commaSeparatedStringToAuthorityList(""));

                //数据库查找方式
                return new User(username, // 客户端 ID
                        clientSecret, // 客户端密钥
                        AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }

        /***
         * 用户账号密码信息认证
         */
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        //静态加载

        /*
        String pwd = new BCryptPasswordEncoder().encode("itheima");
        String permissions = "goods_list,seckill_list";*/


        // 从数据库加载用户信息
        Result<com.health.items.pojo.User> userResult = userFeign.findByUsername(username);

        if (userResult == null || userResult.getData() == null) {
            return null;
        }

        // 获取用户名对应的密码
        String pwd = userResult.getData().getPassword();

        // String pwd = new BCryptPasswordEncoder().encode(password);
        // 指定用户权限
        String permissions = "";
        for (Role role : userResult.getData().getRoles()) {
            permissions +=  role.getKeyword();
        }

        UserJwt userDetails = new UserJwt(username, pwd, AuthorityUtils
                .commaSeparatedStringToAuthorityList(permissions));

        return userDetails;
    }

}
