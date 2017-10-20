package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Serializable 
{
    private long id_post;
    private String posttext;
    private Date postdate;
    private long fk_usuario;
    private long fk_humor;
    private List<Comentario> comments;
    private Usuario usuario;
    private String cor;
    private String feels;

    public Post() 
    {
        comments = new ArrayList<>();
    }
    
    public void addComment(long id_comment, String commenttext, Date commentdate, Usuario user)
    {
        Comentario c = new Comentario();
        c.setCommentdate(commentdate);
        c.setCommenttext(commenttext);
        c.setId_comment(id_comment);
        c.setFk_post(id_post);
        c.setUsuario(user);
        comments.add(c);
    }

    public String getFeels() {
        return feels;
    }

    public void setFeels(String feels) {
        this.feels = feels;
    }
    

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getId_post()
    {
        return id_post;
    }

    public void setId_post(long id_post) 
    {
        this.id_post = id_post;
    }

    public String getPosttext() 
    {
        return posttext;
    }

    public void setPosttext(String posttext) 
    {
        this.posttext = posttext;
    }

    public Date getPostdate() 
    {
        return postdate;
    }

    public void setPostdate(Date postdate) 
    {
        this.postdate = postdate;
    }

    public long getFk_usuario()
    {
        return fk_usuario;
    }

    public void setFk_usuario(long fk_usuario)
    {
        this.fk_usuario = fk_usuario;
    }

    public long getFk_humor() 
    {
        return fk_humor;
    }

    public void setFk_humor(long fk_humor)
    {
        this.fk_humor = fk_humor;
    }

    public List<Comentario> getComments()
    {
        return comments;
    }

    public void setComments(List<Comentario> comments) 
    {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", posttext=" + posttext + ", postdate=" + postdate + ", fk_usuario=" + fk_usuario + ", fk_humor=" + fk_humor + ", comments=" + comments + ", usuario=" + usuario + ", cor=" + cor + ", feels=" + feels + '}';
    }

    


} 