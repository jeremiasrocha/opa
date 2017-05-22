package br.com.opa.controller.principal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.opa.enums.OpaMensagemEnum;
import br.com.opa.infra.message.MensagemFactory;
import br.com.opa.infra.security.CustomIdentity;


public class OpaController<TO> implements Serializable {
	
	private static final long serialVersionUID = 3509768020700398992L;

	protected @Inject MensagemFactory mensagemFactory;
	protected @Inject CustomIdentity customIdentity;

	private boolean edicao;
	private boolean inclusao;
	private boolean listagem;
	private TO to;
	
	public HttpServletRequest getRequest() { 
		FacesContext ctx = getFacesContext();
		ExternalContext exc = ctx.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) exc.getRequest();
		return request;
	}
	
	public HttpServletResponse getResponse() { 
		FacesContext ctx = getFacesContext();
		ExternalContext exc = ctx.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) exc.getResponse();
		return response;
	}
	
	public ServletContext getServletContext() { 
		FacesContext ctx = getFacesContext();
		ExternalContext exc = ctx.getExternalContext();
		return (ServletContext)exc.getContext();
	}
	
	/**
	 * @param pathRelatorio String
	 * @param listaDS List
	 * @param parametros Map
	 * @param nomeRelatorio String
	 * @throws IOException IOException
	 * @throws JRException JRException
	 */
	protected void exportarParaPDF(String nomeRelatorio,byte[] bytes) throws Exception {
		this.adicionaRelatorioResponse(nomeRelatorio, bytes, "pdf");
	}
	
	/**
	 * @param pathRelatorio String
	 * @param listaDS List
	 * @param parametros Map
	 * @param nomeRelatorio String
	 * @throws IOException IOException
	 * @throws JRException JRException
	 */
	protected void exportarParaXLS(String nomeRelatorio,byte[] bytes) throws Exception {
		this.adicionaRelatorioResponse(nomeRelatorio, bytes, "xls");
	}

	/**
	 * Adicionar relat�rio ao response.
	 * 
	 * @param nomeRelatorio String
	 * @param bytes byte[]
	 * @param formato String
	 * @throws IOException ioe
	 */
	protected void adicionaRelatorioResponse(String nomeRelatorio, byte[] bytes, String formato)
			throws IOException {
		if (nomeRelatorio == null) {
			nomeRelatorio = "relatorio";
		}
		HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
		response.setContentType("application/" + formato);
		response.setContentLength(bytes.length);
		response.setHeader("Content-Disposition", "attachment;filename="
				+ nomeRelatorio + "." + formato + ";");
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		getFacesContext().responseComplete();
	}
	
	protected FacesContext getFacesContext() {
		return FacesContext
				.getCurrentInstance();
	}
	
	protected void download(ByteArrayOutputStream outputStream, String fileName) throws IOException {
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
		return bytes;
    }

	@SuppressWarnings("deprecation")
	public String getRealPath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String realPath = request.getRealPath("");
		return realPath;
	}

	protected String getReportPath(String fileName) {
		return File.separator + "relatorios" + File.separator + fileName;
	}
	
	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
 
	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	protected void adicionarMensagemAlerta(String titulo,String descricao){
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, descricao);
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	protected void adicionarMensagemInfo(String titulo,String descricao){
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, descricao);
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	protected void adicionarMensagemErro(String titulo,String descricao){
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, descricao);
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	protected void adicionarMensagemIncluirSucesso(){
		adicionarMensagemInfo(mensagemFactory.getMensagem(OpaMensagemEnum.INCLUIR_SUCESSO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.INCLUIR_SUCESSO.getKey()));
	}

	protected void adiconarMensagemRegistroJaCadastrado(){
		adicionarMensagemErro(mensagemFactory.getMensagem(OpaMensagemEnum.REGISTRO_JA_CADASTRADO.getKey()),
				mensagemFactory.getMensagem(OpaMensagemEnum.REGISTRO_JA_CADASTRADO.getText()));
	}

	protected void adicionarMensagemAlteracaoSucesso(){
		adicionarMensagemInfo(mensagemFactory.getMensagem(OpaMensagemEnum.ALTERAR_SUCESSO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.ALTERAR_SUCESSO.getText()));
	}

	protected void adicionarMensagemExclusaoSucesso(){
		adicionarMensagemInfo(mensagemFactory.getMensagem(OpaMensagemEnum.EXCLUIR_SUCESSO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.EXCLUIR_SUCESSO.getText()));
	}

	protected void adicionarMensagemExclusaoNaoPossivel(){
		adicionarMensagemErro(mensagemFactory.getMensagem(OpaMensagemEnum.EXCLUIR_ERRO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.EXCLUIR_ERRO.getText()));
	}

	protected void adicionarMensagemAcessoNegado(){
		adicionarMensagemAlerta(mensagemFactory.getMensagem(OpaMensagemEnum.ACESSO_NEGADO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.ACESSO_NEGADO.getText()));
	}

	protected void adicionarMensagemErroIncluir(){
		adicionarMensagemErro(mensagemFactory.getMensagem(OpaMensagemEnum.INCLUIR_ERRO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.INCLUIR_ERRO.getText()));
	}

	protected void adicionarMensagemErroAlterar(){
		adicionarMensagemErro(mensagemFactory.getMensagem(OpaMensagemEnum.ALTERAR_ERRO.getKey()), mensagemFactory.getMensagem(OpaMensagemEnum.ALTERAR_ERRO.getText()));
	}

	@SuppressWarnings("unchecked")
	public TO getTo() throws Exception{
		if (to == null) {
			try{
				to = ((Class<TO>)((ParameterizedType)this.getClass().
					       getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
			}catch(ClassCastException cce){
				to = ((Class<TO>)((ParameterizedType)(((Class<TO>)
						this.getClass().getAnnotatedSuperclass().getType()).getGenericSuperclass()))
							.getActualTypeArguments()[0]).newInstance();
			}	
		}
		return to;
	}

	public void setTo(TO to) {
		this.to = to;
	}

	protected MensagemFactory getMensagemFactory() {
		return mensagemFactory;
	}
	
	public String getStackTrace() {
        Throwable throwable = (Throwable)  FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.exception");
        StringBuilder builder = new StringBuilder();
        builder.append(throwable.getMessage()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            builder.append(element).append("\n");
        }
        return builder.toString();
    }

	public boolean isListagem() {
		return listagem;
	}

	public void setListagem(boolean listagem) {
		this.listagem = listagem;
	}

}