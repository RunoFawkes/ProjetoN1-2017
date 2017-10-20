package dao;

import java.util.List;


public interface GenericDAO<E> 
{
    public boolean insert(E e); //C -CREATE
    public E findById(long id);
            
    public List<E> findAll(); //R - READ
    
    public boolean modify(E e); //U - UPDATE
    
    public boolean remove(E e); //D - DELETE
}
