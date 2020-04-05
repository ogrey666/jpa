package com.example.jpa.demo;

import com.example.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // uzycie transakcji dla metody
    @Transactional
    // kontener nie widzi adnotacji z niepublicznych metod! wiedz metody zarzadzane udostepniane innym klasom, dajemy PUBLIC!
    public void updateUser(Integer status, String name) {
        userRepository.updateUserSetStatusForNameNative(status, name);
    }

    void someMethod() {
        updateUser(666, "Jon");
    }
}
