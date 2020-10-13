/*
 * 本类将作为一张表创建在数据库中
 */
package example.nutzbook.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("tbl_user")
public class User {
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Id
    private int id;
    @Name
    @Column
    private String name;
    @Column("password")
    private String password;
    @Column
    private String salt;
    @Column("time_create")
    private Date createTime;
    @Column("time_update")
    private Date updateTime;
}