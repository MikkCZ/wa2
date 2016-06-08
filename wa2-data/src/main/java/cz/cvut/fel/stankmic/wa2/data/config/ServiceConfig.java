package cz.cvut.fel.stankmic.wa2.data.config;

import cz.cvut.fel.stankmic.wa2.data.dao.*;
import cz.cvut.fel.stankmic.wa2.data.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
class ServiceConfig {

    @Bean
    public ActorDao actorDao() {
        return new ActorDao();
    }

    @Bean
    public ActorService actorService() {
        return new ActorService(actorDao(), theatreDao());
    }

    @Bean
    public ActorRatingDao actorRatingDao() {
        return new ActorRatingDao();
    }

    @Bean
    public ActorRatingService actorRatingService() {
        return new ActorRatingService(actorRatingDao(), userDao(), actorDao());
    }

    @Bean
    public AsyncResultDao asyncResultDao() {
        return new AsyncResultDao();
    }

    @Bean
    public AsyncResultService asyncResultService() {
        return new AsyncResultService(asyncResultDao());
    }

    @Bean
    public PlayDao playDao() {
        return new PlayDao();
    }

    @Bean
    public StagingDao stagingDao() {
        return new StagingDao();
    }

    @Bean
    public TheatreDao theatreDao() {
        return new TheatreDao();
    }

    @Bean
    public TheatreService theatreService() {
        return new TheatreService(theatreDao());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserService userService() {
        return new UserService(userDao(), passwordEncoder());
    }

}
