package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    //Method to create a new comment when user submits a comment entry
    //Invoke the
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String generateComment(@PathVariable("imageTitle") String title, @PathVariable("imageId") Integer id, @RequestParam("comment") String comment, Comment newComment, RedirectAttributes redirectAttributes, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");

        newComment.setUser(user);
        newComment.setText(comment);
        newComment.setCreatedDate(LocalDate.now());
        newComment.setImage(imageService.getImage(id));
        commentService.generateComment(newComment);

        redirectAttributes.addAttribute("id", id).addFlashAttribute("id", id);
        redirectAttributes.addAttribute("title", title).addFlashAttribute("title", title);

        return "redirect:/images/{id}/{title}";
    }
}
