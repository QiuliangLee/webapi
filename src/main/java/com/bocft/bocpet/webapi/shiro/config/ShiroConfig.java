package com.bocft.bocpet.webapi.shiro.config;

import com.bocft.bocpet.webapi.shiro.custom.CustomAuthenticationFilter;
import com.bocft.bocpet.webapi.shiro.custom.CustomModularRealmAuthenticator;
import com.bocft.bocpet.webapi.shiro.realm.PhoneRealm;
import com.bocft.bocpet.webapi.shiro.realm.UsernamePasswordRealm;
import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("customWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //登陆接口可以匿名访问
        filterChainMap.put("/auth/**", "anon");
        filterChainMap.put("/user/updatePwd", "anon");
        filterChainMap.put("/captcha/get", "anon");
        filterChainMap.put("/sms/**", "anon");
        /*
         * 使用URL配置filter鉴权时，如果用户没有所需权限，异常拦截器会拦截不到(需要重写AuthorizationFilter）
         * 而使用注解配置@RequireRoles()不存在这个问题，建议用注解配置
         * filterChainMap.put("/test2","roles[123]");
         */
        filterChainMap.put("/**", "authc");
        filterMap.put("authc", new CustomAuthenticationFilter());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "usernamePasswordRealm")
    public Realm usernamePasswordRealm(@Qualifier("customHashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        UsernamePasswordRealm usernamePasswordRealm = new UsernamePasswordRealm();
        usernamePasswordRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return usernamePasswordRealm;
    }

    @Bean(name = "phoneRealm")
    public Realm phoneRealm() {
        return new PhoneRealm();
    }

    @Bean(name = "customSessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(180000 * 1000L);
        return sessionManager;
    }

    /**
     * 认证器  用于多Realm认证
     */
    @Bean(name = "customAuthenticator")
    public AbstractAuthenticator abstractAuthenticator(@Qualifier("usernamePasswordRealm") Realm usernamePasswordRealm,
                                                       @Qualifier("phoneRealm") Realm phoneRealm) {
        // 自定义模块化认证器，用于解决多realm抛出异常问题
        ModularRealmAuthenticator authenticator = new CustomModularRealmAuthenticator();
//        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        // 认证策略：AtLeastOneSuccessfulStrategy(默认)，AllSuccessfulStrategy，FirstSuccessfulStrategy
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        // 加入realms
        List<Realm> realms = new ArrayList<>(2);
        realms.add(usernamePasswordRealm);
        realms.add(phoneRealm);
        authenticator.setRealms(realms);
        return authenticator;
    }

    @Bean(name = "customWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("usernamePasswordRealm") Realm usernamePasswordRealm,
                                                               @Qualifier("phoneRealm") Realm phoneRealm,
                                                               @Qualifier("customAuthenticator") AbstractAuthenticator customAuthenticator,
                                                               @Qualifier("customSessionManager") DefaultWebSessionManager defaultWebSessionManager
    ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> realmList = new ArrayList<>(2);
        realmList.add(usernamePasswordRealm);
        realmList.add(phoneRealm);
        securityManager.setRealms(realmList);
        securityManager.setSessionManager(defaultWebSessionManager);
        securityManager.setAuthenticator(customAuthenticator);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean(name = "customHashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //密码加密方式，选择SHA256
        hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        //加密次数,迭代1024次
        hashedCredentialsMatcher.setHashIterations(1024);
        //存储散列后的密码是否为16进制
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }

    /**
     * 解决spring aop和注解配置一起使用的bug。如果您在使用shiro注解配置的同时，引入了spring aop的starter，
     * 会有一个奇怪的问题，导致shiro注解的请求，不能被映射，需加入这个配置
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
