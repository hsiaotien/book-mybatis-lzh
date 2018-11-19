package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {
	List<SysRole> selectAll();
}
