package org.xbmc.httpapi;

import org.xbmc.api.object.Host;
import org.xbmc.httpapi.client.ControlClient;
import org.xbmc.httpapi.client.InfoClient;
import org.xbmc.httpapi.client.MusicClient;
import org.xbmc.httpapi.client.TvShowClient;
import org.xbmc.httpapi.client.VideoClient;

public class HttpApi {
    public final ControlClient control;
    public final InfoClient info;
    public final MusicClient music;
    public final TvShowClient shows;
    public final VideoClient video;

    public HttpApi(Host host, int i) {
        Connection instance;
        if (host != null) {
            instance = Connection.getInstance(host.addr, host.port);
            instance.setAuth(host.user, host.pass);
        } else {
            instance = Connection.getInstance(null, 0);
        }
        instance.setTimeout(i);
        this.info = new InfoClient(instance);
        this.music = new MusicClient(instance);
        this.video = new VideoClient(instance);
        this.control = new ControlClient(instance);
        this.shows = new TvShowClient(instance);
    }

    public void setHost(Host host) {
        this.info.setHost(host);
        this.music.setHost(host);
        this.video.setHost(host);
        this.control.setHost(host);
        this.shows.setHost(host);
    }
}
