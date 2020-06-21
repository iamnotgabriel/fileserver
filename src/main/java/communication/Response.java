package communication;

import java.io.File;

import model.User;

public class Response {
	public static final int OK = 200;
	public static final int FALIED = 400;
	private User user;
	private File file;
	private int status;
	private String content;
	
	public Response(User user, File file, int status, String content) {
		super();
		this.user = user;
		this.file = file;
		this.status = status;
		this.content = content;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public static Response login(User user, int status) {
		return new Response(user, null, status, null);
	}
	
	public static Response failed(String content) {
		return new Response(null, null, Response.FALIED, content);
	}
	
}
