package project.projectucvwebsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import project.projectucvwebsystem.security.filter.JWTAuthenticationFilter;
import project.projectucvwebsystem.util.Permission;
import project.projectucvwebsystem.util.Role;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {
    
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
        .sessionManagement(ssmngConfig -> ssmngConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(builderRequests());

        return httpSecurity.build();
        
    }

    /* My "endpoints" */
    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> builderRequests() {
        return authConfig -> {
            /*
             * Static
             */
            authConfig.requestMatchers(HttpMethod.GET, "/static/bootstrap.min.css").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/static/css/id.css").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/static/js/request.js").permitAll();
            //authConfig.requestMatchers(HttpMethod.GET, "/static/js/tab.jsx").permitAll();
            //authConfig.requestMatchers(HttpMethod.GET, "/static/js/components/graphic.jsx").permitAll();
            //authConfig.requestMatchers(HttpMethod.GET, "/static/js/highcharts.js").permitAll();
            //authConfig.requestMatchers(HttpMethod.GET, "/static/js/jquery.js").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/static/js/graphic.jsx").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/static/js/render.jsx").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/static/js/components/form.jsx").permitAll();
            
            authConfig.requestMatchers(HttpMethod.GET, "/global/welcome").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/restricted/login-view").permitAll();

            authConfig.requestMatchers(HttpMethod.POST, "/restricted/login-view/login").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/restricted/control-panel").hasAnyRole(
                Role.ADMIN.name(),Role.WAREHOUSE_MANAGER.name(),Role.VENDEDOR.name()
            );
            authConfig.requestMatchers(HttpMethod.POST, "/restricted/control-panel/logout").hasAnyRole(
                Role.ADMIN.name(),Role.WAREHOUSE_MANAGER.name(),Role.VENDEDOR.name()
            );
            authConfig.requestMatchers(HttpMethod.POST, "/restricted/control-panel/delete-supplier").hasAuthority(
                Permission.DELETE_SUPPLIER.name()
            );
            authConfig.requestMatchers(HttpMethod.POST, "/restricted/control-panel/update-supplier").hasAuthority(
                Permission.MODIFY_SUPPLIER.name()
            );

            authConfig.requestMatchers("/error").permitAll();
            authConfig.anyRequest().denyAll();
        };
    }

}
