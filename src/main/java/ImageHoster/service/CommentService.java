package ImageHoster.service;
import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //Method to invoke the generateComment method in the Repository class
    //The method sends the new comment data to be persisted in the database
    public void generateComment(Comment comment) {
        commentRepository.generateComment(comment);
    }
}