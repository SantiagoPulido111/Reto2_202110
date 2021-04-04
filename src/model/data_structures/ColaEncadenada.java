package model.data_structures;

public class ColaEncadenada<T extends Comparable<T>>  extends ListaEncadenada<T>
{
	
	
public void enqueue(T elemento)
{
	addLast(elemento);
}


public T dequeue()
{
	return removeFirst();
}

}
