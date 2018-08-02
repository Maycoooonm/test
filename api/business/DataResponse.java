package org.xbmc.api.business;

public class DataResponse<T> implements Cloneable, Runnable {
    public int cacheType;
    public T value;

    public boolean postCache() {
        return true;
    }

    public void run() {
    }
}
