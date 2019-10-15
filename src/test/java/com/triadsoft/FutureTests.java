package com.triadsoft;

import com.triadsoft.catchers.HashtagCatcher;
import com.triadsoft.catchers.TitleCatcher;
import com.triadsoft.catchers.TwitterCatcher;
import com.triadsoft.dto.SiteNames;
import com.triadsoft.loaders.URLReader;
import com.triadsoft.loaders.SiteFileLoader;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/15/2019 1:24 PM
 */
public class FutureTests {

    private Logger logger = LoggerFactory.getLogger(FutureTests.class);

    @Test
    public void test_CompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        future.get();
    }

    @Test
    public void test_URLReader() throws IOException {
        List<SiteNames> urls = new SiteFileLoader().getSites();
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
    }
}
