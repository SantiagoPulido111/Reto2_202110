package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */

public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T>
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
               elementos = (T[]) new Object[tamanoMax];
               tamanoMax = max;
               tamanoAct = 0;
        }
        
		public void agregar( T dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    T [ ] copia = elementos;
                    elementos = (T[]) new Object[tamanoMax];
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
            elementos = (T[]) new Object[tamanoMax];
            
            //Estoy asumiendo que si el dato esta mas de una vez lo borro todas las veces 
            // Esto en realidad solo se usa si el dato esta mas de una vez 
            
            
            
            int i;
            //Empiezo a rellenar hasta que encuentre el string a eliminar o hasta que llege a una seecion vacia del arreglo
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
            
            //sigo rellenado
           
            if (buscado==dato)
            {
            	for(int a=i;a<tamanoAct+1;a++)
            	{

            		elementos[a-1]=copia[a];	

            	}

            }
			
			return buscado;
		}

}
