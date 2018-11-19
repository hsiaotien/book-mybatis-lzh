package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserMapper {

	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	SysUser selectById(Long id);

	/**
	 * 查询全部用户
	 * @return
	 */
	List<SysUser> selectAll();

	/**
	 * 查询全部用户
	 * @return
	 */
	SysUser[] selectAll02();

	/**
	 * 根据用户id查询角色信息
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesByUserId(Long userId);

	/**
	 * 根据用户id查询角色信息（带user的信息，用到了组合关系）
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesAndUserInfoByUserId(Long userId);

	/**
	 * 新增用户
	 * @param sysUser
	 * @return
	 */
	int insert00(SysUser sysUser);

	/**
	 * 新增用户-使用useGeneratedKeys方式
	 * @param sysUser
	 * @return
	 */
	int insert02(SysUser sysUser);

	/**
	 * 新增用户-使用selectKey方式
	 * @param sysUser
	 * @return
	 */
	int insert03(SysUser sysUser);

	/**
	 * 根据主键更新
	 * @param sysUser
	 * @return
	 */
	int updateById00(SysUser sysUser);

	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	int deleteById00(Long id);

	/**
	 * 通过主键查询
	 * @param sysUser
	 * @return
	 */
	int deleteBySysUser(SysUser sysUser);

	/**
	 * 根据用户id和角色的enabled状态获取用户的角色
	 * @param userId
	 * @param enabled
	 * @return
	 */
	List<SysRole> selectRolesByUserIdAndRoleEnabled00(@Param("userId") Long userId,
													  @Param("enabled") Integer enabled);

	/**
	 * 点取值的方式
	 * @param user
	 * @param sysRole
	 * @return
	 */
	List<SysRole> selectRolesByUserIdAndRoleEnabled01(@Param("user")SysUser user,
													  @Param("role")SysRole sysRole);
}

