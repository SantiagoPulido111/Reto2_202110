package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;
import model.logic.YoutubeVideo;


public final class Ordenamiento<T extends Comparable<T>> 
{



	private static boolean less(Comparable v, Comparable w, Comparator comparador, boolean ascendente)
	{ 

		return ascendente?comparador.compare(v,w)<0:comparador.compare(v,w)>0; 
	}

	private static void exch(ILista lista, int i, int j)
	{ 
		lista.exchange(i, j);
	}


	// 

	/* Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion,
	ascendentemente o descendentemente */
	public final void ordenarInsercion(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		int n = lista.size();
		for (int i = 1+1; i <= n; i++)
		{
			boolean enPosicion = false;
			for (int j = i; j > 1 && !enPosicion; j -= 1)
			{
				int factorComparacion = (ascendente?1:-1)*criterio.compare(lista.getElement(j), lista.getElement(j-1));
				if (factorComparacion < 0)
					lista.exchange(j, j-1);
				else
					enPosicion = true;
			}
		}

	}

	 

	/* Ordenamiento de N elementos en posiciones [1, N], con criterio de comparacion,
	ascendentemente o descendentemente */
	public  void shell(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{ 


		int n = lista.size();
		int h = 1;
		while (h < n/3)
			h = 3 * h + 1;

		while (h >=1)
		{
			// generalizacion del alg. Insertion sort con un valor h >= 1
			for (int i = h+1; i <= n; i++)
			{
				boolean enPosicion = false;
				for (int j = i; j > h && !enPosicion; j -= h)
				{
					int factorComparacion = (ascendente?1:-1) *
							criterio.compare(lista.getElement(j), lista.getElement(j-h));
					if (factorComparacion < 0)
						lista.exchange(j, j-h);
					else
						enPosicion = true;
				}
			}
			h /= 3;
		}






	}


	// 

	private  Comparable<T>[] aux; 

	public  void merge(ILista<T> sublista, int lo, int mid, int hi,Comparator<T> comp, boolean ascendente)
	{ 

		int i = lo, j = mid+1;

		for (int k = lo; k <= hi; k++) 
			aux[k] = sublista.getElement(k+1);

		for (int k = lo; k <= hi; k++) 
			if (i > mid) sublista.changeInfo(k+1, (T) aux[j++]);

			else if (j > hi ) sublista.changeInfo(k+1, (T) aux[i++]);

			else if (less(aux[j], aux[i], comp, ascendente)) sublista.changeInfo(k+1,(T) aux[j++]);

			else sublista.changeInfo(k+1, (T) aux[i++]);
	}


	private  void ordenarMergeSort(ILista sublista, int lo, int hi,Comparator comp, boolean ascendente )
	{ 

		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		ordenarMergeSort(sublista, lo, mid, comp, ascendente); 
		ordenarMergeSort(sublista, mid+1, hi,comp,ascendente); 
		merge(sublista, lo, mid, hi,comp, ascendente); 
	}

	public  void ordenarMergeSort(ILista sublista, Comparator comp, boolean ascendente)
	{
		aux = new Comparable[sublista.size()]; 
		ordenarMergeSort(sublista, 0, sublista.size()-1,comp, ascendente);
	}

	// 


	/**
	 * Método que va dejando el pivot en su lugar, mientras mueve elementos menores
	 * a la izquierda del pivot y elementos mayores a la derecha del pivot.
	 */
	private final int partition(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		int follower, leader;
		follower = leader = lo;
		while (leader < hi)
		{
			int factorComparacion = (ascendente?1:-1) * criterio.compare(lista.getElement(leader), lista.getElement(hi));
			if(factorComparacion < 0)
			{
				lista.exchange(follower, leader);
				follower ++;
			}
			leader ++;
		}
		lista.exchange(follower, hi);
		return follower;
	}

	/**
	 * Se localiza el pivot, utilizando el método de partición.
	 * Luego se hace la recursión con los elementos a la izquierda del pivot
	 * y los elementos a la derecha del pivot.
	 */
	private final void sort(ILista<T> lista, Comparator<T> criterio, boolean ascendente, int lo, int hi)
	{
		if(lo >= hi)
			return;
		int pivot = partition(lista, criterio, ascendente, lo, hi);
		sort(lista, criterio, ascendente, lo, pivot - 1);
		sort(lista, criterio, ascendente, pivot +1, hi);
	}

	/**
	 * Método de entrada, lanza el quick sort recursivo.
	 */
	public final void ordenarQuickSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente)
	{
		sort(lista, criterio, ascendente, 1, lista.size());
	}

}



