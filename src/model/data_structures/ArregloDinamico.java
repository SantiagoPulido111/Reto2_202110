package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */

public class ArregloDinamico<T extends Comparable<T>> implements ILista<T>
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */

	public ArregloDinamico( int tamano)
	{
		tamanoMax = tamano;


		elementos= (T[]) new Comparable[tamano];

		tamanoAct = 0;
	}

	public void addLast( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			//			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		
		tamanoAct++;
	}

	public int darCapacidad() 
	{
		return tamanoMax;
	}

	public int darTamano() 
	{
		return tamanoAct;
	}

	public T darElemento(int pos) 
	{
pos--;
		
		if(pos < tamanoAct && pos >= 0)
		{
			return elementos[pos];									
		}
		else
		{
			return null;				
		}
	}

	public void invertir()
	{
		T [ ] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];
		for(int i=0; i<tamanoAct;i++)
		{
			elementos[tamanoAct-i-1]=copia[i];
		}
	}

	public void addFirst(T element) 
	{
		if ( tamanoAct == tamanoMax )
		{  

			tamanoMax = 2 * tamanoMax;
		}

		T [ ] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];

		elementos[0]=element;

		for ( int i = 1; i < tamanoAct+1; i++)
		{
			elementos[i] = copia[i];
		} 
		tamanoAct++;
	}

	public void addElement(T element, int pos) 
	{
		pos--;

		if(pos>-1&&pos<tamanoAct+1)
		{
			if ( tamanoAct == tamanoMax )
			{  
				tamanoMax = 2 * tamanoMax;
			}

			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];

			elementos[0]=element;

			int i = 0;

			while (  i < pos)
			{
				elementos[i] = copia[i];
				i++;
			} 

			elementos[i]=element;

			while (  i < tamanoAct+1)
			{
				elementos[i] = copia[i];
				i++;
			} 

			tamanoAct++;
		}
		else
		{
			System.out.println("la posicion no esta en los limites ");
		}
	}

	public T removeFirst()
	{

		if (tamanoAct==0)
		{
			return null;
		}
		else if(tamanoAct==0)
		{
			T temp =elementos[0];
			elementos[0]=null;
			tamanoAct=0;
			return temp;
		}
		else
		{
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 1; i < tamanoAct; i++)
			{
				elementos[i-1] = copia[i];
			} 
			tamanoAct--;
			return copia[0];
		}
	}

	public T removeLast() 
	{
		if (tamanoAct==0)
		{
			return null;
		}
		else
		{
			tamanoAct--;
			T temp=elementos[tamanoAct];
			elementos[tamanoAct]=null;
			return temp;
		}
	}

	public T removeElement(int pos) {
		pos--;
		if (pos==0)
		{
			return removeFirst();
		}

		else
		{
			T [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];

			for ( int i = 0; i < pos; i++)
			{
				elementos[i] = copia[i];
			} 
			for ( int i = pos+1; i < tamanoAct; i++)
			{
				elementos[i-1] = copia[i];
			} 

			tamanoAct--;
			return copia[pos];
		}
	}

	public T firstElement() 
	{
		return tamanoAct>0?elementos[0]:null;
	}

	public T lastElement() 
	{
		return tamanoAct>0?elementos[tamanoAct-1]:null;
	}

	public T getElement(int pos) 
	{
		pos--;
		
		return (pos>-1 && pos<tamanoAct) ? elementos[pos]:null;
	}

	public int size() 
	{	
		return tamanoAct;
	}

	public boolean isEmpty() 
	{	
		return tamanoAct==0;
	}

	public int isPresent(T element) 
	{		
		int encontrado=-1;
		for(int i=0;i<tamanoAct&&encontrado==-1;i++)
		{
			if(elementos[i].equals(element))
			{
				encontrado=i;
			}		
		}
		return encontrado;
	}


	public void exchange(int pos1, int pos2) 
	{
		pos1--;
		pos2--;
		if(pos1>-1&&pos2>-1&&pos1<tamanoAct&&pos2<tamanoAct)
		{
			T elem1=elementos[pos1];
			elementos[pos1]=elementos[pos2];
			elementos[pos2]=elem1;
		}
	}

	public void changeInfo(int pos, T newElem) 
	{
		pos--;
		if(pos>-1&&pos<tamanoAct)
		{
			
			elementos[pos]=newElem;
		}
	}


	public ILista<T> sublista(int numElementos)
	{
		ArregloDinamico<T> temp = new ArregloDinamico<>(numElementos);

		for (int i = 0; i < numElementos; i++) 
		{
			temp.addLast(elementos[i]);
		}
		return temp;
	}

	
	public ILista<T> subList(int pos, int size) {
		
		ArregloDinamico<T> temp = new ArregloDinamico<>(size);

		for (int i = pos; i < size+pos; i++) 
		{
			temp.addLast(elementos[i]);
		}
		return temp;
	}
}