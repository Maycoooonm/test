package org.xbmc.api.business;

import android.content.Context;
import java.util.ArrayList;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Episode;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Season;
import org.xbmc.api.object.TvShow;

public interface ITvShowManager extends IManager {
    ArrayList<Episode> getAllEpisodes(Context context);

    ArrayList<Season> getAllSeasons(Context context);

    void getEpisodes(DataResponse<ArrayList<Episode>> dataResponse, Season season, Context context);

    void getEpisodes(DataResponse<ArrayList<Episode>> dataResponse, TvShow tvShow, Context context);

    void getEpisodes(DataResponse<ArrayList<Episode>> dataResponse, TvShow tvShow, Season season, Context context);

    void getRecentlyAddedEpisodes(DataResponse<ArrayList<Episode>> dataResponse, Context context);

    void getSeasons(DataResponse<ArrayList<Season>> dataResponse, TvShow tvShow, Context context);

    void getTvShowActors(DataResponse<ArrayList<Actor>> dataResponse, Context context);

    void getTvShowGenres(DataResponse<ArrayList<Genre>> dataResponse, Context context);

    ArrayList<TvShow> getTvShows(Context context);

    void getTvShows(DataResponse<ArrayList<TvShow>> dataResponse, Context context);

    void getTvShows(DataResponse<ArrayList<TvShow>> dataResponse, Actor actor, Context context);

    void getTvShows(DataResponse<ArrayList<TvShow>> dataResponse, Genre genre, Context context);

    void postActivity();

    void updateEpisodeDetails(DataResponse<Episode> dataResponse, Episode episode, Context context);

    void updateTvShowDetails(DataResponse<TvShow> dataResponse, TvShow tvShow, Context context);
}
