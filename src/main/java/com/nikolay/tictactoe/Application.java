package com.nikolay.tictactoe;

import com.nikolay.tictactoe.model.Player;
import com.nikolay.tictactoe.repository.PlayerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  The entry point of the application.
 *  It runns with the help of sprint-boot
 */

    @SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner init_users(PlayerRepository playerRepository){
        return (args) -> {
            playerRepository.save(new Player("niki","niki@niki.com",
                new BCryptPasswordEncoder().encode("niki")));
            playerRepository.save(new Player("alex","alex@alex.com",
                new BCryptPasswordEncoder().encode("alex")));
            playerRepository.save(new Player("benedict","benedict@benedict.com",
                new BCryptPasswordEncoder().encode("bededict")));
            playerRepository.save(new Player("milan","milan@milan.com",
                new BCryptPasswordEncoder().encode("milan")));
            playerRepository.save(new Player("peter","peter@peter.com",
                new BCryptPasswordEncoder().encode("peter")));
            playerRepository.save(new Player("viki","viki@viki.com",
                new BCryptPasswordEncoder().encode("viki")));
            playerRepository.save(new Player("dani","dani@dani.com",
                new BCryptPasswordEncoder().encode("dani")));
            playerRepository.save(new Player("vasko","vasko@vasko.com",
                new BCryptPasswordEncoder().encode("vasko")));
        };
    }
}