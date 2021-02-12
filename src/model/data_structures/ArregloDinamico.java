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

	public ArregloDinamico( int max )
	{
		tamanoMax = max;


		elementos= (T[]) new Comparable[max];

		tamanoAct = 0;
	}

	public void agregar( T dato )
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
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
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

		if(pos < tamanoAct && pos >= 0)
		{
			return elementos[pos];									
		}
		else
		{
			return null;				
		}
	}

	//Implementacion de buscar y eliminar con compareTo
	public T buscar(T dato) 
	{			
		T buscado=null;

		for ( int i = 0; i < tamanoAct && buscado ==null; i++)
		{
			if(elementos[i].compareTo(dato)==0)
			{
				buscado=elementos[i];
			}
		} 

		return buscado;
	}

	public T eliminar(T dato) 
	{			
		T buscado=null;
		T [ ] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];
		int i;

		for ( i = 0; i < tamanoAct&&buscado==null&&copia[i]!=null; i++) 
		{
			if (copia[i].compareTo(dato)==0)
			{
				buscado=dato;

				tamanoAct--;
			}
			else
			{	
				elementos[i]=copia[i];	
			}
		} 

		if (buscado==dato)
		{
			for(int a=i;a<tamanoAct+1;a++)
			{
				elementos[a-1]=copia[a];	
			}
		}			
		return buscado;
	}
	
	
	public T buscarEq(T dato) 
	{			
		T buscado=null;

		for ( int i = 0; i < tamanoAct && buscado ==null; i++)
		{
			if(elementos[i].equals(dato))
			{
				buscado=elementos[i];
			}
		} 

		return buscado;
	}

	public T eliminarEq(T dato) 
	{			
		T buscado=null;
		T [ ] copia = elementos;
		elementos = (T[]) new Comparable[tamanoMax];
		int i;

		for ( i = 0; i < tamanoAct&&buscado==null&&copia[i]!=null; i++) 
		{
			if (copia[i].equals(dato))
			{
				buscado=dato;

				tamanoAct--;
			}
			else
			{	
				elementos[i]=copia[i];	
			}
		} 

		if (buscado==dato)
		{
			for(int a=i;a<tamanoAct+1;a++)
			{
				elementos[a-1]=copia[a];	
			}
		}			
		return buscado;
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

	
	
	
	
	//TODO desde aca
	
	public void addFirst(T element) 
	{
		elementos[0]=element;
		
	}

	
	public void addLast(T element) 
	{
		elementos[tamanoAct-1]=element;
		
	}

	
	
	
	public void addElement(T element, int pos) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeElement(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T firstElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T lastElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getElement(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isPresent(T element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void exchange(int pos1, int pos2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeInfo(int pos, T newElem) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
}