//package br.com.tudodev.msdiehardemail.modelo;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import org.hibernate.validator.constraints.br.CPF;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "tblusuarios")
//public class Usuario implements Serializable {
//
//	// Gerado pelo Java
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer cod;
//
//	@Size(max = 300, message = "O Nome não pode ter mais de 300 caracteres")
//	@Column(name = "nome")
//	@NotBlank(message = "O nome não pode ficar em branco")
//	private String nome;
//
//	@Size(max = 300, message = "O email não pode ter mais de 300 caracteres")
//	@NotBlank(message = "O email não pode ficar em branco")
//	@Column(name = "email")
//	@Email(message = "O email não é válido: verifique seu formato")
//	private String email;
//
//	@Column(name = "ativo")
//	private Boolean ativo;
//
//	@Size(max = 20, message = "O CPF não pode ter mais de 20 caracteres")
//	@Column(name = "cpf")
//	@CPF(message = "O CPF não é válido")
//	@NotBlank(message = "O CPF não pode ficar em branco")
//	private String cpf;
//
//	@Size(max = 50, message = "O Usuário não pode ter mais de 50 caracteres")
//	@Column(name = "usuario")
//	private String usuario;
//
//	@Size(max = 500, message = "A Senha não pode ter mais de 500 caracteres")
//	@NotBlank(message = "A Senha não pode ficar em branco")
//	@Column(name = "senha")
//	private String senha;
//
//	@Column(name = "consultas")
//	private Boolean consultas;
//
//	@Column(name = "financeiro")
//	private Boolean financeiro;
//
//	@Column(name = "adm")
//	private Boolean adm;
//
//	@Column(name = "cad_usuarios")
//	private Boolean cadUsuarios;
//
//	@Column(name = "config")
//	private Boolean config;
//
//	@Column(name = "cli_cad")
//	private Boolean cliCad;
//
//	@Column(name = "cli_excluir")
//	private Boolean cliExcluir;
//
//	@Column(name = "cli_modif")
//	private Boolean cliModif;
//
//	@Column(name = "forn_cad")
//	private Boolean fornCad;
//
//	@Column(name = "forn_excluir")
//	private Boolean fornExcluir;
//
//	@Column(name = "forn_modif")
//	private Boolean fornModif;
//
//	@Column(name = "auditoria")
//	private Boolean auditoria;
//
//	@Column(name = "data_cad")
//	private LocalDate dataCad;
//
//	@Column(name = "hora_cad")
//	private LocalTime horaCad;
//
//	@Size(max = 50, message = "O Usuário que cadastrou não pode ter mais de 50 caracteres")
//	@Column(name = "usuario_cad")
//	private String usuarioCad;
//
//	@Column(name = "data_alt")
//	private LocalDate dataAlt;
//
//	@Column(name = "hora_alt")
//	private LocalTime horaAlt;
//
//	@Size(max = 50, message = "O Usuário que alterou não pode ter mais de 50 caracteres")
//	@Column(name = "usuario_alt")
//	private String usuarioAlt;
//
//	@Size(max = 100, message = "A Filial não pode ter mais de 100 caracteres")
//	@Column(name = "filial")
//	private String filial;
//
//	@Column(name = "arquivo")
//	private Boolean arquivo;
//
//	@Column(name = "cadastros")
//	private Boolean cadastros;
//
//	@Column(name = "cons_maq")
//	private Boolean consMaq;
//
//	@Column(name = "cad_maq")
//	private Boolean cadMaq;
//
//	@Column(name = "ajuda")
//	private Boolean ajuda;
//
//	@Column(name = "cad_gerais")
//	private Boolean cadGerais;
//
//	@Column(name = "prest_contas")
//	private Boolean prestContas;
//	
//	
//	@Size(max = 1000, message = "O host só pode ter até 1000 caracteres")
//	@Column(name = "emailconfig_host")
//	private String emailConfigHost;
//
//	@Size(max = 50, message = "A porta só pode ter até 50 caracteres")
//	@Column(name = "emailconfig_port")
//	private String emailConfigPort;
//
//	
//	@Size(max = 1000, message = "O username só pode ter até 1000 caracteres")
//	@Column(name = "emailconfig_username")
//	private String emailConfigUsername;	
//	
//	@Column(name = "emailconfig_password")
//	private String emailConfigPassword;	
//
//	@Size(max = 1000, message = "O email do remetente só pode ter até 1000 caracteres")
//	@Column(name = "emailconfig_emailremetente")
//	private String emailConfigEmailRemetente;	
//	
//	@Size(max = 500, message = "O nome de apresentação só pode ter até 500 caracteres")
//	@Column(name = "emailconfig_nomeapresentacao")
//	private String emailConfigNomeapresentacao;	
//	
//	@Size(max = 50, message = "O encoding só pode ter até 50 caracteres")
//	@Column(name = "emailconfig_encoding")
//	private String emailConfigEncoding;	
//	
//	@Size(max = 50, message = "O transport protocol só pode ter até 50 caracteres")
//	@Column(name = "emailconfig_transport_protocol")
//	private String emailConfigTransportProtocol;	
//	
//	@Column(name = "emailconfig_smtp_auth")
//	private Boolean emailConfigSmtpAuth;	
//
//	@Column(name = "emailconfig_smtp_ssl_trust")
//	private String emailConfigSmtpSslTrust;	
//
//	@Column(name = "emailconfig_smtp_starttls_enable")
//	private Boolean emailConfigSmtpStarttlsEnable;	
//	
//	@Column(name = "emailconfig_smtp_starttls_required")
//	private Boolean emailConfigSmtpStarttlsRequired;	
//	
//	
//	@Size(max = 10000, message = "O caminho da logo só pode ter até 10000 caracteres")
//	@Column(name = "emailassinatcaminhologo")
//	private String emailAssinatCaminhoLogo;
//	
//	@Size(max = 300, message = "O nome da assinatura só pode ter até 300 caracteres")
//	@Column(name = "emailassinatnome")
//	private String emailAssinatNome;
//
//	@Size(max = 200, message = "O cargo da assinatura só pode ter até 200 caracteres")
//	@Column(name = "emailassinatcargo")
//	private String emailAssinatCargo;
//
//	@Size(max = 100, message = "O telefone da assinatura só pode ter até 100 caracteres")
//	@Column(name = "emailassinatfone")
//	private String emailAssinatFone;
//	
//	@Size(max = 300, message = "O email da assinatura só pode ter até 300 caracteres")
//	@Column(name = "emailassinatsite")
//	private String emailAssinatSite;
//	
//	
//
//}
