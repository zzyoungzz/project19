package filesource.project16;

import filesource.project16.repository.UserRepository;
import filesource.project16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity  //모든 요청URL이 스프링 시큐리티의 제어를 받도록함.    기본 스프링 필터체인에 등록
@Configuration
public class SecurityConfig {

//    @Autowired
//    private CorsConfig corsConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        sharedObject.userDetailsService(userDetailsService);
        AuthenticationManager authenticationManager = sharedObject.build();

        http.authenticationManager(authenticationManager);



        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//                .csrf(AbstractHttpConfigurer::disable)


//                .addFilter(corsConfig.corsFilter())     // cors 허용 설정
//
//                .addFilter(new JwtAuthenticationFilter(authenticationManager))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))



                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/**").permitAll()     //비로그인 접근가능페이지
//                        .c("/", "/login", "/join").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")        //허용조건
                                .anyRequest().authenticated()       //이상제외는 로그인필수
                )

                .formLogin(form -> form
                        .loginPage("/login")       	//자동적으로 로그인페이지로감. 로그인페이지로 취급될주소
                        .defaultSuccessUrl("/main", true)   //로그인 성공시 루트 url
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
//                .logout((logout) -> logout.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }   //AuthenticationManager는 스프링 시큐리티의 인증을 담당. 사용자 인증시 앞에서 작성한 UserSecurityService와 PasswordEncoder를 사용








//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();

//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        ProviderManager authenticationManager = (ProviderManager)authenticationConfiguration.getAuthenticationManager();
//        return authenticationManager;
//

//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        ProviderManager authenticationManager = (ProviderManager)authenticationConfiguration.getAuthenticationManager();
//        return authenticationManager;
//

//    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();

//    }


}
