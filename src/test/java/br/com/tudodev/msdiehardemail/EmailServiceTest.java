package br.com.tudodev.msdiehardemail;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tudodev.msdiehardemail.modelo.EmailEnviar;
import br.com.tudodev.msdiehardemail.modelo.EmailEnviarTest;
import br.com.tudodev.msdiehardemail.servico.EmailService;

@SpringBootTest
public class EmailServiceTest {

	@Autowired
	EmailService emailService;

	@Test
	@DisplayName("prepararEEnviarEmail Should Receive a Correct Object")
	void prepararEEnviarEmailShouldReceiveACorrectObject() {
		EmailEnviar emailEnviar = new EmailEnviar();
		EmailEnviarTest emailEnviarTest = new EmailEnviarTest();
		Assertions.assertTrue(qtdeCamposSaoIguais(emailEnviar, emailEnviarTest));
		Assertions.assertTrue(nomesDosCamposSaoIguais(emailEnviar, emailEnviarTest));
	}


	private boolean qtdeCamposSaoIguais(Object obj1, Object obj2) {
		Boolean retorno = false;
		int qtdeAtribObj1 = obj1.getClass().getDeclaredFields().length;
		int qtdeAtribObj2 = obj1.getClass().getDeclaredFields().length;

		if (qtdeAtribObj1 == qtdeAtribObj2) {
			retorno = true;
		} else {
			retorno = false;
		}

		return retorno;
	}

	private boolean nomesDosCamposSaoIguais(Object obj1, Object obj2) {
		int qtdeCampos = obj1.getClass().getDeclaredFields().length;
		int contadorCamposIguais = 0;

		Field[] fieldsObj1 = obj1.getClass().getDeclaredFields();
		Field[] fieldsObj2 = obj2.getClass().getDeclaredFields();

		for (Field f : fieldsObj1) {
			String campoObj1 = f.getName();

			for (Field f2 : fieldsObj2) {
				String campoObj2 = f2.getName();
				if (campoObj1.equals(campoObj2)) {
					++contadorCamposIguais;
				}
			}

		}

		System.out.println("Qtde campos: " + qtdeCampos);
		System.out.println("Campos iguais: " + contadorCamposIguais);
		if (qtdeCampos == contadorCamposIguais) {
			return true;
		} else {
			return false;
		}

	}


}
