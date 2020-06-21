package com.dq.espEncodingClient.controller.actions;

import com.dq.espEncodingClient.controller.communication.ArduinoManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetABAction implements Action {

    private ArduinoManager arduino;

    @Override
    public void launch() {
        arduino.actionGetAB();
    }

    @Override
    public String getName() {
        return "Get AB";
    }
}
