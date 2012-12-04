package cz.fjfi.pvs.kosapi.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Snake66
 */
public class AtomTagParser {
    private String name = "";
    private String content = "";
    private List listOfTagAttributeNames = new ArrayList();
    private List listOfTagAttributeValues = new ArrayList();
    private String patternAttributeString = "(\\w+?)=\"(.*?)\"";

    public AtomTagParser(String tagName, String tagAttributes, String tagContent) {
        name = tagName;
        content = tagContent;
        this.parseAttributes(tagAttributes);
    }

    public String getTagName() {
        return name;
    }

    public String getTagContent() {
        return content;
    }

    public int getNumberOfAttributes() {
        return listOfTagAttributeNames.size();
    }

    public String getAttributeName(int number) {
        if (number >= 0 && getNumberOfAttributes() > number) {
            return (String) listOfTagAttributeNames.get(number);
        } else {
            return null;
        }
    }

    public List getAllAttributeNames() {
        if (getNumberOfAttributes() > 0) {
            return listOfTagAttributeNames;
        } else {
            return null;
        }
    }

    public String getAttributeValue(int number) {
        if (number >= 0 && getNumberOfAttributes() > number) {
            return (String) listOfTagAttributeValues.get(number);
        } else {
            return null;
        }
    }

    public List getAllAttributeValues() {
        if (getNumberOfAttributes() > 0) {
            return listOfTagAttributeValues;
        } else {
            return null;
        }
    }

    private void parseAttributes(String tagAttributes) {
        Pattern attributesValue = Pattern.compile(patternAttributeString);
        Matcher m = attributesValue.matcher(tagAttributes);
        while (m.find()) {
            // m.group(0) returns string with tags
            String tagAttributeName = m.group(1);
            String tagAttributeValue = m.group(2);
            listOfTagAttributeNames.add(tagAttributeName);
            listOfTagAttributeValues.add(tagAttributeValue);
        }
    }
}
