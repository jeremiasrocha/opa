package br.com.opa.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class UtilURL {

	public List<String> acessarURL (String stringURL) {
		URL url = null;
		List<String> listaStringRetorno = new ArrayList<String>();
		try {
			url = new URL(stringURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
            	listaStringRetorno.add(inputLine);
            }
            bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaStringRetorno;
	}
	
	public String fazerDownload(String request, String parametros, String nomeArquivo) {
		String destino = "/tmp/" + nomeArquivo;
		try{
			URL url = new URL(request); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
			connection.setUseCaches (false);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(parametros);
			wr.flush();
			InputStream in = connection.getInputStream();
	        FileOutputStream out = new FileOutputStream(destino);
	        byte[] b = new byte[1024];
	        int count;
	        while ((count = in.read(b)) >= 0) {
	            out.write(b, 0, count);
	        }
	        out.flush(); out.close(); in.close();  
			wr.close();
			connection.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		return destino;
	}
	
}
