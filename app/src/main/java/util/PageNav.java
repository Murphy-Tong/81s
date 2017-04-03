package util;

/**
 * Created by TONG on 2016/12/30.
 */
public class PageNav {


    private static String[] TVSectionTitle = {
            "全部", "大陆剧", "港台剧", "日韩剧", "欧美剧"
    };
    private static String[] TVSectionUrl = {
            "http://www.80s.tw/ju/list/----0--p",
            "http://www.80s.tw/ju/list/----9--p",
            "http://www.80s.tw/ju/list/----10--p",
            "http://www.80s.tw/ju/list/----11--p",
            "http://www.80s.tw/ju/list/----12--p"
    };


    private static String[] movieTitleNav = {
            "国语电影", "喜剧片", "动作片", "恐怖片", "爱情片", "科幻片", "评分最高"
    };
    private static String[] moviewSectionUrl = {
            "http://www.80s.tw/movie/list/---1--p",
            "http://www.80s.tw/movie/list/2-----p",
            "http://www.80s.tw/movie/list/1-----p",
            "http://www.80s.tw/movie/list/6-----p",
            "http://www.80s.tw/movie/list/3-----p",
            "http://www.80s.tw/movie/list/4-----p",
            "http://www.80s.tw/movie/list/----g-p"
    };


    private static String[] CourseTitleNav = {
            "最新",
            "热门", "好评"
    };
    private static String[] CourseSectionUrl = {
            "http://www.80s.tw/course/list/-----p",
            "http://www.80s.tw/course/list/----h-p",
            "http://www.80s.tw/course/list/----g-p"
    };

    private static String[] ShortTitleNav = {
            "全部",
            "恶搞短片", "相声小品", "爆笑动物", "香车美女", "其他"
    };
    private static String[] ShortSectionUrl = {
            "http://www.80s.tw/video/list/-----p",
            "http://www.80s.tw/video/list/1-----p",
            "http://www.80s.tw/video/list/2-----p",
            "http://www.80s.tw/video/list/3-----p",
            "http://www.80s.tw/video/list/4-----p",
            "http://www.80s.tw/video/list/5-----p",
    };


    private static String[] MVTitleNav = {
            "全部",
            "华语", "日韩", "欧美"
    };
    private static String[] MVSectionUrl = {
            "http://www.80s.tw/mv/list/-----p",
            "http://www.80s.tw/mv/list/1-----p",
            "http://www.80s.tw/mv/list/2-----p",
            "http://www.80s.tw/mv/list/3-----p"
    };

    private static String[] MediaTitleNav = {
            "最新",
            "最热", "好评"
    };
    private static String[] MediaSectionUrl = {
            "http://www.80s.tw/zy/list/----4--p",
            "http://www.80s.tw/zy/list/----4-h-p",
            "http://www.80s.tw/zy/list/----4-g-p"
    };


    private static String[] COMICTitleNav = {
            "动漫频道",
            "连载动漫", "剧场版"
    };
    private static String[] COMICSectionUrl = {
            "http://www.80s.tw/dm/list/----14--p",
            "http://www.80s.tw/dm/list/l----14--p",
            "http://www.80s.tw/dm/list/j----14--p"
    };
    public static final int MOVIE = 0;
    public static final int TV = 1;
    public static final int COMIC = 2;
    public static final int SHORT = 3;
    public static final int MV = 4;
    public static final int MEDIA = 5;
    public static final int COURSE = 6;

    public static int size(int kind) {
        switch (kind) {
            case SHORT:
                return ShortSectionUrl.length;
            case MV:
                return MVSectionUrl.length;
            case MEDIA:
                return MediaSectionUrl.length;
            case COMIC:
                return COMICSectionUrl.length;
            case MOVIE:
                return movieTitleNav.length;
            case COURSE:
                return CourseSectionUrl.length;
            case TV:
                return TVSectionUrl.length;
            default:
                return 0;
        }
    }

    public static boolean isEmpty(int kind) {
        switch (kind) {
            case MOVIE:
                return movieTitleNav == null || movieTitleNav.length == 0;
            case TV:
                return TVSectionUrl == null || TVSectionUrl.length == 0;
            case MV:
                return MVSectionUrl == null || MVSectionUrl.length == 0;
            case SHORT:
                return ShortSectionUrl == null || ShortSectionUrl.length == 0;
            case COURSE:
                return CourseSectionUrl == null || CourseSectionUrl.length == 0;
            default:
                return true;
        }

    }

    public static String[] getTitles(int kind) {
        switch (kind) {
            case MV:
                return MVTitleNav;

            case SHORT:
                return ShortTitleNav;

            case COURSE:
                return CourseTitleNav;

            case COMIC:
                return COMICTitleNav;

            case MEDIA:
                return MediaTitleNav;

            case MOVIE:
                return movieTitleNav;

            case TV:
                return TVSectionTitle;
            default:
                return null;
        }

    }

    public static String[] getUrls(int kind) {
        switch (kind) {
            case MV:
                return MVSectionUrl;
            case SHORT:
                return ShortSectionUrl;
            case MEDIA:
                return MediaSectionUrl;
            case COMIC:
                return COMICSectionUrl;
            case COURSE:
                return CourseSectionUrl;

            case MOVIE:
                return moviewSectionUrl;
            case TV:
                return TVSectionUrl;
            default:
                return null;
        }

    }

}
