package tk.mybatis.simple.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {

	/**
	 * 手动sql别名完成自动映射
	 * @param id
	 * @return
	 */
	@Select({"select id,role_name roleName,enabled,create_by createBy,create_time createTime ",
	"from sys_role where id = #{id}"
	})
	SysRole selectById(Long id);

	/**
	 * 使用mapUnderscoreToCamelCase配置，字段按照下划线转驼峰方式自动映射
	 * @param id
	 * @return
	 */
	@Select({"select * ",
	"from sys_role where id = #{id}"
	})
	SysRole selectById02(Long id);

	/**
	 * 使用resultMap方式
	 * @param id
	 * @return
	 */
	@Results({
			@Result(property = "id",column = "id",id = true),
			@Result(property = "roleName",column = "role_name"),
			@Result(property = "enabled",column = "enabled"),
			@Result(property = "createBy",column = "create_by"),
			@Result(property = "createTime",column = "create_time"),
	})
	@Select({"select id,role_name,enabled,create_by,create_time ",
			"from sys_role where id = #{id}"
	})
	SysRole selectById03(Long id);

	/**
	 * 加id属性，可以共用
	 * @param id
	 * @return
	 */
	@Results(id = "roleResultMap", value = {
			@Result(property = "id",column = "id",id = true),
			@Result(property = "roleName",column = "role_name"),
			@Result(property = "enabled",column = "enabled"),
			@Result(property = "createBy",column = "create_by"),
			@Result(property = "createTime",column = "create_time"),
	})
	@Select({"select * ",
			"from sys_role where id = #{id}"
	})
	SysRole selectById04(Long id);

	/**
	 * id属性方便共用。
	 * @return
	 */
	@ResultMap("roleResultMap")
	@Select({"select * ",
			"from sys_role"
	})
	List<SysRole> selectAll01();

	/**
	 * 不需要返回主键
	 * @param sysRole
	 * @return
	 */
	@Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
	"values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	int insert(SysRole sysRole);

	/**
	 * 返回自增主键
	 */
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
			"values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insert2(SysRole sysRole);

	/**
	 * 返回非自增主键
	 * @param sysRole
	 * @return
	 */
	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
			"values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()",
	keyProperty = "id",
	resultType = Long.class,
	before = false)
	int insert3(SysRole sysRole);

	/**
	 * 补充：
	 * <selectKey keyColumn="id" resultType="long" keyProperty="id" order="AFTER">
	 * 	SELECT LAST_INSERT_ID()
	 * </selectKey>
	 * 对比xml配置selectKey。 before=false代表AFTER
	 */

	//=========注解@Update @Delete=================
	@Update({"update sys_role",
	"set role_name = #{roleName},",
	"enabled = #{enabled},",
	"create_by = #{createBy},",
	"create_time = #{createTime,jdbcType=TIMESTAMP}",
			"where id = #{id}" })
	int updateById(SysRole sysRole);

	@Delete("delete from sys_role where id = #{id}")
	int deleteById(Long id);

	//=================Provider注解=======

}
