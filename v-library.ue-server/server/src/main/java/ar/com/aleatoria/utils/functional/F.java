package ar.com.aleatoria.utils.functional;

import java.util.ArrayList;

public class F<T> {

	public static <T> T find(ArrayList<T> list, Predicate<T> predicate){
		int i;		
		for (i=0; i < list.size(); i++){
			if (predicate.satisfies(list.get(i))){
				return list.get(i);				
			}
		}
		throw new ElementNotFoundError();
	} 
	
}
