package com.triadsoft;

import com.sun.prism.shader.FillEllipse_LinearGradient_PAD_AlphaTest_Loader;

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
    private static String FILENAME = "sites-file.csv";
    private static String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public InputStream loadFile() {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(FILENAME);
        return inputStream;
    }

    public InputStream loadFile(String filename) {
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream(filename);
        return inputStream;
    }

    public List<SiteNames> getSites() throws IOException {
        return getSites(FILENAME);
    }

    public List<SiteNames> getSites(String filename) throws IOException {
        InputStream is = loadFile(filename);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        String line;
        List<SiteNames> sites = new LinkedList<SiteNames>();
        Pattern p = Pattern.compile(regex);
        Matcher m;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            m = p.matcher(line);
            if(m.matches()) {
                sites.add(new SiteNames(line));
            }
        }
        return sites;
    }
}
