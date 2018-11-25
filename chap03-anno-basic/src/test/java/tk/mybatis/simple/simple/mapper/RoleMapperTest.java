package tk.mybatis.simple.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.simple.model.SysRole;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest{

	/**
	 * 注解@select
	 * 手动指定sql别名完成自动映射
	 */
	@Test
	public void TestSelectById() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = roleMapper.selectById(1L);
		System.out.println("sysRole = " + sysRole);
	}

	/**
	 * 注解@select
	 * 由mybatis-config配置文件中的mapUnderscoreToCamelCase配置，完成自动映射
	 */
	@Test
	public void selectById02() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = roleMapper.selectById02(2L);
		System.out.println("sysRole = " + sysRole);
	}

	@Test
	public void selectById03() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = roleMapper.selectById03(1L);
		System.out.println("sysRole = " + sysRole);
	}


	@Test
	public void selectById04() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = roleMapper.selectById04(2L);
		System.out.println("sysRole = " + sysRole);
	}


	@Test
	public void selectAll01() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		List<SysRole> sysRoles = roleMapper.selectAll01();
		sysRoles.forEach(System.out::println);
	}

	// standard test 注解@select
	@Test
	public void testPlus() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> sysRoles = roleMapper.selectAll01();
			//结果不为空
			Assert.assertNotNull(sysRoles);
			// 角色数量大于0个
			Assert.assertTrue(sysRoles.size() > 0 );
			// 验证下划线字段是否映射成功
			Assert.assertNotNull(sysRoles.get(0).getRoleName());
		} finally {
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}


	@Test
	public void insert() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("superMan");
		sysRole.setEnabled(1);
		sysRole.setCreateBy("1");
		sysRole.setCreateTime(new Date());
		//
		int rows = roleMapper.insert(sysRole);
		//
		Assert.assertEquals(1,rows);
		//
		Assert.assertNull(sysRole.getId());
		// 避免脏数据，回滚,不提交
		sqlSession.rollback();
	}

	@Test
	public void insert2() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("superStar");
		sysRole.setEnabled(1);
		sysRole.setCreateBy("2");
		sysRole.setCreateTime(new Date());
		try {
			//
			int rows = roleMapper.insert2(sysRole);
			//
			Assert.assertEquals(1,rows);
			// 返回自增主键，打断点对比查看
			Assert.assertNotNull(sysRole.getId());
			// 避免脏数据，回滚,不提交
			sqlSession.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}


	@Test
	public void insert3() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = new SysRole();
		sysRole.setRoleName("superStar");
		sysRole.setEnabled(1);
		sysRole.setCreateBy("2");
		sysRole.setCreateTime(new Date());
		try {
			//
			int rows = roleMapper.insert3(sysRole);
			//
			Assert.assertEquals(1,rows);
			// 返回主键，打断点对比查看
			Assert.assertNotNull(sysRole.getId());
			// 避免脏数据，回滚,不提交
			sqlSession.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}


	@Test
	public void updateById() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole sysRole = new SysRole();
		sysRole.setId(1L);
		sysRole.setRoleName("superStar");
		sysRole.setEnabled(1);
		sysRole.setCreateBy("2");
		sysRole.setCreateTime(new Date());
		try {
			//
			int rows = roleMapper.updateById(sysRole);
			//
			Assert.assertEquals(1,rows);
			//
			Assert.assertEquals("2",roleMapper.selectById04(1L).getCreateBy());
			//
			sqlSession.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void deleteById() {
		SqlSession sqlSession = getSqlSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		try {
			int rows = roleMapper.deleteById(1L);
			//
			Assert.assertEquals(1,rows);
			//
			Assert.assertNull(roleMapper.selectById04(1L));
			//
			sqlSession.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}