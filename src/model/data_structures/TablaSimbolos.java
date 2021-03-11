package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K,V> 
{	
	ILista<NodoTS<K,V>> elementos;

	
	
	
	// complejidad o(n)
	
	@Override
	public void put(K key, V valor) 
	{
		NodoTS<K,V> nodo = new NodoTS<K,V>(key, valor);
		
		int temp = elementos.isPresent(nodo); 
		
		if(temp > -1)
		{
			elementos.changeInfo(temp, nodo);
		}
		else
		{
			elementos.addLast(nodo);
		}
	}

	@Override
	public V get(K key) 
	{
		return null;
	}

	@Override
	public V remove(K key) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ILista<K> keySet() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<V> valueSet() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hash(K key) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}