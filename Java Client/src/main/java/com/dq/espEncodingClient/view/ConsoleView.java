package com.dq.espEncodingClient.view;

import com.dq.espEncodingClient.controller.actions.Action;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showSubMessage(String message) {
        showMessage("\t" + message);
    }

    @Override
    public String getProperty(String propertyName) {
        showMessage(propertyName + ": ");
        return scanner.nextLine();
    }

    @Override
    public Action selectFromOptions(List<Action> actions) {
        showMessage("Please select:");

        for (Action a : actions) {
            showSubMessage(a.getName());
        }

        while (true) {
            String name = getProperty("name");
            for (Action a : actions) {
                if (a.getName().equalsIgnoreCase(name)) {
                    return a;
                }
            }
            showMessage("invalid value...");
        }
    }

    @Override
    public int getValidNumber(String message) {
        while (true) {
            try {
                String property = getProperty(message);
                return Integer.valueOf(property);
            } catch (NumberFormatException e) {
                showSubMessage("given value is not a number");
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (scanner != null) {
            scanner.close();
        }
    }
}
