package com.triadsoft.catchers;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 3:00 PM
 */
public class HashtagCatcher extends PatternCatcher {
    public static String HASHTAG = "\\s(#\\w+)\\s";

    public HashtagCatcher() {
        super(HASHTAG);
    }
}
