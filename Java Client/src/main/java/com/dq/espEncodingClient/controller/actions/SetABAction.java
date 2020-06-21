package com.dq.espEncodingClient.controller.actions;

import com.dq.espEncodingClient.controller.communication.ArduinoManager;
import com.dq.espEncodingClient.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SetABAction implements Action {

    private ArduinoManager arduino;
    private View view;

    @Override
    public void launch() {
        view.showMessage("Setting new A, B attributes");
        view.showSubMessage("A should be a Prime Number");
        view.showSubMessage("If A=1 Afinic cipher becomes Cesar's Cipher");
        view.showSubMessage("B is a offset constant");
        int a = view.getValidNumber("A");
        int b = view.getValidNumber("B");
        arduino.actionSetAB(a,b);
    }

    @Override
    public String getName() {
        return "Set AB";
    }
}
