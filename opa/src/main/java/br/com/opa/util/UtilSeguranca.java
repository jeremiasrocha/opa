package br.com.opa.util;

import java.security.MessageDigest;

public class UtilSeguranca {
	
	private String algoritmo;
	
	public UtilSeguranca(String algoritmoCripto) {
		setAlgoritmo(algoritmoCripto);
	}

	public String criptografa(String string) throws Exception{
		MessageDigest md = MessageDigest.getInstance(getAlgoritmo());
		md.update(string.getBytes());
        return bytesToHex(md.digest());
	}
	
	private String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes){
        	result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

}
