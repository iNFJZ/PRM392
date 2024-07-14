package com.infjz.prm392.slot11.Model;

public class PasswordUpdateRequest {
    private String password;
    private String code;

    public PasswordUpdateRequest(String password, String code) {
        this.password = password;
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
