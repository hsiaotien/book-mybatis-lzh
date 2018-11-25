package tk.mybatis.simple.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.simple.model.SysUser04;

import java.util.List;
import java.util.Map;

public interface UserMapper04 {
	/**
	 * select结合if
	 *
	 * @param sysUser04
	 * @return
	 */
	List<SysUser04> selectByUser04(SysUser04 sysUser04);

	/**
	 * update 结合if
	 *
	 * @param sysUser04
	 * @return
	 */
	int updateByIdSelective(SysUser04 sysUser04);

	/**
	 * insert 结合if
	 *
	 * @param sysUser04
	 * @return
	 */
	int insert(SysUser04 sysUser04);

	/**
	 * choose 的用法， 实现if...else if...else if...else...
	 *
	 * @param sysUser04
	 * @return
	 */
	SysUser04 selectByIdOrUserName(SysUser04 sysUser04);

	/**
	 * where set 标签的用法，会自动剔除多余sql代码，代码更简洁
	 */
	/**
	 * where 用法， 解决了where元素没有内容，以及自动去除and or
	 * @param sysUser04
	 * @return
	 */
	SysUser04 selectByUserWithWhere(SysUser04 sysUser04);

	/**
	 * set 用法，可以剔除以逗号结尾的字符串，中的逗号。但并没有解决问题，依旧要保留id =#{id}
	 * @param sysUser04
	 * @return
	 */
	int updateByIdSelective02(SysUser04 sysUser04);

	// foreach用法如下
	//DefaultSqlSession 的 private Object wrapCollection(Object object)方法，即源码
	// 可以看到mybatis对参数都有默认属性名来指代，比如param1 param2 1 2 或者list array。
	// 建议用@Params注解进行明确指代

	/**
	 * foreach 示例01， collection 使用对应的默认值list。 具体源码查看DefaultSqlSession
	 * @param idList
	 * @return
	 */
	List<SysUser04> selectUserByIdList(List<Long> idList);

	/**
	 * foreach 示例02， collection 使用对应的默认值array。 具体源码查看DefaultSqlSession
	 * @param arr
	 * @return
	 */
	List<SysUser04> selectUserByIdArr(Long... arr);// array的简写

	/**
	 * foreach 示例03 ， 推荐这种方式， 使用注解@Param进行明确指代
	 * @param idList
	 * @return
	 */
	List<SysUser04> selectUserByIdListWithAnno(@Param("idList") List<Long> idList);

	/**
	 * foreach 实现批量插入
	 * @param userList
	 * @return
	 */
	int insertList(List<SysUser04> userList);

	/**
	 * foreach 实现批量插入，同时可以回显主键
	 * @param userList
	 * @return
	 */
	int insertListWithKeyCallback(List<SysUser04> userList);

	/**
	 * foreach 实现动态update！
	 * @param map
	 * @return
	 */
	int updateByMap(Map<String,Object> map);

	/**
	 * 下面示例bind用法
	 * bind标签可以使用OGNL表达式创建一个变量并将其绑定到上下文中。
	 * 该标签一方面避免更换数据库后而修改sql（不同数据库的sql拼接不一样）。
	 * 同时也是可以防止sql注入的
	 */

	/**
	 * bind 示例。
	 * @param userName
	 * @return
	 */
	SysUser04 selectByBind(@Param("userName") String userName);

	/**
	 * mybatis 对多数据的支持。
	 * 解决更换数据库带来的所有问题，配置以及编写sql时，在标签中指定为各个数据库指定即可
	 */
	SysUser04 selectByDataBaseProvider(SysUser04 sysUser04);

	/**
	 * mybatis 对多数据库的支持，当然也可以用if标签配合_databaseId来实现
	 * @param sysUser04
	 * @return
	 */
	SysUser04 selectByDataBaseProvider02(SysUser04 sysUser04);

	/**
	 * 补充：
	 * OGNL的用法：
	 * 常用的OGNL表达式有：
	 * 1. e1 or e2
	 * 2. e1 and e2
	 * 3. e1 == e2 或者 e1 eq e2
	 * 4. e1 != e2 或者 e1 neq e2
	 * 5. e1 lt e2 小于
	 * 6. e1 lte e2 小于等于（其他 gt大于 gte大于等于）
	 * 7. e1 + e2 、 e1 - e2 、e1*e2 、 e1/e2 、e1%e2
	 * 8. !e 或 not e 非，取反
	 * 9. e.method(args) 调用对象方法
	 * 10. e.property 对象属性值
	 * 11 e1[e2] 按索引取值（list array和map常用）
	 * 12 @class@method（args） 调用类的静态方法
	 * 13 @class@field 调用类的静态字段值
	 */
	/**
	 * ognl的启发示例，了解ognl的魅力
	 * @param userName
	 * @return
	 */
	SysUser04 selectByOGNL(@Param("userName")String userName);
}