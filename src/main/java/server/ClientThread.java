package server;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import communication.Request;
import communication.Response;
import model.User;

public class ClientThread extends Thread {
	private ClientInfo client;
	private Server server;
	
	public ClientThread(ClientInfo client, Server server) {
		super();
		this.client = client;
		this.server = server;
	}

	public Response login(Request req) {
		// lista com todos os usuários
		List<String[]> lines = server.users.readLines();
		// pega o login e a senha que estão como usuario no request
		User user = req.getUser();
		// resposta do pedido 
		Response resp = Response.login(user, Response.FALIED);
		// login com nenhum usuario cadastrado devolve falha
		if(lines == null) {
			return resp;
		}
		// procura um login e senha que foram passados no pedido
		int l = server.users.getCol("login");
		int p = server.users.getCol("password");
		for(String[] line: lines) {
			boolean login = user.getLogin().equals(line[l]);
			boolean password = user.getPassword().equals(line[p]);
			if( login && password ) {
				// se login e senha for validos devolve o usuario e reposta OK
				user = new User(line);
				resp.setUser(user);
				resp.setStatus(Response.OK);
				return resp;
			}
		}
		return resp;
	}
	
	
	public void run() {
		while(true) {
			// espera a mensagem do cliente
			String msgStr = null;
			try {
				msgStr = client.reader.readLine();
			} catch (IOException e) {
				System.err.println("Falha ao ler mensagem do cliente: " + client.conn.getRemoteSocketAddress());
			}
			
			if(msgStr == null) {
				// conexão fechou
				// para de ouvir
				break;
			}
			
			// converte a menagem em um objeto Mensagem
			Request req = new Gson().fromJson(msgStr, Request.class);
			// inicializa 
			Response resp = Response.failed("Comando inválido");
			// executa a o comando desejado do request
			switch(req.getCmd()) {
				case Request.LOGIN:
					resp = login(req);
					break;
				case Request.SIGNIN:
					break;
			}
			System.out.println(resp);
			client.writer.println(resp);
		}
		// log de saída
		System.out.println("Cliente " + client.conn.getRemoteSocketAddress() +" desconectado");
		// desconecta cliente
		client.disconnect();
		
	}
}
