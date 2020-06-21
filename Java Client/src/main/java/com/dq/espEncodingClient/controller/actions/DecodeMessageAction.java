package com.dq.espEncodingClient.controller.actions;

import com.dq.espEncodingClient.controller.communication.ArduinoManager;
import com.dq.espEncodingClient.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DecodeMessageAction implements Action {

    private ArduinoManager arduino;
    private View view;

    public void launch() {
        String message = view.getProperty("message");
        arduino.actionDecode(message);
    }

    public String getName() {
        return "Decode";
    }
}
