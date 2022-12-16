//package br.com.tudodev.msdiehardemail.modelo;
//
//import java.io.Serializable;
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.Size;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "tblemailsenviados")
//public class EmailEnviado implements Serializable {
//
//	// Gerado pelo Java
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public BigInteger cod;
//
//	
//	@ManyToOne
//	@JoinColumn(name = "codusuarioenvio")
//	private Usuario usuario;
//
//	@Column(name = "remetente")
//	private String remetente;
//
//	@Column(name = "para")
//	private String para;
//	
//	@Column(name = "cc")
//	private String cc;
//	
//	@Column(name = "cco")
//	private String cco;
//	
//	@Size(max = 500, message = "O campo 'Assunto' deve ter até 500 caracteres")
//	@Column(name = "assunto")
//	private String assunto;
//	
//	@Column(name = "corpo")
//	private String corpo;
//	
//	@Column(name = "listadeanexos")
//	private String listaDeAnexos;
//	
//	@Column(name = "data_hora")
//	private LocalDateTime dataHora;
//	
//}
