package org.xbmc.api.business;

import android.content.Context;
import java.util.ArrayList;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Movie;

public interface IVideoManager extends IManager {
    ArrayList<Actor> getActors(Context context);

    void getActors(DataResponse<ArrayList<Actor>> dataResponse, Context context);

    void getMovieActors(DataResponse<ArrayList<Actor>> dataResponse, Context context);

    void getMovieGenres(DataResponse<ArrayList<Genre>> dataResponse, Context context);

    ArrayList<Movie> getMovies(Context context);

    ArrayList<Movie> getMovies(Context context, int i);

    void getMovies(DataResponse<ArrayList<Movie>> dataResponse, Context context);

    void getMovies(DataResponse<ArrayList<Movie>> dataResponse, Actor actor, Context context);

    void getMovies(DataResponse<ArrayList<Movie>> dataResponse, Genre genre, Context context);

    void getPlaylist(DataResponse<ArrayList<String>> dataResponse, Context context);

    void getPlaylistPosition(DataResponse<Integer> dataResponse, Context context);

    void getTvShowActors(DataResponse<ArrayList<Actor>> dataResponse, Context context);

    void getTvShowGenres(DataResponse<ArrayList<Genre>> dataResponse, Context context);

    void postActivity();

    void removeFromPlaylist(DataResponse<Boolean> dataResponse, String str, Context context);

    void setPlaylistVideo(DataResponse<Boolean> dataResponse, int i, Context context);

    void updateMovieDetails(DataResponse<Movie> dataResponse, Movie movie, Context context);
}
