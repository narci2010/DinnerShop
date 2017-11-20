package com.dinner.respository;

import com.dinner.model.domain.user.User;
import com.dinner.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAllUsersTest(){
        List<User> users = userRepository.findAll();
        Assert.assertTrue(users.size()>0);
    }
    @Test
    public void findByEmailTest(){
        Optional<User> userOptional = userRepository.findByEmail("t@z.pl");
        User user = userOptional.get();
        Assert.assertTrue(user.getUsername().equals("t@z.pl"));
    }
}
