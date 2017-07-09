package me.corymiller.golinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoLinkUtils {
    public GoLinkUtils () {}

    public static String provideAbsoluteLink (String link) {
        Pattern absLinkRegex = Pattern.compile("^([a-zA-Z])+://*$");
        Matcher absLinkMatches = absLinkRegex.matcher(link);
        if (absLinkMatches.matches()) {
            return link;
        }
        else {
            return "http://" + link;
        }
    }
}
