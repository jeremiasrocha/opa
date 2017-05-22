package br.com.opa.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.ejb.Stateless;

@Stateless
public class UtilZip {

	
	private static final int BUFFER = 2048;

	public static void descompactar(String caminhoENomeDoArquivoCompactado,
			String diretorioDestino) throws ZipException {
		try {
			OutputStream os = null;
			InputStream is = null;
			ZipEntry entrada;
			// Caminho e nome do arquivo a ser descompactado
			ZipFile zipfile = new ZipFile(caminhoENomeDoArquivoCompactado);
			Enumeration<? extends ZipEntry> e = zipfile.entries();
			File arquivo = null;
			byte[] buffer = new byte[BUFFER];
			while (e.hasMoreElements()) {
				entrada = e.nextElement();
				arquivo = new File(diretorioDestino, entrada.getName());

				// se for diretório inexistente, cria a estrutura
				// e pula pra pr�xima entrada
				if (entrada.isDirectory() && !arquivo.exists()) {
					arquivo.mkdirs();
					continue;
				}
				// se a estrutura de diretórios não existe, cria
				if (!arquivo.getParentFile().exists()) {
					arquivo.getParentFile().mkdirs();
				}
				try {
					// l� o arquivo do zip e grava em disco
					is = zipfile.getInputStream(entrada);
					os = new FileOutputStream(arquivo);
					int bytesLidos = 0;
					if (is == null) {
						throw new ZipException("Erro ao ler a entrada do zip: "
								+ entrada.getName());
					}
					while ((bytesLidos = is.read(buffer)) > 0) {
						os.write(buffer, 0, bytesLidos);
					}
				} finally {
					try {
						if (is != null) {
							is.close();
						}
						if (os != null) {
							os.close();
						}
					} catch (Exception ex) {
					}
				}
			}
			zipfile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new ZipException("Erro ao tratar arquivo ZIP");
		}
	}
	
	private static BufferedInputStream entrada = null;
	private static ZipOutputStream saida = null;
	private static byte[] dados = null;
	private static String diretorioRaizDoDiretorioACompactar = null;

	
	
	public static File compactar(File aCompactar, File arquivoCompactado) throws ZipException {
		try {
			System.out.println("Criando " + arquivoCompactado.getName());
			// compress outfile stream
			saida = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(arquivoCompactado)));
			dados = new byte[BUFFER];
			if (isDiretorio(aCompactar)) 
				diretorioRaizDoDiretorioACompactar = aCompactar.getAbsolutePath();
			compactar(aCompactar);
			
			cleanUp(saida);
			cleanUp(entrada);
			System.out.println("Compactação 100%!");
		} catch (Exception e) {
			throw new ZipException("Problemas ao compactar : " + aCompactar.getAbsolutePath() + " para " + arquivoCompactado.getAbsolutePath());
		}
		return new File(arquivoCompactado.getAbsolutePath() + System.getProperty("file.separator") + arquivoCompactado.getName());
	}
	private static void compactar(File aCompactar) throws FileNotFoundException, IOException {
		if (isDiretorio(aCompactar)) {
			String files[] = aCompactar.list();
			File file = null;
			for (int i = 0; i < files.length; i++) {
				file = new File(aCompactar.getAbsolutePath() + System.getProperty("file.separator") + files[i]);
				if (isDiretorio(file)) {
					compactar(file);
				} else {
					adicionarArquivoAoZIP(file);
				}
			}
		} else {
			adicionarArquivoAoZIP(aCompactar);
		}
	}
	
	public static void ziparArquivos(File[] arquivos, File zip) throws Exception {
		FileOutputStream fout = new FileOutputStream(zip);
		ZipOutputStream zos = new ZipOutputStream(fout);
		try{
			for(int i = 0; i < arquivos.length; i++){
				File file = arquivos[i];
				FileInputStream fileInputStream = new FileInputStream(arquivos[i]);
				BufferedInputStream bis = new BufferedInputStream(fileInputStream);
				ZipEntry ze = new ZipEntry(file.getName());
				zos.putNextEntry(ze);
				byte[] data = new byte[1024];
				int byteCount;
				while ((byteCount = bis.read(data, 0, 1024)) > -1){
					zos.write(data, 0, byteCount);
				}
			}
		}finally{	
			zos.closeEntry();
			zos.close();
		}	
	}	
	
	private static void adicionarArquivoAoZIP(File arquivo) throws FileNotFoundException, IOException {
		System.out.println("Adicionando: " + arquivo.getPath());
		entrada = new BufferedInputStream(new FileInputStream(arquivo.getPath()), BUFFER);
		// Escreve o cabecalho (nome, tamanho, etc)
		saida.putNextEntry(new ZipEntry( definirDiretorioENomeDoArquivoASerAdicionado(arquivo) )); 
		int count;
		while ((count = entrada.read(dados, 0, BUFFER)) != -1) {
			saida.write(dados, 0, count);
		}
		saida.closeEntry(); // fecha todo Entry (entrada)
	}
	private static String definirDiretorioENomeDoArquivoASerAdicionado(File arquivo) {
		String dir = new String(arquivo.getAbsolutePath());
		return (diretorioRaizDoDiretorioACompactar != null)? dir.replace(diretorioRaizDoDiretorioACompactar, "") : arquivo.getName();
	}
	private static boolean isDiretorio(File aCompactar) {
		return aCompactar.isDirectory();
	}
	
	private static void cleanUp(InputStream in) throws Exception {
		in.close();
	}
	private static void cleanUp(ZipOutputStream out) throws Exception {
		out.flush();
		out.close();
	}
	
}