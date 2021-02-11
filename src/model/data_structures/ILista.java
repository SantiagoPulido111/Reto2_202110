package model.data_structures;

public interface ILista<T extends Comparable<T>> 
{
	
	//Agrega un elemento al principio de la lista
	public void addFirst(T element);
	
	
	//Agrega un elemento al final de la lista
	public void addLast(T element);
	
	//Agrega un elemento en una posición válida. 
	//Las posiciones válidas son posiciones donde ya hay un elemento en la lista.
	//La posición del primer elemento es 1, la del segundo es 2 y así sucesivamente.
	public void addElement(T element, int pos);
	
	//Elimina el primer elemento. Se retorna el elemento eliminado.
	T removeFirst();
	
	//Elimina el último elemento. Se retorna el elemento eliminado.
	T removeLast();
	
	//Elimina el elemento de una posición. 
    T removeElement(int pos);
    
    //Retorna el primer elemento de la lista
	T firstElement();
	
	//Retorna el último elemento de la lista
	T lastElement();
	
	//Retorna el elemento en una posición.
	//La posición del primer elemento es 1, la del segundo es 2 y así sucesivamente.
	T getElement( int pos);
	
	//Retorna el tamaño de la lista
    int size();
    //Retorna true si la lista es vacia. false en caso contrario.
	boolean isEmpty( );
	
	//Retorna la posición de un elemento en la lista. 
	//La búsqueda debe usar el método compareTo( … ) 
	//definido en el tipo T. Si no se encuentra el valor retornado es -1.
	int isPresent ( T element);
	
	//Intercambia la información de los elementos en dos posiciones válidas.
	public void exchange (int pos1, int pos2);
	
	//Actualiza el elemento en una posición válida de la lista
	public  void changeInfo (int pos, T newElem);

	
	
}
