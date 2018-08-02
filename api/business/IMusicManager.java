package org.xbmc.api.business;

import android.content.Context;
import java.util.ArrayList;
import org.xbmc.api.object.Album;
import org.xbmc.api.object.Artist;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Song;

public interface IMusicManager extends IManager {
    void addToPlaylist(DataResponse<Boolean> dataResponse, Album album, Context context);

    void addToPlaylist(DataResponse<Boolean> dataResponse, Album album, Song song, Context context);

    void addToPlaylist(DataResponse<Boolean> dataResponse, Artist artist, Context context);

    void addToPlaylist(DataResponse<Boolean> dataResponse, Artist artist, Genre genre, Context context);

    void addToPlaylist(DataResponse<Boolean> dataResponse, Genre genre, Context context);

    void addToPlaylist(DataResponse<Boolean> dataResponse, Song song, Context context);

    ArrayList<Album> getAlbums(Context context);

    void getAlbums(DataResponse<ArrayList<Album>> dataResponse, Context context);

    void getAlbums(DataResponse<ArrayList<Album>> dataResponse, Artist artist, Context context);

    void getAlbums(DataResponse<ArrayList<Album>> dataResponse, Genre genre, Context context);

    void getArtists(DataResponse<ArrayList<Artist>> dataResponse, Context context);

    void getArtists(DataResponse<ArrayList<Artist>> dataResponse, Genre genre, Context context);

    void getCompilations(DataResponse<ArrayList<Album>> dataResponse, Context context);

    void getGenres(DataResponse<ArrayList<Genre>> dataResponse, Context context);

    void getPlaylist(DataResponse<ArrayList<String>> dataResponse, Context context);

    void getPlaylistPosition(DataResponse<Integer> dataResponse, Context context);

    void getSongs(DataResponse<ArrayList<Song>> dataResponse, Album album, Context context);

    void getSongs(DataResponse<ArrayList<Song>> dataResponse, Artist artist, Context context);

    void getSongs(DataResponse<ArrayList<Song>> dataResponse, Genre genre, Context context);

    void play(DataResponse<Boolean> dataResponse, Album album, Context context);

    void play(DataResponse<Boolean> dataResponse, Album album, Song song, Context context);

    void play(DataResponse<Boolean> dataResponse, Artist artist, Context context);

    void play(DataResponse<Boolean> dataResponse, Artist artist, Genre genre, Context context);

    void play(DataResponse<Boolean> dataResponse, Genre genre, Context context);

    void play(DataResponse<Boolean> dataResponse, Song song, Context context);

    void playlistNext(DataResponse<Boolean> dataResponse, Context context);

    void postActivity();

    void removeFromPlaylist(DataResponse<Boolean> dataResponse, int i, Context context);

    void removeFromPlaylist(DataResponse<Boolean> dataResponse, String str, Context context);

    void setPlaylistSong(DataResponse<Boolean> dataResponse, int i, Context context);

    void updateAlbumInfo(DataResponse<Album> dataResponse, Album album, Context context);

    void updateArtistInfo(DataResponse<Artist> dataResponse, Artist artist, Context context);
}
