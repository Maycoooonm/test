package org.xbmc.api.object;

import android.util.Log;
import java.io.Serializable;
import org.json.JSONObject;
import org.xbmc.android.remote_ali.business.provider.HostProvider_ali.Hosts;

public class Host implements Serializable {
    public static final int DEFAULT_CONNECT_TIMEOUT = 10;
    public static final int DEFAULT_EVENTSERVER_PORT = 9777;
    public static final int DEFAULT_HTSP_PORT = 9982;
    public static final int DEFAULT_HTTP_PORT = 8080;
    public static final int DEFAULT_ID = 0;
    public static final int DEFAULT_RESPOSE_TIMEOUT = 5;
    public static final int DEFAULT_SEARCH_TYPE = 0;
    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int DEFAULT_TVHE_PORT = 0;
    public static final int DEFAULT_WOL_PORT = 9;
    public static final int DEFAULT_WOL_WAIT = 40;
    public static final int SEARCH_TYPE_LOCAL = 0;
    public static final int SEARCH_TYPE_REMOTE = 1;
    private static final String a = "Host";
    private static final long b = 7886482294339161092L;
    public String access_point;
    public String addr;
    public String barcode;
    public int connection_timeout = 10;
    public int esPort = DEFAULT_EVENTSERVER_PORT;
    public String ext_addr;
    public int htsp_port = DEFAULT_HTSP_PORT;
    public int id;
    public String local_addr;
    public String mac_addr;
    public String manual_addr;
    public String name;
    public String pass;
    public int port = DEFAULT_HTTP_PORT;
    public int response_timeout = 5;
    public int search_type;
    public int timeout = 5000;
    public int tvhehttp_port = 0;
    public String user;
    public boolean wifi_only = false;
    public int wol_port = 9;
    public int wol_wait = 40;

    public String getSummary() {
        return toString();
    }

    public String toJson() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Hosts.NAME, this.name);
            jSONObject.put(Hosts.SEARCH_TYPE, this.search_type);
            jSONObject.put("manual_addr", this.manual_addr);
            jSONObject.put("addr", this.addr);
            jSONObject.put("ext_addr", this.ext_addr);
            jSONObject.put("local_addr", this.local_addr);
            jSONObject.put(Hosts.BARCODE, this.barcode);
            jSONObject.put("port", this.port);
            jSONObject.put(Hosts.USER, this.user);
            jSONObject.put(Hosts.PASS, this.pass);
            jSONObject.put("esPort", this.esPort);
            jSONObject.put(Hosts.TIMEOUT, this.timeout);
            jSONObject.put(Hosts.WIFI_ONLY, this.wifi_only);
            jSONObject.put(Hosts.ACCESS_POINT, this.access_point);
            jSONObject.put(Hosts.MAC_ADDR, this.mac_addr);
            jSONObject.put(Hosts.WOL_WAIT, this.wol_wait);
            jSONObject.put(Hosts.WOL_PORT, this.wol_port);
            jSONObject.put(Hosts.tvhePORT, this.tvhehttp_port);
            jSONObject.put(Hosts.HTSPPORT, this.htsp_port);
            jSONObject.put(Hosts.CONNECT_TIMEOUT, this.connection_timeout);
            jSONObject.put(Hosts.RESPONSE_TIMEOUT, this.response_timeout);
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.e(a, "Error in toJson", e);
            return "";
        }
    }

    public String toString() {
        return this.addr;
    }
}
