package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import tacos.repository.UserRepository;
import tacos.service.UserRepositoryUserDetailsService;

import javax.sql.DataSource;

/**
 * Created by StuGud on 2020/8/26.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //1.基于内存
    //@Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication()
    //            .withUser("buzz").password("infinity").authorities("ROLE_USER")
    //            .and()
    //            .withUser("woody").password("bullseye").authorities("ROLE_USER");
    //}

    //2.基于JDBC
    //可以使用usersByUsernameQuery();authoritiesByUsernameQuery()自定义用户详情查询
    //final
    //DataSource dataSource;
    //
    //public SecurityConfig(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}
    //
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //
    //    auth.jdbcAuthentication().dataSource(dataSource);
    //}

    //3.基于LDAP

    //4.自定义
    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("userRepositoryUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/","/**").permitAll();
    }
}
