package com.triadsoft.catchers;

/**
 * Esta clase busca el patrón #{texto} como identificación de un hashtag
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 3:00 PM
 */
public class HashtagCatcher extends PatternCatcher {
    public static String HASHTAG = "\\s(#\\w+)\\s";

    public HashtagCatcher() {
        super(HASHTAG);
    }
}
