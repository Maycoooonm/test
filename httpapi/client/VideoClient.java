package org.xbmc.httpapi.client;

import android.graphics.Bitmap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.jmdns.impl.DNSConstants;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;
import org.xbmc.api.data.IVideoClient;
import org.xbmc.api.info.PlayStatus;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Host;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Movie;
import org.xbmc.httpapi.Connection;

public class VideoClient extends a implements IVideoClient {
    public static final String PLAYLIST_ID = "1";
    public static final int PLAYLIST_LIMIT = 100;
    public static final String TAG = "VideoClient";
    private static final String a = "idMovie, c00, c07, strPath, strFileName, c15, c11, c14, ROUND(c05, 2), playCount, c09, art.url";
    private static final String b = "SELECT idMovie, c00, c07, strPath, strFileName, c15, c11, c14, ROUND(c05, 2), playCount, c09, art.url";
    private static final String c = " FROM movie, files, path, art";

    public VideoClient(Connection connection) {
        super(connection);
    }

    private String a(int i, String str) {
        StringBuilder stringBuilder;
        String str2;
        if (i != 11) {
            switch (i) {
                case 6:
                    stringBuilder = new StringBuilder();
                    str2 = " ORDER BY ROUND(c05, 2) ";
                    break;
                case 7:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" ORDER BY c07 ");
                    stringBuilder.append(str);
                    str2 = ", CASE WHEN c10 IS NULL OR c10 = '' THEN lower(c00) ELSE lower(c10) END ";
                    break;
                default:
                    stringBuilder = new StringBuilder();
                    str2 = " ORDER BY CASE WHEN c10 IS NULL OR c10 = '' THEN replace(lower(c00),'the ','') ELSE replace(lower(c10),'the ','') END ";
                    break;
            }
        }
        stringBuilder = new StringBuilder();
        str2 = " ORDER BY files.idFile ";
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private String a(boolean z) {
        return z ? " AND (playCount IS NULL OR playCount = 0) " : "";
    }

