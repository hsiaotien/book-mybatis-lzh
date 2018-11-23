package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysUser04;

import java.util.List;

public interface UserMapper04 {
	List<SysUser04> selectByUser04(SysUser04 sysUser04);

	int updateByIdSelective(SysUser04 sysUser04);

	SysUser04 insert(SysUser04 sysUser04);
}
