package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.provider.PrivilegeProvider;

public interface PrivilegeMapper {

	@SelectProvider(type = PrivilegeProvider.class,method="selectById")
	SysPrivilege selectById(Long id);

	@SelectProvider(type = PrivilegeProvider.class,method="selectById02")
	SysPrivilege selectById02(Long id);
}