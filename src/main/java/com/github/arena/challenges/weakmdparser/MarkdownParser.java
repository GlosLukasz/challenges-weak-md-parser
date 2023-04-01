package com.github.arena.challenges.weakmdparser;

public class MarkdownParser {

    private String result = "";
    private boolean activeList = false;

    public String parse(String markdown) {
        String[] lines = markdown.split("\n");

        for (var line : lines) {
            String theLine = parseLine(line);
            parseLineWithListTags(theLine);
        }

        if (activeList)
            result = result + "</ul>";

        return result;
    }

    private String parseLine(String line) {
        String theLine = parseHeader(line);

        if (theLine == null)
            theLine = parseListItem(line);

        if (theLine == null)
            theLine = parseParagraph(line);

        return theLine;
    }

    private String parseLineWithListTags(String theLine) {
        if (isStartOfList(theLine)) {
            activeList = true;
            result = result + "<ul>";
        } else if (isEndOfList(theLine)) {
            activeList = false;
            result = result + "</ul>";
        }

        return result = result + theLine;
    }

    private boolean isStartOfList(String theLine) {
        return theLine.matches("(<li>).*") && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*") && !activeList;
    }

    private boolean isEndOfList(String theLine) {
        return !theLine.matches("(<li>).*") && activeList;
    }

    private String parseHeader(String markdown) {
        int count = 0;

        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++)
            count++;

        if (count == 0)
            return null;

        return "<h" + (count) + ">" + markdown.substring(count + 1) + "</h" + (count) + ">";
    }

    private String parseListItem(String markdown) {
        if (markdown.startsWith("*"))
            return "<li>" + parseSomeSymbols(markdown.substring(2)) + "</li>";

        return null;
    }

    private String parseParagraph(String markdown) {
        return "<p>" + parseSomeSymbols(markdown) + "</p>";
    }

    private String parseSomeSymbols(String markdown) {
        String workingOn = markdown.replaceAll("__(.+)__", "<strong>$1</strong>");

        return workingOn.replaceAll("_(.+)_", "<em>$1</em>");
    }
}
