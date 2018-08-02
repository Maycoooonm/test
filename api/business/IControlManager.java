package org.xbmc.api.business;

import android.content.Context;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;
import org.xbmc.api.type.SeekType;

public interface IControlManager extends IManager {
    void addToPlaylist(DataResponse<Boolean> dataResponse, String str, int i, Context context);

    void clearPlaylist(DataResponse<Boolean> dataResponse, int i, Context context);

    void getCurrentlyPlaying(DataResponse<ICurrentlyPlaying> dataResponse, Context context);

    void getPlaylistId(DataResponse<Integer> dataResponse, Context context);

    void getVolume(DataResponse<Integer> dataResponse, Context context);

    void playFile(DataResponse<Boolean> dataResponse, String str, int i, Context context);

    void playFolder(DataResponse<Boolean> dataResponse, String str, int i, Context context);

    void playNext(DataResponse<Boolean> dataResponse, Context context);

    void playUrl(DataResponse<Boolean> dataResponse, String str, Context context);

    void queueFolder(DataResponse<Boolean> dataResponse, String str, int i, Context context);

    void seek(DataResponse<Boolean> dataResponse, SeekType seekType, int i, Context context);

    void sendText(DataResponse<Boolean> dataResponse, String str, Context context);

    void setGuiSetting(DataResponse<Boolean> dataResponse, int i, String str, Context context);

    void setPlaylistId(DataResponse<Boolean> dataResponse, int i, Context context);

    void setPlaylistPos(DataResponse<Boolean> dataResponse, int i, int i2, Context context);

    void showPicture(DataResponse<Boolean> dataResponse, String str, Context context);

    void updateLibrary(DataResponse<Boolean> dataResponse, String str, Context context);
}
