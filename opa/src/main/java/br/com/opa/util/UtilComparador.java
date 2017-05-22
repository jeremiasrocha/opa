package br.com.opa.util;  

import java.lang.reflect.Method;
import java.util.Comparator;

/** 
* @param <ClienteLoclatorAVL106BThread> Classe a ser ordenada 
*/  
public class UtilComparador<T> implements Comparator<T> {  

	private String[] methodNames = null;

	public UtilComparador(String... atributos) {  
		this.methodNames = atributos;  
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })  
	public int compare(T o1, T o2) {
		int ret = 0;
		UtilReflection utilReflection = new UtilReflection();
		try {
			for (String methodName : methodNames) {
				Method method1 = utilReflection.getMethodGet(o1, methodName);
				Comparable comp1 = (Comparable) method1.invoke(o1, new Object[]{});

				Method method2 = utilReflection.getMethodGet(o2, methodName);
				Comparable comp2 = (Comparable) method2.invoke(o2, new Object[]{});

				ret += comp1.compareTo(comp2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return ret;
	}

}  