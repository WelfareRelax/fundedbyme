package com.academicwork.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    private List<Integer> pageList = new ArrayList<Integer>();




    @RequestMapping(method = RequestMethod.GET, path = "/blog/{blogId}/{pageId}")
    public ModelAndView listPosts(@PathVariable ("blogId") long blogId, @PathVariable ("pageId") long pageId) {
        System.out.println(blogId);
        System.out.println(pageId);
        Project blog = projectRepository.getProject(blogId);
        //long pageId = blogId/5;

        return new ModelAndView("blog/posts")
                .addObject("blog", blog)
                .addObject("author", projectRepository.getAuthorOf(blog))
                .addObject("posts", projectRepository.getEntriesIn(blog, pageId));

    }
/*    @RequestMapping(method = RequestMethod.POST, path = "blog/{blogId}/posts")
    public ModelAndView InsertPosts(@RequestParam String title, @PathVariable long blogId) {
        Blog blog = projectRepository.postBlog(title, blogId);
        return new ModelAndView("blog/posts/redir/{blogId}")

                .addObject("blog", blog)
                .addObject("author", projectRepository.getAuthorOf(blog))
                .addObject("posts", projectRepository.getEntriesIn(blog));

    }*/


/*@RequestMapping(method = RequestMethod.POST, path = "blog/{blogId}/addposts")
public String InsertPosts(HttpServletRequest request, @RequestParam String from, @RequestParam String to, @RequestParam long amount) {

    Blog blog = projectRepository.postBlog( title, blogId);
    String redirectUrl = request.getScheme() + "://localhost:8080/blog/{blogId}/";
    return "redirect:" + redirectUrl;

}*/

    @RequestMapping(method = RequestMethod.GET, path = "/project/{projectId}")
    public ModelAndView listPostsafter(@PathVariable long projectId) {
        Project project = projectRepository.getProject(projectId);
        return new ModelAndView("blog/posts")
                .addObject("project", project)
                .addObject("author", projectRepository.getAuthorOf(project));

    }
    @GetMapping("/newproject")
    public String newProject(Model model){
        model.addAttribute("project", new Project());
        return "blog/newProject";

    }
@RequestMapping(method=RequestMethod.POST, path="/newproject")
    public String addNewProject(@ModelAttribute Project project){
    project.setUserID(5); //Hårdkodat tills vi får inloggad User
    projectRepository.newProject(project);
    return "redirect:/newproject";

}




}
