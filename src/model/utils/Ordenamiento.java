package model.utils;

import java.util.Comparator;

import model.data_structures.ILista;
import model.logic.YoutubeVideo;


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
	
	
	//TODO merge 

		private static Comparable[] aux; 

		public static void merge(ILista sublista, int lo, int mid, int hi,Comparator comp, boolean ascendente)
		{ 

			int i = lo, j = mid+1;

			for (int k = lo; k <= hi; k++) 
				aux[k] = sublista.getElement(k);

			for (int k = lo; k <= hi; k++) 
				if (i > mid) sublista.changeInfo(k, aux[j++]);

				else if (j > hi ) sublista.changeInfo(k, aux[i++]);

				else if (less(aux[j], aux[i], comp, ascendente)) sublista.changeInfo(k,aux[j++]);

				else sublista.changeInfo(k, aux[i++]);
		}


		private static void sortMerge(ILista sublista, int lo, int hi,Comparator comp, boolean ascendente )
		{ 

			if (hi <= lo) return;
			int mid = lo + (hi - lo)/2;
			sortMerge(sublista, lo, mid, comp, ascendente); 
			sortMerge(sublista, mid+1, hi,comp,ascendente); 
			merge(sublista, lo, mid, hi,comp, ascendente); 
		}

		public static void sortMerge(ILista sublista, Comparator comp, boolean ascendente)
		{
			aux = new Comparable[sublista.size()]; 
			sortMerge(sublista, 0, sublista.size() - 1,comp, ascendente);
		}

		
		//TODO MergeBu
		
		public static void mergeBU(ILista sublista,Comparator comp, boolean ascendente)
		{ 
			int N = sublista.size();
			aux = new Comparable[N];
			for (int sz = 1; sz < N; sz = sz+sz) 
				for (int lo = 0; lo < N-sz; lo += sz+sz) 
					merge(sublista, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1), comp,ascendente);
		}


		//TODO quicksort  a veces deja espacions nulos 


		public static void quickSort(ILista sublista,Comparator comp, boolean ascendente)
		{
			// StdRandom.shuffle(a);
			quickSort(sublista, 0, sublista.size() - 1,comp,ascendente);
		}
		
		private static void quickSort(ILista sublista, int lo, int hi,Comparator comp, boolean ascendente)
		{
			if (hi <= lo) return;
			int j = partition(sublista, lo, hi,comp,ascendente); 
			quickSort(sublista, lo, j-1, comp,ascendente); 
			quickSort(sublista, j+1, hi, comp,ascendente);
		}


		private static int partition(ILista sublista, int lo, int hi,Comparator comp, boolean ascendente)
		{ 
			int follower, leader;
			follower=leader=lo;
			while (leader<hi) 
			{
				int compara=(ascendente?1:-1)*comp.compare(sublista.getElement(leader),sublista.getElement(hi));
				if(compara<0)
				{
					sublista.exchange(follower, leader);
					follower++;
				}
				leader++;
			}
			sublista.exchange(follower, hi);
			return follower;
		}
}



