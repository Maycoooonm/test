package org.xbmc.api.data;

import android.graphics.Bitmap;
import java.util.ArrayList;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Movie;

public interface IVideoClient extends IClient {
    ArrayList<Actor> getActors(INotifiableManager iNotifiableManager);

    Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i);

    ArrayList<Actor> getMovieActors(INotifiableManager iNotifiableManager);

    ArrayList<Genre> getMovieGenres(INotifiableManager iNotifiableManager);

    ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, int i, String str, int i2, boolean z);

    ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, int i, String str, boolean z);

    ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, Actor actor, int i, String str, boolean z);

    ArrayList<Movie> getMovies(INotifiableManager iNotifiableManager, Genre genre, int i, String str, boolean z);

    ArrayList<String> getPlaylist(INotifiableManager iNotifiableManager);

    int getPlaylistPosition(INotifiableManager iNotifiableManager);

    ArrayList<Actor> getTvShowActors(INotifiableManager iNotifiableManager);

    ArrayList<Genre> getTvShowGenres(INotifiableManager iNotifiableManager);

    boolean removeFromPlaylist(INotifiableManager iNotifiableManager, String str);

    boolean setPlaylistPosition(INotifiableManager iNotifiableManager, int i);

    Movie updateMovieDetails(INotifiableManager iNotifiableManager, Movie movie);
}
