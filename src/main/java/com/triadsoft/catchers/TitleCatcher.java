package com.triadsoft.catchers;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 3:20 PM
 */
public class TitleCatcher extends PatternCatcher {
    public static String TITLE_CONTENT = "title.*\\>(.+?)\\</title";
    public TitleCatcher() {
        super(TITLE_CONTENT);
    }
}
