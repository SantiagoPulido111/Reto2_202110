package model.data_structures;

public class NodoClase<T extends Comparable<T>>
{

	
	private T info;			
	private NodoClase<T> next;        
	
	//No hay anterior porque es sencilla
	

	public NodoClase(T infor)
	{
		this.info=infor;
	}
	
	
	
	public void setNext(NodoClase<T> info)
	{
		next=info;
	}
	
	
	public T getInfo()
	{
	return info;
	}
	
	public NodoClase<T> getNext()
	{
	return next;
	}
	
	public boolean hasNext()
	{
		return next!=null;
	}
	
	
	
	
	
	
	
}




