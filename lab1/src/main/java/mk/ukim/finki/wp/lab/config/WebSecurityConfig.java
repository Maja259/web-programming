package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{
    private final PasswordEncoder passwordEncoder;


    public WebSecurityConfig(PasswordEncoder PasswordEncoder) {
        this.passwordEncoder = PasswordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests((requests) ->requests.requestMatchers("/eventBooking","/events","/register","/","/assets/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).formLogin((form) ->form .loginPage("/login").permitAll().failureUrl("/login?error=BadCredentials").defaultSuccessUrl("/events",true))
                .logout((logout) ->logout.logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login"))
                .exceptionHandling((exception) -> exception.accessDeniedPage("/accessDenied"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1= User.builder().username("maja-tankoska").password(passwordEncoder.encode("maja123")).roles("USER").build();
        UserDetails user2=User.builder().username("anastasija-slavkova").password(passwordEncoder.encode("anastasija123")).roles("USER").build();
        UserDetails user3=User.builder().username("marko-spasov").password(passwordEncoder.encode("marko123")).roles("USER").build();
        UserDetails admin=User.builder().username("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2, user3,admin);
    }
}
