package br.com.opa.enums;

public enum OpaMensagemEnum {

	
	/*Mensagens de Caso de Uso*/
	UC_TEMPLATE_ERRO_VALIDACAO_NOME("label.uc.template.erro.nome","label.uc.template.erro.nome"),
	ERROR_CONFIRM_LOGIN("label.uc.usuario.erro.senhaconfirmacao","label.uc.usuario.erro.senhaconfirmacao"),

	//TISS
	UC_GUIA_TISS_EM_LOTE_FATURAMENTO_FECHADO("label.uc.tiss.exception.guiaTissEmLoteFaturamentoFechado","label.uc.tiss.exception.guiaTissEmLoteFaturamentoFechado"),
	UC_GUIA_TISS_EM_LOTE_FATURAMENTO_ABERTO("label.uc.tiss.exception.guiaTissEmLoteFaturamentoAberto","label.uc.tiss.exception.guiaTissEmLoteFaturamentoAberto"),
	UC_GUIA_TISS_ERRO_REMOVER_GUIA_LOTE("label.uc.tiss.exception.erroAoRemoverGuiaLoteFaturamento","label.uc.tiss.exception.erroAoRemoverGuiaLoteFaturamento"),
	UC_GUIA_TISS_TIPO_GUIA_INVALIDO("label.uc.tiss.exception.tipoGuiaInvalido", "label.uc.tiss.exception.tipoGuiaInvalido"),

	//TISS CANCELAMENTO GUIA
	UC_GUIA_TISS_SUCESSO_CANCELAMENTO("label.uc.tiss.msg.sucessoCancelamento", "label.uc.tiss.msg.sucessoCancelamento"),
	UC_GUIA_TISS_ERRO_CANCELAMENTO_GUIA_VINCULADA("label.uc.tiss.exception.cancelarGuiaComGuiaVinculada","label.uc.tiss.exception.cancelarGuiaComGuiaVinculada"),
	UC_GUIA_TISS_ERRO_CANCELAMENTO_ANEXO("label.uc.tiss.exception.cancelarGuiaComAnexo","label.uc.tiss.exception.cancelarGuiaComAnexo"),
	UC_GUIA_TISS_ERRO_CANCELAMENTO_USUARIO_SEM_PERMISSAO("label.uc.tiss.exception.usuarioSemPermissaoParaCancelarGuia","label.uc.tiss.exception.usuarioSemPermissaoParaCancelarGuia"),
	UC_GUIA_TISS_ERRO_CANCELAMENTO_GUIA_OPERADORA("label.uc.tiss.exception.cancelarGuiaNaOperadora","label.uc.tiss.exception.cancelarGuiaNaOperadora"),

