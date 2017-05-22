package br.com.opa.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe responsável por oferecer serviços para os valores de string
 * 
 * @author Evandro Moura
 */
public class UtilString {

	private List<String> valores;
	public static final String QUEBRA_LINHA = "\n";
	public static final String QUEBRA_LINHA_WINDOWS = "\r\n";
	public static final String QUEBRA_LINHA2 = "\\n";
	public static final String QUEBRA_LINHA3 = "\r";
	public static final String QUEBRA_LINHA4 = "\\r";
	public static final String QUEBRA_LINHA5 = "\\r\\n";

	/** Método construtor */
	public UtilString() {
		valores = new ArrayList<String>();
	}

	/**
	 * Método que verifica se está vazio
	 * 
	 * @param valor
	 *            String
	 * @return boolean
	 */
	public boolean vazio(String valor) {
		boolean retorno = false;
		if (valor == null || valor.trim().equals("")) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Método que verifica se está vazio
	 * 
	 * @param valor
	 *            Object
	 * @return boolean
	 */

	public boolean vazio(Object valor) {
		boolean retorno = false;
		if (valor == null || valor.toString().equals("")) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Método que obtem String do valor
	 * 
	 * @param valor
	 *            String
	 * @return boolean
	 */

	public static String getString(String valor) {
		String retorno = "";
		if (valor != null) {
			retorno = valor;
		}
		return retorno;
	}

	/**
	 * Quando a condição para ser preenchido valer para todos os campos esse
	 * metodo será util para invalidar a operação em caso de um único campo
	 * não preenchido
	 * 
	 * @param valor
	 *            String...
	 * @return boolean
	 */
	public boolean vazio(String... valor) {
		boolean retorno = false;
		for (String string : valor) {
			if (vazio(string)) {
				retorno = true;
			}
		}
		return retorno;
	}

	/**
	 * Metodo para verificar valores nulos ou vazios, se um apenas estiver vazio
	 * uma excecao será lançada
	 * 
	 * @param valor
	 *            String...
	 * @return boolean
	 */
	public boolean preenchido(String... valor) {
		boolean retorno = true;
		if (vazio(valor)) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Adiciona um valor string
	 * 
	 * @param valor
	 *            String
	 */
	public void add(String valor) {
		valores.add(valor);
	}

	/**
	 * Retorna os valores adicionados.
	 * 
	 * @return String[]
	 */
	public String[] valores() {
		return this.valores.toArray(new String[valores.size()]);
	}

	/**
	 * Reduz a string para uma apresentacao em listas e buscas, com o objetivo
	 * de n�o desformatar a interface com o cliente
	 * 
	 * @param valor
	 *            String
	 * @param qtd
	 *            Integer
	 * @return String
	 */

	public static String truncar(String valor, Integer qtd) {
		String retorno;
		if (valor.length() > qtd) {
			StringBuilder builder = new StringBuilder(valor.substring(0, qtd));
			builder.append("...");
			retorno = builder.toString();
		} else {
			retorno = valor;
		}
		return retorno;
	}
	
	public static String quebrarLinhaComBr(String valor, Integer qtd){
		String retorno = null;
		if(valor != null) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for(char c:valor.toCharArray()) {
				sb.append(c);
				count++;
				if(count>qtd) {
					sb.append("<br />");
					count=0;
				}
			}
			retorno = sb.toString();
		}	
		return retorno;
	}

	/**
	 * Completa com zeros a esquerda, até a string ter o tamanho tamanhoTotal
	 * 
	 * @param string
	 *            A string de entrada
	 * @param tamanhoTotal
	 *            O tamanho total da string resultate
	 * @return Uma nova string, com zeros a esquerda
	 */
	public String completaComZerosAEsquerda(String string,
			int tamanhoTotal) {
		if (string == null) {
			string = "";
		}
		for (int i = string.length(); i < tamanhoTotal; i++) {
			string = "0" + string;
		}
		return string;
	}
	
	
	/**
	 * Completa com espacos a direita, até a string ter o tamanho tamanhoTotal
	 * 
	 * @param string
	 *            A string de entrada
	 * @param tamanhoTotal
	 *            O tamanho total da string resultate
	 * @return Uma nova string, com zeros a esquerda
	 */
	public static String completaComEspacoADireita(String string,
			int tamanhoTotal) {
		if (string == null) {
			string = "";
		}
		for (int i = string.length(); i < tamanhoTotal; i++) {
			string = string+" ";
		}
		return string;
	}

	/**
	 * Retira Caracteres Especiais
	 * 
	 * @param valor Valor
	 * @return Valor sem os caracteres
	 */
	public String retiraCaracteresEspeciais(String valor) {
		return valor.replaceAll("[^0-9a-zA-Z]", "");
	}

//	/**
//	 * Retorna uma String substituindo seus caracteres acentuados pelos sem
//	 * acentos e espacos em branco
//	 * 
//	 * @param valor
//	 *            String a ser transformada
//	 * @return String String transformada
//	 */
//	public static String extrairNumeroLetrasString(String valor) {
//		valor = Normalizer.normalize(valor, Normalizer.DECOMP, 0);
//		valor = valor.replaceAll("[^\\p{ASCII}]", "");
//		valor = retiraCaracteresEspeciais(valor);
//		return valor;
//	}

//	/**
//	 * Verifica as String sao iguais ignorando os acentos, caracteres especiais
//	 * e espacos em branco entre elas
//	 * 
//	 * @param valorUm
//	 *            String um
//	 * @param valorDois
//	 *            String dois
//	 * @return boolean true caso as String sejam iguais ou null
//	 */
//	public static boolean isStringsIguaisAbsolutamente(String valorUm,
//			String valorDois) {
//		return extrairNumeroLetrasString(valorUm).toLowerCase().equals(
//				extrairNumeroLetrasString(valorDois).toLowerCase());
//	}
	
	/**
	 * Stack Trace
	 * @param e Excecao
	 * @return String do StackTrace
	 */
	public static String stackTrace(Exception e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		return writer.getBuffer().toString();
	}
	
	public String removeAcentos(String str) {
		  str = Normalizer.normalize(str, Normalizer.Form.NFD);
		  str = str.replaceAll("[^\\p{ASCII}]", "");
		  return str;
		 
	}
	
	/**
	 * Método que remove os pontos e os espaços em branco de uma string
	 * e retorna a string apenas
	 * @param str
	 * @return string sem ponto e sem espaço em branco
	 */
	public String removePontosEEspacosBrancos(String str) {
		String nova = str;
		nova = nova.replaceAll("[^0-9]", "");
		return nova;
	}
	 
    public static String removeCaracter(String string, String caracter) {
        StringTokenizer stringTokenizer = new StringTokenizer(string.toString(), caracter);
        StringBuffer stringBuffer = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            stringBuffer.append(stringTokenizer.nextToken());
        }
        return stringBuffer.toString();
    }

	public static String removeTag(String elemento) {
		String aux = "";
		if (elemento.contains("<ans:")){
			 aux = elemento.substring(elemento.indexOf("<ans:") + 5, elemento.length());
			 aux = aux.substring(0, aux.indexOf(">"));
		}else{
			if (elemento.contains("<ansTISS:")){
				 aux = elemento.substring(elemento.indexOf("<ansTISS:") + 9, elemento.length());
				 aux = aux.substring(0, aux.indexOf("/>"));
			}else{
				if (elemento.contains("<")){
					aux = elemento.substring(elemento.indexOf("<") + 1, elemento.length());
					aux = aux.substring(0, aux.indexOf("/>"));
				}else{
					if (elemento.contains("&lt;ans:")){
						aux = elemento.substring(elemento.indexOf("&lt;ans:") + 8, elemento.length());
						aux = aux.substring(0, aux.indexOf("&gt;"));
					}
				}
			}	
		}
		return aux;
	}

	public static String removeCaracteresExcedentes(String string, int limite) {
		return (string.length() > limite)? string.substring(0, limite) : string;
	}
    
    public static String replaceAll(String string, String caracter, String por){
    	if (string == null){
    		return "";
    	}
        char[] carac =  string.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        String aux = "";
        for (char element : carac) {
            aux = String.valueOf(element);
            if (aux.equals(caracter)){
                stringBuffer.append(por);                
            }else{
                stringBuffer.append(aux);
            }
        }
        return stringBuffer.toString();
    }
    
    public static Double removeMascaraValor(String valor){
    	String inteiro = "0";
    	String fracao = null;
    	
        if (valor == null || valor.equals("") || valor.equals("0")){
            valor = "0,00";   
        }
        if (valor.startsWith(",")) {
        	valor = valor.replaceAll(",", "0,");/*CASO O VALOR ENVIADO nÃ£o VENHA COM O "0" ANTES DAS CASAS DECIMAIS*/
        }
        if(valor.indexOf(",") > 0) {
        	inteiro = valor.substring(0, valor.indexOf(","));
        }else if(valor.contains(".")) {
        		return new Double(valor).doubleValue();
        }else {
        	inteiro = valor;
        }
        inteiro = replaceAll(inteiro, ".", "");
       
        int flagError = valor.indexOf(","); //CASO nÃ£o CONTIVER "," RETORNA -1 E EVITA CALCULO ERRADO
        if(flagError == -1) {
        	fracao = "0";
        } else {
        	fracao = valor.substring(valor.indexOf(",")+1);
        }
        return new Double(inteiro+"."+fracao).doubleValue();
    }

	public static String removeZerosAEsquerda(String string) {
		return string.replaceAll("^0*", "");
	}

	public static boolean isNumerico(String string) {
		if (new UtilString().vazio(string)) {
			return false;
	    }
	    int sz = string.length();
	    for (int i = 0; i < sz; i++) {
	        if (Character.isDigit(string.charAt(i)) == false) {
	            return false;
	        }
	    }
	    return true;
	}

	public static boolean isNumeroInteiro(String string) {
		return string.matches("[0-9]+");
	}

	public static String removeNaoNumericos(String str) {
		return !UtilNullEmpty.isNullOrEmpty(str)?str.replaceAll("[^0-9]", ""):"";
	}
	
	public static String retirarQuebrasLinha(String tmpSql, boolean trocarPorEspacoEmBranco) {
		tmpSql = tmpSql.replaceAll(QUEBRA_LINHA_WINDOWS, (trocarPorEspacoEmBranco)? " " : "");
		tmpSql = tmpSql.replaceAll(QUEBRA_LINHA2, (trocarPorEspacoEmBranco)? " " : "");
		tmpSql = tmpSql.replaceAll(QUEBRA_LINHA3, (trocarPorEspacoEmBranco)? " " : "");
		tmpSql = tmpSql.replaceAll(QUEBRA_LINHA4, (trocarPorEspacoEmBranco)? " " : "");
		tmpSql = tmpSql.replaceAll(QUEBRA_LINHA5, (trocarPorEspacoEmBranco)? " " : "");
        char[] a = tmpSql.toCharArray();
        StringBuilder sb = new StringBuilder(tmpSql.length());
        for (int i = 0; i < a.length; i++) {
            if (a[i]==(QUEBRA_LINHA.toCharArray()[0]) || a[i]=='\r') {
            	if (trocarPorEspacoEmBranco) {
            		a[i] = ' ';
            	} else {
            		continue;
            	}
            }
            sb.append(a[i]);
        }
        return sb.toString();
	}
	
	public static String substring(String string, int inicio, int fim){
		String texto= string;
		if(string.length() >= fim){
			texto = string.substring(inicio, fim);
		}
		return texto;
	}
	
	public String removerMascaraCpf(String cpf) {
		String str = new String();
		
		if (!vazio(cpf)) {
			str = cpf;
			while (str.indexOf("-") != -1) {
				if (str.indexOf("-") != 0) {
					str = str.substring(0, str.indexOf("-"))
							+ str.substring(str.indexOf("-") + 1);
				} else {
					str = str.substring(str.indexOf("-") + 1);
				}
			}
			while (str.indexOf(".") != -1) {
				if (str.indexOf(".") != 0) {
					str = str.substring(0, str.indexOf("."))
							+ str.substring(str.indexOf(".") + 1);
				} else {
					str = str.substring(str.indexOf(".") + 1);
				}
			}
		}
		return str;
	}
	
	public static String completaZeroEsquerda(String string, int tamanho) {
    	if(string == null) {
			return string;
		}
        for (int i = 0; tamanho > string.length(); i++) {
            string = "0" + string;
        }
        return string;
    }

}