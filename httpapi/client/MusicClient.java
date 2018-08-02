package org.xbmc.httpapi.client;

import android.graphics.Bitmap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.jmdns.impl.DNSConstants;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;
import org.xbmc.api.data.IMusicClient;
import org.xbmc.api.info.PlayStatus;
import org.xbmc.api.object.Album;
import org.xbmc.api.object.Artist;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Host;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Song;
import org.xbmc.api.type.SortType;
import org.xbmc.httpapi.Connection;

public class MusicClient extends a implements IMusicClient {
    public static final String LIBRARY_TYPE = "songs";
    public static final String PLAYLIST_ID = "0";
    public static final int PLAYLIST_LIMIT = 100;
    public static final String TAG = "MusicClient";
    public static final int VIEW_ALBUMS = 1;
    public static final int VIEW_SONGS = 2;

    public MusicClient(Connection connection) {
        super(connection);
    }

    private String a(int i, String str) {
        StringBuilder stringBuilder;
        String str2;
        if (i == 2) {
            stringBuilder = new StringBuilder();
            str2 = " ORDER BY lower(strArtist) ";
            stringBuilder.append(str2);
            stringBuilder.append(str);
            str2 = ", lower(strAlbum) ";
        } else if (i == 5) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("  ORDER BY iTrack ");
            stringBuilder.append(str);
            str2 = ", lower(strFileName) ";
        } else if (i != 7) {
            stringBuilder = new StringBuilder();
            str2 = " ORDER BY lower(strAlbum) ";
        } else {
            stringBuilder = new StringBuilder();
            str2 = "  ORDER BY iYear ";
            stringBuilder.append(str2);
            stringBuilder.append(str);
            str2 = ", lower(strAlbum) ";
        }
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private StringBuilder a(Album album) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("idAlbum = ");
        stringBuilder.append(album.id);
        return stringBuilder;
    }

    private StringBuilder a(Artist artist) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  song_artist.idArtist = ");
        stringBuilder.append(artist.id);
        return stringBuilder;
    }

    private StringBuilder a(Artist artist, Genre genre) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append("  idArtist = ");
        stringBuilder.append(artist.id);
        stringBuilder.append("  OR idSong IN (");
        stringBuilder.append("     SELECT exartistsong.idSong");
        stringBuilder.append("     FROM exartistsong");
        stringBuilder.append("     WHERE exartistsong.idArtist = ");
        stringBuilder.append(artist.id);
        stringBuilder.append("  ) OR idSong IN (");
        stringBuilder.append("     SELECT song.idSong");
        stringBuilder.append("     FROM song");
        stringBuilder.append("     JOIN album ON song.idAlbum = album.idAlbum");
        stringBuilder.append("     WHERE album.idArtist = ");
        stringBuilder.append(artist.id);
        stringBuilder.append("  ) OR idSong IN (");
        stringBuilder.append("     SELECT song.idSong");
        stringBuilder.append("     FROM song");
        stringBuilder.append("     JOIN exartistalbum ON song.idAlbum = exartistalbum.idAlbum");
        stringBuilder.append("     JOIN album ON song.idAlbum = album.idAlbum");
        stringBuilder.append("     WHERE exartistalbum.idArtist = ");
        stringBuilder.append(artist.id);
        stringBuilder.append("     AND album.strExtraArtists != ''");
        stringBuilder.append("  )");
        stringBuilder.append(") AND (");
        stringBuilder.append("  idGenre = ");
        stringBuilder.append(genre.id);
        stringBuilder.append("  OR idSong IN (");
        stringBuilder.append("     SELECT exgenresong.idSong FROM exgenresong WHERE exgenresong.idGenre = ");
        stringBuilder.append(genre.id);
        stringBuilder.append("  )");
        stringBuilder.append(")");
        return stringBuilder;
    }

    private StringBuilder a(Genre genre) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  songview.idSong IN (");
        stringBuilder.append("    SELECT song_genre.idSong FROM song_genre WHERE song_genre.idGenre = ");
        stringBuilder.append(genre.id);
        stringBuilder.append(")");
        return stringBuilder;
    }

    private StringBuilder a(Song song) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("idSong = ");
        stringBuilder.append(song.id);
        return stringBuilder;
    }

    private ArrayList<Album> a(String str) {
        ArrayList<Album> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Album(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2]), Connection.trimInt(split[i + 3]), Connection.trim(split[i + 4])));
                i += 5;
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

    private ArrayList<Song> a(INotifiableManager iNotifiableManager, StringBuilder stringBuilder, int i, String str) {
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("SELECT songview.idSong, strTitle, group_concat(DISTINCT strArtist) AS strArtists, strAlbum, iTrack, iDuration, strPath, strFileName, art.url");
        stringBuilder2.append(" FROM songview");
        stringBuilder2.append(" LEFT OUTER JOIN song_artist ON song_artist.idSong=songview.idSong");
        stringBuilder2.append(" LEFT OUTER JOIN artist ON song_artist.idArtist=artist.idArtist");
        stringBuilder2.append(" LEFT OUTER JOIN art ON art.media_id=songview.idSong AND art.media_type='song' AND art.type='thumb'");
        stringBuilder2.append(" WHERE ");
        stringBuilder2.append(stringBuilder);
        stringBuilder2.append(" GROUP BY idSong");
        stringBuilder2.append(b(i, str));
        return b(this.mConnection.query("QueryMusicDatabase", stringBuilder2.toString(), iNotifiableManager));
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
                return (String) hashMap.get("Album");
            }

            public String getArtist() {
                return (String) hashMap.get("Artist");
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
                return 1;
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
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.MusicClient.1.getPercentage():float");
            }

            public int getPlayStatus() {
                return PlayStatus.parse((String) hashMap.get("PlayStatus"));
            }

            public int getPlaylistPosition() {
                return Integer.parseInt((String) hashMap.get("SongNo"));
            }

            public int getTime() {
                return a((String) hashMap.get("Time"));
            }

            public String getTitle() {
                return (String) hashMap.get("Title");
            }

            public int getWidth() {
                return 0;
            }

            public boolean isPlaying() {
                return PlayStatus.parse((String) hashMap.get("PlayStatus")) == 1;
            }
        };
    }

    private Album a(Album album, String str) {
        String[] split = str.split("<field>");
        try {
            if (Connection.trim(split[1]).length() > 0) {
                album.genres = Connection.trim(split[1]);
            }
            if (Connection.trim(split[2]).length() > 0) {
                album.label = Connection.trim(split[2]);
            }
            if (Connection.trim(split[3]).length() > 0) {
                album.rating = Connection.trimInt(split[3]);
                return album;
            }
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
        return album;
    }

    private Artist a(Artist artist, String str) {
        String[] split = str.split("<field>");
        try {
            if (Connection.trim(split[1]).length() > 0) {
                artist.born = Connection.trim(split[1]);
            }
            if (Connection.trim(split[2]).length() > 0) {
                artist.formed = Connection.trim(split[2]);
            }
            if (Connection.trim(split[3]).length() > 0) {
                artist.genres = Connection.trim(split[3]);
            }
            if (Connection.trim(split[4]).length() > 0) {
                artist.moods = Connection.trim(split[4]);
            }
            if (Connection.trim(split[5]).length() > 0) {
                artist.styles = Connection.trim(split[5]);
            }
            if (Connection.trim(split[6]).length() > 0) {
                artist.biography = Connection.trim(split[6]);
                return artist;
            }
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
        return artist;
    }

    private boolean a(INotifiableManager iNotifiableManager, StringBuilder stringBuilder) {
        clearPlaylist(iNotifiableManager);
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("songs;");
        stringBuilder2.append(stringBuilder.toString());
        this.mConnection.getBoolean(iNotifiableManager, "AddToPlayListFromDB", stringBuilder2.toString());
        this.mConnection.getBoolean(iNotifiableManager, "SetCurrentPlaylist", "0");
        return playNext(iNotifiableManager);
    }

    private String b(int i, String str) {
        if (i == -1) {
            return "";
        }
        StringBuilder stringBuilder;
        String str2;
        switch (i) {
            case 1:
                stringBuilder = new StringBuilder();
                str2 = " ORDER BY lower(strAlbum) ";
                break;
            case 2:
                stringBuilder = new StringBuilder();
                stringBuilder.append(" ORDER BY lower(strArtist) ");
                stringBuilder.append(str);
                str2 = ", lower(strAlbum) ";
                break;
            case 3:
                stringBuilder = new StringBuilder();
                str2 = " ORDER BY lower(strTitle)";
                break;
            case 4:
                stringBuilder = new StringBuilder();
                str2 = " ORDER BY lower(strFileName)";
                break;
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append(" ORDER BY iTrack ");
                stringBuilder.append(str);
                str2 = ", lower(strFileName) ";
                break;
        }
        stringBuilder.append(str2);
        stringBuilder.append(str);
        str2 = ", iTrack ";
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    private ArrayList<Song> b(String str) {
        ArrayList<Song> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Song(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2]), Connection.trim(split[i + 3]), Connection.trimInt(split[i + 4]), Connection.trimInt(split[i + 5]), Connection.trim(split[i + 6]), Connection.trim(split[i + 7]), Connection.trim(split[i + 8])));
                i += 9;
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

    private ArrayList<Integer> c(String str) {
        ArrayList<Integer> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(Integer.valueOf(Connection.trimInt(split[i])));
                i += 9;
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

    private ArrayList<Artist> d(String str) {
        ArrayList<Artist> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Artist(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2])));
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

    private ArrayList<Genre> e(String str) {
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

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, Album album, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("songs;");
        stringBuilder.append(a(album));
        stringBuilder.append(b(i, str));
        return this.mConnection.getBoolean(iNotifiableManager, "AddToPlayListFromDB", stringBuilder.toString());
    }

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, Artist artist, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("songs;");
        stringBuilder.append(a(artist));
        stringBuilder.append(b(i, str));
        return this.mConnection.getBoolean(iNotifiableManager, "AddToPlayListFromDB", stringBuilder.toString());
    }

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, Artist artist, Genre genre, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("songs;");
        stringBuilder.append(a(artist, genre));
        stringBuilder.append(b(i, str));
        return this.mConnection.getBoolean(iNotifiableManager, "AddToPlayListFromDB", stringBuilder.toString());
    }

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, Genre genre, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("songs;");
        stringBuilder.append(a(genre));
        stringBuilder.append(b(i, str));
        return this.mConnection.getBoolean(iNotifiableManager, "AddToPlayListFromDB", stringBuilder.toString());
    }

    public boolean addToPlaylist(INotifiableManager iNotifiableManager, Song song) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(song.path);
        stringBuilder.append(Connection.VALUE_SEP);
        stringBuilder.append("0");
        return this.mConnection.getBoolean(iNotifiableManager, "AddToPlayList", stringBuilder.toString());
    }

    public boolean clearPlaylist(INotifiableManager iNotifiableManager) {
        return this.mConnection.getBoolean(iNotifiableManager, "ClearPlayList", "0");
    }

    public ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT albumview.idAlbum, strAlbum, group_concat(DISTINCT strArtist) AS strArtists, iYear, art.url");
        stringBuilder.append(" FROM albumview");
        stringBuilder.append(" LEFT OUTER JOIN album_artist ON album_artist.idAlbum=albumview.idAlbum");
        stringBuilder.append(" LEFT OUTER JOIN artist ON album_artist.idArtist=artist.idArtist");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=albumview.idAlbum AND art.media_type='album' AND art.type='thumb'");
        stringBuilder.append(" WHERE albumview.strAlbum <> '' GROUP BY albumview.idAlbum");
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, ArrayList<Integer> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT albumview.idAlbum, strAlbum, group_concat(DISTINCT strArtist) AS strArtists, iYear, art.url");
        stringBuilder.append(" FROM albumview");
        stringBuilder.append(" LEFT OUTER JOIN album_artist ON album_artist.idAlbum=albumview.idAlbum");
        stringBuilder.append(" LEFT OUTER JOIN artist ON album_artist.idArtist=artist.idArtist");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=albumview.idAlbum AND art.media_type='album' AND art.type='thumb'");
        stringBuilder.append(" WHERE albumview.strAlbum <> ''");
        stringBuilder.append(" AND album_artist.idArtist IN (");
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            stringBuilder.append((Integer) it.next());
            i++;
            if (arrayList.size() < i) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(") GROUP BY albumview.idAlbum");
        return a(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, Artist artist, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT albumview.idAlbum, strAlbum, group_concat(DISTINCT strArtist) AS strArtists, iYear, art.url");
        stringBuilder.append(" FROM albumview");
        stringBuilder.append(" LEFT OUTER JOIN album_artist ON album_artist.idAlbum=albumview.idAlbum");
        stringBuilder.append(" LEFT OUTER JOIN artist ON album_artist.idArtist=artist.idArtist");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=albumview.idAlbum AND art.media_type='album' AND art.type='thumb'");
        stringBuilder.append(" WHERE albumview.strAlbum <> ''");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(" AND album_artist.idArtist = ");
        stringBuilder2.append(artist.id);
        stringBuilder2.append(" GROUP BY albumview.idAlbum");
        stringBuilder.append(stringBuilder2.toString());
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, Genre genre, int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT albumview.idAlbum, strAlbum, group_concat(DISTINCT strArtist) AS strArtists, iYear, art.url");
        stringBuilder.append(" FROM albumview");
        stringBuilder.append(" LEFT OUTER JOIN album_artist ON album_artist.idAlbum=albumview.idAlbum");
        stringBuilder.append(" LEFT OUTER JOIN artist ON album_artist.idArtist=artist.idArtist");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=albumview.idAlbum AND art.media_type='album' AND art.type='thumb'");
        stringBuilder.append("  WHERE albumview.strAlbum <> ''");
        stringBuilder.append("  AND (albumview.idAlbum IN (");
        stringBuilder.append("        SELECT song.idAlbum FROM song");
        stringBuilder.append("        JOIN song_genre ON song.idSong = song_genre.idSong");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("        WHERE song_genre.idGenre =  ");
        stringBuilder2.append(genre.id);
        stringBuilder.append(stringBuilder2.toString());
        stringBuilder.append("  )) GROUP BY albumview.idAlbum");
        stringBuilder.append(a(i, str));
        return a(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Artist> getArtists(INotifiableManager iNotifiableManager, Genre genre, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT idArtist, strArtist, art.url ");
        stringBuilder.append("  FROM artist");
        stringBuilder.append("  LEFT JOIN art ON art.media_id=idArtist AND art.media_type='artist' AND art.type='thumb'");
        stringBuilder.append("  WHERE (idArtist IN (");
        stringBuilder.append("    SELECT DISTINCT s.idArtist");
        stringBuilder.append("    FROM song_genre AS g, song_artist AS s");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("    WHERE g.idGenre = ");
        stringBuilder2.append(genre.id);
        stringBuilder.append(stringBuilder2.toString());
        stringBuilder.append("    AND g.idSong = s.idSong");
        stringBuilder.append("  ))");
        if (z) {
            stringBuilder.append("  AND (");
            stringBuilder.append("    idArtist IN (");
            stringBuilder.append("      SELECT album.idArtist");
            stringBuilder.append("      FROM album");
            stringBuilder.append("    ) OR idArtist IN (");
            stringBuilder.append("      SELECT exartistalbum.idArtist");
            stringBuilder.append("      FROM exartistalbum");
            stringBuilder.append("      JOIN album ON album.idAlbum = exartistalbum.idAlbum");
            stringBuilder.append("      WHERE album.strExtraArtists != ''");
            stringBuilder.append("    )");
            stringBuilder.append("  ) AND artist.strArtist != ''");
        }
        stringBuilder.append(" ORDER BY upper(strArtist), strArtist");
        return d(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Artist> getArtists(INotifiableManager iNotifiableManager, boolean z) {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        if (z) {
            stringBuilder.append("SELECT idArtist, strArtist, art.url ");
            stringBuilder.append("  FROM artist");
            stringBuilder.append("  LEFT JOIN art ON art.media_id=idArtist AND art.media_type='artist' AND art.type='thumb'");
            stringBuilder.append("  WHERE (");
            stringBuilder.append("    idArtist IN (");
            stringBuilder.append("      SELECT album.idArtist");
            stringBuilder.append("      FROM album");
            stringBuilder.append("    ) OR idArtist IN (");
            stringBuilder.append("      SELECT exartistalbum.idArtist");
            stringBuilder.append("      FROM exartistalbum");
            stringBuilder.append("      JOIN album ON album.idAlbum = exartistalbum.idAlbum");
            stringBuilder.append("      WHERE album.strExtraArtists != ''");
            stringBuilder.append("    )");
            str = ") AND artist.strArtist != ''";
        } else {
            stringBuilder.append("SELECT idArtist, strArtist, art.url FROM artist");
            str = "  LEFT JOIN art ON art.media_id=idArtist AND art.media_type='artist' AND art.type='thumb'";
        }
        stringBuilder.append(str);
        stringBuilder.append(" ORDER BY upper(strArtist), strArtist");
        return d(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Integer> getCompilationArtistIDs(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idArtist");
        stringBuilder.append("  FROM artist");
        stringBuilder.append("  WHERE lower(strArtist) LIKE 'various artists%%'");
        stringBuilder.append("  OR lower(strArtist) LIKE 'v.a.%%'");
        stringBuilder.append("  OR lower(strArtist) = 'va'");
        return c(this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i) {
        if (iCoverArt instanceof Artist) {
            return getCover(iNotifiableManager, iCoverArt, i, Artist.getThumbUri(iCoverArt), Artist.getFallbackThumbUri(iCoverArt));
        }
        return getCover(iNotifiableManager, iCoverArt, i, Album.getThumbUri(iCoverArt), Album.getFallbackThumbUri(iCoverArt));
    }

    public ArrayList<Genre> getGenres(INotifiableManager iNotifiableManager) {
        return e(this.mConnection.query("QueryMusicDatabase", "SELECT idGenre, strGenre FROM genre ORDER BY upper(strGenre), strGenre", iNotifiableManager));
    }

    public ArrayList<String> getPlaylist(INotifiableManager iNotifiableManager) {
        return this.mConnection.getArray(iNotifiableManager, "GetPlaylistContents", "0");
    }

    public int getPlaylistPosition(INotifiableManager iNotifiableManager) {
        return this.mConnection.getInt(iNotifiableManager, "GetPlaylistSong");
    }

    public int getPlaylistSize(INotifiableManager iNotifiableManager) {
        return this.mConnection.getInt(iNotifiableManager, "GetPlaylistLength", "0");
    }

    public ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Album album, int i, String str) {
        return a(iNotifiableManager, a(album), i, str);
    }

    public ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Artist artist, int i, String str) {
        return a(iNotifiableManager, a(artist), i, str);
    }

    public ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Artist artist, Genre genre, int i, String str) {
        return a(iNotifiableManager, a(artist, genre), i, str);
    }

    public ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Genre genre, int i, String str) {
        return a(iNotifiableManager, a(genre), i, str);
    }

    public boolean play(INotifiableManager iNotifiableManager, Album album, int i, String str) {
        StringBuilder a = a(album);
        a.append(b(i, str));
        return a(iNotifiableManager, a);
    }

    public boolean play(INotifiableManager iNotifiableManager, Artist artist, int i, String str) {
        StringBuilder a = a(artist);
        a.append(b(i, str));
        return a(iNotifiableManager, a);
    }

    public boolean play(INotifiableManager iNotifiableManager, Artist artist, Genre genre) {
        StringBuilder a = a(artist, genre);
        a.append(b(2, SortType.ORDER_ASC));
        return a(iNotifiableManager, a);
    }

    public boolean play(INotifiableManager iNotifiableManager, Genre genre, int i, String str) {
        StringBuilder a = a(genre);
        a.append(b(i, str));
        return a(iNotifiableManager, a);
    }

    public boolean play(INotifiableManager iNotifiableManager, Song song) {
        return a(iNotifiableManager, a(song));
    }

    public boolean playNext(INotifiableManager iNotifiableManager) {
        return this.mConnection.getBoolean(iNotifiableManager, "PlayNext");
    }

    public boolean playPrev(INotifiableManager iNotifiableManager) {
        return this.mConnection.getBoolean(iNotifiableManager, "PlayPrev");
    }

    public boolean playlistSetSong(INotifiableManager iNotifiableManager, int i) {
        return this.mConnection.getBoolean(iNotifiableManager, "SetPlaylistSong", String.valueOf(i));
    }

    public boolean removeFromPlaylist(INotifiableManager iNotifiableManager, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0;");
        stringBuilder.append(i);
        return this.mConnection.getBoolean(iNotifiableManager, "RemoveFromPlaylist", stringBuilder.toString());
    }

    public boolean removeFromPlaylist(INotifiableManager iNotifiableManager, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0;");
        stringBuilder.append(str);
        return this.mConnection.getBoolean(iNotifiableManager, "RemoveFromPlaylist", stringBuilder.toString());
    }

    public void setHost(Host host) {
        this.mConnection.setHost(host);
    }

    public boolean setPlaylistPosition(INotifiableManager iNotifiableManager, int i) {
        return this.mConnection.getBoolean(iNotifiableManager, "SetPlaylistSong", String.valueOf(i));
    }

    public Album updateAlbumInfo(INotifiableManager iNotifiableManager, Album album) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT g.strGenre, ai.strLabel, ai.iRating");
        stringBuilder.append("  FROM album_genre a");
        stringBuilder.append("  LEFT JOIN genre g ON a.idGenre=g.idGenre");
        stringBuilder.append("  LEFT JOIN albuminfo AS ai ON ai.idAlbum = a.idAlbum");
        stringBuilder.append("  WHERE a.idGenre = g.idGenre");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("  AND a.idAlbum = ");
        stringBuilder2.append(album.id);
        stringBuilder.append(stringBuilder2.toString());
        return a(album, this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public Artist updateArtistInfo(INotifiableManager iNotifiableManager, Artist artist) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT strBorn, strFormed, strGenres, strMoods, strStyles, strBiography");
        stringBuilder.append("  FROM artistinfo");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("  WHERE idArtist = ");
        stringBuilder2.append(artist.id);
        stringBuilder.append(stringBuilder2.toString());
        return a(artist, this.mConnection.query("QueryMusicDatabase", stringBuilder.toString(), iNotifiableManager));
    }
}
