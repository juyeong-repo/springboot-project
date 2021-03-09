package com.security.bit.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http   
            .authorizeRequests()
            	// 페이지 권한 설정
                .antMatchers("/","/main","/member/new").permitAll()
             // 이렇게 한 세트여서 자주 윗줄처럼 써줌
                .antMatchers("/admin/**").hasRole("ADMIN")  
                .antMatchers("/member/myinfo").hasRole("MEMBER")
                //.anyRequest().authenticated() 
                .antMatchers("/**").permitAll() //밑처럼 명령을 주기 위해
                //어드민과 멤버를 제외한 곳에 모두 허용하는 것.
            .and()
            	.formLogin() // 로그인 설정
	            	.loginPage("/member/login")
	                .defaultSuccessUrl("/main")  // .defaultSuccessUrl("/member/login/result")
	                .failureUrl("/loginfail")
	                .permitAll() //위의 3개는 아무나 접근할 수 있어야 하므로 퍼밋올.
            .and()
	            .logout()// 로그아웃 설정
	                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
	                //리퀘스트를 AntPathRequestMatcher로 쓸 수도 있다. 
	                .logoutSuccessUrl("/member/logout/result")
	                .invalidateHttpSession(true) //세션만료
        	.and()
            	.exceptionHandling().accessDeniedPage("/member/denied");
        //accessDeniedPage는 스프링 기본 제공 
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        // static 디렉터리의 하위 파일 목록은 인증 무시 
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
}
