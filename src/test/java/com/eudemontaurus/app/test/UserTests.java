package com.eudemontaurus.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.eudemon.taurus.app.SbjtApplication;
import com.eudemon.taurus.app.entities.User;
import com.eudemon.taurus.app.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SbjtApplication.class)
public class UserTests {
	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private UserService sv;
	
	@Test
	public void test() throws Exception {
		User user = this.sv.getUser(34);
		assertThat(user.getName()).isEqualTo("ppp");
		
		stringRedisTemplate.opsForValue().set("ggg", "vvv");
		
		redisTemplate.opsForValue().set(user.getId() + "", user);
		
		assertThat(redisTemplate.opsForValue().get("34").getName()).isEqualTo("ppp");
	}
}
