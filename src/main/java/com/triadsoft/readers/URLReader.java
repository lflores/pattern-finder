package com.triadsoft.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/10/2019 12:40 PM
 */
public class URLReader {
    private static Logger logger = LoggerFactory.getLogger(URLReader.class);
    private URL siteUrl;

    public URLReader(String siteUrl) {
        try {
            this.siteUrl = new URL(siteUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String readSite() {
        if (Objects.isNull(this.siteUrl)) {
            return null;
        }
        try {
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(siteUrl.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\\n");
            }
            reader.close();
            return buffer.toString();
        } catch (IOException ex) {
            logger.error("Error loading {} site", this.siteUrl);
        }
        return null;
    }
}
