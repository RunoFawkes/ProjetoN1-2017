
package dao;

import model.Post;
import model.Userprofile;
import model.Usuario;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PostDAO implements GenericDAO<Post> {
    
    @Override
    public boolean insert(Post e) {
        boolean result = false;

        Connection con = DatabaseConnection.getInstance().getConnection();
        

        String sql = "INSERT INTO post(post_text,post_date,fk_humor,fk_usuario) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getPosttext());
            pst.setDate(2, new java.sql.Date(e.getPostdate().getTime()));
            pst.setLong(3,e.getFk_humor());
            pst.setLong(4, e.getUsuario().getId_usuario());
            
  
            int resp = pst.executeUpdate();
            if(resp>0){
                result = true;
            }
            
    
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
   
        Connection con = DatabaseConnection.getInstance().getConnection();
      
        String sql = "SELECT * FROM post INNER JOIN usuario ON fk_usuario=id_usuario JOIN userprofile ON id_usuario=id_userprofile";
        try {
            Statement stm = con.createStatement();

           
            ResultSet rs = stm.executeQuery(sql);

            
            while (rs.next()) {
                Usuario u = new Usuario();

                Userprofile up = new Userprofile();
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setAvatar(null);

                u.setUserprofile(up);
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setId_usuario(rs.getLong("id_usuario"));

                Post p = new Post();
                p.setId_post(rs.getLong("id_post"));
                p.setPostdate(new Date(rs.getDate("post_date").getTime()));
                p.setUsuario(u);
                posts.add(p);
            }

          
            stm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(long id) {
        Post post = new Post();
      
        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "SELECT * FROM usuario INNER JOIN userprofile ON id_usuario=id_userprofile WHERE id_usuario=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            
    
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Usuario user = new Usuario();
                Userprofile up = new Userprofile();
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setAvatar(null);

                user.setUserprofile(up);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_usuario(rs.getLong("id_usuario"));
                
                post.setUsuario(user);
                post.setPostdate(new java.util.Date(rs.getDate("post_date").getTime()));
                post.setPosttext(rs.getString("post_text"));
            }
            
     
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return post;
    }
    
    public List<Post> findByUser(Usuario user) {
        List<Post> posts = new ArrayList<>();
    
        Connection con = DatabaseConnection.getInstance().getConnection();


        String sql = "SELECT * FROM post INNER JOIN humor ON post.fk_humor=humor.id_humor WHERE fk_usuario=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, user.getId_usuario());
            
         
            ResultSet rs = pst.executeQuery();
            

            while(rs.next()){
                Post post = new Post();
                post.setId_post(rs.getLong("id_post"));
                post.setUsuario(user);
                post.setCor(rs.getString("cor"));
                post.setFeels(rs.getString("humor_nome"));
                post.setFk_humor(rs.getLong("fk_humor"));
                post.setPostdate(new java.util.Date(rs.getDate("post_date").getTime()));
                post.setPosttext(rs.getString("post_text"));
                posts.add(post);
            }
    
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }

    @Override
    public boolean modify(Post e) {
        boolean result = false;

        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "UPDATE post SET post_date=?, post_text=?, fk_humor=? WHERE id_post=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(e.getPostdate().getTime()));
            pst.setString(2, e.getPosttext());
            pst.setLong(3, e.getFk_humor());
            pst.setLong(4, e.getId_post());
           
            int result2 = pst.executeUpdate();
  
            if(result2>0){
                result = true;
            }
            
            
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean remove(Post e) {
        boolean result = false;
 
        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "DELETE FROM post WHERE id_post=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, e.getId_post());
      
            int resp = pst.executeUpdate();
     
            if(resp>0) result = true;
      
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return result;
    }

    public boolean removeAll() {
        boolean result = false;
 
        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "DELETE FROM post";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
      
            int resp = pst.executeUpdate();
     
            if(resp>0) result = true;
      
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return result;
    }

}
