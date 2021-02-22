package model.logic;

import model.data_structures.ILista;

public class Ordenamientos 
{

	private static boolean less(Comparable v, Comparable w)
	{ 
		return v.compareTo(w) < 0; 
	}

	private static void exch(ILista<YoutubeVideo> sublista, int i, int j)
	{ 
		sublista.exchange(i, j);
	}



	//TODO insertion, 

	public static void insertion(ILista<YoutubeVideo> sublista)
	{ 

		int N = sublista.size();
		for (int i = 1; i < N; i++)
		{ 

			for (int j = i; j > 0 && less(sublista.getElement(j), sublista.getElement(j-1)); j--)
				exch(sublista, j, j-1);
			    

		}
	}




	//TODO shell 



	public static void shell(ILista<YoutubeVideo> sublista)
	{ 

		int N = sublista.size();
		int h = 1;
		while (h < N/3) h = 3*h + 1; 

		while (h >= 1)
		{ 
			for (int i = h; i < N; i++)
			{ 
				
				for (int j = i; j >= h && less(sublista.getElement(j), sublista.getElement(j-h)); j -= h)
					exch(sublista, j, j-h);
			}
			h = h/3;
		}
	}



	//TODO merge 



	private static YoutubeVideo[] aux; 

	public static void merge(ILista<YoutubeVideo> sublista, int lo, int mid, int hi)
	{ 

		int i = lo, j = mid+1;

		for (int k = lo; k <= hi; k++) 
			aux[k] = sublista.getElement(k);

		for (int k = lo; k <= hi; k++) 
			if (i > mid) sublista.changeInfo(k, aux[j++]);

			else if (j > hi ) sublista.changeInfo(k, aux[i++]);

			else if (less(aux[j], aux[i])) sublista.changeInfo(k,aux[j++]);

			else sublista.changeInfo(k, aux[i++]);
	}


	private static void sortMerge(ILista<YoutubeVideo> sublista, int lo, int hi)
	{ 

		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sortMerge(sublista, lo, mid); 
		sortMerge(sublista, mid+1, hi); 
		merge(sublista, lo, mid, hi); 
	}

	public static void sortMerge(ILista<YoutubeVideo> sublista)
	{
		aux = new YoutubeVideo[sublista.size()]; 
		sortMerge(sublista, 0, sublista.size() - 1);
	}

	//TODO MergeBu



	public static void mergeBU(ILista<YoutubeVideo> sublista)
	{ 

		int N = sublista.size();
		aux = new YoutubeVideo[N];
		for (int sz = 1; sz < N; sz = sz+sz) 
			for (int lo = 0; lo < N-sz; lo += sz+sz) 
				merge(sublista, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}





	//TODO quicksort 

	
	 public static void quickSort(Comparable[] a)
	 {
	// StdRandom.shuffle(a);
	 quickSort(a, 0, a.length - 1);
	 }
	 private static void quickSort(Comparable[] a, int lo, int hi)
	 {
	 if (hi <= lo) return;
	 int j = partition(a, lo, hi); 
	 quickSort(a, lo, j-1); 
	 quickSort(a, j+1, hi);
	 }
	

	 private static int partition(Comparable[] a, int lo, int hi)
	 { 
		 
	  int i = lo, j = hi+1;
	  
	  Comparable v = a[lo]; 
	  
	  while (true)
	  {
		  
	  while (less(a[++i], v)) if (i == hi) break;
	  while (less(v, a[--j])) if (j == lo) break;
	  if (i >= j) break;
	  exch(a, i, j);
	  }
	  exch(a, lo, j); 
	  return j; 
	 }




}
