package com.github.arena.challenges.weakmdparser;

public class ParseLine {

    private boolean activeList;
    private final ParserFactory parserFactory = new ParserFactory();

    public String parseLine(String line){
        return parseLineWithListTags(parseLineWithoutListTags(line));
    }

    public boolean isActiveList(){
        return activeList;
    }

    private String parseLineWithoutListTags(String line) {
        Parser headerParser = parserFactory.createParser("Header");
        String theLine = headerParser.parse(line);

        if (theLine == null) {
            Parser listItemParser = parserFactory.createParser("ListItem");
            theLine = listItemParser.parse(line);
        }

        if (theLine == null) {
            Parser paragraphParser = parserFactory.createParser("Paragraph");
            theLine = paragraphParser.parse(line);
        }

        return theLine;
    }

    private String parseLineWithListTags(String theLine) {
        if (isStartOfList(theLine)) {
            activeList = true;
            theLine = ("<ul>") + theLine;
        } else if (isEndOfList(theLine)) {
            activeList = false;
            theLine = ("</ul>") + theLine;
        }

        return theLine;
    }

    private boolean isStartOfList(String theLine) {
        return theLine.matches("(<li>).*") && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*") && !activeList;
    }

    private boolean isEndOfList(String theLine) {
        return !theLine.matches("(<li>).*") && activeList;
    }
}
