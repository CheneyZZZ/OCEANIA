package com.example.codeneuron.Service.Project;

import com.example.codeneuron.VO.ProjectForm;
import com.example.codeneuron.VO.ProjectVO;
import com.example.codeneuron.VO.ResponseVO;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
 ////projectService还需要和用java-callgraph分析出edge和node的方法整合一下，初始化node、edge存入数据库，计算closeness更新edge表，（这些需要写一下）
    // 计算第一次的连通域存入数据库（这个调用domainCal的setThreshold方法，有存入数据库）
    /**
     * 获取当前用户下所有project
     * @param userId
     * @return
     */
    public ResponseVO getAllProjectsByUserId(int userId);

    /**
     * 通过id获取Project
     * @param projectId
     * @return
     */
    public ResponseVO getProjectById(int projectId);

    /**
     * 删除某个project
     * @param projectId
     * @return
     */
    public ResponseVO deleteProjectById(int projectId);

    /**
     * 删除用户对应的所有project
     * @param userId
     * @return
     */
    public ResponseVO deleteProjectByUserId(int userId);

    /**
     * 为用户创建新project
     * @param projectForm
     * @return
     */
    public ResponseVO createProjectForUser(ProjectForm projectForm);

    /**
     * 给前端的方法，获取某个project的所有边，这里的project是已经初始化过的，所以直接拿
     * @param projectId
     * @return
     */
    public ResponseVO getEdgeForProject(int projectId);

    /**
     * 同上，获取某个project的所有顶点
     * @param projectId
     * @return
     */
    public ResponseVO getNodeForProject(int projectId);
}
