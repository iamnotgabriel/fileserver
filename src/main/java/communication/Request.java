package communication;

import java.io.File;

import com.google.gson.Gson;

import model.User;

public class Request {
	public static final  String POST = "POST";
	public static final String GET = "GET";
	public static final int LOGIN = 100;
	public static final int SIGNIN = 101;
	private String method;
	private User user;
	private File file;
	private int cmd;
	
	public Request(String method, User user, File file, int cmd) {
		super();
		this.method = method;
		this.user = user;
		this.file = file;
		this.cmd = cmd;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this);
	}
	
	public static Request login(User user) {
		return new Request(POST, user, null, LOGIN);
	}
	
	public static Request signin(User user) {
		return new Request(POST, user, null, SIGNIN);
	}

}
