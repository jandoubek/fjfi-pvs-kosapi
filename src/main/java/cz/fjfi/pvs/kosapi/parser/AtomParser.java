package cz.fjfi.pvs.kosapi.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Snake66
 */
public class AtomParser {
    private String atom;
    private List listOfContents = new ArrayList();
    private List listOfContentAttributeNames = new ArrayList();
    private List listOfContentAttributeValues = new ArrayList();
    private String patternEntryString = "<atom:entry(.*?)>(.*?)</atom:entry>";
    private String patternContentString = "<atom:content(.*?)>(.*?)</atom:content>";
    private String patternAttributeString = "(\\w+?)=\"(.*?)\"";

    public AtomParser(String atom) {
        this.atom = atom;
        this.parse();
    }

    public int getNumberOfEntries() {
        return listOfContents.size();
    }

    public AtomContentParser getEntry(int number) {
        if (number >= 0 && getNumberOfEntries() > number) {
            return (AtomContentParser) listOfContents.get(number);
        } else {
            return null;
        }
    }

    public List getAllEntries() {
        if (getNumberOfEntries() > 0) {
            return listOfContents;
        } else {
            return null;
        }
    }

    public int getNumberOfAttributes() {
        return listOfContentAttributeNames.size();
    }

    public String getAttributeName(int number) {
        if (number >= 0 && getNumberOfAttributes() > number) {
            return (String) listOfContentAttributeNames.get(number);
        } else {
            return null;
        }
    }

    public List getAllAttributeNames() {
        if (getNumberOfAttributes() > 0) {
            return listOfContentAttributeNames;
        } else {
            return null;
        }
    }

    public String getAttributeValue(int number) {
        if (number >= 0 && getNumberOfAttributes() > number) {
            return (String) listOfContentAttributeValues.get(number);
        } else {
            return null;
        }
    }

    public List getAllAttributeValues() {
        if (getNumberOfAttributes() > 0) {
            return listOfContentAttributeValues;
        } else {
            return null;
        }
    }

    private void parse() {
        Pattern entryPattern = Pattern.compile(patternEntryString);
        Matcher m = entryPattern.matcher(atom);
        while (m.find()){
            String entryContent = m.group(2);
            Pattern contentPattern = Pattern.compile(patternContentString);
            Matcher m2 = contentPattern.matcher(entryContent);
            if (m2.find()) {
                // m2.group(0) returns string with tags
                String contentAttributes = m2.group(1);
                String contentContent = m2.group(2);
                parseAttributes(contentAttributes);
                AtomContentParser atomContentParser = new AtomContentParser(entryContent,contentContent);
                listOfContents.add(atomContentParser);
            }
        }
    }
    
    private void parseAttributes(String contentAttributes) {
        Pattern attributesValue = Pattern.compile(patternAttributeString);
        Matcher m = attributesValue.matcher(contentAttributes);
        while (m.find()) {
            // m.group(0) returns string with tags
            String contentAttributeName = m.group(1);
            String contentAttributeValue = m.group(2);
            listOfContentAttributeNames.add(contentAttributeName);
            listOfContentAttributeValues.add(contentAttributeValue);
        }
    }
}
