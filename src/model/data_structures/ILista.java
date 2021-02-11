package model.data_structures;

public interface ILista<T extends Comparable<T>> 
{
	
	//Agrega un elemento al principio de la lista
	public void addFirst(T element);
	
	
	//Agrega un elemento al final de la lista
	public void addLast(T element);
	
	//Agrega un elemento en una posici�n v�lida. 
	//Las posiciones v�lidas son posiciones donde ya hay un elemento en la lista.
	//La posici�n del primer elemento es 1, la del segundo es 2 y as� sucesivamente.
	public void addElement(T element, int pos);
	
	//Elimina el primer elemento. Se retorna el elemento eliminado.
	T removeFirst();
	
	//Elimina el �ltimo elemento. Se retorna el elemento eliminado.
	T removeLast();
	
	//Elimina el elemento de una posici�n. 
    T removeElement(int pos);
    
    //Retorna el primer elemento de la lista
	T firstElement();
	
	//Retorna el �ltimo elemento de la lista
	T lastElement();
	
	//Retorna el elemento en una posici�n.
	//La posici�n del primer elemento es 1, la del segundo es 2 y as� sucesivamente.
	T getElement( int pos);
	
	//Retorna el tama�o de la lista
    int size();
    //Retorna true si la lista es vacia. false en caso contrario.
	boolean isEmpty( );
	
	//Retorna la posici�n de un elemento en la lista. 
	//La b�squeda debe usar el m�todo compareTo( � ) 
	//definido en el tipo T. Si no se encuentra el valor retornado es -1.
	int isPresent ( T element);
	
	//Intercambia la informaci�n de los elementos en dos posiciones v�lidas.
	public void exchange (int pos1, int pos2);
	
	//Actualiza el elemento en una posici�n v�lida de la lista
	public  void changeInfo (int pos, T newElem);

	
	
}
