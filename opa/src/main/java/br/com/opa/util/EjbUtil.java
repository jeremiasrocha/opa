package br.com.opa.util;

import javax.inject.Named;
import javax.naming.InitialContext;

@Named
public class EjbUtil<T> {
	
	
	public T getService(String name)throws Exception{
		return (T) new InitialContext().lookup("java:global/opa/"+name);
	}

}