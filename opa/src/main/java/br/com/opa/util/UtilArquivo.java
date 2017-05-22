package br.com.opa.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UtilArquivo {
	
	public static InputStream converterBytesEmInputStream(byte[] dados) throws IOException {
		return new ByteArrayInputStream(dados);
	}
	
	public static void downloadArquivo(ByteArrayOutputStream outputStream, String fileName) throws IOException {
        try{
        	HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
	    	response.reset();
	        response.setContentLength(outputStream.size());
	        response.setContentType("application/".concat(fileName.substring(fileName.length()-3, fileName.length())));
	        response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName).concat(";"));
	        ServletOutputStream outputStreamServlet = response.getOutputStream();
	        outputStream.toByteArray();
	        outputStream.writeTo(outputStreamServlet);
	        outputStreamServlet.flush();
	        outputStreamServlet.close();
	        outputStream.flush();
	        outputStream.close();
	        getFacesContext().responseComplete();
        }catch (Exception e) {
        	e.printStackTrace();
        }    
    }
	
	public static ByteArrayOutputStream converterBytesEmByteArrayOutputStream(byte[] bytes) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
		baos.write(bytes, 0, bytes.length);
		baos.close();
		return baos;
	}
	
	public static byte[] converteInputStreamEmBytes(InputStream inputStream) throws Exception {
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		byte[] bytes = new byte[bis.available()];
		while(bis.available()>0) {
			bis.read(bytes);
		}
		bis.close();
		System.out.println(bytes.length);
		return bytes;
    }
	
	protected static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	public byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		
		if (length > Integer.MAX_VALUE) {
			throw new IOException("File is too large!");
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		try {
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		return bytes;
	}

	public String getStringFromFile(File file) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStream is = new FileInputStream(file);
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			throw new IOException("File is too large!");
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		try {
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
				sb.append(new String(bytes));
			}
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		return sb.toString();
	}
	
	private static String getExtension(File file) {
	    String nomeArq = file.getName();
	    String ext = nomeArq.substring(nomeArq.lastIndexOf('.') + 1);
	    return ext;
	}
	
	public File escreverConteudoEmArquivo(List<String> conteudo,String caminhoCompletoArquivo)throws Exception{
		if(new File(caminhoCompletoArquivo).exists()){
			new File(caminhoCompletoArquivo).delete();
		}
		FileWriter fw = new FileWriter(caminhoCompletoArquivo, true);
		for(String linha:conteudo){
			fw.write(linha+"\r\n");
		}
		fw.close();
		return new File(caminhoCompletoArquivo);
	}
	
	
	public File getFileFromBytes(byte[] bytes,String name) throws Exception {
		File novoArquivo = new File(name);
		novoArquivo.createNewFile();
		OutputStream out = new FileOutputStream(name);
		out.write(bytes);
		out.close();
		return new File(name);
	}
	
	public boolean verificarArquivoExistente(String caminhoArquivo,String nome)throws Exception{
		return new File(caminhoArquivo,nome).exists();
	}
	
	public void criarArquivoEmDisco(String nome,String diretorio,byte[] conteudo)throws Exception{
		
		if(!verificarArquivoExistente(diretorio,nome)){
		File arquivo = new File(diretorio,nome);
		OutputStream outFile = new FileOutputStream(arquivo);
		outFile.write(conteudo);
		outFile.close();
		outFile.flush();
		}
	}
	
	public void criarDeleteArquivoEmDisco(String nome,String diretorio,byte[] conteudo)throws Exception{
			File arquivo = new File(diretorio,nome);
			OutputStream outFile = new FileOutputStream(arquivo);
			outFile.write(conteudo);
			outFile.close();
			outFile.flush();
		
	}
	
	
	/**
	 * Captura de maneira recursiva, o caminho absoluto de cada arquivo encontrado sob o diretório passado. 
	 * @param f
	 * @return uma lista de nomes de arquivos com o seu caminho absoluto 
	 */
	public static final List<String> capturarArquivosDeDiretorio(final File f) {
		List<String> list = new ArrayList<String>();
		final File[] ff = f.listFiles();
		for (int i = 0; i < ff.length; i++) {
			final File file = ff[i];
			if(file.isDirectory()) {
				list.addAll(capturarArquivosDeDiretorio(file));
			}
			else {
				list.add(file.getAbsolutePath());
			}
		}
		return list;
		
	}
	
	public void convertFileToByteArrayOutputStream(File file, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[2048];
        int read = -1;
        while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {   
            byteArrayOutputStream.write(buffer, 0, read);
        }
    }
	
	public static byte[] getBytes(InputStream is) throws IOException {
	    int len;
	    int size = 1024;
	    byte[] buf;

	    if (is instanceof ByteArrayInputStream) {
	      size = is.available();
	      buf = new byte[size];
	      len = is.read(buf, 0, size);
	    } else {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      buf = new byte[size];
	      while ((len = is.read(buf, 0, size)) != -1)
	        bos.write(buf, 0, len);
	      buf = bos.toByteArray();
	    }
	    return buf;
	  }
	
	
	public File criarArquivo(final byte[] b) throws IOException {
		final File file = File.createTempFile("tmp_", "_tmp");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(b);
			baos.writeTo(fos);
			baos.flush();
			baos.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public File criarArquivoExtensao(final byte[] b,String extensao) throws IOException {
		final File file = File.createTempFile("tmp_",extensao);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(b);
			baos.writeTo(fos);
			baos.flush();
			baos.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
	
	public static byte[] readFully(InputStream input) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    while ((bytesRead = input.read(buffer)) != -1)
	    {
	        output.write(buffer, 0, bytesRead);
	    }
	    return output.toByteArray();
	}


}
