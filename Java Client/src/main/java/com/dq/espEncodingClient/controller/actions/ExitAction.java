package com.dq.espEncodingClient.controller.actions;

public class ExitAction implements Action {

    @Override
    public void launch() {
        System.exit(0);
    }

    @Override
    public String getName() {
        return "Exit";
    }
}
