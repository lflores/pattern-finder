package com.triadsoft.catchers;

/**
 * Ésta clase captura una cuenta de twitter a partir de la cadena @{text}
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 3:01 PM
 */
public class TwitterCatcher extends PatternCatcher {
    public TwitterCatcher() {
        super("(@[A-Za-z0-9\\_\\-]+)");
    }
}
