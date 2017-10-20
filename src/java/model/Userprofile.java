
package model;

import java.io.Serializable;
import java.util.Date;


public class Userprofile implements Serializable
{
  private long id_userprofile;
  private String firstname, lastname, email, avatar, location, bio;
  private Date birthday;
  private int type;

    public Userprofile() 
    {
    }

    public long getId_userprofile()
    {
        return id_userprofile;
    }

    public void setId_userprofile(long id_userprofile) 
    {
        this.id_userprofile = id_userprofile;
    }

    public String getFirstname() 
    {
        return firstname;
    }

    public void setFirstname(String firstname) 
    {
        this.firstname = firstname;
    }

    public String getLastname() 
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    
    
    public Date getBirthday() 
    {
        return birthday;
    }

    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }
    
    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    @Override
    public String toString() 
    {
        return "Userprofile{" + "id_userprofile=" + id_userprofile + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", avatar=" + avatar + ", location=" + location + ", bio=" + bio + ", birthday=" + birthday + ", type=" + type + '}';
    }
   
  
}
