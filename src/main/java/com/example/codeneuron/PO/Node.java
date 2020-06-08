package com.example.codeneuron.PO;

public class Node {
    public Node(){}
    public Node(int id,String name,int projectId){
        this.id=id;
        this.name=name;
        this.visited=false;
        this.projectId=projectId;
    }
    private int id;
    //name包括包名、类名、函数名和参数类型
    private String name;

    private boolean visited;//节点是否被访问过

    private int projectId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getVisited(){ return visited; }

    public void setVisited(boolean visited){ this.visited=visited; }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
