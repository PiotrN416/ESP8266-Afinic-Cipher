package com.dq.espEncodingClient;

import com.dq.espEncodingClient.controller.actions.*;
import com.dq.espEncodingClient.controller.communication.ArduinoManager;
import com.dq.espEncodingClient.view.ConsoleView;
import com.dq.espEncodingClient.view.View;

import java.util.ArrayList;
import java.util.List;

public class Application {
    static private ArduinoManager arduinoManager;
    static private View view;
    static private List<Action> actions;

    public static void main(String[] args) {
        view = new ConsoleView();
        arduinoManager = new ArduinoManager(view);
        actions = getActions();

        while (true) {
            Action action = view.selectFromOptions(actions);
            action.launch();
        }
    }

    private static List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new EncodeMessageAction(arduinoManager, view));
        actions.add(new DecodeMessageAction(arduinoManager, view));
        actions.add(new GetABAction(arduinoManager));
        actions.add(new SetABAction(arduinoManager, view));
        actions.add(new ExitAction());
        return actions;
    }
}