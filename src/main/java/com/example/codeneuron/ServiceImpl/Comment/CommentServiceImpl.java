package com.example.codeneuron.ServiceImpl.Comment;

import com.example.codeneuron.Dao.CommentMapper;
import com.example.codeneuron.PO.Comment;
import com.example.codeneuron.Service.Comment.CommentService;
import com.example.codeneuron.VO.CommentVO;
import com.example.codeneuron.VO.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    /**
     * 获取评论
     * @param projectId
     * @param type 有效类型包括 node,edge,domain
     * @return
     */
    @Override
    public ResponseVO getComment(int projectId, String type){
        List<Comment> commentList;
        if(type.equals("node")){
            commentList = commentMapper.getCommentOfNodesForProject(projectId);
        }
        else if(type.equals("edge")){
            commentList = commentMapper.getCommentOfEdgesForProject(projectId);
        }
        else if(type.equals("domain")){
            commentList  = commentMapper.getCommentOfDomainsForProject(projectId);
        }
        else{
            return ResponseVO.buildFailure("No type "+type);
        }
        List<CommentVO> commentVOS = new ArrayList<>();
        for(Comment comment: commentList){
            commentVOS.add(new CommentVO(comment.getId(),comment.getTitle(),comment.getContent(),comment.getType(),comment.getRelatedId()));
        }
        return ResponseVO.buildSuccess(commentVOS);
    }

    @Override
    public ResponseVO getOneComment(String type,int relatedId){
        Comment comment;
        if(type.equals("node")){
            comment = commentMapper.getCommentOfNodeByNodeId(relatedId);
        }else if(type.equals("edge")){
            comment = commentMapper.getCommentOfEdgeByEdgeId(relatedId);
        }else if(type.equals("domain")){
            comment = commentMapper.getCommentOfDomainByDomainId(relatedId);
        }else{
            return ResponseVO.buildFailure("Wrong type parameter");
        }
        if(comment==null){
            return ResponseVO.buildFailure("No comment for "+type+" "+relatedId);
        }
        CommentVO res = new CommentVO(comment.getId(),comment.getTitle(),comment.getContent(),type,relatedId);
        return ResponseVO.buildSuccess(res);
    }

    /**
     * 增加评论
     * @param title
     * @param content
     * @param type 有效类型同上
     * @return
     */
    @Override
    public ResponseVO addComment(String title,String content,String type,int relatedId){
        Comment comment = new Comment();
        comment.setRelatedId(relatedId);
        comment.setTitle(title);
        comment.setContent(content);
        comment.setType(type);
        commentMapper.createComment(comment);
        if(type.equals("node")){
            commentMapper.createCommentForNode(relatedId, comment.getId());
        }else if(type.equals("edge")){
            commentMapper.createCommentForEdge(relatedId, comment.getId());
        }else if(type.equals("domain")){
            commentMapper.createCommentForDomain(relatedId, comment.getId());
        }
        CommentVO commentVO = new CommentVO(comment.getId(),comment.getTitle(),comment.getContent(),comment.getType(),comment.getRelatedId());
        return ResponseVO.buildSuccess(commentVO);
    }

    /**
     * 更新评论
     * @param commentId
     * @param title
     * @param content
     * @return
     */
    @Override
    public ResponseVO updateComment(int commentId,String title,String content){
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setTitle(title);
        comment.setContent(content);
        commentMapper.updateComment(comment);
        return ResponseVO.buildSuccess();
    }

    /**
     * 删除评论
     * @param commentId
     * @param type 有效类型同上
     * @return
     */
    @Override
    public ResponseVO deleteComment(int commentId,int relatedId, String type){
        commentMapper.deleteComment(commentId);
        if(type.equals("node")){
            commentMapper.deleteCommentForNode(commentId, relatedId);
        }else if(type.equals("edge")){
            commentMapper.deleteCommentForEdge(commentId,relatedId);
        }else if(type.equals("domain")){
            commentMapper.deleteCommentForDomain(commentId, relatedId);
        }
        return ResponseVO.buildSuccess();
    }

}
