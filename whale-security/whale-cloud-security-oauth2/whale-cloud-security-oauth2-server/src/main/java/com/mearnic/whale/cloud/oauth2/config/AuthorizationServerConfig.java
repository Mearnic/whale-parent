//package com.mearnic.whale.cloud.oauth2.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//import javax.annotation.Resource;
//
//
//@Configuration
//@EnableAuthorizationServer//开启认证服务器功能
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Resource
//    private PasswordEncoder passwordEncoder;
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    /**
//     * 配置被允许访问此认证服务器的客户端详情信息
//     * 方式1：内存方式管理
//     * 方式2：数据库管理
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        // 使用内存方式
//        clients.inMemory()
//                // 客户端id
//                .withClient("wj-pc")
//                // 客户端密码，要加密,不然一直要求登录
//                .secret(passwordEncoder.encode("wj-secret"))
//                // 资源id, 如商品资源
//                .resourceIds("product-server")
//                // 授权类型, 可同时支持多种授权类型
//                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
//                // 授权范围标识，哪部分资源可访问（all是标识，不是代表所有）
//                .scopes("all")
//                // false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码，
//                .autoApprove(true)
//                .redirectUris("http://www.baidu.com/");// 客户端回调地址
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.checkTokenAccess("isAuthenticated()");
//    }
//
//    /**
//     * 重写父类的方法
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        //密码模式需要设置此认证管理器
//        endpoints.authenticationManager(authenticationManager);
//        // 刷新令牌获取新令牌时需要
//        endpoints.userDetailsService(userDetailsService);
//        endpoints.pathMapping("/oauth/confirm_access","/confirm_access");
//    }
//}
