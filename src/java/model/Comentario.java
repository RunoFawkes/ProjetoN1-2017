
package model;

import java.io.Serializable;
import java.util.Date;

public class Comentario implements Serializable 
{
    private long id_comment;
    private String commenttext;
    private Date commentdate;
    private long fk_post; 
    private Usuario usuario; 

    public Comentario() 
    {
    }

    public long getId_comment()
    {
        return id_comment;
    }

    public void setId_comment(long id_comment) 
    {
        this.id_comment = id_comment;
    }

    public String getCommenttext() 
    {
        return commenttext;
    }

    public void setCommenttext(String commenttext)
    {
        this.commenttext = commenttext;
    }

    public Date getCommentdate() 
    {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) 
    {
        this.commentdate = commentdate;
    }

    public long getFk_post()
    {
        return fk_post;
    }

    public void setFk_post(long fk_post)
    {
        this.fk_post = fk_post;
    }

    public Usuario getUsuario() 
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }

    @Override
    public String toString() 
    {
        return "Comment{" + "id_comment=" + id_comment + ", commenttext=" + commenttext + ", commentdate=" + commentdate + ", fk_post=" + fk_post + ", usuario=" + usuario + '}';
    }
    
}
