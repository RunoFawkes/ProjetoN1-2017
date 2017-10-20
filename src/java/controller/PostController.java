
package controller;

import controller.Controller;
import model.Humor;
import model.Post;
import model.Usuario;
import dao.HumorDAO;
import dao.PostDAO;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController implements Controller{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage = "error.jsp";
    private PostDAO pdao;
    private HumorDAO hdao;
    
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        pdao = new PostDAO();
        hdao = new HumorDAO();
        
    }

    public void execute() {
        String action = request.getParameter("command")
                .split("\\.")[1];
        long id;
        Post post = new Post();
        Humor h = new Humor();
        List<Post> posts;
        Usuario user = (Usuario)request.getSession().getAttribute("user");
                
        switch(action){
            case "post":   
                long id_humor = Long.parseLong(request.getParameter("humor"));
                h = hdao.findById(id_humor);
                
                post.setFk_humor(id_humor);
                post.setCor(h.getCor());
                post.setFeels(h.getHumor_nome());
                post.setPosttext(request.getParameter("posttext"));
                post.setPostdate(new Date());
                
                post.setUsuario(user);
                boolean resp = pdao.insert(post);
                if(resp) request.getSession().setAttribute("msg", "Post inserted");
                else request.getSession().setAttribute("msg", "Error, post not inserted.");
                responsePage = "home.jsp";
                
                posts = pdao.findByUser(user);
                user.clearPosts();
                for (Post p : posts) {
                    user.addPost(p.getId_post(),p.getPosttext(),p.getPostdate(),p.getFk_humor(),p.getCor(), p.getFeels());
                }
                
                break;
                
             case "update":
                id = Long.parseLong(request.getParameter("postId"));
                post = pdao.findById(id);
                post.setId_post(id);
                post.setPosttext(request.getParameter("posttext"));
                post.setFk_humor(Long.parseLong(request.getParameter("humor")));       
                post.setPostdate(new Date());
                
                pdao.modify(post);
                responsePage = "home.jsp";
                
                posts = pdao.findByUser(user);
                user.clearPosts();
                for (Post p : posts) {
                    user.addPost(p.getId_post(),p.getPosttext(),p.getPostdate(),p.getFk_humor(),p.getCor(), p.getFeels());
                }
                
                break; 
                
            case "remove":       
                id = Long.parseLong(request.getParameter("postId"));
                post = pdao.findById(id);
                post.setId_post(id);
                pdao.remove(post);
                responsePage = "home.jsp";
                
                posts = pdao.findByUser(user);
                user.clearPosts();
                for (Post p : posts) {
                    user.addPost(p.getId_post(),p.getPosttext(),p.getPostdate(),p.getFk_humor(),p.getCor(), p.getFeels());
                }
                
                break;
                
            case "removeall":
                if(user.getUsername().equals("ADMIN")){
                    pdao.removeAll();
                    responsePage = "home.jsp";

                    posts = pdao.findByUser(user);
                    user.clearPosts();
                    for (Post p : posts) {
                        user.addPost(p.getId_post(),p.getPosttext(),p.getPostdate(),p.getFk_humor(),p.getCor(), p.getFeels());
                    }
                }
        }
    }


    @Override
    public String getReponsePage() 
    {
        return this.responsePage;
    }
}
