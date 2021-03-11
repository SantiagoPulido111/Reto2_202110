package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K,V> 
{	
	
	//Usare ArregloDinamico paraa elementos 
	ILista<NodoTS<K,V>> elementos;

	
	
	
	// Modificaremos las clase
	
	//TODO implementar
	public void put(K key, V valor) 
	{
		NodoTS<K,V> nodo = new NodoTS<K,V>(key, valor);
		
	
	}

	//TODO implementar
	public V get(K key) 
	{
		return null;
	}

	//TODO implementar
	public V remove(K key) 
	{
		
		return null;
	}

	
	public boolean contains(K key) 
	{
		return (this.get(key)==null);
	}

	public int size() 
	{
		return elementos.size();
	}

	
	public boolean isEmpty()
	{
		return elementos.isEmpty();
	}

	//TODO implementar
	public ILista<K> keySet() 
	{
		
		return null;
	}

	//TODO implementar
	public ILista<V> valueSet() 
	{
		ArregloDinamico<V> lista = new ArregloDinamico<>(size());
		
		for(int i=1;i<size()+1;i++)
		{
			
			//lista.addLast();
		}
		return null;
	}


	//TODO implementar
	public int hash(K key) 
	{
		
		return 0;
	}
	
}