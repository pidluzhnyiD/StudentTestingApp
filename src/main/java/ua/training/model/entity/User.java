package ua.training.model.entity;

public class User {
	 private int id;
	 private String name;
	 private String login;
	 private String password;
	 private Role role;

	 public User() {
		 
	 }
	 
	 public User(int i, String name, String login, String password, Role role) {
		 this.id = i;
		 this.name = name;
		 this.login = login;
		 this.password = password;
		 this.role = role;    
	 }
	 
	 public int getId() {
		 return id;
	 }
	 
	 public void setId(int id) {
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
	 
	 public Role getRole() {
		 return role;
	 }
	 
	 public void setRole(Role role) {
		 this.role = role;
	 }
	 
	 
	 @Override
	 public String toString() {
		 return "{ Login : " + login + " Name : " + name + ", Role : " + role + " }";
	 }
}
