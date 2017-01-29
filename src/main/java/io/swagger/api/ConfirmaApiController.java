package io.swagger.api;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import io.swagger.api.model.util.FilasEnum;
import io.swagger.api.model.util.MailUtil;
import io.swagger.model.entities.Reserva;
import io.swagger.model.entities.Usuario;
import io.swagger.model.repositories.ReservaRepository;
import io.swagger.model.repositories.UsuarioRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-01-28T15:43:10.271Z")

@Controller
public class ConfirmaApiController implements ConfirmaApi {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private ReservaRepository reservaRepo;

	public ResponseEntity<Void> confirmaGet() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public ResponseEntity<Void> confirmaPost() {

		Gson gson = null;
		StringBuilder sb = null;
		try {

			gson = new Gson();
			sb = new StringBuilder();

			for (FilasEnum fila : FilasEnum.values()) {

				if (fila.equals(FilasEnum.FILA_USUARIO)) {

					String usuarioJson = this.obterDadosFila("usuarios");

					Usuario novoUsuario = gson.fromJson(usuarioJson, Usuario.class);

					if (novoUsuario != null) {

						Usuario usuarioPersitido = userRepo.findById(novoUsuario.getId());

						if (usuarioPersitido == null)
							userRepo.save(novoUsuario);

						sb.append("\n\nUsuários criados com sucesso: ").append("\n").append("Nome: ")
								.append(novoUsuario.getNome()).append("\ncpf: ").append(novoUsuario.getCpf());
					}

				} else if (fila.equals(FilasEnum.FILA_RESERVAS)) {

					String reservaJson = this.obterDadosFila("reservas");

					Reserva novaReserva = gson.fromJson(reservaJson, Reserva.class);

					reservaRepo.save(novaReserva);

					sb.append("\n\nReservas criadas com sucesso: ").append("\n").append("Reserva: ")
							.append(novaReserva.getId()).append("\nUsuário: ").append(novaReserva.getUsuario().getNome());

				}
			}

			MailUtil.enviarEmail(sb.toString(), "rodrigoats1@gmail.com");

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private String obterDadosFila(String fila) throws JMSException {

		TextMessage message = null;
		try {

			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

			Connection connection = factory.createConnection();

			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Destination queue = session.createQueue(fila);

			MessageConsumer consumer = session.createConsumer(queue);

			message = (TextMessage) consumer.receive();

			message.getText();
			session.close();

			connection.close();

		}

		catch (Exception ex) {

			ex.printStackTrace();

		}

		if (message == null)
			return null;

		return message.getText();
	}

}
