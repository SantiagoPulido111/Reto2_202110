package model.data_structures;

public class TablaHashLinearProbing <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K, V> 
{
	ILista<NodoTS<K, V>> elementos;
	private int tamano;
	private int tamanoActual;

	public TablaHashLinearProbing(int tamanoInicial)
	{

		elementos = new ArregloDinamico<>(tamanoInicial);

		tamano = nextPrime(tamanoInicial);
		elementos = new ArregloDinamico<NodoTS<K,V>>(tamanoInicial);
		tamanoActual = 0;

		for (int i = 1; i < tamano + 1; i++) 
		{
			elementos.addLast(null);
		}
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



	@Override
	public void put(K key, V valor) 
	{
		int posicion = hash(key);
		NodoTS<K,V> nodo = elementos.getElement(posicion);

		if(nodo != null  && !nodo.isEmpty())
		{
			posicion = getNextEmpty(posicion);
		}
		
		elementos.changeInfo(posicion, new NodoTS<K,V>(key, valor));
		tamanoActual++;

	}

	private int getNextEmpty(int posicion)
	{
		int posicionRetornar = posicion +1;
		
		while(elementos.getElement(posicionRetornar) != null && !elementos.getElement(posicionRetornar).isEmpty())
		{
			posicionRetornar++;
			
			if(posicionRetornar > tamano)
				posicionRetornar = 1;
		}
		return posicionRetornar;
	}



	@Override
	public V get(K key) 
	{
		int posicion = hash(key);
		V retornar = null;
		boolean encontroNull = false;
		
		while(retornar == null && !encontroNull)
		{
			NodoTS<K,V> nodoAct = elementos.getElement(posicion);
			
			if(nodoAct == null)
			{
				encontroNull = true; 
			}
			else if(nodoAct.getKey().compareTo(key) == 0)
			{
				retornar = nodoAct.getValor();
			}
			else
			{
				posicion++;
				if(posicion > tamano)
					posicion = 1;
			}
		}
		return retornar;
	}



	@Override
	public V remove(K key) 
	{
		int posicion = hash(key);
		V retornar = null;
		boolean encontroNull = false;
		
		while(retornar == null && !encontroNull)
		{
			NodoTS<K,V> nodoAct = elementos.getElement(posicion);
			
			if(nodoAct == null)
			{
				encontroNull = true; 
			}
			else if(nodoAct.getKey().compareTo(key) == 0)
			{
				retornar = nodoAct.getValor();
			}
			else
			{
				posicion++;
				if(posicion > tamano)
					posicion = 1;
			}
		}
		
		if(retornar != null)
		{
			elementos.getElement(posicion).setEmpty();
		}
		return retornar;
	}



	@Override
	public boolean contains(K key) 
	{
		return (this.get(key)==null);
	}



	@Override
	public int size() 
	{
		return elementos.size();
	}



	@Override
	public boolean isEmpty() 
	{
		return elementos.isEmpty();
	}



	@Override
	public ILista<K> keySet() 
	{
		ArregloDinamico<K> lista = new ArregloDinamico<>(size());

		for(int i=1;i<size()+1;i++)
		{
			lista.addLast(elementos.getElement(i).getKey());
		}
		return lista;
	}



	@Override
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