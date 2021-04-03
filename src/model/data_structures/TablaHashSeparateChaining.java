package model.data_structures;

import model.logic.Stopwatch;

import model.logic.YoutubeVideo;

public class TablaHashSeparateChaining <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K, V> 
{	
	
	int a = 47;
	int b = 54;
	
	
	ILista<ILista <NodoTS<K,V>>> elementos;
	private int tamano;
	private int tamanoActual;
	
		
	public TablaHashSeparateChaining(int tamanoInicial)
	{
		elementos = new ArregloDinamico<>(tamanoInicial);

		for (int i = 1; i < tamanoInicial + 1; i++) 
		{
			elementos.addLast(null);
		}
	}


	public void put(K key, V valor) 
	{
		int posicion = mad(key);
		ListaEncadenada<NodoTS<K,V>> LSC = (ListaEncadenada<NodoTS<K, V>>) elementos.getElement(posicion);

		if(elementos.getElement(posicion) == null && !contains(key))
		{
			LSC = new ListaEncadenada<NodoTS<K,V>>();
			LSC.addLast(new NodoTS<K,V>(key, valor));
		}
		else
		{			
			elementos.changeInfo(posicion, new ListaEncadenada<NodoTS<K,V>>());
			elementos.getElement(posicion).addLast(new NodoTS<K,V>(key, valor));
		}
		tamanoActual++;
	}

	private int mad(K key) 
	{
		int m = elementos.size();

		return Math.abs((a * (key.hashCode()) + b) % nextPrime(m * 10)) % m + 1;
	}

	static boolean isPrime(int n)

	{
		if (n <= 1) return false;
		if (n <= 3) return true;

		if (n % 2 == 0 || n % 3 == 0) return false;

		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)

				return false;         
		return true;
	}


	static int nextPrime(int N)
	{
		if (N <= 1)
			return 2;

		int prime = N;
		boolean found = false;

		while (!found)

		{
			prime++;

			if (isPrime(prime))
				found = true;
		}
		return prime;
	}


	public V get(K key) 
	{
		V elemento =null;
		int max = elementos.size(); 
		boolean encontrado= max==0;
		int min =1;

		while(!encontrado) 
		{
			int mid = (max+min)/2;        
			if (elementos.getElement(mid).getKey().equals(key))
			{
				elemento = elementos.getElement(mid).getValor();
				encontrado=true;
			}
			else if (max == min) encontrado=true;
			else if (elementos.getElement(mid).getKey().compareTo(key)>0) max=mid;
			else min = mid + 1;
		}
		return elemento;
	}

	// esto es basicmaente copiar el get, pero si ecuentra elimina el elmento con los metodos deILIST
	public V remove(K key) 
	{
		V elemento =null;
		int max = elementos.size(); 
		boolean encontrado= max==0;
		int min =1;

		while(!encontrado) 
		{
			int mid = (max+min)/2;        
			if (elementos.getElement(mid).getKey().equals(key))
			{
				elemento= elementos.getElement(mid).getValor();
				elementos.removeElement(mid);
				encontrado=true;
			}
			else if (max == min) encontrado=true;
			else if (elementos.getElement(mid).getKey().compareTo(key)>0) max=mid;
			else min=mid+1;
		}
		return elemento;
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


	public ILista<K> keySet() 
	{
		ArregloDinamico<K> lista = new ArregloDinamico<>(size());

		for(int i=1;i<size()+1;i++)
		{
			lista.addLast(elementos.getElement(i).getKey());
		}
		return lista;
	}


	public ILista<V> valueSet() 
	{
		ArregloDinamico<V> lista = new ArregloDinamico<>(size());
		for(int i=1;i<size()+1;i++)
		{
			lista.addLast(elementos.getElement(i).getValor());
		}
		return lista;
	}

	@Override
	public int hash(K key) 
	{
		return 0;
	}
}
