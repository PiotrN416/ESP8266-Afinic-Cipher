package com.dq.espEncodingClient.controller.actions;

import com.dq.espEncodingClient.controller.communication.ArduinoManager;
import com.dq.espEncodingClient.view.View;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EncodeMessageAction implements Action {

    private ArduinoManager arduino;
    private View view;

    public void launch() {
        String message = view.getProperty("message");
        arduino.actionEncode(message);
    }

    public String getName() {
        return "Encode";
    }
}
