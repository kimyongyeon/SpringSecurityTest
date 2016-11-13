package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Created by yongyeon on 2016-08-07.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    /**
     * 인메모리 방식
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 인메모리 사용해서 처리하는 부분
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//
//        auth.inMemoryAuthentication().withUser("kim").password("1234").authorities("ROLE_USER");
        // 데이터베이스 이용해서 처리하는 부분
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true from tb_user where username = ?")
            .authoritiesByUsernameQuery("select username, 'ROLE_USER' from tb_user where username = ?");
    }
}
