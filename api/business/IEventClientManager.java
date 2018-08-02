package org.xbmc.api.business;

import org.xbmc.eventclient.JoyStick;

public interface IEventClientManager extends IManager {
    void sendAction(String str);

    void sendButton(String str, String str2, boolean z, boolean z2, boolean z3, short s, byte b);

    void sendButton(short s, boolean z, boolean z2, boolean z3, short s2, byte b);

    void sendJoyStick(JoyStick joyStick);

    void sendLog(byte b, String str);

    void sendMouse(int i, int i2);

    void sendMouse(int i, int i2, int i3, int i4);

    void sendNotification(String str, String str2);

    void sendNotification(String str, String str2, byte b, byte[] bArr);
}
