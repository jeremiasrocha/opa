package br.com.opa.util;

import java.lang.reflect.InvocationTargetException;

public class UtilEnum<T> {

	
	public T obterValorEnum(T[] listaValoresEnum,String nomeChave,Integer nomeCampoValor){
		T retorno = null;
		try {
			UtilReflection utilReflection = new UtilReflection();
			for(T objEnum:listaValoresEnum){
					if(((Integer)utilReflection.getMethod(objEnum, nomeChave).invoke(objEnum, null)).equals(nomeCampoValor)){
						retorno = objEnum;
					}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public T obterValorEnum(T[] listaValoresEnum,String nomeChave,String nomeCampoValor){
		T retorno = null;
		try {
			UtilReflection utilReflection = new UtilReflection();
			for(T objEnum:listaValoresEnum){
					if(((String)utilReflection.getMethod(objEnum, nomeChave).invoke(objEnum, null)).equals(nomeCampoValor)){
						retorno = objEnum;
					}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
