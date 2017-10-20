
package controller;

import controller.Controller;
import model.Humor;
import model.Post;
import model.Userprofile;
import model.Usuario;
import dao.HumorDAO;
import dao.PostDAO;
import dao.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioController implements Controller
{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;
    private UsuarioDAO udao;
    private HumorDAO hdao;
    private PostDAO pdao;
    private String username;
    private String password;
    private Usuario user;
    private String password2;
    private String firstname;
    private String lastname;
    private String email;
    private String bday;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) 
    {
        this.request = request;
        this.response = response;

    }

    @Override
    public void execute() 
    {
        String action = request.getParameter("command").split("\\.")[1];
        udao = new UsuarioDAO();
        hdao = new HumorDAO();
        pdao = new PostDAO();
        
        switch (action) {
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");
               

                user = udao.findByUsername(username);
                List<Humor> humores = hdao.findAll();
          
                if (user == null) {
                    request.getSession().setAttribute("errormsg", "User not found!");
                    responsePage = "error.jsp";
                } else if (password.equals(user.getPassword())) {
                    request.getSession().setAttribute("humores", humores);
                    request.getSession().setAttribute("user", user);
                    responsePage = "home.jsp";

                    String remember = request.getParameter("remember");
                    int maxage = 0;
                    if (remember != null) {
                        maxage = 60 * 60 * 24 * 7;
                    }

                    Cookie c1 = new Cookie("username", username);
                    c1.setMaxAge(maxage);
                    response.addCookie(c1);

                    Cookie c2 = new Cookie("password", password);
                    c2.setMaxAge(maxage);
                    response.addCookie(c2);
                    
                    List<Post> posts;
                    posts = pdao.findByUser(user);
                    user.clearPosts();
                    for (Post p : posts) {
                        user.addPost(p.getId_post(),p.getPosttext(),p.getPostdate(),p.getFk_humor(),p.getCor(), p.getFeels());
                    }
                    
                } else {
                    request.getSession().setAttribute("errormsg", "Wrong password!");
                    responsePage = "error.jsp";
                }
                
                
                break;
            case "register":
                username = request.getParameter("username");
                password = request.getParameter("password");
                password2 = request.getParameter("password2");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                email = request.getParameter("email");
                bday = request.getParameter("birthday");

                Date birthday = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    birthday = sdf.parse(bday);
                } catch (ParseException ex) {
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }

                user = udao.findByUsername(username);
                if (user != null) {
                    request.getSession().setAttribute("errormsg", "Username already in use.");
                    responsePage = "error.jsp";
                } else {
                    user = udao.findByEmail(email);
                    if (user != null) {
                        request.getSession().setAttribute("errormsg", "Email already in use.");
                        responsePage = "error.jsp";
                    } else if (!password.equals(password2)) {
                        request.getSession().setAttribute("errormsg", "Passwords don't match.");
                        responsePage = "error.jsp";
                    } else {
                        user = new Usuario();
                        user.setUsername(username);
                        user.setPassword(password);

                        Userprofile up = new Userprofile();
                        up.setFirstname(firstname);
                        up.setLastname(lastname);
                        up.setEmail(email);
                        up.setBirthday(birthday);

                        user.setUserprofile(up);
                        udao.insert(user);
                        responsePage = "index.jsp";
                    }
                }
                break;
            case "logout":
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
            case "update":
                user = (Usuario)request.getSession().getAttribute("user");
                
                password = request.getParameter("password");
                password2 = request.getParameter("password2");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                bday = request.getParameter("birthday");

                Date birthday2 = new Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                try {
                   
                    birthday2 = sdf2.parse(bday);
                } catch (ParseException ex) {
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (password.equals("") && password2.equals("")) {
                    password = user.getPassword();
                } else if (!password.equals(password2)) {
                    request.getSession().setAttribute("errormsg", "Passwords don't match.");
                    responsePage = "error.jsp";
                }
                
                user.setPassword(password);

                Userprofile up = user.getUserprofile();
                up.setFirstname(firstname);
                up.setLastname(lastname);
                up.setBirthday(birthday2);

                user.setUserprofile(up);
                udao.modify(user);
                responsePage = "home.jsp";

                break;
                
            case "remove":
                user = (Usuario)request.getSession().getAttribute("user");
                udao.remove(user);
                request.getSession().invalidate();
                responsePage = "index.jsp";
                Cookie c1 = new Cookie("username", username);
                c1.setMaxAge(0);
                response.addCookie(c1);

                Cookie c2 = new Cookie("password", password);
                c2.setMaxAge(0);
                response.addCookie(c2);
                break;
        }
       
    }

    @Override
    public String getReponsePage() 
    {
        return this.responsePage;
    }
}
