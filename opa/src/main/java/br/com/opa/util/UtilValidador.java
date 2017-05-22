package br.com.opa.util;

public class UtilValidador {

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isCPFValido(String cpf) {
		if ((cpf==null) || (cpf.length()!=11) || isCPFContemDigitosIguais(cpf)) return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	}

	public static boolean isCNPJValido(String cnpj) {
		if ((cnpj==null)||(cnpj.length()!=14) || isCNPJContemDigitosIguais(cnpj)) return false;

		Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	}
	
	private static boolean isCPFContemDigitosIguais(String cpf) {
		return cpf.equals("00000000000")
			|| cpf.equals("11111111111")
			|| cpf.equals("22222222222")
			|| cpf.equals("33333333333")
			|| cpf.equals("44444444444")
			|| cpf.equals("55555555555")
			|| cpf.equals("66666666666")
			|| cpf.equals("77777777777")
			|| cpf.equals("88888888888")
			|| cpf.equals("99999999999");
	}

	private static boolean isCNPJContemDigitosIguais(String cnpj) {
		return cnpj.equals("00000000000000")
		|| cnpj.equals("11111111111111")
		|| cnpj.equals("22222222222222")
		|| cnpj.equals("33333333333333")
		|| cnpj.equals("44444444444444")
		|| cnpj.equals("55555555555555")
		|| cnpj.equals("66666666666666")
		|| cnpj.equals("77777777777666")
		|| cnpj.equals("88888888888888")
		|| cnpj.equals("99999999999999");
	}

	public static boolean isCampoPreenchido(String string) {
		return string != null && string != "";
	}

	public static boolean isTelefoneValido(String telefone) {
		if(telefone != null && telefone.trim() != "") {
			if(telefone.length() != 13 || telefone.charAt(0) != '(' || telefone.charAt(3) != ')' || telefone.charAt(8) != '-') return false;
			try {
				new Long(telefone.replace("(","").replace(")","").replace("-",""));
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public static boolean isQuantidadeMinimaDeCaracteresValida(String stringAValidar, int quantidadeMinima) {
		return stringAValidar != null && stringAValidar.length() >= quantidadeMinima;
	}
	
	public static boolean isQuantidadeMaximaDeCaracteresValida(String stringAValidar, int quantidadeMaxima) {
		return stringAValidar != null && stringAValidar.length() <= quantidadeMaxima;
	}
	
	public static boolean isEmailValido(String email) {
		return email != null && email.matches("[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]*[a-zA-Z0-9-_]" ) && email.contains(".") && email.lastIndexOf("@") < email.lastIndexOf(".") && !email.substring(email.indexOf("@")).contains("..");
	}
	
	public static String realizarValidacaoDeEmail(String email, String nomeDoCampo) {
		String resposta = new String();
		if(!isEmailValido(email)) {
			resposta += "O campo " + nomeDoCampo + " não foi formatado como e-mail corretamente! ";
		} 
		return resposta;
	}
	
	public static String realizarValidacaoDeTelefone(String telefone, String nomeDoCampo) {
		String resposta = new String();
		if(!isTelefoneValido(telefone)) {
			resposta += "O campo " + nomeDoCampo + " não possui o formato (##)####-#### válido! ";
		} 
		return resposta;
	}
	
	public static String realizarValidacaoDeNumeroPositivo(String numero, String nomeDoCampo) {
		String resposta = new String();
		if(!isNumeroPositivo(numero)) {
			resposta += "O campo " + nomeDoCampo + " não é um número positivo! ";
		} 
		return resposta;
	}
	
	private static boolean isNumeroPositivo(String numero) {
		try {
			return new Long(numero) > 0;  
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isHoraValida(String hora) {
		try {
			boolean ret = true;
			if (!isCampoPreenchido(hora)) return ret;
			hora = hora.substring(0, 8);
			String [] hms = hora.split(":");
			if (Integer.parseInt(hms[0]) < 0 || Integer.parseInt(hms[0]) > 23) ret = false;
			else if (Integer.parseInt(hms[1]) < 0 || Integer.parseInt(hms[1]) > 59) ret = false;
			else if (Integer.parseInt(hms[2]) < 0 || Integer.parseInt(hms[2]) > 59) ret = false;
			return ret;
		} catch (Exception e) {
			return false;
		}
	}

	public static String realizarValidacoesBasicasDeCampo(String stringAValidar, String nomeDoCampo, Integer tamanhoMinimo, Integer tamanhoMaximo) {
		String resposta = new String();
		if(!isCampoPreenchido(stringAValidar)) {
			resposta += "O campo " + nomeDoCampo + " não foi preenchido! ";
		} else if(tamanhoMinimo != null && !isQuantidadeMinimaDeCaracteresValida(stringAValidar, tamanhoMinimo)) {
			resposta += "O campo " + nomeDoCampo + " não possui a quantidade mínima de caracteres (" + tamanhoMinimo + ")! ";	
		} else if(tamanhoMaximo != null && !isQuantidadeMaximaDeCaracteresValida(stringAValidar, tamanhoMaximo)){
			resposta += "O campo " + nomeDoCampo + " excede a quantidade máxima de caracteres (" + tamanhoMaximo + ")! ";
		}
		return resposta;
	}

}
