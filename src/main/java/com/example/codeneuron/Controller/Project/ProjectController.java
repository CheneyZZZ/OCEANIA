package com.example.codeneuron.Controller.Project;


import com.example.codeneuron.Service.Comment.CommentService;
import com.example.codeneuron.Service.Project.ProjectService;
import com.example.codeneuron.VO.ProjectForm;
import com.example.codeneuron.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/project")
public class ProjectController {
    ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService=projectService;
    }


    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResponseVO getProjectInfo(@RequestParam("id") int id){
        return projectService.getProjectById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseVO getProjectForUser(@RequestParam("userId") int userId){
        return projectService.getAllProjectsByUserId(userId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseVO deleteProjectById(@RequestParam("projectId") int projectId){
        return projectService.deleteProjectById(projectId);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public ResponseVO deleteProjectForUser(@RequestParam("userId") int userId){
        return projectService.deleteProjectByUserId(userId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseVO createProjectForUser(@RequestBody ProjectForm projectForm){
        return projectService.createProjectForUser(projectForm);
    }

    @RequestMapping(value = "/edges", method = RequestMethod.GET)
    public ResponseVO getEdgeForProject(@RequestParam("projectId") int projectId){
        return projectService.getEdgeForProject(projectId);
    }

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public ResponseVO getNodeForProject(@RequestParam("projectId") int projectId){
        return projectService.getNodeForProject(projectId);
    }
}
