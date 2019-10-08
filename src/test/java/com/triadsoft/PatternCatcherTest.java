package com.triadsoft;

import com.triadsoft.catchers.HashtagCatcher;
import com.triadsoft.catchers.TitleCatcher;
import com.triadsoft.catchers.TwitterCatcher;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 12:54 PM
 */
public class PatternCatcherTest {
    private Logger logger = LoggerFactory.getLogger(FileLoadTest.class);

    @Test
    public void testReadFileWithClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("nytimes.com.html").getFile());
        assertTrue(file.exists());
        String content = getHtmlContent("nytimes.com.html");
        assertNotNull(content);
    }

    @Test
    public void testTitle_ok() {
        String example1Html = getHtmlContent("html-tittle-test.html");
        logger.debug(example1Html);
        assertNotNull(example1Html);
        TitleCatcher catcher = new TitleCatcher();
        List<String> matchList = catcher.getMatches(example1Html);
        assertTrue(matchList.size() > 0);
        assertTrue(matchList.size() == 1);
        logger.debug("" + matchList);
    }


    @Test
    public void testNyTimes_title() {
        String nyTimes = getHtmlContent("nytimes.com.html");
        assertNotNull(nyTimes);
        TitleCatcher catcher = new TitleCatcher();
        List<String> matchList = catcher.getMatches(nyTimes);
        logger.debug("" + matchList);
        assertTrue(matchList.size() == 1);
        assertTrue("The New York Times - Breaking News, World News & Multimedia".equals(matchList.get(0)));
    }

    @Test
    public void testNyTimes_hashtag() {
        String nyTimes = getHtmlContent("nytimes.com.html");
        assertNotNull(nyTimes);
        HashtagCatcher catcher = new HashtagCatcher();
        List<String> matchList = catcher.getMatches(nyTimes);
        logger.debug("" + matchList);
        assertTrue(matchList.size() == 1);
        assertTrue("#mytesthashtag".equals(matchList.get(0)));
    }
//
    @Test
    public void testNyTimes_twitter() {
        String twitterExample = getHtmlContent("twitter-home.html");
        //logger.debug(twitterExample);
        assertNotNull(twitterExample);
        TwitterCatcher catcher = new TwitterCatcher();
        List<String> matchList = catcher.getMatches(twitterExample);
        logger.debug("Count: {}, {}", matchList.size(), matchList);
        assertTrue(matchList.size() == 26);
        assertTrue("@le_Parisien".equals(matchList.get(0)));
        assertTrue("@keyframes".equals(matchList.get(25)));
    }

    private String getHtmlContent(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
//            InputStream inputStream = getClass()
//                    .getClassLoader().getResourceAsStream(filename);
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            BufferedReader br = new BufferedReader(reader);
//
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(filename).getFile());
            InputStream inputStream = new FileInputStream(file);
            //return new String(Files.readAllBytes(file.toPath()));
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "";
    }
}
