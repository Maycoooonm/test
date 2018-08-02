package org.xbmc.api.business;

import android.content.Context;
import android.graphics.Bitmap;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.presentation.INotifiableController;

public interface IManager {
    boolean coverLoaded(ICoverArt iCoverArt, int i);

    void getCover(DataResponse<Bitmap> dataResponse, ICoverArt iCoverArt, int i, Bitmap bitmap, Context context, boolean z);

    Bitmap getCoverSync(ICoverArt iCoverArt, int i);

    void post(Runnable runnable);

    void setController(INotifiableController iNotifiableController);
}
