package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import communication.Request;
import communication.Response;
import model.User;

public class Client {
	private Socket socket;
	private InputStream in;
	private InputStreamReader inr;
	private BufferedReader reader;
	private PrintWriter writer;

	public Client(String host, int door) throws Exception {
		this.socket = new Socket(host, door);
		this.in = socket.getInputStream();
		this.inr = new InputStreamReader(in, StandardCharsets.UTF_8);
		this.reader = new BufferedReader(inr);
		this.writer = new PrintWriter(
				new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true
		);
	}

	public Response waitResponse() throws IOException{
		String msg;
		Gson json = new Gson();
		while(!reader.ready());
		msg = reader.readLine();
		return json.fromJson(msg, Response.class);
	}
	
	public void close() throws Exception {
		socket.close();
	}
	
	public boolean login(User user) throws Exception {
		Request req = Request.login(user);
		writer.println(req);
		Response resp =  waitResponse();
		System.out.println(resp);
		if(resp.getStatus() == Response.OK) {
			App.user = resp.getUser();
			return true;
		}
		return false;
	}
}
