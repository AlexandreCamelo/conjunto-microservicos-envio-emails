package br.com.tudodev.msdiehardemail.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEnviarTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String emailConfigHost;
	private String emailConfigPort;
	private String emailConfigUsername;
	private String emailConfigPassword;
	private String emailConfigEmailRemetente;
	private String emailConfigNomeapresentacao;
	private String emailConfigEncoding;
	private String emailConfigTransportProtocol;
	private Boolean emailConfigSmtpAuth;
	private String emailConfigSmtpSslTrust;
	private Boolean emailConfigSmtpStarttlsEnable;
	private Boolean emailConfigSmtpStarttlsRequired;

	private List<String> para;
	private List<String> cc;
	private List<String> cco;
	private String assunto;
	private String corpoTxtOuHtml;
	private List<String> listaDeAnexos;
	private Boolean inserirAssinatura;
	private String emailAssinatCaminhoLogo;
	private String emailAssinatNome;
	private String emailAssinatCargo;
	private String emailAssinatFone;
	private String emailAssinatSite;
	private LocalDateTime dataHora;

}