    private ArrayList<Movie> a(String str) {
        String str2 = str;
        ArrayList<Movie> arrayList = new ArrayList();
        String[] split = str2.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Movie(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trimInt(split[i + 2]), Connection.trim(split[i + 3]), Connection.trim(split[i + 4]), Connection.trim(split[i + 5]), Connection.trim(split[i + 6]), Connection.trim(split[i + 7]), Connection.trimDouble(split[i + 8]), Connection.trimInt(split[i + 9]), Connection.trim(split[i + 10]), Connection.trim(split[i + 11])));
                i += 12;
            } catch (Exception e) {
                Exception exception = e;
                PrintStream printStream = System.err;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(exception.getMessage());
                printStream.println(stringBuilder.toString());
                printStream = System.err;
                stringBuilder = new StringBuilder();
                stringBuilder.append("response = ");
                stringBuilder.append(str2);
                printStream.println(stringBuilder.toString());
                exception.printStackTrace();
            }
        }
        return arrayList;
    }

    static ICurrentlyPlaying a(final HashMap<String, String> hashMap) {
        return new ICurrentlyPlaying() {
            private static final long b = 5036994329211476714L;

            private int a(String str) {
                String[] split = str.split(Connection.PAIR_SEP);
                int i = 0;
                if (split.length == 2) {
                    return (Integer.parseInt(split[0]) * 60) + Integer.parseInt(split[1]);
                }
                if (split.length == 3) {
                    i = ((Integer.parseInt(split[0]) * DNSConstants.DNS_TTL) + (Integer.parseInt(split[1]) * 60)) + Integer.parseInt(split[2]);
                }
                return i;
            }

            public String getAlbum() {
                String str = (String) hashMap.get("Tagline");
                if (str != null) {
                    return str;
                }
                str = ((String) hashMap.get("Filename")).replaceAll("\\\\", "/");
                return str.substring(0, str.lastIndexOf("/"));
            }

            public String getArtist() {
                return (String) hashMap.get("Genre");
            }

            public int getDuration() {
                return a((String) hashMap.get("Duration"));
            }

            public String getFilename() {
                return (String) hashMap.get("Filename");
            }

            public int getHeight() {
                return 0;
            }

            public int getMediaType() {
                return 2;
            }

            public float getPercentage() {
                /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r2 = this;
                r0 = r1;	 Catch:{ NumberFormatException -> 0x0014 }
                r1 = "Percentage";	 Catch:{ NumberFormatException -> 0x0014 }
                r0 = r0.get(r1);	 Catch:{ NumberFormatException -> 0x0014 }
                r0 = (java.lang.String) r0;	 Catch:{ NumberFormatException -> 0x0014 }
                r0 = java.lang.Integer.valueOf(r0);	 Catch:{ NumberFormatException -> 0x0014 }
                r0 = r0.intValue();	 Catch:{ NumberFormatException -> 0x0014 }
                r0 = (float) r0;
                return r0;
            L_0x0014:
                r0 = r1;
                r1 = "Percentage";
                r0 = r0.get(r1);
                r0 = (java.lang.String) r0;
                r0 = java.lang.Float.valueOf(r0);
                r0 = r0.floatValue();
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.VideoClient.1.getPercentage():float");
            }

            public int getPlayStatus() {
                return PlayStatus.parse((String) hashMap.get("PlayStatus"));
            }

            public int getPlaylistPosition() {
                return Integer.parseInt((String) hashMap.get("VideoNo"));
            }

            public int getTime() {
                return a((String) hashMap.get("Time"));
            }

            public String getTitle() {
                String str = (String) hashMap.get("Title");
                if (str != null) {
                    return str;
                }
                String[] split = ((String) hashMap.get("Filename")).replaceAll("\\\\", "/").split("/");
                return split[split.length - 1];
            }

            public int getWidth() {
                return 0;
            }

            public boolean isPlaying() {
                return PlayStatus.parse((String) hashMap.get("PlayStatus")) == 1;
            }
        };
    }

    private Movie a(String str, Movie movie) {
        String[] split = str.split("<field>");
        try {
            movie.tagline = Connection.trim(split[1]);
            movie.plot = Connection.trim(split[2]);
            movie.numVotes = Connection.trimInt(split[3]);
            movie.studio = Connection.trim(split[4]);
            movie.rated = Connection.trim(split[5]);
            movie.trailerUrl = Connection.trim(split[6]);
            return movie;
        } catch (Exception e) {
            PrintStream printStream = System.err;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ERROR: ");
            stringBuilder.append(e.getMessage());
            printStream.println(stringBuilder.toString());
            printStream = System.err;
            stringBuilder = new StringBuilder();
            stringBuilder.append("response = ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            e.printStackTrace();
            return movie;
        }
    }

    public static ArrayList<Actor> parseActorRoles(String str) {
        ArrayList<Actor> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Actor(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2]), Connection.trim(split[i + 3])));
                i += 4;
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(e.getMessage());
                printStream.println(stringBuilder.toString());
                printStream = System.err;
                stringBuilder = new StringBuilder();
                stringBuilder.append("response = ");
                stringBuilder.append(str);
                printStream.println(stringBuilder.toString());
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static ArrayList<Actor> parseActors(String str) {
        ArrayList<Actor> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Actor(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2])));
                i += 3;
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(e.getMessage());
                printStream.println(stringBuilder.toString());
                printStream = System.err;
                stringBuilder = new StringBuilder();
                stringBuilder.append("response = ");
                stringBuilder.append(str);
                printStream.println(stringBuilder.toString());
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static ArrayList<Genre> parseGenres(String str) {
        ArrayList<Genre> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Genre(Connection.trimInt(split[i]), Connection.trim(split[i + 1])));
                i += 2;
            } catch (Exception e) {
                PrintStream printStream = System.err;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(e.getMessage());
                printStream.println(stringBuilder.toString());
                printStream = System.err;
                stringBuilder = new StringBuilder();
                stringBuilder.append("response = ");
                stringBuilder.append(str);
                printStream.println(stringBuilder.toString());
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public ArrayList<Actor> getActors(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idActor, strActor, art.url");
        stringBuilder.append(" FROM actors LEFT OUTER JOIN art ON art.media_id=idActor AND art.media_type='actor' and art.type='thumb'");
        stringBuilder.append(" ORDER BY upper(strActor), strActor");
        return parseActors(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i) {
        return getCover(iNotifiableManager, iCoverArt, i, Movie.getThumbUri(iCoverArt), Movie.getFallbackThumbUri(iCoverArt));
    }

    public ArrayList<Actor> getMovieActors(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT actors.idActor, strActor, art.url");
        stringBuilder.append(" FROM actors LEFT OUTER JOIN art ON art.media_id=actors.idActor AND art.media_type='actor' and art.type='thumb', actorlinkmovie");
        stringBuilder.append(" WHERE actorlinkmovie.idActor = actors.idActor");
        stringBuilder.append(" ORDER BY upper(strActor), strActor");
        return parseActors(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Genre> getMovieGenres(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idGenre, strGenre FROM genre");
        stringBuilder.append(" WHERE idGenre IN (SELECT idGenre FROM genrelinkmovie)");
        stringBuilder.append(" ORDER BY upper(strGenre)");
        return parseGenres(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, int i, String str, int i2, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(c);
        stringBuilder.append(" WHERE movie.idFile=files.idFile AND path.idPath=files.idPath AND movie.idMovie=art.media_id AND art.media_type='movie' AND art.type='thumb'");
        stringBuilder.append(a(z));
        stringBuilder.append(a(i, str));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" LIMIT -1 OFFSET ");
        stringBuilder2.append(i2);
        stringBuilder.append(stringBuilder2.toString());
        return a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(c);
        stringBuilder.append(" WHERE movie.idFile=files.idFile AND path.idPath=files.idPath AND movie.idMovie=art.media_id AND art.media_type='movie' AND art.type='thumb'");
        stringBuilder.append(a(z));
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, Actor actor, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(c);
        stringBuilder.append(" WHERE movie.idFile=files.idFile AND path.idPath=files.idPath AND movie.idMovie=art.media_id AND art.media_type='movie' AND art.type='thumb' AND movie.idmovie IN (");
        stringBuilder.append("   SELECT DISTINCT idMovie ");
        stringBuilder.append("   FROM actorlinkmovie ");
        stringBuilder.append("   WHERE idActor =");
        stringBuilder.append(actor.id);
        stringBuilder.append(" )");
        stringBuilder.append(a(z));
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, Genre genre, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(b);
        stringBuilder.append(c);
        stringBuilder.append(" WHERE movie.idFile=files.idFile AND path.idPath=files.idPath  AND movie.idMovie=art.media_id AND art.media_type='movie' AND art.type='thumb' AND movie.idmovie IN (");
        stringBuilder.append("   SELECT DISTINCT idMovie ");
        stringBuilder.append("   FROM genrelinkmovie ");
        stringBuilder.append("   WHERE idGenre =");
        stringBuilder.append(genre.id);
        stringBuilder.append(" )");
        stringBuilder.append(a(z));
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<String> getPlaylist(INotifiableManager iNotifiableManager) {
        return this.mConnection.getArray(iNotifiableManager, "GetPlaylistContents", "1");
    }

    public int getPlaylistPosition(INotifiableManager iNotifiableManager) {
        return this.mConnection.getInt(iNotifiableManager, "GetPlaylistSong");
    }

    public ArrayList<Actor> getTvShowActors(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT actors.idActor, strActor, art.url");
        stringBuilder.append(" FROM actors LEFT OUTER JOIN art ON art.media_id=actors.idActor AND art.media_type='actor' and art.type='thumb', actorlinktvshow");
        stringBuilder.append(" WHERE actorlinktvshow.idActor = actors.idActor");
        stringBuilder.append(" ORDER BY upper(strActor), strActor");
        return parseActors(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Genre> getTvShowGenres(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idGenre, strGenre FROM genre");
        stringBuilder.append(" WHERE idGenre IN (SELECT idGenre FROM genrelinktvshow)");
        stringBuilder.append(" ORDER BY upper(strGenre)");
        return parseGenres(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public boolean removeFromPlaylist(INotifiableManager iNotifiableManager, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1;");
        stringBuilder.append(str);
        return this.mConnection.getBoolean(iNotifiableManager, "RemoveFromPlaylist", stringBuilder.toString());
    }

    public void setHost(Host host) {
        this.mConnection.setHost(host);
    }

    public boolean setPlaylistPosition(INotifiableManager iNotifiableManager, int i) {
        return this.mConnection.getBoolean(iNotifiableManager, "SetPlaylistSong", String.valueOf(i));
    }

    public Movie updateMovieDetails(INotifiableManager iNotifiableManager, Movie movie) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c03, c01, c04, c18, c12, c19");
        stringBuilder.append(c);
        stringBuilder.append(" WHERE movie.idFile=files.idFile AND path.idPath=files.idPath AND movie.idMovie=art.media_id AND art.media_type='movie' AND art.type='thumb' AND movie.idmovie = ");
        stringBuilder.append(movie.getId());
        a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager), movie);
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT actors.idActor, strActor, art.url, strRole");
        stringBuilder.append(" FROM actors LEFT OUTER JOIN art ON art.media_id=idActor AND art.media_type='actor' and art.type='thumb', actorlinkmovie");
        stringBuilder.append(" WHERE actors.idActor = actorlinkmovie.idActor");
        stringBuilder.append(" AND actorlinkmovie.idMovie =");
        stringBuilder.append(movie.getId());
        movie.actors = parseActorRoles(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
        return movie;
    }
}
