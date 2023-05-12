package com.tugraz.studybuddy.data.model;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseModel implements Serializable {

    //    @DocumentId
    private String id;
    //    @ServerTimestamp
    private Date createdAt;
    //    @ServerTimestamp
    private Date updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
