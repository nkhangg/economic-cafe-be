package economic.main.service;


import economic.main.model.Comment;
import economic.main.model.User;
import economic.main.payload.respone.modal.CommentResponce;

public interface CommentsService {
    CommentResponce buldCommnets(Comment comment, User curUser);
}
