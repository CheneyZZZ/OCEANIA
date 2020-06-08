package com.example.codeneuron.VO;

public class CommentForm {
    private String title;
    private String content;
    private String type;
    private int relatedId;

    public CommentForm(){}
    public CommentForm(String title,String content,String type,int relatedId){
        this.title=title;
        this.content=content;
        this.type=type;
        this.relatedId =relatedId;
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
