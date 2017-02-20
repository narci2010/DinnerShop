package com.dinner.respository;

import com.dinner.model.business.User;
import com.dinner.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Tomek on 29-Jan-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIntegrationTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAllUsersTest(){
        List<User> users = userRepository.findAll();

        System.out.println(users.get(0).getFirstName());
        System.out.println(users.get(0).getLastName());
        System.out.println(users.get(0).getAccount().getMoney());
        System.out.println(users.get(0).getEmail());

        Assert.assertTrue(users.size()>0);
    }
    @Test
    public void findByEmailTest(){
        User user = userRepository.findByEmail("to@zie.pl");
        Assert.assertTrue(user.getEmail().equals("to@zie.pl"));
    }
}
