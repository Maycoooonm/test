package org.xbmc.api.type;

import android.content.res.Resources;

public abstract class ThumbSize {
    public static final double BANNER_AR = 0.1846965699208443d;
    public static final int BIG = 3;
    public static final double FULL_AR = 0.75d;
    public static final double LANDSCAPE_AR = 0.5625d;
    public static final int MEDIUM = 2;
    public static int PIXELS_HEIGHT = 0;
    public static int PIXELS_WIDTH = 0;
    public static final float PIXEL_SCALE = Resources.getSystem().getDisplayMetrics().density;
    public static final double POSTER_AR = 1.4799154334038056d;
    public static final int SCREENHEIGHT = 5;
    public static final int SCREENWIDTH = 4;
    public static float SCREEN_SCALE = 1.0f;
    public static final int SMALL = 1;

    public static class Dimension {
        public static final int BANNER = 4;
        public static final int LANDSCAPE = 3;
        public static final int PORTRAIT = 1;
        public static final int SQUARE = 2;
        public static final int UNKNOWN = 0;
        public int format;
        public int x;
        public int y;

        Dimension(int i, int i2) {
            this.x = i;
            this.y = i2;
        }

        Dimension(int i, int i2, int i3) {
            this(i, i2);
            this.format = i3;
        }

        public boolean equals(Dimension dimension) {
            return dimension.x == this.x && dimension.y == this.y;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(");
            stringBuilder.append(this.x);
            stringBuilder.append("x");
            stringBuilder.append(this.y);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }
    }

    private static int a(int i, float f) {
        float f2;
        switch (i) {
            case 1:
                f2 = 50.0f;
                break;
            case 2:
                f2 = 105.0f;
                break;
            case 3:
                f2 = 400.0f;
                break;
            case 4:
                return PIXELS_WIDTH;
            case 5:
                return PIXELS_HEIGHT;
            default:
                return 0;
        }
        return (int) ((f2 * PIXEL_SCALE) * f);
    }

    public static String getDir(int i) {
        switch (i) {
            case 1:
                return "/small";
            case 2:
                return "/medium";
            case 3:
                return "/original";
            default:
                return "";
        }
    }

    public static int getPixel(int i) {
        return a(i, SCREEN_SCALE);
    }

    public static int getPixel(int i, boolean z) {
        return a(i, z ? 1.0f : SCREEN_SCALE);
    }

    public static Dimension getTargetDimension(int i, int i2, int i3, int i4) {
        if (i2 != 2) {
            switch (i2) {
                case 21:
                case 22:
                case 23:
                case 24:
                    break;
                default:
                    return new Dimension(getPixel(i), getPixel(i));
            }
        }
        double d = ((double) i3) / ((double) i4);
        if (d > 0.95d && d < 1.05d) {
            return new Dimension(getPixel(i), getPixel(i), 2);
        }
        if (d < 1.0d) {
            return new Dimension(getPixel(i), (int) (POSTER_AR * ((double) getPixel(i))), 1);
        }
        if (d < 1.5d) {
            return new Dimension((int) (((double) getPixel(i)) / FULL_AR), getPixel(i), 3);
        }
        if (d < 2.0d) {
            return new Dimension((int) (((double) getPixel(i)) / LANDSCAPE_AR), getPixel(i), 3);
        }
        if (d <= 5.0d) {
            return new Dimension((int) (((double) getPixel(i)) / LANDSCAPE_AR), getPixel(i), 0);
        }
        switch (i) {
            case 1:
                return new Dimension(getPixel(4), (int) (((double) getPixel(4)) * BANNER_AR), 4);
            case 2:
                return new Dimension(getPixel(5), (int) (((double) getPixel(5)) * BANNER_AR), 4);
            default:
                return new Dimension(758, 140, 4);
        }
    }

    public static int scale(int i) {
        return Math.round(((float) i) * PIXEL_SCALE);
    }

    public static void setScreenSize(int i, int i2) {
        PIXELS_WIDTH = i;
        PIXELS_HEIGHT = i2;
        if (i >= i2) {
            i = i2;
        }
        SCREEN_SCALE = (((float) i) / PIXEL_SCALE) / 320.0f;
    }

    public static int[] values() {
        return new int[]{3, 2, 1};
    }
}
