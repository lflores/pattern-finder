package com.triadsoft;

import com.triadsoft.catchers.HashtagCatcher;
import com.triadsoft.catchers.TitleCatcher;
import com.triadsoft.catchers.TwitterCatcher;
import com.triadsoft.dto.SiteNames;
import com.triadsoft.loaders.URLReader;
import com.triadsoft.loaders.SiteFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class PatternFinderApp
{
    private static Logger logger = LoggerFactory.getLogger(PatternFinderApp.class);

    public static void main( String[] args )
    {
        logger.info("I found {} arguments",args.length);
        SiteFileLoader loader = new SiteFileLoader();
        try {
            List<SiteNames> urls = loader.getSites();
            logger.info(urls.toString());
            List<URLReader> sites =
                    urls.stream().map(i -> new URLReader(i.getUrl()))
                            .collect(Collectors.toList());

            List<CompletableFuture<String>> futures =
                    sites.stream()
                            .map(t -> CompletableFuture.supplyAsync(() -> t.readSite()))
                            .collect(Collectors.toList());

            List<String> result =
                    futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());

            List<List<String>> titles =
                    result.stream()
                            .map(t -> new TitleCatcher().getMatches(t))
                            .collect(Collectors.toList());

            logger.info("Titles: " + titles);

            List<List<String>> twitters =
                    result.stream()
                            .map(t -> new TwitterCatcher().getMatches(t))
                            .collect(Collectors.toList());

            logger.info("Twitters: " + twitters);

            List<List<String>> hashtags =
                    result.stream()
                            .map(t -> new HashtagCatcher().getMatches(t))
                            .collect(Collectors.toList());

            logger.info("Hashtags: " + hashtags);

        }catch (IOException ex){
            logger.error("No se pudo obtener la lista de sitios",ex);
        }

    }
}
