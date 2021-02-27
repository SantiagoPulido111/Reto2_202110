package model.data_structures;


public class ListaEncadenada<T extends Comparable<T>> implements ILista<T>
{
	private  NodoClase<T> primerNodo;
	private NodoClase<T> ultimoNodo;
	private  int tamano;


	public ListaEncadenada()
	{
		tamano=0;
	}


	//mejorado
	public void addFirst(T element) 
	{
		if (primerNodo !=null)
		{
			NodoClase<T> temp = primerNodo;
			primerNodo= new NodoClase<T>(element);
			primerNodo.setNext(temp);
		}
		else
		{
			primerNodo= new NodoClase<T>(element);
			ultimoNodo= primerNodo;
		}
		tamano++;
	}


	//mejorado
	public void addLast(T element) 
	{
		if (primerNodo !=null)
		{
			NodoClase<T> nuevo=new NodoClase<T>(element);
			ultimoNodo.setNext(nuevo);
			ultimoNodo=nuevo;
		}
		else
		{
			primerNodo= new NodoClase<T>(element);
			ultimoNodo= primerNodo;
		}
		tamano++;
	}

	//listo
	public void addElement(T element, int pos) 
	{

		pos--;



		if(pos==0)
		{
			addFirst(element);
		}
		else if(pos==tamano)
		{
			addLast(element);
			System.out.println("llego aca");
		}
		else if (primerNodo !=null&&pos>0&&pos<tamano+1)
		{
			NodoClase<T>actual= primerNodo;
			int i =0;
			while(actual.hasNext()&&i<pos-1)
			{
				actual=actual.getNext();
				i++;
			}

			if(i==pos-1)
			{
				NodoClase<T>siguiente= actual.getNext();
				//desde pos -1 incerto el nuevo en pos
				actual.setNext(new NodoClase(element));
				//me muevo a pos 
				actual=actual.getNext();
				//adiciono lo que estaba en pos ( con lo que el guardba y sus suguientes)
				actual.setNext(siguiente);
			}
			tamano++;
		}
	}

	public T removeFirst() 
	{
		tamano--;

		if (primerNodo !=null&&tamano>0)
		{
			T temp=primerNodo.getInfo();
			primerNodo=primerNodo.getNext();
			return temp;
		}

		else if(tamano==0)
		{
			T temp=primerNodo.getInfo();
			primerNodo=null;
			ultimoNodo=null;
			return temp;
		}
		else 
		{
			return null;
		}

	}

	public T removeLast() 
	{
		tamano--;

		if (primerNodo !=null)
		{

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
				ultimoNodo=anterior;
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
	public T removeElement(int pos) 
	{

		pos--;



		if(pos==0)
		{
			return removeFirst();
		}
		//deberia ser tamano -1 pero ya lo reste arriba 
		if(pos==tamano)
		{
			return removeLast();
		}
		else if(primerNodo!=null&&primerNodo.hasNext()&&pos>0)
		{
			tamano--;
			NodoClase<T>anterior= primerNodo;
			NodoClase<T>actual= anterior.getNext();
			int i=1;
			while(actual.hasNext() && i<pos)
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


	public T firstElement() 
	{
		return primerNodo!=null?primerNodo.getInfo():null;
	}

	public T lastElement() 
	{
		return ultimoNodo!=null?ultimoNodo.getInfo():null;
	}

	public T getElement(int pos) 
	{
		pos--;

		if(primerNodo!=null)
		{
			int i=0;
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




	public int size() 
	{
		return tamano;
	}

	public boolean isEmpty() 
	{
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


	//trabaja con otros
	public void exchange(int pos1, int pos2) 
	{
		

		if (pos1 != pos2) 
		{


			NodoClase anterior1 = null ;
			NodoClase actual1 =primerNodo ; 

			int i=1;
			while (actual1 != null && i != pos1) 
			{ 
				anterior1 = actual1; 
				actual1 = actual1.getNext(); 
				i++;
			} 


			// Search for y (keep track of prevY and currY) 
			NodoClase anterior2 = null;
			NodoClase actual2 = primerNodo; 
			int j=1;
			while (actual2 != null && j != pos2) 
			{ 
				anterior2 = actual2; 
				actual2 = actual2.getNext(); 
				j++;
			} 


			if (actual1 != null && actual2 != null) 
			{

				
				if (anterior1 != null) 
					anterior1.setNext(actual2); 
				else primerNodo = actual2; 

				
				if (anterior2 != null) 
					anterior2.setNext(actual1); 
				else  primerNodo = actual1; 

				NodoClase temp = actual1.getNext(); 
				actual1.setNext(actual2.getNext());
				actual2.setNext(temp);

			}

		}
	} 


	public void changeInfo(int pos, T newElem) 
	{


		pos--;


		if(primerNodo!=null)
		{
			int i=0;
			NodoClase<T>actual=primerNodo;
			while(actual.hasNext()&&i<pos)
			{
				actual=actual.getNext();
				i++;
			}

			if(i==pos)
			{
				actual.setInfo(newElem);
			}
		}
	}

	public ILista<T> sublista(int numElementos)
	{
		ListaEncadenada<T> temp = new ListaEncadenada<>();
		NodoClase<T> temp2 = primerNodo;

		for (int i = 0; i < numElementos; i++) 
		{
			temp.addLast(temp2.getInfo());
			temp2 = temp2.getNext();
		}
		return temp;
	}

	public ILista<T> subList(int pos, int size) {



		ListaEncadenada<T> temp = new ListaEncadenada<>();
		NodoClase<T> temp2 = new NodoClase<T>(this.getElement(pos));

		for (int i = 0; i < size&&temp2.hasNext(); i++) 
		{
			temp.addLast(temp2.getInfo());
			temp2 = temp2.getNext();
		}
		return temp;
	}



}