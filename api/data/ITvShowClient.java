package org.xbmc.api.data;

import android.graphics.Bitmap;
import java.util.ArrayList;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Episode;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Season;
import org.xbmc.api.object.TvShow;

public interface ITvShowClient extends IClient {
    Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i);

    ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, int i, String str, boolean z);

    ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, Season season, int i, String str, boolean z);

    ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, TvShow tvShow, int i, String str, boolean z);

    ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, TvShow tvShow, Season season, int i, String str, boolean z);

    ArrayList<Episode> getRecentlyAddedEpisodes(INotifiableManager iNotifiableManager, boolean z);

    ArrayList<Season> getSeasons(INotifiableManager iNotifiableManager, int i, String str, boolean z);

    ArrayList<Season> getSeasons(INotifiableManager iNotifiableManager, TvShow tvShow, boolean z);

    ArrayList<Actor> getTvShowActors(INotifiableManager iNotifiableManager);

    ArrayList<Genre> getTvShowGenres(INotifiableManager iNotifiableManager);

    ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, int i, String str, boolean z);

    ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, Actor actor, int i, String str, boolean z);

    ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, Genre genre, int i, String str, boolean z);

    Episode updateEpisodeDetails(INotifiableManager iNotifiableManager, Episode episode);

    TvShow updateTvShowDetails(INotifiableManager iNotifiableManager, TvShow tvShow);
}
