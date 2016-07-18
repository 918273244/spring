package com.cmcc.domain;

import java.util.Date;

//类的每一个对象代表数据库的一条备份信息
public class DbBak {
    
    private String id;
    private String filename;
    private Date baktime;
    private String description;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public Date getBaktime() {
        return baktime;
    }
    public void setBaktime(Date baktime) {
        this.baktime = baktime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
