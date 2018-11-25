package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser04;

import java.util.*;

public class UserMapper04Test extends BaseMapper04Test {

	// where 冗余1==1 最大限度降低拼接错误
	@Test
	public void selectByUser04() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			// only select by userName
			SysUser04 record = new SysUser04();
			record.setUserName("ad");
			List<SysUser04> listSysUser04 = userMapper04.selectByUser04(record);
			assert listSysUser04.size() > 0;
			// only select by userEmail
			SysUser04 record02 = new SysUser04();
			record02.setUserEmail("test@mybatis.tk");
			List<SysUser04> sysUser04List = userMapper04.selectByUser04(record02);
			assert sysUser04List.size() > 0;

			// both by userName and userEmail
			SysUser04 record03 = new SysUser04();
			record03.setUserName("ad");
			record03.setUserEmail("test@mybatis.tk");
			List<SysUser04> sysUser04s = userMapper04.selectByUser04(record03);
			assert sysUser04s.size() == 0;
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}


	@Test
	public void updateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			SysUser04 recordForUpdate = new SysUser04();
			//更新id为1 的用户
			recordForUpdate.setId(1L);
			//修改邮箱
			recordForUpdate.setUserEmail("mytest@mybatis.tk");
			int rows = userMapper04.updateByIdSelective(recordForUpdate);
			//
			assert 1==rows;
			//
			SysUser04 recordForSelect = new SysUser04();
			recordForSelect.setId(1L);
			List<SysUser04> sysUser04s = userMapper04.selectByUser04(recordForSelect);
			SysUser04 sysUser04 = sysUser04s.get(0);
			//
			Assert.assertEquals("admin",sysUser04.getUserName());
			Assert.assertEquals("mytest@mybatis.tk",sysUser04.getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 sysUser04 = new SysUser04();
			sysUser04.setUserName("test-selective");
			sysUser04.setUserPassword("123");
			sysUser04.setUserInfo("test info");
			sysUser04.setCreateTime(new Date());
			//
			int rows = userMapper04.insert(sysUser04);
			assert rows == 1;
			// 设置了回选主键
			assert sysUser04.getId() != null;
			// 这里复用之前的查找语句。 用selectById更合适
			SysUser04 record = new SysUser04();
			record.setUserEmail("test@mybatis.tk");
			List<SysUser04> list = userMapper04.selectByUser04(record);
			//
			Assert.assertEquals("test@mybatis.tk",list.get(0).getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testChoose() {
		SqlSession sqlSession = getSqlSession();
		try {
			//断点查看sql
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			// 只查询id
			SysUser04 query = new SysUser04();
			query.setId(1L);
			SysUser04 user01 = userMapper04.selectByIdOrUserName(query);
			assert user01 != null;
			// 只查询userName
			query.setId(null);
			query.setUserName("admin");
			SysUser04 user02 = userMapper04.selectByIdOrUserName(query);
			Assert.assertNotNull(user02);
			// 两者都传，就只根据id查（查看sql）
			query.setId(1001L);
			query.setUserName("admin");
			SysUser04 user03 = userMapper04.selectByIdOrUserName(query);
			assert user03 != null;
			// 两者都不不传
			query.setId(null);
			query.setUserName(null);
			SysUser04 user04 = userMapper04.selectByIdOrUserName(query);
			assert user04 == null;
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testWhere() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 record = new SysUser04();
			record.setUserEmail("test@mybatis.tk");
			SysUser04 user = userMapper04.selectByUserWithWhere(record);
			assert user != null;
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectUserByIdList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			List<Long> idList = Arrays.asList(1L, 1001L);
			//
			List<SysUser04> list = userMapper04.selectUserByIdList(idList);
			assert list.size() > 0;
			assert list.size() == 2;
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectUserByIdArr() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			Long[] arr = {1L, 2L};
			//
			List<SysUser04> list = userMapper04.selectUserByIdArr(arr);
			assert list.size() > 0;
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectUserByIdListWithAnno() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			List<Long> isList = Arrays.asList(1L, 1001L);
			//
			List<SysUser04> list = userMapper04.selectUserByIdListWithAnno(isList);
			//
			assert list.size() == 2;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 补充：
	 * mybatis如何接受参数的？
	 * 1. 只有一个数组参数或者集合参数， 根据DefaultSqlSession源码，可以看出用array 或者 list即可接收
	 * 2. 有多个参数，会有默认的1,2 或者param1 param2. 建议用@Param注解，为每一个参数指定一个名字
	 * 3. 参数是map类型。  类似第二种方式，直接指定为map中的key即可，或者使用默认值_parameter，推荐使用@Param注解指定名字
	 * 4. 参数是一个对象的，直接指定为对象的属性名即可，当对象内多层嵌套对象时，可以使用属性.属性（数组和集合可以用下标取值[num])进行指定深层的属性值
	 */

	/**
	 * foreach 完成批量导入. 查看console的sql拼接情况，可以看出，mybatis的重要一环就是拼接sql。强大之处在于它提供了动态的sql。
	 */
	@Test
	public void insertList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			List<SysUser04> userList = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				SysUser04 sysUser04 = new SysUser04();
				sysUser04.setUserName("test"+i);
				sysUser04.setUserPassword("123");
				sysUser04.setUserEmail("test@mybatis.tk");
				userList.add(sysUser04);
			}
			// 对新建的对象批量插入
			int rows = userMapper04.insertList(userList);
			//
			assert rows == 2;
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void insertListWithKeyCallback() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			List<SysUser04> userList = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				SysUser04 sysUser04 = new SysUser04();
				sysUser04.setUserName("test"+i+"plus");
				sysUser04.setUserPassword("123");
				sysUser04.setUserEmail("test@mybatis.tk");
				userList.add(sysUser04);
			}
			// 对新建的对象批量插入
			int rows = userMapper04.insertListWithKeyCallback(userList);
			//
			assert rows == 3;
			// 批量插入，主键也可以回写（功能由lzh提交）
			for (SysUser04 sysUser04 : userList) {
				System.out.println("sysUser04.getId() = " + sysUser04.getId());
			}
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	/**
	 * foreach实现动态update更新指定列名的值
	 */
	@Test
	public void updateByMap() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			HashMap<String, Object> map = new HashMap<>();
			//查询条件，同样是要更新的字段
			map.put("id",1L);
			// 要动态更新的其他字段以及值
			map.put("user_email","test@mybatis.tk");
			map.put("user_password","456");
			//
			int rows = userMapper04.updateByMap(map);
			//
			assert rows == 1;
			// 这里复用存在的方法，应该用selectById更合适
			List<SysUser04> list = userMapper04.selectUserByIdArr(1L);
			String userEmail = list.get(0).getUserEmail();
			assert "test@mybatis.tk".equals(userEmail);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void selectByBind() {

		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 sysUser04 = userMapper04.selectByBind("ad");
			assert "admin".equals(sysUser04.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectByDataBaseProvider() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 record = new SysUser04();
			record.setUserName("ad");
			//
			SysUser04 sysUser04 = userMapper04.selectByDataBaseProvider(record);
			assert "admin".equals(sysUser04.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectByDataBaseProvider02() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 record = new SysUser04();
			record.setUserName("ad");
			//
			SysUser04 sysUser04 = userMapper04.selectByDataBaseProvider02(record);
			//
			assert "admin".equals(sysUser04.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void selectByOGNL() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper04 userMapper04 = sqlSession.getMapper(UserMapper04.class);
			//
			SysUser04 user04 = userMapper04.selectByOGNL("ad");
			//
			assert "admin".equals(user04.getUserName());
		} finally {
			sqlSession.close();
		}
	}


}