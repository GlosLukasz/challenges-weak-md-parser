package com.github.arena.challenges.weakmdparser;

public class ListItemParser extends Parser {

    @Override
    public String parse(final String markdown) {
        if (markdown.startsWith("*"))
            return "<li>" + parseSomeSymbols(markdown.substring(2)) + "</li>";

        return null;
    }
}
