package com.mycpu.myselfbatis;

import com.mycpu.entity.UserEntiry;
import com.mycpu.mappers.UserMapper;
import com.mycpu.session.SqlSession;
import com.mycpu.session.SqlSessionFactory;
import com.mycpu.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class MyselfbatisApplicationTests {

	@Test
	void contextLoads() throws Exception {

		SqlSessionFactory build =
				new SqlSessionFactoryBuilder().build("my-config.properties");

		SqlSession sqlSession = build.openSqlSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(">>>>> 第一次查询");
		UserEntiry userEntiry = mapper.selectUser(2L);
		System.out.println("userEntiry1 :" + userEntiry);

		System.out.println(">>>>> 第一次查询");
		UserEntiry userEntiry2 = mapper.selectUser(1L);
		System.out.println("userEntiry2 :" + userEntiry2);

	}

}
