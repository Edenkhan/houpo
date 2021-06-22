package com.ghost.test;

import com.youruan.dentistry.console.ConsoleApplication;
import com.youruan.dentistry.console.base.utils.JwtTokenUtils;
import com.youruan.dentistry.core.platform.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class)
public class TestToken {

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Test
    public void test1() {
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setUsername("admin");
        String token = jwtTokenUtil.generateToken(employee);
        System.out.println("token = "+token);
        String username = jwtTokenUtil.getUserNameFromToken(token);
        System.out.println("username = "+username);
    }

}
