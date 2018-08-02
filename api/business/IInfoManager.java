package org.xbmc.api.business;

import android.content.Context;
import java.util.ArrayList;
import org.xbmc.api.object.FileLocation;
import org.xbmc.api.type.DirectoryMask;

public interface IInfoManager extends IManager {
    void getDirectory(DataResponse<ArrayList<FileLocation>> dataResponse, String str, Context context, int i);

    void getDirectory(DataResponse<ArrayList<FileLocation>> dataResponse, String str, DirectoryMask directoryMask, int i, int i2, Context context, int i3);

    void getGuiSettingBool(DataResponse<Boolean> dataResponse, int i, Context context);

    void getGuiSettingInt(DataResponse<Integer> dataResponse, int i, Context context);

    void getShares(DataResponse<ArrayList<FileLocation>> dataResponse, int i, Context context);

    void getSystemInfo(DataResponse<String> dataResponse, int i, Context context);

    void setGuiSettingBool(DataResponse<Boolean> dataResponse, int i, boolean z, Context context);

    void setGuiSettingInt(DataResponse<Boolean> dataResponse, int i, int i2, Context context);
}
