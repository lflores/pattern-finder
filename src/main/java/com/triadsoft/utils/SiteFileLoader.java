package com.triadsoft.utils;

import com.triadsoft.dto.SiteNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 9:37 AM
 */
public class SiteFileLoader {
    private Logger logger = LoggerFactory.getLogger(SiteFileLoader.class);
    private static String FILENAME = "sites-file.csv";
    private static String VALID_URL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public List<SiteNames> getSites() throws IOException {
        return getSites(FILENAME);
    }

    public List<SiteNames> getSites(String filename) throws IOException {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(filename);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String line;
        List<SiteNames> sites = new LinkedList<SiteNames>();
        Pattern p = Pattern.compile(VALID_URL);
        Matcher m;
        while ((line = br.readLine()) != null) {
            logger.debug(line);
            m = p.matcher(line);
            if(m.matches()) {
                sites.add(new SiteNames(line));
            }
        }
        return sites;
    }
}
