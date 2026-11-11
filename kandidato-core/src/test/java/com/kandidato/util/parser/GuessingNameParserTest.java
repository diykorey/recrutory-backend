package com.kandidato.util.parser;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andriy on 1/11/15.
 */
public class GuessingNameParserTest {


    private static final String FIRST_NAME = "Bill";
    private static final String LAST_NAME = "Gates";

    private static final String FIRST_NAME_CYRYLLIC = "Біл";
    private static final String LAST_NAME_CYRYLLIC = "Гейтс";

    private static final String ONE_WORD_NAME = "paulmccartney";

    private static final String DOT = ".";
    private static final String DASH = "-";
    private static final String UNDERSCORE = "_";
    private static final String SPACE = " ";

    private static final String EXTENSION = "pdf";

    @Test
    public void shouldTrimExtensions() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + LAST_NAME + SPACE + SPACE + DOT + EXTENSION);
        verify(userNameParser);
    }

    @Test
    public void shouldTrimSpaces() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + LAST_NAME + SPACE + SPACE);
        verify(userNameParser);
    }

    @Test
    public void shouldParseCamelCaseManyWords() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + LAST_NAME + LAST_NAME);
        NameParser.PersonName result = userNameParser.parseName();
        Assert.assertEquals(FIRST_NAME, result.getFirstName());
        Assert.assertEquals(LAST_NAME + LAST_NAME, result.getLastName());
    }

    @Test
    public void shouldNotParseOneWord() {
        GuessingNameParser userNameParser = new GuessingNameParser(ONE_WORD_NAME);
        NameParser.PersonName result = userNameParser.parseName();
        Assert.assertEquals(ONE_WORD_NAME, result.getFirstName());
        Assert.assertEquals(ONE_WORD_NAME, result.getLastName());
    }

    @Test
    public void shouldParseCyrillicCamelCase() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME_CYRYLLIC + LAST_NAME_CYRYLLIC);
        NameParser.PersonName result = userNameParser.parseName();
        Assert.assertEquals(FIRST_NAME_CYRYLLIC, result.getFirstName());
        Assert.assertEquals(LAST_NAME_CYRYLLIC, result.getLastName());
    }

    @Test
    public void shouldParseCamelCase() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + LAST_NAME);
        verify(userNameParser);
    }

    @Test
    public void shouldParseSpaceSeparatedName() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + SPACE + LAST_NAME);
        verify(userNameParser);
    }

    @Test
    public void shouldParseDotSeparatedName() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + DOT + LAST_NAME);
        verify(userNameParser);
    }

    @Test
    public void shouldParseDashSeparatedName() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + DASH + LAST_NAME);
        verify(userNameParser);
    }

    @Test
    public void shouldParseUnderscoreSeparatedName() {
        GuessingNameParser userNameParser = new GuessingNameParser(FIRST_NAME + UNDERSCORE + LAST_NAME);
        verify(userNameParser);
    }

    private void verify(GuessingNameParser userNameParser) {
        NameParser.PersonName result = userNameParser.parseName();
        Assert.assertEquals(FIRST_NAME, result.getFirstName());
        Assert.assertEquals(LAST_NAME, result.getLastName());
    }


}
