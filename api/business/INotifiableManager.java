package org.xbmc.api.business;

import org.xbmc.android.remote_ali.business.Command;

public interface INotifiableManager {
    void onError(Exception exception);

    void onFinish(DataResponse<?> dataResponse);

    void onMessage(int i, String str);

    void onMessage(String str);

    void onWrongConnectionState(int i, Command<?> command);

    void retryAll();
}
