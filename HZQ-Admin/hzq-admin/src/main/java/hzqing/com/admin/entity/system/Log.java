package hzqing.com.admin.entity.system;

import hzqing.com.admin.utils.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_log")
public class Log {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 请求路径
     */
    @Column(name = "opt_path")
    private String optPath;

    /**
     * 请求类型，新增，删除，查看
     */
    @Column(name = "opt_type")
    private String optType;

    /**
     * 操作时间
     */
    @Column(name = "opt_time")
    private Date optTime;

    /**
     * 备注
     */
    private String note;

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
     * 获取请求路径
     *
     * @return opt_path - 请求路径
     */
    public String getOptPath() {
        return optPath;
    }

    /**
     * 设置请求路径
     *
     * @param optPath 请求路径
     */
    public void setOptPath(String optPath) {
        this.optPath = optPath;
    }

    /**
     * 获取请求类型，新增，删除，查看
     *
     * @return opt_type - 请求类型，新增，删除，查看
     */
    public String getOptType() {
        return optType;
    }

    /**
     * 设置请求类型，新增，删除，查看
     *
     * @param optType 请求类型，新增，删除，查看
     */
    public void setOptType(String optType) {
        this.optType = optType;
    }

    /**
     * 获取操作时间
     *
     * @return opt_time - 操作时间
     */
    public Date getOptTime() {
        return optTime;
    }

    /**
     * 设置操作时间
     *
     * @param optTime 操作时间
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}