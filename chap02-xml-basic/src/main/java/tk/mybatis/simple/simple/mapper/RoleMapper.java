package tk.mybatis.simple.simple.mapper;

import tk.mybatis.simple.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {
	List<SysRole> selectAll();
}
