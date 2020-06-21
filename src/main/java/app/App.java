package app;

import control.Input;
import model.User;

public class App {
	public static Client socket;
	public static User user;
	private static String login = null, password = null, host = "localhost";
	private static int port = 5000;
	
	public static void main(String[] args) throws Exception {
		if(readArgs(args)) {
			// cadastro de usuarios
		}
		socket = new Client(host, port);
		user = new User(login, password);
		if(!socket.login(user)) {
			System.out.println("Login ou senha inválidos");
			System.exit(0);
		}
		Input input = new Input();
		while(true) {
			System.out.print(">>> ");
			input.readLine();
		}
	}
	
	public static boolean readArgs(String[] args) {
		if(args.length < 4) {
			System.out.println("Login:  	java -jar app.jar  -u `login` -p `senha`[-H `localhost` -P `5000`]");
			System.out.println("Cadastro:  	java -jar app.jar");
			return true;
		}
		for(int i = 0; i < args.length; i++) {
			if( (i+1) < args.length && args[i].matches("^-{1,2}u.*$") ) {
				login = args[++i];
				i++;
			}
			if( (i+1) < args.length && args[i].matches("^-{1,2}p.*$") ) {
				password = args[++i];
				i++;
			}
			if( (i+1) < args.length && args[i].matches("^-{1,2}H.*$") ) {
				host = args[++i];
				i++;
			}
			if( (i+1) < args.length && args[i].matches("^-{1,2}P.*$") ) {
				port = Integer.parseInt(args[++i]);
				i++;
			}
		}
		if (login == password && password == null) {
			return true;
		}
		return false;
	}
	
}
