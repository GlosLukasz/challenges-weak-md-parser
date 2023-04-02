package com.github.arena.challenges.weakmdparser;

public class ParagraphParser extends Parser {

    @Override
    public String parse(final String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }
}
