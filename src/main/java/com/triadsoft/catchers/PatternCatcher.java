package com.triadsoft.catchers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 12:36 PM
 */
public abstract class PatternCatcher {
    private Logger logger = LoggerFactory.getLogger(PatternCatcher.class);
    private String catcherRegex;

    public PatternCatcher(String catcherRegex) {
        this.catcherRegex = catcherRegex;
    }

    public List<String> getMatches(String content) {
        List<String> matches = new ArrayList<String>();
        Pattern p = Pattern.compile(catcherRegex,Pattern.MULTILINE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            if (m.groupCount() == 1) {
                matches.add(m.group(1));
            }
        }
        return matches;
    }
}