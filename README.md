# conjunto-microservicos-envio-emails
 
 Esse projeto faz parte de um conjunto de 4 projetos/microserviços, que estão publicados, aqui, em meu repositório. Faça um clone dos 4 e siga as instruções, abaixo.
 
 
Os microserviços funcionam em conjunto com o RABBITMQ e com o KEYCLOAK. Dessa forma, para que o sistema de microserviços funcione, você precisará subir cointainers docker, contendo essas duas aplicações. 


 Na data da publicação desse repositório, os comandos docker para subir esses dois serviços, são:
 
 docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
 
 e
 
 docker run -p 8802:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.2 start-dev


OBS: suba os containers RABBITMQ e KEYCLOAK, antes de subir os 4 microserviços, abaixo.
 
 
 
 Para que cada microserviço funcione adequadamente, eles deverão estar funcionando ao mesmo tempo.
 
 Os microserviços são:
  
 1) conjunto-microservicos-eureka;
 
 2) conjunto-microservicos-gateway;
 
 3) conjunto-microservicos-envio-emails;
 
 4) conjunto-microservicos-clientes.
 
 
 Obs: para que não haja erros, eles deverão ser executados na ordem acima.
 
 
 Os endpoints que você poderá acessar, são:
 
 http://localhost:8801/emails/enviar
 
 http://localhost:8801/clientes/enviarEmail
 http://localhost:8801/clientes/enviarEmailMensageria
 
