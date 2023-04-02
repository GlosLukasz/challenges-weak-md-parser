package com.github.arena.challenges.weakmdparser;

public class HeaderParser extends Parser {

    @Override
    public String parse(String markdown) {
        int count = 0;

        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++)
            count++;

        if (count == 0)
            return null;

        return "<h" + (count) + ">" + markdown.substring(count + 1) + "</h" + (count) + ">";
    }
}
