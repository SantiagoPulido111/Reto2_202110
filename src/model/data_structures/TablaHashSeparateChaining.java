package model.data_structures;

import model.logic.Stopwatch;

import model.logic.YoutubeVideo;

public class TablaHashSeparateChaining <K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K, V> 
{	


	int p;
	int a;
	int b;

	private int numrehashes;
	ILista<ListaEncadenada<NodoTS<K,V>>> elementos;
	private int tamano;
	private int tamanoActual;
	private double factorMax;

	//TODO implementar rehash y paar eso recibir factor de carga 
	public TablaHashSeparateChaining(int tamanoInicial, double factorCarga)
	{

		factorMax=factorCarga;
		tamano= nextPrime(tamanoInicial);
		elementos = new ArregloDinamico<ListaEncadenada<NodoTS<K,V>>>(tamano);
		tamanoActual=0;

		for (int i = 0; i < tamano; i++) 
		{
			elementos.addLast(null);
		}

		p= nextPrime(tamano*5);

		a= (int) Math.round(Math.random()*(p-1));


		b= (int) Math.round(Math.random()*(p-1));


		numrehashes=0;
	}


	public void put(K key, V valor) 
	{
		int posicion = hash(key);

		ListaEncadenada<NodoTS<K,V>> LSC = (ListaEncadenada<NodoTS<K, V>>) elementos.getElement(posicion);
		if(LSC!=null && !contains(key))
		{
			LSC.addLast(new NodoTS<K,V>(key,valor));
		}
		else
		{
			elementos.changeInfo(posicion, new ListaEncadenada<NodoTS<K,V>>());
			elementos.getElement(posicion).addLast(new NodoTS<K,V>(key,valor));
		}
		tamanoActual++;
		verificarCarga();
	}


	private void verificarCarga() {
		if((tamanoActual/tamano)>factorMax) reHash();
	}

	//TODO falta 
	private void reHash() 
	{
		numrehashes++;

		ColaEncadenada<NodoTS<K,V>> nodos =	darNodos();
		tamanoActual=0;
		tamano= nextPrime(tamano*2);
		elementos =new ArregloDinamico<ListaEncadenada<NodoTS<K,V>>>(tamano);

		for (int i = 0; i <tamano; i++) 
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
			ILista<NodoTS<K, V>> listaSC= elementos.getElement(i);
			if (listaSC!=null) 
			{
				for (int j = 1; j <= listaSC.size(); j++) 
				{
					cola.enqueue(listaSC.getElement(j));
				}

			}

		}
		return cola;
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

		V elemento = null;

		int posicion = hash(key);

		ListaEncadenada<NodoTS<K,V>> LSC = (ListaEncadenada<NodoTS<K, V>>) elementos.getElement(posicion);

		if(LSC != null)
		{


			for(int i=1; i<LSC.size()+1&&elemento==null;i++)
			{
				NodoTS<K,V> temp = LSC.getElement(i);
				if(temp.getKey().compareTo(key)==0)
				{
					elemento=temp.getValor();

				}
			}

		}

		return elemento;
	}

	// esto es basicmaente copiar el get, pero si ecuentra elimina el elmento con los metodos deILIST
	public V remove(K key) 
	{

		V elemento = null;

		int posicion = hash(key);

		ListaEncadenada<NodoTS<K,V>> LSC = (ListaEncadenada<NodoTS<K, V>>) elementos.getElement(posicion);

		if(LSC != null)
		{


			for(int i=1; i<LSC.size()+1&&elemento==null;i++)
			{
				NodoTS<K,V> temp = LSC.getElement(i);
				if(temp.getKey().compareTo(key)==0)
				{
					elemento=temp.getValor();
					LSC.removeElement(i);
                    elementos.changeInfo(i, LSC);
				}
			}

		}

		return elemento;
	}




	public int size() 
	{
		return tamano;
	}
	public int Numtuplas() 
	{
		return tamanoActual;
	}

	public boolean isEmpty()
	{
		return elementos.isEmpty();
	}


	public ILista<K> keySet() 
	{
		ArregloDinamico<K> lista = new ArregloDinamico<>(tamanoActual);

		for(int i=1;i<elementos.size()+1;i++)
		{
			ILista <NodoTS<K,V>> listatemp=elementos.getElement(i);
			if( listatemp!=null)
			{

				for(int j=1; j<listatemp.size();j++)
				{
					lista.addLast(listatemp.getElement(j).getKey());
				}
			}
		}
		return lista;
	}


	public ILista<V> valueSet() 
	{
		ArregloDinamico<V> lista = new ArregloDinamico<>(tamanoActual);

		for(int i=1;i<elementos.size()+1;i++)
		{
			ILista <NodoTS<K,V>> listatemp=elementos.getElement(i);
			if( listatemp!=null)
			{

				for(int j=1; j<listatemp.size();j++)
				{
					lista.addLast(listatemp.getElement(j).getValor());
				}
			}
		}
		return lista;
	}

	//TODO falta implementar MAD


	private int mad(K key) 
	{
		int m = tamano;

		return Math.abs((a * (key.hashCode()) + b) % p) % m + 1;
	}

	public int hash(K key) 
	{
		return mad(key);
	}



	public boolean contains(K key) {

		return get(key)!=null;
	}

	public int darNumReHashes() {

		return numrehashes;

	}



}
