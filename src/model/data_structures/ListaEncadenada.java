package model.data_structures;

public class ListaEncadenada<T extends Comparable<T>> implements ILista<T>
{
	private  NodoClase<T> primerNodo;


	public ListaEncadenada(T primer)
	{
		addFirst(primer);
	}






	public void addFirst(T element) 
	{
		if (primerNodo !=null)
		{
			NodoClase<T> temp = primerNodo.getNext();
			primerNodo= new NodoClase<T>(element);
			primerNodo.setNext(temp);

		}
		primerNodo= new NodoClase<T>(element);

	}



	public void addLast(T element) {


		if (primerNodo !=null)
		{
			NodoClase<T>actual= primerNodo;

			while(actual.hasNext())
			{
				actual=actual.getNext();
			}
			actual.setNext(new NodoClase(element));
		}
		else
		{
			primerNodo= new NodoClase<T>(element);
		}
	}




	public void addElement(T element, int pos) 
	{

		if (primerNodo !=null)
		{
			NodoClase<T>actual= primerNodo;
			int i =1;
			while(actual.hasNext()&&i<pos)
			{
				actual=actual.getNext();
				i++;
			}
			if(i==pos)
			{
				NodoClase<T>siguiente= actual.getNext();
				actual.setNext(new NodoClase(element));
				actual=actual.getNext();
				actual.setNext(siguiente);
			}
		}
		else if(pos==1)
		{
			primerNodo= new NodoClase<T>(element);
		}
		//Si se ingresa una poscion que no sea posible el metodo no hace nada 

	}






	public T removeFirst() {

		if (primerNodo !=null)
		{
			T temp=primerNodo.getInfo();
			primerNodo=primerNodo.getNext();

			return temp;
		}
		else 
		{
			return null;
		}
	}


	public T removeLast() {

		if (primerNodo !=null){

			if(primerNodo.hasNext())
			{
				NodoClase<T>anterior= primerNodo;
				NodoClase<T>actual= anterior.getNext();

				while(actual.hasNext())
				{
					anterior=anterior.getNext();
					actual=actual.getNext();
				}

				T temp=actual.getInfo();
				anterior.setNext(null);

				return temp;
			}

			else
			{
				return removeFirst();
			}
		}

		else return null;
	}




	//El metodo retorna null si se intenta eliminar una posicion imposible 
	public T removeElement(int pos) {


		if(pos==1)
		{
			return removeFirst();

		}
		else if(primerNodo!=null&&primerNodo.hasNext()&&pos>1)
		{
			NodoClase<T>anterior= primerNodo;
			NodoClase<T>actual= anterior.getNext();
			int i=2;
			while(actual.hasNext()&& i<pos)
			{
				anterior=anterior.getNext();
				actual=actual.getNext();
				i++;

			}
			if(i==pos)
			{
				T temp=actual.getInfo();
				NodoClase<T>siguiente=actual.getNext();
				anterior.setNext(siguiente);
				return temp;
			}
			else
			{
				return null;
			}

		}
		else
		{
			return null;
		}

	}


	public T firstElement() {

		return primerNodo!=null?primerNodo.getInfo():null;
	}



	public T lastElement() {

		if(primerNodo!=null)
		{
			NodoClase<T>actual=primerNodo;

			while(actual.hasNext())
			{
				actual=actual.getNext();
			}

			return actual.getInfo();
		}
		else return null;
	}


	public T getElement(int pos) {


		if(primerNodo!=null)
		{
			int i=1;
			NodoClase<T>actual=primerNodo;
			while(actual.hasNext()&&i<pos)
			{
				actual=actual.getNext();
				i++;
			}

			return actual.getInfo();
		}
		else return null;
	}



	public int size() {

		if(primerNodo!=null){


			int i=1;
			NodoClase<T>actual=primerNodo;
			while(actual.hasNext())
			{
				actual=actual.getNext();
				i++;
			}
			return i;
		}
		else return 0;

	}

	public boolean isEmpty() {


		return primerNodo==null;
	}

	
	

	public int isPresent(T element) 
	{

		int i=-1;

		if(primerNodo!=null) 
		{
			NodoClase<T>actual=primerNodo;
			int j=0;
			boolean pare = false;
			while(!pare)
			{
				j++;
				if(actual.getInfo().compareTo(element)==0)
				{
					pare=true;
					i=j;
				}
				else
				{
					actual=actual.getNext();
					pare=(actual==null);
				}
			}



		}
		return i;
	}

	
	
	
//TODO 
	public void exchange(int pos1, int pos2) {
		
		
	}

	//TODO 
	public void changeInfo(int pos, T newElem) {
		
	}





}
