package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = this.getSqlSession();
		try {
			// 获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用selectById方法，查询id=1的用户
			SysUser user = userMapper.selectById(1L);
			// user不能为空
			Assert.assertNotNull(user);
			// userName = admin
			Assert.assertEquals("admin",user.getUserName());
		} finally {
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = this.getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> sysUsers = userMapper.selectAll();
			// 结果不为空
			Assert.assertNotNull(sysUsers);
			// 用户数量大于0个
			Assert.assertTrue(sysUsers.size()>0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll02() {
		SqlSession sqlSession = this.getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser[] sysUsers = userMapper.selectAll02();
			// 结果不为空
			Assert.assertNotNull(sysUsers);
			// 用户数量大于0个
			Assert.assertTrue(sysUsers.length > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = this.getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> sysRoles = userMapper.selectRolesByUserId(1L);
			// 结果不为空
			Assert.assertNotNull(sysRoles);
			// 用户数量大于0个
			Assert.assertTrue(sysRoles.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesAndUserInfoByUserId() {
		SqlSession sqlSession = this.getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> sysRoles = userMapper.selectRolesAndUserInfoByUserId(1L);
			// 结果不为空
			Assert.assertNotNull(sysRoles);
			// 用户数量大于0个
			Assert.assertTrue(sysRoles.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	//=========================insert用法=============================//
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个User对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			// 正常情况下应该读入一张图片存到byte[]数组中
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			// 将新建的对象插入数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = userMapper.insert00(user);
			// 只插入一条数据
			Assert.assertEquals(1,result);
			// id 为null ,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			// 为了不影响其他测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行commit 也不会提交到数据库
			sqlSession.rollback();
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	// 返回自增主键的值,方式一
	@Test
	public void testInsert02() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个User对象
			SysUser user = new SysUser();
			user.setUserName("test2");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			// 正常情况下应该读入一张图片存到byte[]数组中
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			// 将新建的对象插入数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = userMapper.insert02(user);
			// 只插入一条数据
			Assert.assertEquals(1,result);
			// id 为null ,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			// 为了不影响其他测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行commit 也不会提交到数据库
			sqlSession.rollback();
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	// 返回自增主键的值,方式二
	@Test
	public void testInsert03() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个User对象
			SysUser user = new SysUser();
			user.setUserName("test2");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			// 正常情况下应该读入一张图片存到byte[]数组中
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			// 将新建的对象插入数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = userMapper.insert03(user);
			// 只插入一条数据
			Assert.assertEquals(1,result);
			// id 为null ,没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			// 为了不影响其他测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行commit 也不会提交到数据库
			sqlSession.rollback();
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	//================update用法========================//
	@Test
	public void testUptateById00(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 从数据库中查找一个user对象
			SysUser user = userMapper.selectById(1L);
			// 当前userName为admin
			Assert.assertEquals("admin",user.getUserName());
			// 修改用户名
			user.setUserName("admin_test");
			// 更新数据，特别注意，这里的返回值result是执行sql影响的行数
			int result = userMapper.updateById00(user);
			// 只更新一条数据
			Assert.assertEquals(1,result);
			// 根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			// 修改后的名字是admin_test
			Assert.assertEquals("admin_test",user.getUserName());
		} finally {
			// 为了不影响其他测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行commit 也不会提交到数据库
			sqlSession.rollback();
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testDeleteById00(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 从数据库中查询一个user对象，根据id=1查询
			SysUser user1 = userMapper.selectById(1L);
			// 现在还能查询出user对象
			Assert.assertNotNull(user1);
			// 调用方法删除
			Assert.assertEquals(1,userMapper.deleteById00(1L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1L));

			// 使用SysUser参数再进行一次测试，根据id=1001查询
			SysUser user2 = userMapper.selectById(1001L);
			// 现在还能查询出user对象
			Assert.assertNotNull(user2);
			// 调用方法删除，注意这里使用的是参数user2
			Assert.assertEquals(1,userMapper.deleteBySysUser(user2));
			// 再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1001L));
		} finally {
			// 为了不影响其他测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession() 是不自动提交的
			// 因此不手动执行commit 也不会提交到数据库
			sqlSession.rollback();
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用方法查询
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled00(1L, 1);
			// 结果不能为空
			Assert.assertNotNull(userList);
			// 角色数量大于0个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserIdAndRoleEnabled02() {
		SqlSession sqlSession = getSqlSession();
		SysUser user = new SysUser();
		user.setId(1L);
		SysRole role = new SysRole();
		role.setEnabled(1);
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用方法查询
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled01(user, role);
			// 结果不能为空
			Assert.assertNotNull(userList);
			// 角色数量大于0个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			// 不要忘记关闭sqlSession
			sqlSession.close();
		}
	}
}
