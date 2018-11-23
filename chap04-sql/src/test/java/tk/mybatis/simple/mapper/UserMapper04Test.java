package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser04;

import java.util.List;

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
}