package com.dq.espEncodingClient.controller.communication;

import com.dq.espEncodingClient.view.View;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ArduinoManager {

    private static final String PORT_NAME = "COM3";
    private static final int PORT_OPEN_TIME_OUT = 4000;
    private static final int DATA_SENDING_RATE = 9600;

    private SerialPort serialPort;
    private BufferedReader input;
    private OutputStream output;
    private View view;

    public ArduinoManager(View view) {
        this.view = view;

        CommPortIdentifier portId;
        try {
            portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);

            serialPort = (SerialPort) portId.open(this.getClass().getName(), PORT_OPEN_TIME_OUT);
            serialPort.setSerialPortParams(
                    DATA_SENDING_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener(new ResponseListener(input, view));
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            view.showMessage(e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @SneakyThrows
    public void sendMessageToBoard(String message) {
        for (Character c : message.toLowerCase().toCharArray()) {
            output.write(c);
        }
        serialPort.notifyOnDataAvailable(true);
    }

    public void actionGetAB() {
        sendMessageToBoard("00#");
    }

    public void actionSetAB(int a, int b) {
        sendMessageToBoard("01#" + a + "/" + b);
    }

    public void actionEncode(String message) {
        sendMessageToBoard("10#" + message);
    }

    public void actionDecode(String message) {
        sendMessageToBoard("11#" + message);
    }
}
