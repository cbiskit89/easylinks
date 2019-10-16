package me.corymiller.easylinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyLinksUtils {
    public EasyLinksUtils () {}

    public static String provideAbsoluteLink (String link) {
        Pattern absLinkRegex = Pattern.compile("^(http|https)://.*$");
        Matcher absLinkMatches = absLinkRegex.matcher(link);
        if (absLinkMatches.matches()) {
            return link;
        }
        else {
            return "http://" + link;
        }
    }
}
