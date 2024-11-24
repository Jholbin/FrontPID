package com.cibertec.entity;

public class User {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String dni;
    private String email;
    private String role;

    // Constructor
    public User(String username, String password, String firstname, String lastname, String dni, String email, String role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters

    public String getDNI(){
        return dni;
    }
    public void setDNI(String dni){
        this.dni = dni;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}