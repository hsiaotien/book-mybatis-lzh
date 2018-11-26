package tk.mybatis.simple.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_user
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class SysUser06 {
    /**
    *用户ID
    * id
    */
    private Long id;

    /**
    *用户名
    * user_name
    */
    private String userName;

    /**
    *密码
    * user_password
    */
    private String userPassword;

    /**
    *邮箱
    * user_email
    */
    private String userEmail;

    /**
    *创建时间
    * create_time
    */
    private Date createTime;

    /**
    *简介
    * user_info
    */
    private String userInfo;

    /**
    *头像
    * head_img
    */
    private byte[] headImg;

    //增加sysRole字段，示例一对一映射
    @Setter
    @Getter
    private SysRole06 sysRole06;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.id
     *
     * @return the value of sys_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.id
     *
     * @param id the value for sys_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_name
     *
     * @return the value of sys_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_name
     *
     * @param userName the value for sys_user.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_password
     *
     * @return the value of sys_user.user_password
     *
     * @mbggenerated
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_password
     *
     * @param userPassword the value for sys_user.user_password
     *
     * @mbggenerated
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_email
     *
     * @return the value of sys_user.user_email
     *
     * @mbggenerated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_email
     *
     * @param userEmail the value for sys_user.user_email
     *
     * @mbggenerated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.create_time
     *
     * @return the value of sys_user.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.create_time
     *
     * @param createTime the value for sys_user.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_info
     *
     * @return the value of sys_user.user_info
     *
     * @mbggenerated
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_info
     *
     * @param userInfo the value for sys_user.user_info
     *
     * @mbggenerated
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.head_img
     *
     * @return the value of sys_user.head_img
     *
     * @mbggenerated
     */
    public byte[] getHeadImg() {
        return headImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.head_img
     *
     * @param headImg the value for sys_user.head_img
     *
     * @mbggenerated
     */
    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }
}