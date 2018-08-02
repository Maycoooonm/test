package org.xbmc.api.business;

import android.content.SharedPreferences;

public interface ISortableManager {
    void setPreferences(SharedPreferences sharedPreferences);

    void setSortKey(int i);
}
