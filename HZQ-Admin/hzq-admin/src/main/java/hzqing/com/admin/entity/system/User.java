package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

@Table(name = "sys_user")
public class User {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 登陆名
     */
    private String username;

    private String password;

    private String note;

    private String email;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户名称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 手机号
     */
    private String phone;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取登陆名
     *
     * @return username - 登陆名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登陆名
     *
     * @param username 登陆名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户头像
     *
     * @return avatar - 用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像
     *
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取用户名称
     *
     * @return full_name - 用户名称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置用户名称
     *
     * @param fullName 用户名称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return attr1
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * @param attr1
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * @return attr2
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * @param attr2
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * @return attr3
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * @param attr3
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * @return attr4
     */
    public String getAttr4() {
        return attr4;
    }

    /**
     * @param attr4
     */
    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    /**
     * @return attr5
     */
    public String getAttr5() {
        return attr5;
    }

    /**
     * @param attr5
     */
    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", note='" + note + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", attr4='" + attr4 + '\'' +
                ", attr5='" + attr5 + '\'' +
                '}';
    }
}