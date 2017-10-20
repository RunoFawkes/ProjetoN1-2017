
package dao;

import model.Userprofile;
import model.Usuario;
import connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioDAO implements GenericDAO<Usuario>
{

    @Override
    public boolean insert(Usuario e) {
        boolean result = false;
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        String sql = "INSERT INTO usuario(username,password) VALUES(?,?)";
        
        try 
        {
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, e.getUsername());
            pst.setString(2, e.getPassword());
            
            int resp = pst.executeUpdate();
            long key = 0;
            ResultSet keys = pst.getGeneratedKeys();
            while(keys.next()){
                key = keys.getLong(1);
            }
            
            if(resp>0){ 
               
                String sqlb = "INSERT INTO userprofile VALUES(?,?,?,?,?,?,?,null)";
                PreparedStatement pstb = con.prepareStatement(sqlb);
                pstb.setLong(1, key);
                pstb.setString(2, e.getUserprofile().getFirstname());
                pstb.setString(3, e.getUserprofile().getLastname());
                pstb.setString(4, e.getUserprofile().getEmail());
                pstb.setDate(5, new java.sql.Date(e.getUserprofile().getBirthday().getTime()));
                pstb.setString(6, e.getUserprofile().getLocation());
                pstb.setString(7, e.getUserprofile().getBio());
    
                result = pstb.execute();
     
                pstb.close();
           }  
        } catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public Usuario findById(long id) 
    {
        Usuario user = new Usuario();
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM usuario INNER JOIN userprofile ON id_usuario=id_userprofile WHERE usuario=?";
        try
        {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
            
                Userprofile up = new Userprofile();            
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setLocation(rs.getString("location"));
                up.setBio(rs.getString("bio"));
                up.setAvatar(null);

                user.setUserprofile(up);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_usuario(rs.getLong("id_usuario"));
            }
            
            pst.close();
        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return user;
    }
    
    public Usuario findByUsername(String username) 
    {
        Usuario user = null;
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM usuario INNER JOIN userprofile ON id_usuario=id_userprofile WHERE username=?";
        try
        {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
            
                Userprofile up = new Userprofile();            
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setLocation(rs.getString("location"));
                up.setBio(rs.getString("bio"));
                up.setAvatar(null);
                
                user = new Usuario();
                user.setUserprofile(up);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_usuario(rs.getLong("id_usuario"));
            }
            
            pst.close();
        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return user;
    }
    
     public Usuario findByEmail(String email) 
     {
        Usuario user = null;
        
        Connection con = DatabaseConnection.getInstance().getConnection();
 
        String sql = "SELECT * FROM usuario INNER JOIN userprofile ON id_usuario=id_userprofile WHERE email=?";
        try 
        {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Userprofile up = new Userprofile();
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setLocation(rs.getString("location"));
                up.setBio(rs.getString("bio"));
                up.setAvatar(null);

                user = new Usuario();
                user.setUserprofile(up);
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId_usuario(rs.getLong("id_usuario"));
            }
            
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Usuario> findAll()
    {
        List<Usuario> users = new ArrayList<>();
        
        Connection con = DatabaseConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM usuario INNER JOIN userprofile ON id_usuario=id_userprofile";
        try
        {
            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(sql);
  
            while(rs.next())
            {
                Usuario u = new Usuario();
                
                Userprofile up = new Userprofile();            
                up.setFirstname(rs.getString("firstname"));
                up.setLastname(rs.getString("lastname"));
                up.setEmail(rs.getString("email"));
                up.setId_userprofile(rs.getLong("id_userprofile"));
                java.sql.Date bday = rs.getDate("birthday");
                up.setLocation(rs.getString("location"));
                up.setBio(rs.getString("bio"));
                up.setBirthday(new java.util.Date(bday.getTime()));
                up.setAvatar(null);
                
                u.setUserprofile(up);
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setId_usuario(rs.getLong("id_usuario"));
                
                users.add(u);
            }
           
        stm.close();
        
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }    
        
        return users;
    }

    @Override
    public boolean modify(Usuario e) {
        boolean result = false;
       
        Connection con = DatabaseConnection.getInstance().getConnection();
        
     
        String sql = "UPDATE usuario SET username=?, password=? WHERE id_usuario=?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, e.getUsername());
            pst.setString(2, e.getPassword());
            pst.setLong(3, e.getId_usuario());
    
            boolean result2 = pst.execute();           
         
            if(result2){
                
                String sqlb = "UPDATE userprofile SET firstname=?, lastname=?, email=?, birthday=?,location=?, bio=? avatar=? WHERE id_userprofile=?";
                PreparedStatement pstb = con.prepareStatement(sqlb);
                pstb.setString(1, e.getUserprofile().getFirstname());
                pstb.setString(2, e.getUserprofile().getLastname());
                pstb.setString(3, e.getUserprofile().getEmail());
                pstb.setDate(4, new java.sql.Date(e.getUserprofile().getBirthday().getTime()));
                pstb.setString(5, e.getUserprofile().getLocation());
                pstb.setString(6, e.getUserprofile().getBio());
                pstb.setString(7, "");

                result = pstb.execute();
            
                 pstb.close();
            }
           
            pst.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean remove(Usuario e) {
       boolean result = false;
       
       Connection con = DatabaseConnection.getInstance().getConnection();
       
       String sql = "DELETE FROM usuario WHERE id_usuario=?";
       
        try 
        {
            PreparedStatement pst = con.prepareCall(sql);
            pst.setLong(1, e.getId_usuario());
            
            int resp = pst.executeUpdate();
            
            if(resp>0) result = true;
            
            pst.close();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return result;
    }
    
    
}
