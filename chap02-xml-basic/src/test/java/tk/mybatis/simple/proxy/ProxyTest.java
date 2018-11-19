package tk.mybatis.simple.proxy;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.mapper.BaseMapperTest;
import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysRole;

import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyTest extends BaseMapperTest {

	@Test
	public void test01() {
		// 获取sqlSession
		SqlSession sqlSession = this.getSqlSession();
		// 获取UserMapper接口
		MyMapperProxy<RoleMapper> roleMapperMyMapperProxy = new MyMapperProxy<>(RoleMapper.class, sqlSession);
		RoleMapper roleMapper = (RoleMapper)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[]{RoleMapper.class},
				roleMapperMyMapperProxy);
		//调用selectAll
		List<SysRole> sysRoles = roleMapper.selectAll();
		sysRoles.forEach(System.out::println);
	}


}
