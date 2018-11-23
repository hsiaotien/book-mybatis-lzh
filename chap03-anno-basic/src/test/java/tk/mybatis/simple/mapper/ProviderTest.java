package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

public class ProviderTest extends BaseMapperTest {

	@Test
	public void test() {
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取接口
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			// 调用方法
			SysPrivilege sysPrivilege = privilegeMapper.selectById(1L);
			//
			Assert.assertNotNull(sysPrivilege);
			//
			Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void test02() {
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取接口
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			// 调用方法
			SysPrivilege sysPrivilege = privilegeMapper.selectById02(1L);
			//
			Assert.assertNotNull(sysPrivilege);
			//
			Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
		} finally {
			sqlSession.close();
		}
	}
}
