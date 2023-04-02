package com.github.arena.challenges.weakmdparser;

public class MarkdownParser {

    private final ParseLine lineParser = new ParseLine();

    public String parse(String markdown) {
        if (markdown == null)
            throw new NullPointerException();

        StringBuilder result = new StringBuilder();
        String[] lines = markdown.split("\n");

        for (var line : lines)
            result.append(lineParser.parseLine(line));

        if (lineParser.isActiveList())
            result.append("</ul>");

        return result.toString();
    }
}
