package org.xbmc.httpapi;

public class WrongDataFormatException extends Exception {
    private static final long a = 42438942451326636L;
    private String b;
    private String c;

    public WrongDataFormatException(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Wrong data format, expected '");
        stringBuilder.append(str);
        stringBuilder.append("', got '");
        stringBuilder.append(str2);
        stringBuilder.append("'.");
        super(stringBuilder.toString());
        this.b = str;
        this.c = str2;
    }

    public String getExpected() {
        return this.b;
    }

    public String getReceived() {
        return this.c;
    }
}
