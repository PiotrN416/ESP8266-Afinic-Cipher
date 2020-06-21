package com.dq.espEncodingClient.view;

import com.dq.espEncodingClient.controller.actions.Action;

import java.util.List;

public interface View {
    void showMessage(String message);

    void showSubMessage(String message);

    String getProperty(String propertyName);

    Action selectFromOptions(List<Action> actions);

    int getValidNumber(String message);
}
