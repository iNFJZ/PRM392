package com.infjz.prm392.slot11.ServerRespone;

import com.infjz.prm392.slot11.Model.Account;

public class ServerResponseLogin {
    private boolean error;
    private String message;
    private Account account; // Add account field

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Account getAccount() {
        return account;
    }
}
