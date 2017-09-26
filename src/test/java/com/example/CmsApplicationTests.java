package com.example;

import com.example.dao.mapper.UserMapper;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsApplicationTests {

    @Test
    public void HeloWorld(){
        System.out.println("HelloWorld");
    }
//	private UserMapper userMapper;
//	@Autowired
//	public void setUserMapper(UserMapper userMapper) {
//		this.userMapper = userMapper;
//	}
//
//	@Test
//	public void contextLoads() {
//		User user=new User(null,"admin","123456","8825305769@qq.com","17862901361");
//		userMapper.save(user);
//	}

}
