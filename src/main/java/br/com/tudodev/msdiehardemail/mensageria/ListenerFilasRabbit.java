package br.com.tudodev.msdiehardemail.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tudodev.msdiehardemail.modelo.EmailEnviar;
import br.com.tudodev.msdiehardemail.servico.EmailService;

@Component
public class ListenerFilasRabbit {

	@Autowired
	EmailService emailService;

	@RabbitListener(queues = "${rabbitmq.filas.email}")
	public void enviarEmail(@RequestBody String strEmailEnviar) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			EmailEnviar emailEnviar = mapper.readValue(strEmailEnviar, EmailEnviar.class);
			emailService.prepararEEnviarEmail(emailEnviar);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
}
