package economic.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import economic.main.model.Comment;
import economic.main.model.LikesComments;
import economic.main.model.User;
import economic.main.payload.respone.modal.CommentResponce;
import economic.main.reponsitory.CommentReponsitory;
import economic.main.reponsitory.LikesCommentsReponsitory;
import economic.main.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    private LikesCommentsReponsitory likesCommentsReponsitory;

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Override
    public CommentResponce buldCommnets(Comment comment, User curUser) {
        CommentResponce commentResponce = new CommentResponce(comment);

            List<LikesComments> likesComments = likesCommentsReponsitory.findByComment(comment);

            commentResponce.setLikes(likesComments.size());

            if(curUser != null){

                likesComments.stream().forEach((lc) -> {
                    if(lc.getUser().getId() == curUser.getId()){
                        commentResponce.setLiked(true);
                        return;
                    }
                });
            }

            List<Comment> repComments = commentReponsitory.findByRepId(comment.getId());

            if(!repComments.isEmpty()){
                commentResponce.setItems(this.buldCommnets(repComments.get(0), curUser));
            }

            return commentResponce;
    }
    
}
