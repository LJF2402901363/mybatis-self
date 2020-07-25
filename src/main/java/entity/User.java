package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Classname:User
 *
 * @description:用户类
 * @author: 陌意随影
 * @Date: 2020-07-24 09:49
 * @Version: 1.0
 **/
public class User implements Serializable {
    //用户ID主键
    private Integer id;
    //用户名
    private String name;
    //密码
    private  String password;
    //年龄
    private  Integer age;
    //创建日期
    private  Date createTime;

    public User() {
    }

    public User(Integer id, String name, String password, Integer age, Date createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                '}';
    }
}
