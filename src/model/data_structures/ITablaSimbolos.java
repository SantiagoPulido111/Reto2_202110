package model.data_structures;

public interface ITablaSimbolos <K extends Comparable<K>,V extends Comparable<V>> 
{
	public void put ( K key , V value);
	
	V get (K key);
	
	V remove (K key);
	
	public boolean contains(K key);
	
	int size ( );
	
	boolean isEmpty ( );
	
	public ILista<K> keySet ( );
	
	public ILista<V> valueSet ( );
	
	int hash (K key);
}
