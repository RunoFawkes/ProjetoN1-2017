
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario implements Serializable
{
    private long id_usuario;
    private String username, password;
    private Userprofile userprofile;
    private List<Post> posts;

    public Usuario()
    {
         posts = new ArrayList<>();
    }
    
    public void addPost(long id_post, String posttext, Date postdate, long id_humor, String cor, String feels){
        Post post = new Post();
        post.setId_post(id_post);
        post.setCor(cor);
        post.setFeels(feels);
        post.setPosttext(posttext);
        post.setPostdate(postdate);
        post.setFk_humor(id_humor);
        post.setFk_usuario(id_usuario);
        posts.add(post);
    }
    
    public void clearPosts(){
        this.posts = new ArrayList<>();
    }
    
    public List<Post> getPosts() {
        return posts;
    }

    public long getId_usuario() 
    {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario)
    {
        this.id_usuario = id_usuario;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public Userprofile getUserprofile()
    {
        return userprofile;
    }

    public void setUserprofile(Userprofile userprofile) 
    {
        this.userprofile = userprofile;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + ", userprofile=" + userprofile + ", posts=" + posts + '}';
    }
 
    
}
