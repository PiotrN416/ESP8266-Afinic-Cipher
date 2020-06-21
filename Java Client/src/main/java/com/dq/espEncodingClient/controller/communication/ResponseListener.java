package com.dq.espEncodingClient.controller.communication;

import com.dq.espEncodingClient.view.View;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;

@AllArgsConstructor
public class ResponseListener implements SerialPortEventListener {

    private BufferedReader input;
    private View view;

    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String incomingMessage = input.readLine();
                view.showMessage("Response: " + incomingMessage);
            } catch (Exception e) {
                view.showMessage(e.getClass().getSimpleName() + " " + e.getMessage());
            }
        }
    }
}
