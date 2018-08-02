package org.xbmc.api.data;

import android.graphics.Bitmap;
import java.util.ArrayList;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.Album;
import org.xbmc.api.object.Artist;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Song;

public interface IMusicClient extends IClient {
    public static final int MUSICDB_ALBUM = 3;
    public static final int MUSICDB_ARTIST = 2;
    public static final int MUSICDB_COMPILATION = 8;
    public static final int MUSICDB_GENRE = 1;
    public static final int MUSICDB_RECENTLY_ADDED = 6;
    public static final int MUSICDB_RECENTLY_PLAYED = 7;
    public static final int MUSICDB_SINGLES = 10;
    public static final int MUSICDB_SONG = 4;
    public static final int MUSICDB_TOP100 = 5;
    public static final int MUSICDB_YEARS = 9;

    boolean addToPlaylist(INotifiableManager iNotifiableManager, Album album, int i, String str);

    boolean addToPlaylist(INotifiableManager iNotifiableManager, Artist artist, int i, String str);

    boolean addToPlaylist(INotifiableManager iNotifiableManager, Artist artist, Genre genre, int i, String str);

    boolean addToPlaylist(INotifiableManager iNotifiableManager, Genre genre, int i, String str);

    boolean addToPlaylist(INotifiableManager iNotifiableManager, Song song);

    boolean clearPlaylist(INotifiableManager iNotifiableManager);

    ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, int i, String str);

    ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, ArrayList<Integer> arrayList);

    ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, Artist artist, int i, String str);

    ArrayList<Album> getAlbums(INotifiableManager iNotifiableManager, Genre genre, int i, String str);

    ArrayList<Artist> getArtists(INotifiableManager iNotifiableManager, Genre genre, boolean z);

    ArrayList<Artist> getArtists(INotifiableManager iNotifiableManager, boolean z);

    ArrayList<Integer> getCompilationArtistIDs(INotifiableManager iNotifiableManager);

    Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i);

    ArrayList<Genre> getGenres(INotifiableManager iNotifiableManager);

    ArrayList<String> getPlaylist(INotifiableManager iNotifiableManager);

    int getPlaylistPosition(INotifiableManager iNotifiableManager);

    int getPlaylistSize(INotifiableManager iNotifiableManager);

    ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Album album, int i, String str);

    ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Artist artist, int i, String str);

    ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Artist artist, Genre genre, int i, String str);

    ArrayList<Song> getSongs(INotifiableManager iNotifiableManager, Genre genre, int i, String str);

    boolean play(INotifiableManager iNotifiableManager, Album album, int i, String str);

    boolean play(INotifiableManager iNotifiableManager, Artist artist, int i, String str);

    boolean play(INotifiableManager iNotifiableManager, Artist artist, Genre genre);

    boolean play(INotifiableManager iNotifiableManager, Genre genre, int i, String str);

    boolean play(INotifiableManager iNotifiableManager, Song song);

    boolean playNext(INotifiableManager iNotifiableManager);

    boolean playPrev(INotifiableManager iNotifiableManager);

    boolean playlistSetSong(INotifiableManager iNotifiableManager, int i);

    boolean removeFromPlaylist(INotifiableManager iNotifiableManager, int i);

    boolean removeFromPlaylist(INotifiableManager iNotifiableManager, String str);

    boolean setPlaylistPosition(INotifiableManager iNotifiableManager, int i);

    Album updateAlbumInfo(INotifiableManager iNotifiableManager, Album album);

    Artist updateArtistInfo(INotifiableManager iNotifiableManager, Artist artist);
}
