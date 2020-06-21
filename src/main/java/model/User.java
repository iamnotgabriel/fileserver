package model;

public class User {
	private long id;
	private String name, login, password, email;
	
	public User(String name, String login, String password, String email) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	public User(String...line) {
		super();
		this.id = Long.parseLong(line[0]);
		this.name = line[1];
		this.login = line[2];
		this.password = line[3];
		this.email = line[4];
	}
	
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [login=" + login + " email=" + email+ "]";
	}
	
	public static boolean validateName(String name) {
		return name.matches("^[a-z A-Z]{4,}$");
	}
	
	public static boolean validateEmail(String email) {
		return email.matches("^[a-zA-Z0-9_.]+@[a-z0-9_][-a-z0-9]*(\\.[-a-z0-9]+)*$");
	}
	
	public static boolean validatePassword(String password) {
		return password.matches("^[a-zA-Z0-9]{4,}$");
	}
	
	public static boolean validateLogin(String login) {
		return login.matches("^[a-zA-Z0-9_.]{4,}$");
	}
	
	
	
}
