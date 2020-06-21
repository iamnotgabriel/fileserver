package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.CSVHandler;

public class Server {
	private static final String USERS_PATH = "users.csv";
	private static final int PORT = 5000;
	public CSVHandler users;
	private ServerSocket socket;
	
	public Server() {
		try {
			this.socket = new ServerSocket(PORT);
			this.users = loadUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg) {
		Server s = new Server();
		System.out.println("Servidor iniciado com sucesso");
		
		System.out.println("Esperando por conexões...");
		while(true) {
			Socket conn;
			// espera por nova conexão
			ClientInfo client = null;
			try {
				conn = s.socket.accept();
				client = new ClientInfo(conn, s);
				
			} catch (IOException e) {
				System.out.println("Falha ao estabelecer conexão com o cliente");
				continue;
			}
			// log da nova conexão
			System.out.println("Conectado com o cliente " + conn.getRemoteSocketAddress());
			
			// começa a ouvir menagens do cliente
			Thread thr = new ClientThread(client, s);
			thr.start();
		}
		
	}
	
	private static CSVHandler loadUsers() {
		CSVHandler users = null;
		boolean writeHead = !Files.exists( Paths.get(USERS_PATH) );
		try {
			users = new CSVHandler(USERS_PATH, "id", "name", "email", "login", "password");
			if(writeHead) {
				users.write(users.cols);
			}
			System.out.println("Arquivo criado com sucesso");
		} catch (Exception e) {
			System.out.println("Não foi possivel criar arquivo");
			System.exit(1);
		}
		return users;
	}
}
