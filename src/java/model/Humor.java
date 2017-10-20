package model;

import java.io.Serializable;

public class Humor implements Serializable 
{
    private long id_humor;
    private String cor;
    private String humor_nome;

    public Humor() 
    {
        
    }

    public long getId_humor() 
    {
        return id_humor;
    }

    public void setId_humor(long id_humor) 
    {
        this.id_humor = id_humor;
    }

    public String getCor()
    {
        return cor;
    }

    public void setCor(String cor) 
    {
        this.cor = cor;
    }

    public String getHumor_nome() 
    {
        return humor_nome;
    }

    public void setHumor_nome(String humor_nome)
    {
        this.humor_nome = humor_nome;
    }

    @Override
    public String toString() 
    {
        return "Humor{" + "id_humor=" + id_humor + ", cor=" + cor + ", humor_nome=" + humor_nome + '}';
    }
    
    
}
