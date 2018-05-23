package hzqing.com.admin.entity.system;

import javax.persistence.Table;
import java.util.Date;
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
        this.id = id == null ? null : id.trim();
    }

    public String getOptPath() {
        return optPath;
    }

    public void setOptPath(String optPath) {
        this.optPath = optPath == null ? null : optPath.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
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
        this.note = note == null ? null : note.trim();
    }
}