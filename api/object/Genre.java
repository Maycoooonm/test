package org.xbmc.api.object;

import java.io.Serializable;

public class Genre implements Serializable, INamedResource {
    private static final long a = 9073064679039418773L;
    public int id;
    public String name;

    public Genre(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public String getShortName() {
        return this.name;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(this.id);
        stringBuilder.append("] ");
        stringBuilder.append(this.name);
        return stringBuilder.toString();
    }
}
