# conjunto-microservicos-envio-emails
 
 Esse projeto faz parte de um conjunto de 4 projetos/microserviços, que estão publicados, aqui, em meu repositório. Faça um clone dos 4 e siga as instruções, abaixo.
 
 
Os microserviços foram construídos com o Spring Boot e funcionam em conjunto com o RABBITMQ e o KEYCLOAK. Dessa forma, para que o conjunto completo de microserviços funcione corretamente, você precisará subir cointainers docker, contendo essas duas aplicações. 


 Na data da publicação desse repositório (dezembro/2022), os comandos docker para subir esses dois serviços, são:
 
 <code>docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management</code>
 </br>e</br>
 <code>docker run -p 8802:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.2 start-dev</code>


Após subir o RABBITMQ, você deverá criar uma fila, chamada 'diehardemail' (sem aspas).

Após subir o KEYCLOAK, você deverá configurar um novo REALM, chamado 'keycloak_diehardweb' (sem aspas) e, dentro dele, criar um client, chamado 'diehardemails' (sem aspas). 

Não entrarei em detalhes sobre como configurar essas duas aplicações, pois isso foge ao escopo. Caso não saiba fazer isso, entre nos seus respectivos sites e leia  suas documentações (https://www.keycloak.org/ e https://www.rabbitmq.com/).
 
 Na documentação, verifique, PRINCIPALMENTE, qual é a URI para solicitar o token de autenticação, no KEYCLOAK. O token será necessário para que você consiga fazer as requisições aos serviços.
 
OBS: suba os containers RABBITMQ e KEYCLOAK, ANTES de subir os 4 microserviços, abaixo. Caso não faça isso, erros ocorrerão.
 
 </br></br></br>
Para que cada microserviço funcione adequadamente, todos eles deverão estar em funcionamento, ao mesmo tempo.
 
 Os microserviços são:
  <code>
 1) conjunto-microservicos-eureka;
 
 2) conjunto-microservicos-gateway;
 
 3) conjunto-microservicos-envio-emails;
 
 4) conjunto-microservicos-clientes.
 </code>
 
 Obs: para que não haja erros, eles deverão ser executados na ordem acima.
 
 
 Os endpoints que você poderá acessar, são:
 
 http://localhost:8801/emails/enviar  (POST)</br>
 http://localhost:8801/clientes/enviarEmail  (POST))</br>
 http://localhost:8801/clientes/enviarEmailMensageria  (POST))</br>
 
 
 Em cada uma das requisições, acima, você deverá colocar um objeto 'Emailenviar' em seu corpo.
 
 O modelo desse objeto está no arquivo 'objEmailEnviar.txt', que está na raiz do projeto <code>conjunto-microservicos-envio-emails</code>. Ele já está no formato JSON, prontinho para você copiar e colar, no seu client rest (recomendo o INSOMNIA, pois o POSTMAN não reconheceu os tokens de autenticação).
 
 O objeto possui alguns campos de configuração do email, para que seja possível seu envio. Preencha esses campos, corretamente, de acordo com as instruções do seu servidor de email.
 O objeto também possui campos para preenchimento dos dados do email, tais como: remetente, destinatário, assunto, corpo, etc. 
 
 O corpo do email pode ser preenchido com instruções HTML, para formatação do mesmo.
 
 Existem campos para confecção de uma assinatura ao final do corpo do email. A assinatura poderá ter, inclusive uma imagem.
 
 
 
 
 
 
 
 
 
