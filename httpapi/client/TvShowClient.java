package org.xbmc.httpapi.client;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.jmdns.impl.DNSConstants;
import org.xbmc.api.business.INotifiableManager;
import org.xbmc.api.data.IControlClient.ICurrentlyPlaying;
import org.xbmc.api.data.ITvShowClient;
import org.xbmc.api.info.PlayStatus;
import org.xbmc.api.object.Actor;
import org.xbmc.api.object.Episode;
import org.xbmc.api.object.Genre;
import org.xbmc.api.object.Host;
import org.xbmc.api.object.ICoverArt;
import org.xbmc.api.object.Season;
import org.xbmc.api.object.TvShow;
import org.xbmc.httpapi.Connection;

public class TvShowClient extends a implements ITvShowClient {
    private static final String a = "TvShowClient";

    public TvShowClient(Connection connection) {
        super(connection);
    }

    private String a(int i, String str) {
        StringBuilder stringBuilder;
        String str2;
        if (i != 3) {
            switch (i) {
                case 6:
                    stringBuilder = new StringBuilder();
                    str2 = " ORDER BY ROUND(tvshow.c04, 2) ";
                    break;
                case 7:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" ORDER BY tvshow.c05 ");
                    stringBuilder.append(str);
                    str2 = ", CASE WHEN tvshow.c15 IS NULL OR tvshow.c15 = '' THEN lower(tvshow.c00) ELSE lower(tvshow.c15) END ";
                    break;
                case 8:
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(" ORDER BY episodeview.c12+0 ");
                    stringBuilder.append(str);
                    str2 = ", episodeview.c13+0 ";
                    break;
                case 9:
                    stringBuilder = new StringBuilder();
                    str2 = " ORDER BY episodeview.c00 ";
                    break;
                case 10:
                    stringBuilder = new StringBuilder();
                    str2 = " ORDER BY episodeview.c03 ";
                    break;
                default:
                    return " ORDER BY lower(tvshow.c00) asc";
            }
        }
        stringBuilder = new StringBuilder();
        str2 = " ORDER BY CASE WHEN tvshow.c15 IS NULL OR tvshow.c15 = '' THEN replace(lower(tvshow.c00),'the ','') ELSE replace(lower(tvshow.c15),'the ','') END ";
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
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
                return (String) hashMap.get("Title");
            }

            public String getArtist() {
                StringBuilder stringBuilder;
                String str;
                if (Integer.valueOf((String) hashMap.get("Season")).intValue() == 0) {
                    stringBuilder = new StringBuilder();
                    str = "Specials / Episode ";
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Season ");
                    stringBuilder.append((String) hashMap.get("Season"));
                    str = " / Episode ";
                }
                stringBuilder.append(str);
                stringBuilder.append((String) hashMap.get(Episode.TAG));
                return stringBuilder.toString();
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
                return 22;
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
                throw new UnsupportedOperationException("Method not decompiled: org.xbmc.httpapi.client.TvShowClient.1.getPercentage():float");
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
                return (String) hashMap.get("Show Title");
            }

            public int getWidth() {
                return 0;
            }

            public boolean isPlaying() {
                return PlayStatus.parse((String) hashMap.get("PlayStatus")) == 1;
            }
        };
    }

    private Episode a(String str, Episode episode) {
        try {
            episode.plot = Connection.trim(str.split("<field>")[1]);
            return episode;
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
            return episode;
        }
    }

    private TvShow a(String str, TvShow tvShow) {
        try {
            tvShow.summary = Connection.trim(str.split("<field>")[1]);
            return tvShow;
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
            return tvShow;
        }
    }

    public Bitmap getCover(INotifiableManager iNotifiableManager, ICoverArt iCoverArt, int i) {
        return getCover(iNotifiableManager, iCoverArt, i, TvShow.getThumbUri(iCoverArt), TvShow.getFallbackThumbUri(iCoverArt));
    }

    public ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idEpisode, c00, \"\" AS c01, ROUND(c03, 2), c04, c05, c06, playCount, c10, c12, c13, strPath, strFileName, strTitle, art.url");
        stringBuilder.append(" FROM episodeview LEFT OUTER JOIN art ON art.media_id=episodeview.idEpisode AND art.media_type='episode' AND art.type='thumb'");
        if (z) {
            stringBuilder.append(" WHERE (playCount IS NULL OR playCount = 0) ");
        }
        stringBuilder.append(a(i, str));
        return parseEpisodes(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, Season season, int i, String str, boolean z) {
        return getEpisodes(iNotifiableManager, season.show, season, i, str, z);
    }

    public ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, TvShow tvShow, int i, String str, boolean z) {
        return getEpisodes(iNotifiableManager, tvShow, null, i, str, z);
    }

    public ArrayList<Episode> getEpisodes(INotifiableManager iNotifiableManager, TvShow tvShow, Season season, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idEpisode, c00, \"\" AS c01, ROUND(c03, 2), c04, c05, c06, playCount, c10, c12, c13, strPath, strFileName, strTitle, art.url");
        stringBuilder.append(" FROM episodeview  LEFT OUTER JOIN art ON art.media_id=episodeview.idEpisode AND art.media_type='episode' AND art.type='thumb'");
        stringBuilder.append(" WHERE idShow in ( select idShow from tvshow where c00 in (select c00 from tvshow where idShow = ");
        stringBuilder.append(tvShow.id);
        stringBuilder.append("))");
        if (z) {
            stringBuilder.append(" AND (playCount IS NULL OR playCount = 0) ");
        }
        if (season != null) {
            stringBuilder.append(" AND (c12 = ");
            stringBuilder.append(season.number);
            stringBuilder.append(" OR (c12 = 0 AND (c15 = 0 OR c15 = ");
            stringBuilder.append(season.number);
            stringBuilder.append(")))");
        }
        stringBuilder.append(a(i, str));
        return parseEpisodes(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Episode> getRecentlyAddedEpisodes(INotifiableManager iNotifiableManager, boolean z) {
        return null;
    }

    public ArrayList<Season> getSeasons(INotifiableManager iNotifiableManager, int i, String str, boolean z) {
        ArrayList tvShows = getTvShows(iNotifiableManager, i, str, z);
        HashMap hashMap = new HashMap();
        Iterator it = tvShows.iterator();
        while (it.hasNext()) {
            TvShow tvShow = (TvShow) it.next();
            hashMap.put(Integer.valueOf(tvShow.id), tvShow);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT min(tvshow.idShow) as idShow, episode.c12, art.url ");
        stringBuilder.append("FROM tvshow ");
        stringBuilder.append("\tLEFT OUTER JOIN episode ON episode.idShow = tvshow.idShow ");
        if (z) {
            stringBuilder.append(" LEFT OUTER JOIN files ON files.idFile = episode.idFile ");
        }
        stringBuilder.append(" LEFT OUTER JOIN seasons ON seasons.idShow=tvshow.idShow AND seasons.season=episode.c12");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=seasons.idSeason AND art.media_type='season' AND art.type='thumb'");
        stringBuilder.append("WHERE NOT episode.c12 is null ");
        if (z) {
            stringBuilder.append(" AND (files.playCount IS NULL OR files.playCount = 0) ");
        }
        stringBuilder.append("GROUP BY episode.c12, tvshow.c00 ");
        stringBuilder.append("ORDER BY tvshow.idShow, episode.c12+0");
        return parseSeasons(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager), hashMap);
    }

    public ArrayList<Season> getSeasons(INotifiableManager iNotifiableManager, TvShow tvShow, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c12, art.url from (");
        stringBuilder.append("\tSELECT episode.c12, tvshow.idshow ");
        stringBuilder.append("\tFROM tvshow ");
        stringBuilder.append("\t\tLEFT OUTER JOIN episode ON episode.idShow = tvshow.idShow ");
        if (z) {
            stringBuilder.append(" LEFT OUTER JOIN files ON files.idFile = episode.idFile ");
        }
        stringBuilder.append("\tWHERE tvshow.c00 = (SELECT c00 FROM tvshow WHERE tvshow.idShow = ");
        stringBuilder.append(tvShow.id);
        stringBuilder.append("\t) ");
        if (z) {
            stringBuilder.append(" AND (files.playCount IS NULL OR files.playCount = 0) ");
        }
        stringBuilder.append("GROUP BY episode.c12, tvshow.c00 ");
        stringBuilder.append(") q");
        stringBuilder.append(" LEFT OUTER JOIN seasons ON seasons.idShow=q.idShow AND seasons.season=q.c12");
        stringBuilder.append(" LEFT OUTER JOIN art ON art.media_id=seasons.idSeason AND art.media_type='season' AND art.type='thumb'");
        stringBuilder.append(" where not q.c12 is null ");
        stringBuilder.append("ORDER BY q.c12+0");
        return parseSeasons(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager), tvShow);
    }

    public ArrayList<Actor> getTvShowActors(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT actors.idActor, strActor FROM actors, actorlinktvshow");
        stringBuilder.append(" WHERE actorlinktvshow.idActor = actors.idActor");
        stringBuilder.append(" ORDER BY upper(strActor), strActor");
        return VideoClient.parseActors(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<Genre> getTvShowGenres(INotifiableManager iNotifiableManager) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT idGenre, strGenre FROM genre");
        stringBuilder.append(" WHERE idGenre IN (SELECT idGenre FROM genrelinktvshow)");
        stringBuilder.append(" ORDER BY upper(strGenre)");
        return VideoClient.parseGenres(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT tvshow.idShow, tvshow.c00, \"\" AS c01, ROUND(tvshow.c04, 2), tvshow.c05, tvshow.c08, tvshow.c13, tvshow.c14,");
        stringBuilder.append("\t\tpaths.strPath,");
        stringBuilder.append("\t\tcounts.totalcount AS totalCount,");
        stringBuilder.append("\t\tcounts.watchedcount AS watchedCount,");
        stringBuilder.append("\t\tcounts.totalcount = counts.watchedcount AS watched,");
        stringBuilder.append("\t\tart.url AS artUrl");
        stringBuilder.append("\tFROM (");
        stringBuilder.append("\t\tselect min(tvshow.idShow) as idShow, tvshow.c00 from tvshow");
        stringBuilder.append("\t\tgroup by tvshow.c00");
        stringBuilder.append("\t) showNames");
        stringBuilder.append("\tLEFT OUTER join tvshow on showNames.idShow = tvshow.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT min(tvshow.idShow) as idShow, tvshow.c00, count(1) AS totalcount, count(files.playCount) AS watchedcount");
        stringBuilder.append(" \tFROM tvshow");
        stringBuilder.append("\t\tJOIN episode ON episode.idShow = tvshow.idShow");
        stringBuilder.append("\t\tJOIN files ON files.idFile = episode.idFile");
        stringBuilder.append("\t\tGROUP BY tvshow.c00");
        stringBuilder.append("\t) counts ON tvshow.idShow = counts.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT tvshow.idShow, strPath ");
        stringBuilder.append("\t\tFROM tvshow");
        stringBuilder.append("\t\tJOIN tvshowlinkpath ON tvshow.idShow = tvshowlinkpath.idShow");
        stringBuilder.append("\t\tJOIN path ON path.idpath = tvshowlinkpath.idPath");
        stringBuilder.append("\t\tWHERE path.idPath in (SELECT max(idPath) FROM tvshowlinkpath GROUP BY idShow)");
        stringBuilder.append("\t)  paths on tvshow.idShow = paths.idShow ");
        stringBuilder.append(" LEFT OUTER JOIN ");
        stringBuilder.append("\t\tart ON art.media_id=tvshow.idShow AND art.media_type='tvshow' AND art.type='thumb'");
        if (z) {
            stringBuilder.append(" WHERE counts.totalcount > counts.watchedcount ");
        }
        stringBuilder.append(a(i, str));
        Log.i(a, stringBuilder.toString());
        return parseShows(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, Actor actor, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT tvshow.idShow, tvshow.c00, \"\" AS c01, ROUND(tvshow.c04, 2), tvshow.c05, tvshow.c08, tvshow.c13, tvshow.c14,");
        stringBuilder.append("\t\tpaths.strPath,");
        stringBuilder.append("\t\tcounts.totalcount AS totalCount,");
        stringBuilder.append("\t\tcounts.watchedcount AS watchedCount,");
        stringBuilder.append("\t\tcounts.totalcount = counts.watchedcount AS watched,");
        stringBuilder.append("\t\tart.url AS artUrl");
        stringBuilder.append("\tFROM (");
        stringBuilder.append("\t\tselect min(tvshow.idShow) as idShow, tvshow.c00 from tvshow");
        stringBuilder.append("\t\tgroup by tvshow.c00");
        stringBuilder.append("\t) showNames");
        stringBuilder.append("\tLEFT OUTER join tvshow on showNames.idShow = tvshow.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT min(tvshow.idShow) as idShow, tvshow.c00, count(1) AS totalcount, count(files.playCount) AS watchedcount");
        stringBuilder.append(" \tFROM tvshow");
        stringBuilder.append("\t\tJOIN episode ON episode.idShow = tvshow.idShow");
        stringBuilder.append("\t\tJOIN files ON files.idFile = episode.idFile");
        stringBuilder.append("\t\tGROUP BY tvshow.c00");
        stringBuilder.append("\t) counts ON tvshow.idShow = counts.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT tvshow.idShow, strPath ");
        stringBuilder.append("\t\tFROM tvshow");
        stringBuilder.append("\t\tJOIN tvshowlinkpath ON tvshow.idShow = tvshowlinkpath.idShow");
        stringBuilder.append("\t\tJOIN path ON path.idpath = tvshowlinkpath.idPath");
        stringBuilder.append("\t\tWHERE path.idPath in (SELECT max(idPath) FROM tvshowlinkpath GROUP BY idShow)");
        stringBuilder.append("\t)  paths on tvshow.idShow = paths.idShow ");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT min(tvshow.idShow) as idShow, actorlinktvshow.idActor FROM tvshow");
        stringBuilder.append("\t\tLEFT OUTER JOIN actorlinktvshow ON tvshow.idShow = actorlinktvshow.idShow");
        stringBuilder.append("\t\tGROUP BY tvshow.c00, actorlinktvshow.idActor");
        stringBuilder.append("\t) actors on tvshow.idShow = actors.idShow");
        stringBuilder.append(" LEFT OUTER JOIN ");
        stringBuilder.append("\t\tart ON art.media_id=tvshow.idShow AND art.media_type='tvshow' AND art.type='thumb'");
        stringBuilder.append("\tWHERE actors.idActor = ");
        stringBuilder.append(actor.id);
        if (z) {
            stringBuilder.append(" AND counts.totalcount > counts.watchedcount ");
        }
        stringBuilder.append(a(i, str));
        Log.i(a, stringBuilder.toString());
        return parseShows(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    public ArrayList<TvShow> getTvShows(INotifiableManager iNotifiableManager, Genre genre, int i, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT tvshow.idShow, tvshow.c00, \"\" AS c01, ROUND(tvshow.c04, 2), tvshow.c05, tvshow.c08, tvshow.c13, tvshow.c14,");
        stringBuilder.append("\t\tpaths.strPath,");
        stringBuilder.append("\t\tcounts.totalcount AS totalCount,");
        stringBuilder.append("\t\tcounts.watchedcount AS watchedCount,");
        stringBuilder.append("\t\tcounts.totalcount = counts.watchedcount AS watched,");
        stringBuilder.append("\t\tart.url AS artUrl");
        stringBuilder.append("\tFROM (");
        stringBuilder.append("\t\tselect min(tvshow.idShow) as idShow, tvshow.c00 from tvshow");
        stringBuilder.append("\t\tgroup by tvshow.c00");
        stringBuilder.append("\t) showNames");
        stringBuilder.append("\tLEFT OUTER join tvshow on showNames.idShow = tvshow.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT min(tvshow.idShow) as idShow, tvshow.c00, count(1) AS totalcount, count(files.playCount) AS watchedcount");
        stringBuilder.append(" \tFROM tvshow");
        stringBuilder.append("\t\tJOIN episode ON episode.idShow = tvshow.idShow");
        stringBuilder.append("\t\tJOIN files ON files.idFile = episode.idFile");
        stringBuilder.append("\t\tGROUP BY tvshow.c00");
        stringBuilder.append("\t) counts ON tvshow.idShow = counts.idShow");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT tvshow.idShow, strPath ");
        stringBuilder.append("\t\tFROM tvshow");
        stringBuilder.append("\t\tJOIN tvshowlinkpath ON tvshow.idShow = tvshowlinkpath.idShow");
        stringBuilder.append("\t\tJOIN path ON path.idpath = tvshowlinkpath.idPath");
        stringBuilder.append("\t\tWHERE path.idPath in (SELECT max(idPath) FROM tvshowlinkpath GROUP BY idShow)");
        stringBuilder.append("\t)  paths on tvshow.idShow = paths.idShow ");
        stringBuilder.append("\tLEFT OUTER join (");
        stringBuilder.append("\t\tSELECT min(tvshow.idShow) as idShow, genrelinktvshow.idGenre FROM tvshow");
        stringBuilder.append("\t\tLEFT OUTER JOIN genrelinktvshow ON tvshow.idShow = genrelinktvshow.idShow");
        stringBuilder.append("\t\tGROUP BY tvshow.c00, genrelinktvshow.idGenre");
        stringBuilder.append("\t) genres on tvshow.idShow = genres.idShow");
        stringBuilder.append(" LEFT OUTER JOIN ");
        stringBuilder.append("\t\tart ON art.media_id=tvshow.idShow AND art.media_type='tvshow' AND art.type='thumb'");
        stringBuilder.append("\tWHERE genres.idGenre = ");
        stringBuilder.append(genre.id);
        if (z) {
            stringBuilder.append(" AND counts.totalcount > counts.watchedcount ");
        }
        stringBuilder.append(a(i, str));
        Log.i(a, stringBuilder.toString());
        return parseShows(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
    }

    protected ArrayList<Episode> parseEpisodes(String str) {
        String str2 = str;
        ArrayList<Episode> arrayList = new ArrayList();
        String[] split = str2.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Episode(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2]), Connection.trimDouble(split[i + 3]), Connection.trim(split[i + 4]), Connection.trim(split[i + 5]), Connection.trimInt(split[i + 7]), Connection.trim(split[i + 8]), Connection.trimInt(split[i + 9]), Connection.trimInt(split[i + 10]), Connection.trim(split[i + 11]), Connection.trim(split[i + 12]), Connection.trim(split[i + 13]), Connection.trim(split[i + 14])));
                i += 15;
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

    protected ArrayList<Season> parseSeasons(String str, HashMap<Integer, TvShow> hashMap) {
        ArrayList<Season> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                int trimInt = Connection.trimInt(split[i]);
                if (hashMap.containsKey(Integer.valueOf(trimInt))) {
                    arrayList.add(new Season(Connection.trimInt(split[i + 1]), false, (TvShow) hashMap.get(Integer.valueOf(trimInt)), Connection.trim(split[i + 2])));
                }
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

    protected ArrayList<Season> parseSeasons(String str, TvShow tvShow) {
        ArrayList<Season> arrayList = new ArrayList();
        String[] split = str.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new Season(Connection.trimInt(split[i]), false, tvShow, Connection.trim(split[i + 1])));
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

    protected ArrayList<TvShow> parseShows(String str) {
        String str2 = str;
        ArrayList<TvShow> arrayList = new ArrayList();
        String[] split = str2.split("<field>");
        int i = 1;
        while (i < split.length) {
            try {
                arrayList.add(new TvShow(Connection.trimInt(split[i]), Connection.trim(split[i + 1]), Connection.trim(split[i + 2]), Connection.trimDouble(split[i + 3]), Connection.trim(split[i + 4]), Connection.trim(split[i + 5]), Connection.trim(split[i + 6]), Connection.trim(split[i + 7]), Connection.trim(split[i + 8]), Connection.trimInt(split[i + 9]), Connection.trimInt(split[i + 10]), Connection.trimBoolean(split[i + 11]), Connection.trim(split[i + 12])));
                i += 13;
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

    public void setHost(Host host) {
        this.mConnection.setHost(host);
    }

    public Episode updateEpisodeDetails(INotifiableManager iNotifiableManager, Episode episode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c01 ");
        stringBuilder.append(" FROM episodeview ");
        stringBuilder.append(" WHERE idEpisode=");
        stringBuilder.append(episode.id);
        episode = a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager), episode);
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT actors.idActor, strActor, art.url, strRole");
        stringBuilder.append(" FROM actors LEFT OUTER JOIN art ON art.media_id=actors.idActor AND art.media_type='actor' AND art.type='thumb', actorlinkepisode");
        stringBuilder.append(" WHERE actors.idActor = actorlinkepisode.idActor");
        stringBuilder.append(" AND actorlinkepisode.idEpisode =");
        stringBuilder.append(episode.id);
        episode.actors = VideoClient.parseActorRoles(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
        return episode;
    }

    public TvShow updateTvShowDetails(INotifiableManager iNotifiableManager, TvShow tvShow) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c01");
        stringBuilder.append("  FROM tvshow");
        stringBuilder.append("  WHERE tvshow.idShow = ");
        stringBuilder.append(tvShow.id);
        tvShow = a(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager), tvShow);
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT actors.idActor, strActor, art.url, strRole");
        stringBuilder.append(" FROM actors LEFT JOIN art ON art.media_id=actors.idActor AND art.media_type='actor' AND art.type='thumb', actorlinktvshow");
        stringBuilder.append(" WHERE actors.idActor = actorlinktvshow.idActor");
        stringBuilder.append(" AND actorlinktvshow.idShow =");
        stringBuilder.append(tvShow.getId());
        tvShow.actors = VideoClient.parseActorRoles(this.mConnection.query("QueryVideoDatabase", stringBuilder.toString(), iNotifiableManager));
        return tvShow;
    }
}
