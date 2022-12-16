package br.com.tudodev.msdiehardemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tudodev.msdiehardemail.modelo.EmailEnviar;
import br.com.tudodev.msdiehardemail.servico.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/enviar")
	public ResponseEntity<EmailEnviar> enviar(
			@RequestBody EmailEnviar emailEnviar) {
		emailService.prepararEEnviarEmail(emailEnviar);
		return ResponseEntity.status(200).body(emailEnviar);
	}
}
