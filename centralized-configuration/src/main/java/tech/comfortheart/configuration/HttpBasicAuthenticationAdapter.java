package tech.comfortheart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class HttpBasicAuthenticationAdapter extends WebSecurityConfigurerAdapter {
    @Value("${access.username}")
    private String username;
    @Value("${access.password}")
    private String password;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(username)
                .password("{noop}" + password).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/encrypt").permitAll()
                .anyRequest().hasRole("USER");
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {return username;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {return password;}
}
