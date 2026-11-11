package com.kandidato.util.parser;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User name parser, which makes its best to guess the name from an input string.
 * <p/>
 * Created by andriy on 1/11/15.
 */
public class GuessingNameParser implements NameParser {

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\s|\\.|\\-|\\_|(?<=[a-z])(?=[A-Z])", Pattern.UNICODE_CHARACTER_CLASS);
    public static final int ASSUMED_MAXIMUM_EXTENSION_LENGTH = 5;
    public static final char EMPTY_SEPARATOR = 0;

    private final Matcher matcher;

    private final String input;

    public GuessingNameParser(final String input) {
        String trimmedInput = trimExtensions(input);
        this.matcher = SEPARATOR_PATTERN.matcher(trimmedInput);
        this.input = trimmedInput;
    }

    @Override
    public PersonName parseName() {
        if (matcher.find()) {
            return getUserNameByPattern(input);
        } else {
            String[] camelCaseSplit = StringUtils.splitByCharacterTypeCamelCase(input);
            if (camelCaseSplit.length > 1) {
                return parseFromArray(camelCaseSplit);
            }
        }
        return new PersonName(input, input);
    }

    private PersonName parseFromArray(String[] camelCaseSplit) {
        String firstName = camelCaseSplit[0];
        String secondName = StringUtils.join(camelCaseSplit, EMPTY_SEPARATOR, 1, camelCaseSplit.length);
        return new PersonName(firstName, secondName);
    }

    private PersonName getUserNameByPattern(String input) {
        String assumedFirstName = input.substring(0, matcher.start());
        String assumedLastName = input.substring(matcher.end());
        return new PersonName(assumedFirstName, assumedLastName);
    }

    private String trimExtensions(String input) {
        int extensionStartIndex = input.lastIndexOf('.');
        if (extensionStartIndex > -1 && isReallyAnExtension(input, extensionStartIndex)) {
            input = input.substring(0, extensionStartIndex);
        }
        return input.trim();
    }

    private boolean isReallyAnExtension(String input, int extensionStartIndex) {
        return input.length() - extensionStartIndex < ASSUMED_MAXIMUM_EXTENSION_LENGTH;
    }


}