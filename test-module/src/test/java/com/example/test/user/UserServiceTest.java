package com.example.test.user;

import com.example.common.entity.User;
import com.example.test.config.TestConfig;
import com.example.user.UserServiceApplication;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserServiceApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createAndRetrieveUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());

        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(retrievedUser);
        assertEquals("Test User", retrievedUser.getName());
        assertEquals("test@example.com", retrievedUser.getEmail());
    }
}