package cz.fjfi.pvs.kosapi.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Snake66
 */
public class AtomContentParser {
    private String entry;
    private String content;
    private String title = "";
    private String id = "";
    private String updated = "";
    private String author = "";
    private List<AtomTagParser> listOfTags = new ArrayList<AtomTagParser>();
    private String patternTitleString = "<atom:title>(.*?)</atom:title>";
    private String patternIdString = "<atom:id>(.*?)</atom:id>";
    private String patternUpdatedString = "<atom:updated>(.*?)</atom:updated>";
    private String patternAuthorString = "<atom:author><atom:name>(.*?)</atom:name></atom:author>";
    private String patternTagString = "<(\\S+?)(.*?)>(.*?)</\\1>";

    public AtomContentParser(String entry, String content) {
        this.entry = entry;
        this.content = content;
        this.setTitle();
        this.setId();
        this.setUpdated();
        this.setAuthor();
        this.parse();
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getUpdated() {
        return updated;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfTags() {
        return listOfTags.size();
    }

    public AtomTagParser getTag(int number) {
        if (number >= 0 && getNumberOfTags() > number) {
            return (AtomTagParser) listOfTags.get(number);
        } else {
            return null;
        }
    }
    
    public AtomTagParser getTagByName(String searchedName)
    {
		Iterator<AtomTagParser> tagsIterator = listOfTags.iterator();
		while(tagsIterator.hasNext())
		{
			AtomTagParser tag = tagsIterator.next();
			if(searchedName.equals(tag.getTagName()))
			{
				return tag;
			}
		}
		return null;
    }

    public List<AtomTagParser> getAllTags() {
        if (getNumberOfTags() > 0) {
            return listOfTags;
        } else {
            return null;
        }
    }

    private void setTitle() {
        Pattern attributesValue = Pattern.compile(patternTitleString);
        Matcher m = attributesValue.matcher(entry);
        if (m.find()) {
            // m.group(0) returns content with tags
            String atomTitleName = m.group(1);
            title = atomTitleName;
        }
    }

    private void setId() {
        Pattern attributesValue = Pattern.compile(patternIdString);
        Matcher m = attributesValue.matcher(entry);
        if (m.find()) {
            // m.group(0) returns content with tags
            String atomIdName = m.group(1);
            id = atomIdName;
        }
    }

    private void setUpdated() {
        Pattern attributesValue = Pattern.compile(patternUpdatedString);
        Matcher m = attributesValue.matcher(entry);
        if (m.find()) {
            // m.group(0) returns content with tags
            String atomUpdatedName = m.group(1);
            updated = atomUpdatedName;
        }
    }

    private void setAuthor() {
        Pattern attributesValue = Pattern.compile(patternAuthorString);
        Matcher m = attributesValue.matcher(entry);
        if (m.find()) {
            // m.group(0) returns content with tags
            String atomAuthorName = m.group(1);
            author = atomAuthorName;
        }
    }

    private void parse() {
        Pattern tagPattern = Pattern.compile(patternTagString);
        Matcher m = tagPattern.matcher(content);
        while (m.find()) {
            // m.group(0) returns content with tags
            String tagName = m.group(1);
            String tagAttributes = m.group(2);
            String tagContent = m.group(3);
            AtomTagParser atomTagParser = new AtomTagParser(tagName,tagAttributes,tagContent);
            listOfTags.add(atomTagParser);
        }
    }
}
