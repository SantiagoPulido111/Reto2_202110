package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>,V extends Comparable<V>> implements ITablaSimbolos<K,V> 
{	

	//Usare ArregloDinamico paraa elementos 
	ILista<NodoTS<K,V>> elementos;


	public TablaSimbolos(int tamanoInicial)
	{

		elementos=new ArregloDinamico<>(tamanoInicial);

	}



	// Modificaremos las clase

	//Cuando meta videos desde el modelo tengo que revisar si hay una lista, de ser asi tomo la lista, la aumento y la cambio
	public void put(K key, V valor) 
	{
		//Es mejor intentar esto para que quede O( log N+ N) en ves de O(N+N) en realidad ambas son O(N) pero pues es un ahorro extra ahi
		// si no sirve se puede hacer una bsuqueda normal (un for desde 1 hasta elementos.size) y pues eso va a ser O(N+N)=O(N)
		int max = elementos.size(); 
		boolean encontrado= max==0;
		int min =1;
		NodoTS<K,V> element= new NodoTS<K,V>(key,valor);
		if(encontrado) elementos.addLast(element);

		while(!encontrado) 
		{
			int mid = (max+min)/2;        
			if (elementos.getElement(mid).getKey().equals(key))
			{
				elementos.changeInfo(mid, element);
				encontrado=true;
			}
			else if (max == min) 
			{
				//TODO revisar esto 
	            if(elementos.getElement(mid).getKey().compareTo(key)>0) elementos.addElement(element, mid);
	            else elementos.addElement(element, mid+1);
				encontrado=true;
				
			}
			else if (elementos.getElement(mid).getKey().compareTo(key)>0) max=mid;
			else min=mid+1;
		}
		
		
		
		
		
		
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
				elemento= elementos.getElement(mid).getValor();
				encontrado=true;
			}
			else if (max == min) encontrado=true;
			else if (elementos.getElement(mid).getKey().compareTo(key)>0) max=mid;
			else min=mid+1;
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




}