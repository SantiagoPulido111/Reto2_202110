package model.data_structures;

public class NodoTS<K extends Comparable<K>, V> implements Comparable<NodoTS<K, V>> 
{
	private K key;
	private V valor;
	
	
	public NodoTS(K keyP, V valorP)
	{
		setkey(keyP);
		setValor(valorP);
	}
	

	@Override
	public int compareTo(NodoTS<K,V> otro) 
	{
		return this.key.compareTo( otro.key );
	}
	
	public K getKey() 
	{
		return key;
	}

	public void setkey(K keyP) 
	{
		this.key = keyP;
	}
	
	public V getValor() 
	{
		return valor;
	}

	public void setValor(V valorP) 
	{
		this.valor = valorP;
	}

}
