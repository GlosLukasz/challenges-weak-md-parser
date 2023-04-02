package com.github.arena.challenges.weakmdparser;

public abstract class Parser {

    public abstract String parse(String markdown);

    public String parseSomeSymbols(String markdown) {
        String workingOn = markdown.replaceAll("__(.+)__", "<strong>$1</strong>");

        return workingOn.replaceAll("_(.+)_", "<em>$1</em>");
    }
}
