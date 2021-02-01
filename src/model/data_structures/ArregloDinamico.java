package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico implements IArregloDinamico {
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
        private String elementos[ ];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = new String[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
        
		public void agregar( String dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    String [ ] copia = elementos;
                    elementos = new String[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = copia[i];
                    } 
            	    System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
               }	
               elementos[tamanoAct] = dato;
               tamanoAct++;
       }

		public int darCapacidad() {
			return tamanoMax;
		}

		public int darTamano() {
			return tamanoAct;
		}

		public String darElemento(int i) {
			// TODO LISTO
			return elementos[i];
		}

		public String buscar(String dato) {
			// TODO LISTO
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			
			String buscado=null;
			
			for ( int i = 0; i < tamanoAct && buscado ==null; i++)
            {
				if(elementos[i].compareTo(dato)==0)
				{
					buscado=elementos[i];
				}
            } 
			
			return buscado;
		}

		public String eliminar(String dato) {
			// TODO Preguntar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			
			
			
			String buscado=null;
			
			String [ ] copia = elementos;
            elementos = new String[tamanoMax];
            
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
