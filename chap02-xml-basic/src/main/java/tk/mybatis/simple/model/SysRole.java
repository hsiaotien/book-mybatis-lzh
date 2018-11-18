package tk.mybatis.simple.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 */
@Data
public class SysRole implements Serializable {
	private static final long serialVersionUID = 6320941908222932112L;
	/**
	 * 角色ID
	 */
	private Long id;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 有效标志
	 */
	private Integer enabled;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;

	// 增加SysUser对象
	/**
	 * 用户信息
	 */
	private SysUser user;
}
