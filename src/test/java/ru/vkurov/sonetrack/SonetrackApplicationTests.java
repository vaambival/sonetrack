package ru.vkurov.sonetrack;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vkurov.sonetrack.data.entity.RoleEntity;
import ru.vkurov.sonetrack.data.entity.UserEntity;
import ru.vkurov.sonetrack.data.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SonetrackApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void RoleDao() {
		List<RoleEntity> userList = userRepository.findUserRoles("Fedor");
		System.out.println(userList);

		List<UserEntity> allUsers = userRepository.findAll();
		System.out.println(allUsers);
	}

}
