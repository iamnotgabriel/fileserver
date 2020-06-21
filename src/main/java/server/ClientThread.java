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
		List<String[]> lines = server.users.readLines();
		User user = req.getUser();
		Response resp = Response.login(user, Response.FALIED);
		
		if(lines == null) {
			return resp;
		}
		
		for(String[] line: lines) {
			int l = server.users.getCol("login");
			int p = server.users.getCol("password");
			boolean login = user.getLogin().equals(line[l]);
			boolean password = user.getPassword().equals(line[p]);
			if( login && password ) {
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
			// executa a o metedo desejado
			switch(req.getCmd()) {
				case Request.LOGIN:
					Response resp = login(req);
					System.out.println(resp);
					client.writer.println(resp);
					break;
				case Request.SIGNIN:
					break;
			}
		}
		// log de saída
		System.out.println("Cliente " + client.conn.getRemoteSocketAddress() +" desconectado");
		// desconecta cliente
		client.disconnect();
		
	}
}
