package com.lwj.persistence.pojo;

public class Complaint extends ComplaintKey {
    private String content;

    private Boolean check;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}