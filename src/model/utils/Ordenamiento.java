package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;


public final class Ordenamiento<T extends Comparable<T>> 
{

	//TODO revisar esto porque en quick sort a veces compara contra null
	//TODO ponerle el comparador a todo esto con el comparador 
	//TODO hacer que los metodos sean para la clase genercia T

	private static boolean less(Comparable v, Comparable w, Comparator comparador, boolean ascendente)
	{ 

		return ascendente?comparador.compare(v,w)<0:comparador.compare(v,w)>0; 
	}

	private static void exch(ILista lista, int i, int j)
	{ 
		lista.exchange(i, j);
	}


	//TODO insertion, 

	public static void insertion(ILista lista,Comparator comp, boolean ascendente)
	{ 

		int N = lista.size();
		for (int i = 1; i < N; i++)
		{ 

			for (int j = i; j > 0 && less(lista.getElement(j), lista.getElement(j-1),comp,ascendente); j--)
				exch(lista, j, j-1);
		}
	}


	//TODO shell 

	public static void shell(ILista lista, Comparator criterio, boolean ascendente)
	{ 

		int N = lista.size();
		int h = 1;
		while (h < N/3) h = 3*h + 1; 

		while (h >= 1)
		{ 
			for (int i = h; i < N; i++)
			{ 

				for (int j = i; j >= h && less(lista.getElement(j), lista.getElement(j-h),criterio,ascendente); j -= h)
					exch(lista, j, j-h);
			}
			h = h/3;
		}
	}
}



