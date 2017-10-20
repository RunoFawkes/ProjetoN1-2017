
package dao;

import model.Humor;
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

public class HumorDAO implements GenericDAO<Humor> {

    @Override
    public boolean insert(Humor h) {
        boolean result = false;

        Connection con = DatabaseConnection.getInstance().getConnection();
        

        String sql = "INSERT INTO humor(humor_nome, cor) VALUES(?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, h.getHumor_nome());
            pst.setString(2, h.getCor());

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
    public List<Humor> findAll() {
        List<Humor> humores = new ArrayList<>();
   
        Connection con = DatabaseConnection.getInstance().getConnection();
      
        String sql = "SELECT * FROM humor";
        try {
            Statement stm = con.createStatement();
    
            ResultSet rs = stm.executeQuery(sql);
           
            while (rs.next()) {
                Humor h = new Humor();
                
                h.setId_humor(rs.getLong("id_humor"));
                h.setHumor_nome(rs.getString("humor_nome"));
                h.setCor(rs.getString("cor"));
                
                humores.add(h);
            }

          
            stm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return humores;
    }
    
    @Override
    public Humor findById(long id) {
        Humor h = new Humor();
      
        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "SELECT * FROM humor WHERE humor.id_humor = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
         
                h.setId_humor(rs.getLong("id_humor"));
                h.setHumor_nome(rs.getString("humor_nome"));
                h.setCor(rs.getString("cor"));
      
            }
              
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return h;
    }
    

    @Override
    public boolean modify(Humor h) {
        boolean result = false;

        Connection con = DatabaseConnection.getInstance().getConnection();   

        String sql = "UPDATE humor SET humor_nome=?, cor=? WHERE id_humor=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, h.getHumor_nome());
            pst.setString(2, h.getCor());
            pst.setLong(3, h.getId_humor());
  
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
    public boolean remove(Humor h) {
        boolean result = false;
 
        Connection con = DatabaseConnection.getInstance().getConnection();

        String sql = "DELETE FROM humor WHERE id_humor=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, h.getId_humor());
      
            int resp = pst.executeUpdate();
     
            if(resp>0) result = true;
      
            pst.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                
        return result;
    }

 
    
}
