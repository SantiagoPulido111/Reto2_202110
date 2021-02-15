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

		//Si se ingresa una poscion que no sea posible el metodo no hace nada 


	}






	public T removeFirst() {

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


	public T firstElement() {

		return primerNodo!=null?primerNodo.getInfo():null;
	}



	public T lastElement() 
	{

		return ultimoNodo!=null?ultimoNodo.getInfo():null;
	}


	public T getElement(int pos) {


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

	public boolean isEmpty() {


		return primerNodo==null;
	}




	public int isPresent(T element) 
	{

		int i=-1;

		if(primerNodo!=null) 
		{
			NodoClase<T>actual=primerNodo;
			int j=-1;
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





	public void exchange(int pos1, int pos2) 
	{
		addElement(removeElement(pos1),pos2);
		addElement(removeElement(pos2+1),pos1);

	}


	public void changeInfo(int pos, T newElem) 
	{
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





}
