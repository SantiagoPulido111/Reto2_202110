package model.data_structures;

public class TablaHashLinearProbing <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K, V> 
{
	
	
	int p;
	int a;
	int b;
	private int numrehashes;
	
	ILista<NodoTS<K, V>> elementos;
	private int tamano;
	private int tamanoActual;
	private int numTuplas;
	private double factorMax;
	
	//TODO implementar rehash y paar eso recibir factor de carga 
	public TablaHashLinearProbing(int tamanoInicial, double factorCarga)
	{

		factorMax=factorCarga;
		tamano = nextPrime(tamanoInicial);
		elementos = new ArregloDinamico<NodoTS<K,V>>(tamano);
		tamanoActual = 0;
		numTuplas=0;

		for (int i = 1; i < tamano + 1; i++) 
		{
			elementos.addLast(null);
		}
		
		
        p= nextPrime(tamano*5);
		
		a= (int) Math.round(Math.random()*(p-5));
		

		b= (int) Math.round(Math.random()*(p-5));
		
		numrehashes=0;
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




	public void put(K key, V valor) 
	{
		int posicion = hash(key);
		NodoTS<K,V> nodo = elementos.getElement(posicion);
		
       
       if(nodo != null  && !nodo.isEmpty())
		{
			posicion = getNextEmpty(posicion);
			
		}
		
       //TODO revisar esto, para no hacer reHashes innecesarios 
       if(elementos.getElement(posicion)!=null&&elementos.getElement(posicion).isEmpty())
       {
    	   tamanoActual--;
       }
       
		elementos.changeInfo(posicion, new NodoTS<K,V>(key, valor));
		tamanoActual++;
		
		verificarCarga();

	}
	
	private void verificarCarga() {
		if((tamanoActual/tamano)>factorMax) reHash();
	}
	
	
	private void reHash() 
	{

	
		numrehashes++;
		ColaEncadenada<NodoTS<K,V>> nodos =	darNodos();
		tamanoActual=0;
		tamano= nextPrime(tamano*2);
		elementos =new ArregloDinamico<NodoTS<K,V>>(tamano);

		for (int i = 1; i < tamano + 1; i++) 
		{
			elementos.addLast(null);
		}

		NodoTS<K, V> actual;
		while((actual= nodos.dequeue()) != null)
		{
			put(actual.getKey(),actual.getValor());
		}


	}


	private ColaEncadenada<NodoTS<K, V>> darNodos() 
	{

		ColaEncadenada<NodoTS<K,V>> cola =new 	ColaEncadenada<NodoTS<K,V>>();
		for (int i = 1; i <= elementos.size(); i++) 
		{
			NodoTS<K, V> NodoSC= elementos.getElement(i);
			if (NodoSC!=null) cola.enqueue(NodoSC);
		}
		return cola;
	}


	private int getNextEmpty(int posicion)
	{
		int posicionRetornar = posicion +1;
		if(posicionRetornar > tamano)
			posicionRetornar = 1;
		
		
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
			else if(nodoAct.getKey()!=null && nodoAct.getKey().compareTo(key) == 0)
			{
				
				//TODO que pasa si es un nodo cuya key = null, ahi le puse algo 
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
			else if(nodoAct.getKey()!=null && nodoAct.getKey().compareTo(key) == 0)
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
		return (this.get(key)!=null);
	}



	@Override
	public int size() 
	{
		return tamano;
	}

	public int Numtuplas() 
	{
		return numTuplas;
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
			NodoTS<K, V> temp = elementos.getElement(i);
			if(temp!=null)
			{
				lista.addLast(elementos.getElement(i).getKey());
			}
		}
		return lista;
	}



	@Override
	public ILista<V> valueSet() 
	{
		ArregloDinamico<V> lista = new ArregloDinamico<>(size());
		for(int i=1;i<size()+1;i++)
		{
			NodoTS<K, V> temp = elementos.getElement(i);
			if(temp!=null)
			{
				lista.addLast(elementos.getElement(i).getValor());
			}
		}
		return lista;
	}

	private int mad(K key) 
	{
		int m = tamano;

		return Math.abs((a * (key.hashCode()) + b) % p) % m + 1;
	}


	public int hash(K key) 
	{
		return mad(key);
	}
	
	
	
	public int darNumReHashes() {
		
		return numrehashes;
		
	}
	
	
}