	//TISS PEDIDO INSISTENCIA GUIA
	UC_GUIA_TISS_SUCESSO_INSISTENCIA("label.uc.tiss.msg.sucessoInsistencia","label.uc.tiss.msg.sucessoInsistencia"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_USUARIO_SEM_PERMISSAO("label.uc.tiss.exception.usuarioSemPermissaoParaPedirInsistencia","label.uc.tiss.exception.usuarioSemPermissaoParaPedirInsistencia"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_DATA_NAO_PERMITIDA("label.uc.tiss.exception.dataNaoPermitidaParaPedidoDeInsistencia", "label.uc.tiss.exception.dataNaoPermitidaParaPedidoDeInsistencia"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_SENHA_INVALIDA("label.uc.tiss.exception.senhaInvalida","label.uc.tiss.exception.senhaInvalida"),
	UC_GUIA_TISS_ERRO_PEDIR_INSISTENCIA_OPERADORA("label.uc.tiss.exception.erroAoPedirInsistenciaNaOperadora","label.uc.tiss.exception.erroAoPedirInsistenciaNaOperadora"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_SOLICITACAO_NAO_REGISTRADA_OPERADORA_BENEFICIARIO("label.uc.tiss.exception.erroInsistenciaSolicitacaoNaoRegistradaOperadoraBeneficiario","label.uc.tiss.exception.erroInsistenciaSolicitacaoNaoRegistradaOperadoraBeneficiario"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_SOLICITACAO_NAO_NEGADA("label.uc.tiss.exception.erroInsistenciaSolicitacaoNaoNegada","label.uc.tiss.exception.erroInsistenciaSolicitacaoNaoNegada"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_PERMITIDA_PARA_GUIAS_NEGADAS("label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadas","label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadas"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_PERMITIDA_PARA_GUIAS_NEGADAS_TOTAL_OU_PARCIALMENTE("label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadasOuParcialmenteNegadas","label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadasOuParcialmenteNegadas"),
	UC_GUIA_TISS_ERRO_INSISTENCIA_PERMITIDA_PARA_GUIAS_NEGADAS_TOTAL_OU_PARCIALMENTE_OU_AUTORIZADAS("label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadasOuParcialmenteNegadasOuAutorizadas","label.uc.tiss.exception.erroInsistenciaPermitidaParaGuiasNegadasOuParcialmenteNegadasOuAutorizadas"),
	UC_GUIA_TISS_ERRO_PEDIDO_INSISTENCIA_BLOQUEADO("label.uc.tiss.exception.erroPedidoInsistenciaBloqueado","label.uc.tiss.exception.erroPedidoInsistenciaBloqueado"),

	//TISS ARQUIVO
	UC_TISS_ARQUIVO_ERRO_CADASTRAR_ARQUIVO_AWS("label.uc.guiaTissArquivo.msg.erroCadastrarArquivoAWS","label.uc.guiaTissArquivo.msg.erroCadastrarArquivoAWS"),
	UC_TISS_ARQUIVO_ERRO_RECUPERAR_ARQUIVO_AWS("label.uc.guiaTissArquivo.msg.erroRecuperarArquivoAWS","label.uc.guiaTissArquivo.msg.erroRecuperarArquivoAWS"),

	//TISS PRORROGAR DATA VALIDADE AUTORIZAÇÃO
	UC_GUIA_TISS_SUCESSO_PRORROGACAO_VALIDADE("label.uc.prorrogarDataValidade.msg.sucessoProrrogacaoValidade","label.uc.prorrogarDataValidade.msg.ProrrogacaoValidade"),
	UC_GUIA_TISS_ERRO_PRORROGACAO_VALIDADE_SISTEMA_GESTAO("label.uc.prorrogarDataValidade.msg.erroSistemaGestao","label.uc.prorrogarDataValidade.msg.erroSistemaGestao"),
	UC_GUIA_TISS_ERRO_PRORROGACAO_VALIDADE_USUARIO("label.uc.prorrogarDataValidade.msg.erroUsuarioSemPermisao","label.uc.prorrogarDataValidade.msg.erroUsuarioSemPermisao"),
	UC_GUIA_TISS_ERRO_PRORROGACAO_VALIDADE_QUANTIDADE_DIAS("label.uc.prorrogarDataValidade.msg.erroQuantidadeDias","label.uc.prorrogarDataValidade.msg.erroQuantidadeDias"),
	UC_GUIA_TISS_ERRO_PRORROGACAO_VALIDADE_GUIA_EM_PRODUCAO_MEDICA("label.uc.prorrogarDataValidade.msg.erroGuiaEmProducaoMedica","label.uc.prorrogarDataValidade.msg.erroGuiaEmProducaoMedica"),

	//VALORIZACAO
	ERRO_AO_VALORIZAR_PROCEDIMENTOS("label.erroValorizarProcedimentos", "label.erroValorizarProcedimentos"),

	//PESQUISA BENEFICIARIO
	UC_PESQUISA_BENEFICIARIO_PESQUISA_NAO_PERMITIDA_PARA_OPERADORA("label.uc.msg.beneficiario.pequisaNaoPermitidaParaOperadora","label.uc.msg.beneficiario.pequisaNaoPermitidaParaOperadora"),
	UC_BENEFICIARIO_ERRO_NAO_ENCONTRADO_GESTAO("label.uc.beneficiario.msg.beneficiarioNaoEncontradoNoGestao","label.uc.beneficiario.msg.beneficiarioNaoEncontradoNoGestao"),
	UC_BENEFICIARIO_ERRO_NAO_ENCONTRADO_WSD("label.uc.beneficiario.msg.beneficiarioNaoEncontradoNoWSD","label.uc.beneficiario.msg.beneficiarioNaoEncontradoNoWSD"),
	
	//BENEFICIARIO
	UC_BENEFICIARIO_ERRO_NOME_INVALIDO("label.uc.beneficiario.msg.nomeBeneficiarioInvalido", "label.uc.beneficiario.msg.nomeBeneficiarioInvalido"),
	UC_BENEFICIARIO_ERRO_CODIGO_INVALIDO("label.uc.beneficiario.msg.codigoBeneficiarioInvalido", "label.uc.beneficiario.msg.codigoBeneficiarioInvalido"),
	UC_BENEFICIARIO_ERRO_OPERADORA_INVALIDA("label.uc.beneficiario.msg.operadoraBeneficiarioInvalida", "label.uc.beneficiario.msg.operadoraBeneficiarioInvalida"),

	//CONTRATADO
	UC_CONTRATADO_ERRO_NAO_ENCONTRADO_GESTAO("label.uc.contratado.msg.contratadoNaoEncontradoNoGestao","label.uc.contratado.msg.contratadoNaoEncontradoNoGestao"),

	//PROTOCOLO ATENDIMENTO
	UC_PROTOCOLO_ATENDIMENTO_CANCELADO_SUCESSO("label.uc.protocoloAtendimento.msg.protocoloCanceladoComSucesso","label.uc.protocoloAtendimento.msg.protocoloCanceladoComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_COMPLEMENTO_CADASTRADO_SUCESSO("label.uc.protocoloAtendimento.msg.complementoCadastradoComSucesso","label.uc.protocoloAtendimento.msg.complementoCadastradoComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_FINALIZADO_SUCESSO("label.uc.protocoloAtendimento.msg.finalizadoComSucesso","label.uc.protocoloAtendimento.msg.finalizadoComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_RESPOSTA_CADASTRADA_SUCESSO("label.uc.protocoloAtendimento.msg.respostaCadastradaComSucesso","label.uc.protocoloAtendimento.msg.respostaCadastradaComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_ENVIADA_SUCESSO("label.uc.protocoloAtendimento.msg.solicitacaoEnviadaComSucesso","label.uc.protocoloAtendimento.msg.solicitacaoEnviadaComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_CONSULTAR_STATUS_SUCESSO("label.uc.protocoloAtendimento.msg.consultarStatusComSucesso","label.uc.protocoloAtendimento.msg.consultarStatusComSucesso"),
	UC_PROTOCOLO_ATENDIMENTO_ERRO_IMPRIMIR("label.uc.protocoloAtendimento.msg.erroImprimir","label.uc.protocoloAtendimento.msg.erroImprimir"),
	UC_PROTOCOLO_ATENDIMENTO_NAO_ENCONTRADO("label.uc.protocoloAtendimento.msg.protocoloNaoEncontrado","label.uc.protocoloAtendimento.msg.protocoloNaoEncontrado"),
	UC_PROTOCOLO_ATENDIMENTO_ERRO_CADASTRO_GESTAO("label.uc.protocoloAtendimento.msg.erroCadastroGestao","label.uc.protocoloAtendimento.msg.erroCadastroGestao"),

	//GRUPO CONTROLE PROTOCOLO ATENDIMENTO
	UC_GRUPO_PROTOCOLO_ATENDIMENTO_NOME_JA_CADASTRADO("label.uc.grupoControleProtocoloAtendimento.nomeJaCadastrado","label.uc.grupoControleProtocoloAtendimento.nomeJaCadastrado"),
	UC_GRUPO_PROTOCOLO_ATENDIMENTO_NAO_ENCONTRADO("label.uc.grupoControleProtocoloAtendimento.grupoControleProtocoloNaoEncontrado","label.uc.grupoControleProtocoloAtendimento.grupoControleProtocoloNaoEncontrado"),

	//USUARIO
	UC_USUARIO_LOGIN_JA_CADASTRADO("label.uc.usuario.msg.loginJaCadastrado", "label.uc.usuario.msg.loginJaCadastrado"),
	UC_USUARIO_SENHA_ALTERADA_SUCESSO("label.uc.usuario.msg.senhaAlteradaComSucesso","label.uc.usuario.msg.senhaAlteradaComSucesso"),
	UC_USUARIO_NAO_AUTORIZADO("label.uc.usuario.msg.usuarioNaoAutorizado","label.uc.usuario.msg.usuarioNaoAutorizado"),
	UC_USUARIO_NAO_ENCONTRADO("label.uc.usuario.msg.usuarioNaoEncontrado","label.uc.usuario.msg.usuarioNaoEncontrado"),
	
	//UC OPERADORA
	UC_OPERADORA_OPERADORA_NAO_ENCONTRADA("label.uc.operadora.operadoraNaoEncontrada","label.uc.operadora.operadoraNaoEncontrada"),

	//UC REGRA
	UC_REGRA_ITEM_JA_CADASTRADO("label.uc.regra.msg.itemJaCadastrado", "label.uc.regra.msg.itemJaCadastrado"),

	//UC RESTRICAO GESTAO SERVICO
	UC_RESTRICAO_GESTAO_SERVICO_CARENCIA("label.uc.restricaoGestaoServico.restricaoCarencia","label.uc.restricaoGestaoServico.restricaoCarencia"),
	UC_RESTRICAO_GESTAO_SERVICO_COBERTURA("label.uc.restricaoGestaoServico.restricaoCobertura","label.uc.restricaoGestaoServico.restricaoCobertura"),
	UC_RESTRICAO_GESTAO_SERVICO_QUANTIDADE("label.uc.restricaoGestaoServico.restricaoQuantidade","label.uc.restricaoGestaoServico.restricaoQuantidade"),

	//UC_ANALISE_ATENDIMENTO
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_REQUER_AUDITORIA_MEDICA("label.uc.analiseAtendimento.msg.servicoRequerAuditoriaMedica","label.uc.analiseAtendimento.msg.servicoRequerAuditoriaMedica"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_PROC_FORA_ROL_ANS("label.uc.analiseAtendimento.msg.servicoForaRolANS", "label.uc.analiseAtendimento.msg.servicoForaRolANS"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_RESTRICAO_CONTRATUAL("label.uc.analiseAtendimento.msg.servicoComRestricaoContratual","label.uc.analiseAtendimento.msg.servicoComRestricaoContratual"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_PERICIA_MEDICA("label.uc.analiseAtendimento.msg.servicoRequerPericiaMedica", "label.uc.analiseAtendimento.msg.servicoRequerPericiaMedica"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_PARECER_ESPECIALISTA("label.uc.analiseAtendimento.msg.servicoRequerParecerMedico", "label.uc.analiseAtendimento.msg.servicoRequerParecerMedico"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_FORA_DATA_ATENDIMENTO("label.uc.analiseAtendimento.msg.servicoForaDataAtendimento","label.uc.analiseAtendimento.msg.servicoForaDataAtendimento"),
	UC_ANALISE_ATENDIMENTO_MSG_BLOQ_INTERVALO_MINIMO("label.uc.analiseAtendimento.msg.servicoRequerIntervalorMinimo","label.uc.analiseAtendimento.msg.servicoRequerIntervalorMinimo"),
	
	UC_ANALISE_ATENDIMENTO_MSG_ANALISE_ENCAMINHADA_SUCESSO("label.uc.analiseAtendimento.msg.analiseEncaminhadaSucesso", "label.uc.analiseAtendimento.msg.analiseEncaminhadaSucesso"),
	UC_ANALISE_ATENDIMENTO_MSG_USUARIO_VISUALIZANDO("label.uc.analiseAtendimento.msg.analiseAtendimentoSendoVisualizadaPeloUsuario", "label.uc.analiseAtendimento.msg.analiseAtendimentoSendoVisualizadaPeloUsuario"),

	/*Mensagens Globais*/
	INCLUIR_SUCESSO("label.msg.incluirSucesso","label.msg.textIncluirSucesso"),
	ALTERAR_SUCESSO("label.msg.alterarSucesso","label.msg.textAlterarSucesso"),
	EXCLUIR_SUCESSO("label.msg.excluirSucesso","label.msg.textExcluirSucesso"),
	ERRO_INESPERADO("label.global.erroInesperado", "label.global.erroInesperado"),
	EXCLUIR_ERRO("label.msg.excluirErro","label.msg.textExcluirErro"),
	INCLUIR_ERRO("label.msg.incluirErro","label.msg.incluirErro"),
	ALTERAR_ERRO("label.msg.alterarErro","label.msg.alterarErro"),
	ERRO_REGISTRO_ALTERADO_POR_OUTRO_USUARIO("label.msg.erroRegistroAlteradoPorOutroUsuario","label.msg.erroRegistroAlteradoPorOutroUsuario"),
	REGISTRO_JA_CADASTRADO("label.msg.registroJaCadastrado","label.msg.registroJaCadastrado"),

	ACESSO_NEGADO("label.global.acessoNegado","label.global.acessoNegado"),
	
	ERROR_LOGIN("label.global.error.login","label.global.error.login"),
	EMAIL_INCORRETO("label.global.email.incorreto","label.global.email.incorreto"),
	EMAIL_INVALIDO("label.global.emailInvalido","label.global.emailInvalido"),
	ENVIO_EMAIL_SUCESSO("label.global.email.sucesso","label.global.email.sucesso"),
	ENVIO_EMAIL_ERROR("label.global.email.error","label.global.email.error"),
	EMAIL_REMETENTE("label.global.email.remetente","label.global.email.remetente"),
	EMAIL_ASSUNTO("label.global.email.assunto","label.global.email.assunto"),
	EMAIL_CONTEUDO("label.global.email.conteudo","label.global.email.conteudo"),
	CPF_INVALIDO("label.global.cpfInvalido","label.global.cpfInvalido"),
	TELEFONE_INVALIDO("label.global.telefoneInvalido","label.global.telefoneInvalido"),
	
	ARQUIVO_INVALIDO("label.global.arquivo.invalido","label.global.arquivo.invalido"),
	ARQUIVO_XML_INVALIDO("label.global.arquivo.xmlInvalido","label.global.arquivo.xmlInvalido"), 
	
	UC_SMS_DDD_INVALIDO("label.uc.sms.msg.dddInvalido", "label.uc.sms.msg.dddInvalido"),
	UC_SMS_NUMERO_INVALIDO("label.uc.sms.msg.numeroInvalido", "label.uc.sms.msg.numeroInvalido");
	
	private OpaMensagemEnum(String key,String text){
		setKey(key);
		setText(text);
	}
	private String key;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}