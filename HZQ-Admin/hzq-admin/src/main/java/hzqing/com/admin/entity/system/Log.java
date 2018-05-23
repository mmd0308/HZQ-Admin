package hzqing.com.admin.entity.system;

import org.apache.ibatis.type.Alias;

import javax.persistence.Table;
import java.util.Date;

@Alias("log")
@Table(name = "sys_log")
public class Log {
    private String id;
    private String optPath;
    private String optType;
    private Date optTime;
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptPath() {
        return optPath;
    }

    public void setOptPath(String optPath) {
        this.optPath = optPath;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}