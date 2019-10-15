package com.triadsoft.catchers;

/**
 * Ésta clase captura el patron de título html para obtener el titulo de la página
 * a partir del texto html
 * @author triad (flores.leonardo@gmail.com)
 * @created 10/8/2019 3:20 PM
 */
public class TitleCatcher extends PatternCatcher {
    public static String TITLE_CONTENT = "title.*\\>(.+?)\\</title";
    public TitleCatcher() {
        super(TITLE_CONTENT);
    }
}
