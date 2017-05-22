
package br.com.opa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.jxpath.JXPathContext;
/**
 *@author evandro 
 */
public final class UtilSelectItems {

    private UtilSelectItems(){
    	
    }
    
    /**
     * M�todo que cria um SelectItemList
     * 
     * @param lista List
     * @return List<SelectItem>  
     */    
    
    @SuppressWarnings("rawtypes")
    public static List<SelectItem> createSelectItemList(List lista) {

        List<SelectItem> retorno = new ArrayList<SelectItem>();
        Iterator iter = lista.iterator();

        while (iter.hasNext()) {
            Object element = iter.next();

            SelectItem selectItem = new SelectItem(element, String.valueOf(element));
            retorno.add(selectItem);
        }
        return retorno;
    }
    
    /**
     * Recebe uma lista de beans como parametro e devolve uma lista de selectItem do JSF
     * 
     * @param lista = Uma lista de beans qualquer.
     * @param fieldLabel = O atributo dos beans que ser�o exibidos no label do selectItem
     * @return Retorna uma lista de SelectItem para ser usado em p�ginas JSF
     */
    @SuppressWarnings({"rawtypes" })
    public static List<SelectItem> createSelectItemList(List lista, String fieldLabel) {
        List<SelectItem> retorno = new ArrayList<SelectItem>();
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            Object element = iter.next();
            try {
                JXPathContext context = JXPathContext.newContext(element);
                Object label = context.getValue(fieldLabel);
                SelectItem selectItem = new SelectItem(element, String.valueOf(label));
                retorno.add(selectItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }
    
    /**
     * Recebe uma lista de beans como parametro e devolve uma lista de selectItem do JSF.
     * 
     * @param lista = Uma lista de beans qualquer.
     * @param fieldValue = O atributo dos beans que ser�o 
     * inseridos no value dos elementos de sele��o.
     * @param fieldLabel = O atributo dos beans que ser�o exibidos no label do selectItem
     * @return Retorna uma lista de SelectItem para ser usado em p�ginas JSF
     */
    @SuppressWarnings("rawtypes")
    public static List<SelectItem> createSelectItemList(Collection lista, 
	    String fieldValue, String fieldLabel ) {

        List<SelectItem> retorno = new ArrayList<SelectItem>();
        Iterator iter = lista.iterator();

        while (iter.hasNext()) {
            Object element = iter.next();

            try {

                JXPathContext context = JXPathContext.newContext(element);
                Object label = context.getValue(fieldLabel);
                Object value = context.getValue(fieldValue);
                
                if (value instanceof Integer ){
                    value = String.valueOf(value);
                }

                SelectItem selectItem = new SelectItem(value, String.valueOf(label));
                retorno.add(selectItem);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }
    
    
    
    /**
     * Recebe uma lista de beans como parametro e devolve uma lista de selectItem do JSF.
     * 
     * @param lista = Uma lista de beans qualquer.
     * @param fieldValue = O atributo dos beans que ser�o 
     * inseridos no value dos elementos de sele��o.
     * @param fieldLabel = O atributo dos beans que ser�o exibidos no label do selectItem
     * @return Retorna uma lista de SelectItem para ser usado em p�ginas JSF
     */
    @SuppressWarnings("rawtypes")
    public static List<SelectItem> createSelectItemList(Collection lista, 
	    String fieldValue, String... fieldLabel ) {
    	UtilString utilString = new UtilString();
        List<SelectItem> retorno = new ArrayList<SelectItem>();
        Iterator iter = lista.iterator();

        while (iter.hasNext()) {
            Object element = iter.next();

            try {
                JXPathContext context = JXPathContext.newContext(element);
                StringBuilder sbField = new StringBuilder();
                for(int i=0;i<fieldLabel.length;i++){
                	if(!utilString.vazio(context.getValue(fieldLabel[i]))){
	                	sbField.append(context.getValue(fieldLabel[i]));
	                	if(i<(fieldLabel.length-1)){
	                		sbField.append(" - ");
	                	}	
                	}	
                }	
                Object value = context.getValue(fieldValue);
                
                if (value instanceof Integer ){
                    value = String.valueOf(value);
                }

                SelectItem selectItem = new SelectItem(value, String.valueOf(sbField.toString()));
                retorno.add(selectItem);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }
}
