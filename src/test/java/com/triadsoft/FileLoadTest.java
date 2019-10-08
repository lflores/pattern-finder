package com.triadsoft;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple PatternFinderApp.
 */
public class FileLoadTest {

    @Test
    public void testFileLoad_ok() {
        SiteFileLoader loader = new SiteFileLoader();
        try {
            List<SiteNames> names = loader.getSites();
            assertNotNull(names);
            assertTrue(!names.isEmpty());
            assertTrue(names.size() == 21);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileLoad_invalidUrl() {
        SiteFileLoader loader = new SiteFileLoader();
        try {
            List<SiteNames> names = loader.getSites("sites-file-invalids.csv");
            assertNotNull(names);
            assertTrue(!names.isEmpty());
            assertTrue(names.size() == 21);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
