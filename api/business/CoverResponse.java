package org.xbmc.api.business;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import org.xbmc.api.object.ICoverArt;

public class CoverResponse extends DataResponse<Bitmap> {
    private final Context a;
    private final IManager b;
    private final Bitmap c;
    private final int d;
    private final Handler e;
    private boolean f = false;
    private ICoverArt g = null;

    public CoverResponse(Context context, IManager iManager, Bitmap bitmap, int i, Handler handler) {
        this.a = context;
        this.b = iManager;
        this.c = bitmap;
        this.d = i;
        this.e = handler;
    }

    public synchronized void load(ICoverArt iCoverArt, int i, boolean z) {
        if (this.f) {
            this.g = iCoverArt;
        } else {
            this.f = true;
            this.g = null;
            this.b.getCover(this, iCoverArt, i, this.c, this.a, z);
        }
    }

    public synchronized void load(ICoverArt iCoverArt, boolean z) {
        load(iCoverArt, 1, z);
    }

    public synchronized void run() {
        if (this.g == null) {
            if (this.e != null) {
                this.e.sendMessage(this.e.obtainMessage(1, this.value));
            }
            this.f = false;
        } else {
            this.b.getCover(this, this.g, this.d, this.c, this.a, false);
            this.g = null;
        }
    }
}
