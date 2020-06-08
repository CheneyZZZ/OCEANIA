package com.example.codeneuron.Service.Project;

import com.example.codeneuron.Dao.AccountMapper;
import com.example.codeneuron.Dao.EdgeMapper;
import com.example.codeneuron.Dao.NodeMapper;
import com.example.codeneuron.Dao.ProjectMapper;
import com.example.codeneuron.PO.Edge;
import com.example.codeneuron.PO.Node;
import com.example.codeneuron.PO.Project;
import com.example.codeneuron.PO.User;
import com.example.codeneuron.VO.ProjectForm;
import com.example.codeneuron.VO.ProjectVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectServiceTest {

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ProjectService projectService;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    EdgeMapper edgeMapper;

    @Test
    public void getAllProjectsByUserId() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        int userId=accountMapper.getUserByName("tintin").getId();
        for(int i=0;i<20;i++){
            ProjectForm projectForm=new ProjectForm();
            projectForm.setName("project"+i);
            projectForm.setClosenessThreshold((double)i/20.0);
            projectForm.setUserId(userId);
            projectService.createProjectForUser(projectForm);
        }
        List<ProjectVO> projects=(List<ProjectVO>) projectService.getAllProjectsByUserId(userId).getContent();
        int exceptedUserId=user.getId();
        for(int i=0;i<20;i++) {
            Assert.assertEquals("project" + i, projects.get(i).getName());
            Assert.assertEquals(exceptedUserId, projects.get(i).getUserId());
            Assert.assertEquals((double)i/20.0,projects.get(i).getClosenessThreshold(),2);
        }
    }

    @Test
    public void getProjectById() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        ProjectForm projectForm=new ProjectForm();
        projectForm.setName("projectOne");
        projectForm.setClosenessThreshold(0.4);
        int userId=user.getId();
        projectForm.setUserId(userId);
        projectService.createProjectForUser(projectForm);
        List<ProjectVO> projects=(List<ProjectVO>)projectService.getAllProjectsByUserId(userId).getContent();
        int projectId=projects.get(0).getId();
        ProjectVO projectVO=(ProjectVO)projectService.getProjectById(projectId).getContent();
        Assert.assertEquals("projectOne",projectVO.getName());
        Assert.assertEquals(0.4,projectVO.getClosenessThreshold(),2);
        Assert.assertEquals(userId,projectVO.getUserId());
    }

    @Test
    public void deleteProjectById() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        ProjectForm projectForm=new ProjectForm();
        projectForm.setName("projectOne");
        projectForm.setClosenessThreshold(0.4);
        int userId=user.getId();
        projectForm.setUserId(userId);
        projectService.createProjectForUser(projectForm);
        List<ProjectVO> projects=(List<ProjectVO>)projectService.getAllProjectsByUserId(userId).getContent();
        int projectId=projects.get(0).getId();
        projectService.deleteProjectById(projectId);
        Assert.assertEquals(null,projectService.getProjectById(projectId).getContent());
    }

    @Test
    public void deleteProjectByUserId() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        int userId = user.getId();
        for(int i=0;i<20;i++) {
            ProjectForm projectForm = new ProjectForm();
            projectForm.setName("project"+i);
            projectForm.setClosenessThreshold(0.4);
            projectForm.setUserId(userId);
            projectService.createProjectForUser(projectForm);
        }
        projectService.deleteProjectByUserId(userId);
        Assert.assertEquals(0,((List<Project>)projectService.getAllProjectsByUserId(userId).getContent()).size());
    }

    @Test
    public void createProjectForUser() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        ProjectForm projectForm=new ProjectForm();
        projectForm.setName("projectOne");
        projectForm.setClosenessThreshold(0.4);
        int userId=user.getId();
        projectForm.setUserId(userId);
        projectService.createProjectForUser(projectForm);
        List<ProjectVO> projects=(List<ProjectVO>) projectService.getAllProjectsByUserId(userId).getContent();
        Assert.assertEquals(1,projects.size());
        Assert.assertEquals("projectOne",projects.get(0).getName());
        Assert.assertEquals(0.4,projects.get(0).getClosenessThreshold(),2);
    }

    @Test
    public void getEdgeForProject() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        ProjectForm projectForm=new ProjectForm();
        projectForm.setName("projectOne");
        projectForm.setClosenessThreshold(0.4);
        int userId=user.getId();
        projectForm.setUserId(userId);
        projectService.createProjectForUser(projectForm);
        List<ProjectVO> projects=(List<ProjectVO>)projectService.getAllProjectsByUserId(userId).getContent();
        int projectId=projects.get(0).getId();
        for(int i=0;i<100;i++){
            Node node=new Node();
            node.setName("node"+i);
            node.setProjectId(projectId);
            nodeMapper.createNewNode(node);
        }
        List<Edge> tempEdges=new ArrayList<Edge>();
        for(int i=0;i<50;i++){
            Edge edge=new Edge();
            edge.setCalleeName("node"+i);
            edge.setCallerName("node"+(i+1));
            edge.setTypeOfCall("M");
            tempEdges.add(edge);
        }
        for(int i=50;i<99;i++){
            Edge edge=new Edge();
            edge.setCalleeName((i+1)+"node");
            edge.setCallerName(i+"node");
            edge.setTypeOfCall("O");
            tempEdges.add(edge);
        }
        edgeMapper.insertEdgeForProject(tempEdges,projectId);
        List<Edge> edges=(List<Edge>)projectService.getEdgeForProject(projectId).getContent();
        for(int i=0;i<50;i++){
            Assert.assertEquals("node"+i,edges.get(i).getCalleeName());
            Assert.assertEquals("node"+(i+1),edges.get(i).getCallerName());
        }
        for(int i=50;i<99;i++){
            Assert.assertEquals((i+1)+"node",edges.get(i).getCalleeName());
            Assert.assertEquals(i+"node",edges.get(i).getCallerName());
        }
    }

    @Test
    public void getNodeForProject() {
        User user=new User();
        user.setName("tintin");
        user.setPassword("fffjjj123");
        accountMapper.createNewUser(user);
        ProjectForm projectForm=new ProjectForm();
        projectForm.setName("projectOne");
        projectForm.setClosenessThreshold(0.4);
        int userId=user.getId();
        projectForm.setUserId(userId);
        projectService.createProjectForUser(projectForm);
        List<ProjectVO> projects=(List<ProjectVO>)projectService.getAllProjectsByUserId(userId).getContent();
        int projectId=projects.get(0).getId();
        for(int i=0;i<50;i++){
            Node node=new Node();
            node.setName("node"+i);
            node.setProjectId(projectId);
            nodeMapper.createNewNode(node);
        }
        List<Node> nodes=(List<Node>) projectService.getNodeForProject(projectId).getContent();
        for(int i=0;i<50;i++){
            Assert.assertEquals("node"+i,nodes.get(i).getName());
        }
    }
}