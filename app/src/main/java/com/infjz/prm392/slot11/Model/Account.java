package com.infjz.prm392.slot11.Model;

public class Account {
    private String username;
    private String email;
    private String password;
    private String code;  // Add this line

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Add a constructor for code
    public Account(String username, String email, String password, String code) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;  // Add this line
    }

    public void setCode(String code) {
        this.code = code;  // Add this line
    }
}
