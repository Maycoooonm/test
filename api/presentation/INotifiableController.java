package org.xbmc.api.presentation;

import org.xbmc.android.remote_ali.business.Command;
import org.xbmc.api.business.INotifiableManager;

public interface INotifiableController {
    void onError(Exception exception);

    void onMessage(String str);

    void onWrongConnectionState(int i, INotifiableManager iNotifiableManager, Command<?> command);

    void runOnUI(Runnable runnable);
}
