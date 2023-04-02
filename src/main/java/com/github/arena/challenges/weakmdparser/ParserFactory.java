package com.github.arena.challenges.weakmdparser;

public class ParserFactory {

    public Parser createParser(String parserType) {
        if (parserType.equalsIgnoreCase("Header"))
            return new HeaderParser();
        else if (parserType.equalsIgnoreCase("ListItem"))
            return new ListItemParser();
        else if (parserType.equalsIgnoreCase("Paragraph"))
            return new ParagraphParser();
        else
            return null;
    }
}
