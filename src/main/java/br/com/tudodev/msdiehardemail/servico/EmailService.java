package br.com.tudodev.msdiehardemail.servico;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import br.com.tudodev.msdiehardemail.modelo.EmailEnviar;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailService implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Autowired
//	EmailEnviadoReposit repOrcEmailEnviado;

	private String arquivosAnexadosParaBD;
	Integer tamanhoMaxArquivo = 100000000;
	Map<String, File> mapaArquivosAnexados = new HashMap<>();
	List<File> arquivosAnexadosParaTentativaDeApagamento = new ArrayList<>();
//	EmailEnviado emailEnviado = new EmailEnviado();

	public void prepararEEnviarEmail(EmailEnviar emailEnviar) {
		log.info("Começando a enviar...");

		MimeMessage email;
		MimeMessageHelper mensagem;
		String emailDe = "";
		String emailNomeApresentacao = "";
		String strCorpoEditado = emailEnviar.getCorpoTxtOuHtml();

		try {
			JavaMailSender enviadorConfigurado = enviador(emailEnviar);
			email = enviadorConfigurado.createMimeMessage();
			mensagem = new MimeMessageHelper(email, true);
			emailDe = emailEnviar.getEmailConfigEmailRemetente();
			emailNomeApresentacao = emailEnviar
					.getEmailConfigNomeapresentacao();
			mensagem.setFrom(emailDe, emailNomeApresentacao);

			if (emailEnviar.getPara() != null
					|| emailEnviar.getPara().size() > 0) {
				mensagem.setTo(emailEnviar.getPara().toArray(new String[0]));
			}

			if (emailEnviar.getCc() != null || emailEnviar.getCc().size() > 0) {
				mensagem.setCc(emailEnviar.getCc().toArray(new String[0]));
			}

			if (emailEnviar.getCco() != null
					&& emailEnviar.getCco().size() > 0) {
				mensagem.setBcc(emailEnviar.getCco().toArray(new String[0]));
			} else {
				mensagem.setBcc(emailEnviar.getEmailConfigEmailRemetente());
			}

			mensagem.setSubject(emailEnviar.getAssunto());

			if (emailEnviar.getInserirAssinatura()) {
				File arqLogo = new File(
						emailEnviar.getEmailAssinatCaminhoLogo());

				strCorpoEditado += htmlAssinaturaEmail(
						emailEnviar.getEmailAssinatNome(),
						emailEnviar.getEmailAssinatCargo(),
						emailEnviar.getEmailAssinatFone(),
						emailEnviar.getEmailAssinatSite());
				mensagem.setText(strCorpoEditado, true);

				FileSystemResource logoEmbutido = new FileSystemResource(
						arqLogo);
				mensagem.addInline("cidDoLogo", logoEmbutido);
			}

			if (emailEnviar.getListaDeAnexos() != null
					&& emailEnviar.getListaDeAnexos().size() > 0) {
				for (String caminhoArq : emailEnviar.getListaDeAnexos()) {
					File arquivo = new File(caminhoArq);

					if (arquivo.exists()) {
						mensagem.addAttachment(arquivo.getName(), arquivo);
						arquivosAnexadosParaBD = arquivosAnexadosParaBD + ";"
								+ arquivo.getName();

					}
				}
			}

			sohEnviar(email, enviadorConfigurado);
		} catch (AddressException ae) {
			log.error("Algum email é inválido. Verifique a digitação.");
			limparDadosEmail();
			ae.printStackTrace();

		} catch (MessagingException e) {
			log.error(
					"Ocorreu algum problema desconhecido. Não foi possível enviar o email.");
			limparDadosEmail();
			e.printStackTrace();
		} catch (UnsupportedEncodingException uns) {
			log.error(
					"Configuração de email incorreta (encoding). Não foi possível enviar o email.");
			limparDadosEmail();
			uns.printStackTrace();
		} catch (Exception ex) {
			log.error(
					"Ocorreu algum problema desconhecido. Não foi possível enviar o email.");
			limparDadosEmail();
			ex.printStackTrace();
		}

	}

	public void sohEnviar(MimeMessage mimeMessage,
			JavaMailSender enviadorConfigurado) {
		try {
			log.info("Enviando...");
			enviadorConfigurado.send(mimeMessage);
			log.info("Enviado!!!");

		} catch (MailSendException un) {
			if (un.getMessage().toLowerCase()
					.contains("unknownhostexception".toLowerCase())
					|| un.getMessage().toLowerCase()
							.contains("mailconnectexception")) {
				log.error(
						"Não foi possível enviar o email. Configuração de email incorreta.");
			} else {
				log.error(
						"Não foi possível enviar o email. Problema desconhecido.");
			}

			limparDadosEmail();
			un.printStackTrace();
		} catch (Exception ex) {
			log.error(
					"Ocorreu algum problema desconhecido. Não foi possível enviar o email.");
			limparDadosEmail();
			ex.printStackTrace();
		}
	}

	private JavaMailSender enviador(EmailEnviar emailEnviar) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		try {
			String host = emailEnviar.getEmailConfigHost();
			Integer porta = Integer.valueOf(emailEnviar.getEmailConfigPort());
			String strUsuario = emailEnviar.getEmailConfigUsername();
			String senha = emailEnviar.getEmailConfigPassword();
			String encoding = emailEnviar.getEmailConfigEncoding();
			String protocTransp = emailEnviar.getEmailConfigTransportProtocol();
			Boolean smtpAuth = emailEnviar.getEmailConfigSmtpAuth();
			String smtpSslTrust = emailEnviar.getEmailConfigSmtpSslTrust();
			Boolean startTlsEnable = emailEnviar
					.getEmailConfigSmtpStarttlsEnable();
			Boolean startTlsRequired = emailEnviar
					.getEmailConfigSmtpStarttlsRequired();

			if (host == null || host.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (porta == null || porta <= 0) {
				mensagemConfigEmail();
				return null;
			} else if (strUsuario == null || strUsuario.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (senha == null || senha.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (encoding == null || encoding.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (protocTransp == null || protocTransp.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (smtpAuth == null) {
				mensagemConfigEmail();
				return null;
			} else if (smtpSslTrust == null || smtpSslTrust.isBlank()) {
				mensagemConfigEmail();
				return null;
			} else if (startTlsEnable == null) {
				mensagemConfigEmail();
				return null;
			} else if (startTlsRequired == null) {
				mensagemConfigEmail();
				return null;
			}

			mailSender.setHost(host);
			mailSender.setPort(porta);
			mailSender.setUsername(strUsuario);
			mailSender.setPassword(senha);
			mailSender.setDefaultEncoding(encoding);
			Properties props = mailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", protocTransp);
			props.put("mail.smtp.auth", smtpAuth);
			props.put("mail.smtp.ssl.trust", smtpSslTrust);
			props.put("mail.smtp.starttls.enable", startTlsEnable);
			props.put("mail.smtp.starttls.required", startTlsRequired);
			// props.put("mail.debug", "true");
			return mailSender;

		} catch (NumberFormatException nf) {
			log.error(
					"Efetue ou corrija a configuração do seu email, no cadastro de usuários.");
			nf.printStackTrace();
			return null;
		}
	}

	private void mensagemConfigEmail() {
		log.warn(
				"Efetue ou corrija a configuração do seu email, no cadastro de usuários.");
	}

	public void limparDadosEmail() {
		mapaArquivosAnexados = new HashMap<>();
		for (File arq : arquivosAnexadosParaTentativaDeApagamento) {
			if (arq.exists()) {
				arq.delete();
			}
		}

		arquivosAnexadosParaTentativaDeApagamento = new ArrayList<>();
	}

	public String htmlAssinaturaEmail(String emailAssinatNome,
			String emailAssinatCargo, String emailAssinatFone,
			String emailAssinatSite) {
		String mensagem = "    <table>\n" + "        <tbody>\n"
				+ "            <tr>\n"
				+ "                <td style=\"width: 155px;\" colspan=\"2\" rowspan=\"5\">\n"
				+ "                    <p>&nbsp;</p>\n"
				+ "                    <img src=\"cid:cidDoLogo\" alt=\"\" width=\"auto\" height=\"150\" />"
				+ "                </td>\n" + "\n" + "                <td>\n"
				+ "                    <tr style=\"height: 30px; padding: 0px;\">\n"
				+ "                        <td style=\"width: 300px;\">\n"
				+ "                            <p style=\"height: 10px; color: #808080; font-size: 25px; margin: 0px 0px 10px 0px;\">\n"
				+ "                                <strong>" + emailAssinatNome
				+ "</strong>\n" + "                            </p>\n"
				+ "                        </td>\n"
				+ "                    </tr>\n"
				+ "                    <tr style=\"height: 30px; padding: 0px;\">\n"
				+ "                        <td style=\"width: 300px;\">\n"
				+ "                            <p style=\"height: 10px; color: #808080; font-size: 15px; margin: 0px 0px 10px 0px;\">\n"
				+ "                                <strong>" + emailAssinatCargo
				+ "</strong>\n" + "                            </p>\n"
				+ "                        </td>\n"
				+ "                    </tr>\n"
				+ "                    <tr style=\"height: 30px; padding: 0px;\">\n"
				+ "                        <td style=\"width: 300px;\">\n"
				+ "\n"
				+ "                            <p style=\"height: 10px; color: #808080; font-size: 25px; margin: 0px 0px 10px 0px;\">\n"
				+ "                                <strong>" + emailAssinatFone
				+ "</strong>\n" + "                            </p>\n" + "\n"
				+ "                        </td>\n"
				+ "                    </tr>\n"
				+ "                    <tr style=\"height: 30px; padding: 0px;\">\n"
				+ "                        <td style=\"width: 300px;\">\n"
				+ "                            <a href=\"https://"
				+ emailAssinatSite + "\" target=\"_blank\"\n"
				+ "                                style=\"color: #E57308; font-size: 20px;\"><strong>"
				+ emailAssinatSite + "</strong></p>\n"
				+ "                        </td>\n"
				+ "                    </tr>\n" + "                </td>\n"
				+ "            </tr>\n" + "        </tbody>\n"
				+ "    </table>\n" + "";

		return mensagem;
	}

}