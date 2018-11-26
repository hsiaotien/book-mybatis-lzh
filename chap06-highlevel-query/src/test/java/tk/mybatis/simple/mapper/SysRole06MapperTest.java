package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser06;

public class SysRole06MapperTest extends BaseMapper06Test{

	@Test
	public void selectOneToOne() {
		// 获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取mapper接口
			SysRole06Mapper roleMapper = sqlSession.getMapper(SysRole06Mapper.class);
			// 特别注意，在测试数据中，id = 1L 的用户有两个角色，这里是假设用户和角色是一对一的关系的！
			// 使用id = 1001L模拟一对一
			SysUser06 user = roleMapper.selectOneToOne(1001L);
			//
			assert user != null;
			//
			Assert.assertNotNull(user.getSysRole06());
			//
			System.out.println("此用户的角色名 : " + user.getSysRole06().getRoleName());
		} finally {
			sqlSession.close();
		}
	}
}