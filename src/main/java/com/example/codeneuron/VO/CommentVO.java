package com.example.codeneuron.VO;

public class CommentVO {
    private int id;
    private String title;
    private String content;
    private String type;
    private int relatedId;

    public CommentVO(){}

    public CommentVO(int id,String title,String content,String type,int relatedId){
        this.id=id;
        this.title=title;
        this.content=content;
        this.type=type;
        this.relatedId=relatedId;
    }


    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }
}
