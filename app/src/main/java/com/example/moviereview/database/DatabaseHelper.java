package com.example.moviereview.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.moviereview.model.Movie;
import com.example.moviereview.model.Review;
import com.example.moviereview.model.User;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "moviereview.db";
    private static final int DB_VERSION = 2;

    private static final String TABLE_USER = "user";
    private static final String TABLE_MOVIE = "movie";
    private static final String TABLE_REVIEW = "review";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL UNIQUE, "
                + "password TEXT NOT NULL, "
                + "nickname TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_MOVIE + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT NOT NULL, "
                + "year TEXT, "
                + "director TEXT, "
                + "rating REAL, "
                + "poster TEXT, "
                + "description TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_REVIEW + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "movie_id INTEGER NOT NULL, "
                + "user_id INTEGER NOT NULL, "
                + "content TEXT NOT NULL, "
                + "rating REAL, "
                + "date TEXT)");

        insertDefaultMovies(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // ==================== Movie ====================

    private void insertDefaultMovies(SQLiteDatabase db) {
        Object[][] movies = {
                {"肖申克的救赎", "1994", "弗兰克·德拉邦特", 9.7, "poster_1",
                        "银行家安迪因被误判谋杀罪而入狱。在肖申克监狱中，他结识了囚犯瑞德，两人成为挚友。安迪凭借才智在狱中赢得尊重，同时暗中策划了一场长达近二十年的越狱计划。最终，他成功越狱，重获自由。"},
                {"霸王别姬", "1993", "陈凯歌", 9.6, "poster_2",
                        "段小楼与程蝶衣是从小在京剧班学艺的师兄弟，两人合演《霸王别姬》成名。历经半个世纪的战乱与政治变迁，两人的命运与情感纠葛交织在一起。程蝶衣对艺术的执着最终化为一场悲剧。"},
                {"阿甘正传", "1994", "罗伯特·泽米吉斯", 9.5, "poster_3",
                        "阿甘是一个智商只有75的简单男人，但他凭借母亲的教诲和一股傻劲，经历了美国几十年的重大历史事件。从越战英雄到乒乓球冠军，从捕虾船长到亿万富翁，阿甘的一生就是一部传奇。"},
                {"泰坦尼克号", "1997", "詹姆斯·卡梅隆", 9.4, "poster_4",
                        "1912年，穷画家杰克与贵族少女罗丝在泰坦尼克号上相遇，两人坠入爱河。当巨轮撞上冰山沉没时，杰克为救罗丝牺牲了自己的生命。这段跨越阶级的爱情故事成为永恒的经典。"},
                {"千与千寻", "2001", "宫崎骏", 9.4, "poster_5",
                        "10岁女孩千寻随父母搬家途中误入了一个神灵世界。她的父母因贪吃变成了猪，千寻为了救回父母，必须在汤婆婆的澡堂工作。在白龙的帮助下，千寻逐渐成长，最终成功救回了父母。"},
                {"星际穿越", "2014", "克里斯托弗·诺兰", 9.4, "poster_6",
                        "近未来的地球黄沙遍野，前NASA宇航员库珀在女儿墨菲的书房里发现了神秘的重力异常现象。他被选中穿越虫洞，前往遥远的星系为人类寻找新的家园。"},
                {"盗梦空间", "2010", "克里斯托弗·诺兰", 9.3, "poster_7",
                        "道姆·柯布是一名精于潜意识盗取的神偷。他接受了一项看似不可能的任务：在目标人物的潜意识中植入一个想法。为此，他和团队必须深入多层梦境。"},
                {"楚门的世界", "1998", "彼得·威尔", 9.3, "poster_8",
                        "楚门从出生起就生活在一个巨大的摄影棚中，他的一切都被全球直播。当他逐渐发现真相后，决心逃离这个虚假的世界，去寻找真正的自由。"},
                {"辛德勒的名单", "1993", "史蒂文·斯皮尔伯格", 9.5, "poster_9",
                        "二战期间，德国商人奥斯卡·辛德勒目睹了纳粹对犹太人的残酷迫害。他倾尽家财，以开设工厂为名，拯救了1100多名犹太人的生命。"},
                {"蝙蝠侠：黑暗骑士", "2008", "克里斯托弗·诺兰", 9.2, "poster_10",
                        "蝙蝠侠、戈登警长和检察官哈维·丹特组成了打击犯罪的三方联盟。然而混乱的代言人小丑出现了，他将哥谭市推向了前所未有的混乱边缘。"},
                {"搏击俱乐部", "1999", "大卫·芬奇", 9.0, "poster_11",
                        "一个患有失眠症的白领遇到了肥皂商人泰勒·德顿，两人创建了一个秘密的地下搏击俱乐部。俱乐部的规模逐渐扩大，事情也朝着不可控的方向发展。"},
                {"美丽人生", "1997", "罗伯托·贝尼尼", 9.5, "poster_12",
                        "犹太青年圭多与美丽的教师多拉相爱并组建了家庭。二战爆发后，一家人被关进集中营。圭多为保护儿子的心灵，编造了一个巨大的游戏谎言。"},
                {"教父", "1972", "弗朗西斯·科波拉", 9.3, "poster_13",
                        "维托·柯里昂是纽约最大的黑帮首领。当家族遭遇危机时，原本远离黑帮的小儿子迈克尔挺身而出，继承了父亲的衣钵，成为新一代教父。"},
                {"忠犬八公的故事", "2009", "拉斯·霍尔斯道姆", 9.4, "poster_14",
                        "大学教授帕克在车站偶遇一只走失的秋田犬，收养了它并取名八公。八公每天都会在车站等帕克下班，直到帕克因病去世，八公依然日复一日在车站等待。"},
                {"放牛班的春天", "2004", "克里斯托夫·巴拉蒂", 9.3, "poster_15",
                        "过气的音乐家克莱门特来到一所问题少年寄宿学校担任代课老师。他通过组建合唱团，用音乐打开了孩子们封闭的心灵，改变了他们的人生。"}
        };
        for (Object[] m : movies) {
            ContentValues cv = new ContentValues();
            cv.put("title", (String) m[0]);
            cv.put("year", (String) m[1]);
            cv.put("director", (String) m[2]);
            cv.put("rating", (Double) m[3]);
            cv.put("poster", (String) m[4]);
            cv.put("description", (String) m[5]);
            db.insert(TABLE_MOVIE, null, cv);
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MOVIE, null);
        while (c.moveToNext()) {
            Movie m = new Movie();
            m.setId(c.getInt(0));
            m.setTitle(c.getString(1));
            m.setYear(c.getString(2));
            m.setDirector(c.getString(3));
            m.setRating(c.getFloat(4));
            m.setPoster(c.getString(5));
            m.setDescription(c.getString(6));
            list.add(m);
        }
        c.close();
        return list;
    }

    public Movie getMovieById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MOVIE + " WHERE id=?", new String[]{String.valueOf(id)});
        if (c.moveToFirst()) {
            Movie m = new Movie();
            m.setId(c.getInt(0));
            m.setTitle(c.getString(1));
            m.setYear(c.getString(2));
            m.setDirector(c.getString(3));
            m.setRating(c.getFloat(4));
            m.setPoster(c.getString(5));
            m.setDescription(c.getString(6));
            c.close();
            return m;
        }
        c.close();
        return null;
    }

    // ==================== User ====================

    public long registerUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("nickname", username);
        return db.insert(TABLE_USER, null, cv);
    }

    public User loginUser(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE username=? AND password=?",
                new String[]{username, password});
        if (c.moveToFirst()) {
            User u = new User();
            u.setId(c.getInt(0));
            u.setUsername(c.getString(1));
            u.setPassword(c.getString(2));
            u.setNickname(c.getString(3));
            c.close();
            return u;
        }
        c.close();
        return null;
    }

    // 一键注销：删除用户及其影评
    public void deleteUser(int userId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_REVIEW, "user_id=?", new String[]{String.valueOf(userId)});
        db.delete(TABLE_USER, "id=?", new String[]{String.valueOf(userId)});
    }

    // ==================== Review ====================

    public void deleteReview(int reviewId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_REVIEW, "id=?", new String[]{String.valueOf(reviewId)});
    }

    public void addReview(int movieId, int userId, String content, float rating, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("movie_id", movieId);
        cv.put("user_id", userId);
        cv.put("content", content);
        cv.put("rating", rating);
        cv.put("date", date);
        db.insert(TABLE_REVIEW, null, cv);
    }

    public List<Review> getReviewsByMovieId(int movieId) {
        List<Review> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT r.id, r.movie_id, r.user_id, r.content, r.rating, r.date, u.username "
                        + "FROM " + TABLE_REVIEW + " r "
                        + "LEFT JOIN " + TABLE_USER + " u ON r.user_id = u.id "
                        + "WHERE r.movie_id=? ORDER BY r.id DESC",
                new String[]{String.valueOf(movieId)});
        while (c.moveToNext()) {
            Review r = new Review();
            r.setId(c.getInt(0));
            r.setMovieId(c.getInt(1));
            r.setUserId(c.getInt(2));
            r.setContent(c.getString(3));
            r.setRating(c.getFloat(4));
            r.setDate(c.getString(5));
            r.setUsername(c.getString(6));
            list.add(r);
        }
        c.close();
        return list;
    }
}
