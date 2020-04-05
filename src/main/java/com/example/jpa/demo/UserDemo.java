package com.example.jpa.demo;

import com.example.jpa.domain.User;
import com.example.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class UserDemo {

    // Logowanie (ogarnac, zeby uzywac Lomboka - adnotacja)
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final UserService userService;
    private final TransactionTemplate transactionTemplate;

    // domniema posiadanie @Autowired, bo jest w klasie @Configuration
    public UserDemo(UserService userService, TransactionTemplate transactionTemplate) {
        this.userService = userService;
        this.transactionTemplate = transactionTemplate;
    }

    @Bean
    public CommandLineRunner userDemoBean(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("jon", 10, 1, "jon@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("piotr", 12, 3, "piotr@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("henryk", 14, 1, "henryk@email.com", LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("zbyszek", 3, 2, "zbyszek@email.com", LocalDateTime.now().minusDays(ThreadLocalRandom.current().nextLong(10))));
            userRepository.save(new User("seba", 22, 1, "seba@email.com", LocalDateTime.now().plusDays(ThreadLocalRandom.current().nextLong(10))));
//            log.info("All users {}", userRepository.findAll()); // logging + placeholder replacement
            // logowanie per wiersz (ladne formatowanie)
            userRepository.findAll().forEach(user -> log.info("User {}", user));
            log.info("All active users native{}", userRepository.findAllActiveUsersUsingNativeQuery());
            log.info("All active users {}", userRepository.findAllActiveUsersUsingJpql());
            log.info("All users by status {}", userRepository.findUserByStatus(3));
            log.info("All users by status and name named params active {}", userRepository.findUserByStatusAdNameNamedParamsNative(1, "seba"));
            // po dodaniu serwisu mozemy juz tym zarzadzac ladnie
            userService.updateUser(666, "jon");
//            userService.someMethod();
            /*
//            drugi sposob zarzadzaniem transakcja - przez TransactionTemplate
            transactionTemplate.execute(transactionStatus -> {
               transactionStatus.
            });

            */
            log.info("{}", userRepository.findDates(LocalDateTime.now().minusDays(15), LocalDateTime.now()));
            userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
            userRepository.findAll(PageRequest.of(1,10,Sort.by("name")));
        };
    }
}
