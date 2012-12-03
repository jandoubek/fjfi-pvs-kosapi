/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.fjfi.pvs.kosapi.parser;

import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Snake66
 */
public class AtomParserTest extends TestCase {
    private String atomFeed = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><atom:feed xmlns=\"http://kosapi.feld.cvut.cz/schema/3\" xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:osearch=\"http://a9.com/-/spec/opensearch/1.1/\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xml:base=\"https://kosapi.fit.cvut.cz/api/3b/\" xml:lang=\"en\"><atom:id>https://kosapi.fit.cvut.cz/api/3b/courses</atom:id><atom:updated>2012-12-02T14:32:19.343</atom:updated><atom:link rel=\"next\" href=\"courses?offset=10&amp;limit=10\"/><atom:entry><atom:title>Foreign Language - Spanish 5</atom:title><atom:id>urn:cvut:kos:course:23368305</atom:id><atom:updated>2004-11-28T14:13:42.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JS5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JS5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇpanÄ›lĹˇtina 5</name><name xml:lang=\"en\">Foreign Language - Spanish 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Spanish 8</atom:title><atom:id>urn:cvut:kos:course:23368605</atom:id><atom:updated>2004-11-28T14:14:28.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JS8/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JS8</code><completion>CREDIT_EXAM</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇpanÄ›lĹˇtina 8</name><name xml:lang=\"en\">Foreign Language - Spanish 8</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 5</atom:title><atom:id>urn:cvut:kos:course:23368705</atom:id><atom:updated>2004-11-28T14:16:03.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇvĂ©dĹˇtina 5</name><name xml:lang=\"en\">Foreign Language - Swedish 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 6</atom:title><atom:id>urn:cvut:kos:course:23368805</atom:id><atom:updated>2004-11-28T14:16:17.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV6/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV6</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇvĂ©dĹˇtina 6</name><name xml:lang=\"en\">Foreign Language - Swedish 6</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 7</atom:title><atom:id>urn:cvut:kos:course:23368905</atom:id><atom:updated>2004-11-28T14:16:32.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV7/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV7</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇvĂ©dĹˇtina 7</name><name xml:lang=\"en\">Foreign Language - Swedish 7</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 8</atom:title><atom:id>urn:cvut:kos:course:23369005</atom:id><atom:updated>2004-11-28T14:16:48.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV8/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV8</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - ĹˇvĂ©dĹˇtina 8</name><name xml:lang=\"en\">Foreign Language - Swedish 8</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369205</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX2/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX2</code><completion>CREDIT</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplnĂ­ 2</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369305</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX3/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX3</code><completion>CREDIT</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplnĂ­ 3</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369405</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX4/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX4</code><completion>CREDIT_EXAM</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplnĂ­ 4</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369505</atom:id><atom:updated>2001-04-08T18:55:20.0</atom:updated><atom:author><atom:name>f6</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">JAZYK - doplnĂ­ 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><osearch:itemsPerPage>10</osearch:itemsPerPage><osearch:startIndex>0</osearch:startIndex></atom:feed>";
    private AtomParser atomParser = new AtomParser(atomFeed);
    private String[] entryTitles = {"Foreign Language - Spanish 5",
                                    "Foreign Language - Spanish 8",
                                    "Foreign Language - Swedish 5",
                                    "Foreign Language - Swedish 6",
                                    "Foreign Language - Swedish 7",
                                    "Foreign Language - Swedish 8",
                                    "",
                                    "",
                                    "",
                                    ""};
    private String[] entryIDs = {"urn:cvut:kos:course:23368305",
                                    "urn:cvut:kos:course:23368605",
                                    "urn:cvut:kos:course:23368705",
                                    "urn:cvut:kos:course:23368805",
                                    "urn:cvut:kos:course:23368905",
                                    "urn:cvut:kos:course:23369005",
                                    "urn:cvut:kos:course:23369205",
                                    "urn:cvut:kos:course:23369305",
                                    "urn:cvut:kos:course:23369405",
                                    "urn:cvut:kos:course:23369505"};
    private String[] entryUpdateds = {"2004-11-28T14:13:42.0",
                                    "2004-11-28T14:14:28.0",
                                    "2004-11-28T14:16:03.0",
                                    "2004-11-28T14:16:17.0",
                                    "2004-11-28T14:16:32.0",
                                    "2004-11-28T14:16:48.0",
                                    "2001-04-23T15:16:30.0",
                                    "2001-04-23T15:16:30.0",
                                    "2001-04-23T15:16:30.0",
                                    "2001-04-08T18:55:20.0"};
    private String[] entryAuthors = {"f6carsky",
                                    "f6carsky",
                                    "f6carsky",
                                    "f6carsky",
                                    "f6carsky",
                                    "f6carsky",
                                    "f6spotak",
                                    "f6spotak",
                                    "f6spotak",
                                    "f6"};
    private String[] contentAttributeNames = {"type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type",
                                                "type","type"};
    private String[] contentAttributeValues = {"xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course",
                                                "xml","course"};
    
    public AtomParserTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getNumberOfEntries method, of class AtomParser.
     */
    public void testGetNumberOfEntries() {
        int result = atomParser.getNumberOfEntries();

	assertEquals(result,10);
    }

    /**
     * Test of getEntry method, of class AtomParser.
     */
    public void testGetEntry() {
        int n = atomParser.getNumberOfEntries();
        for (int i = 0; i < n; i++) {
            AtomContentParser entry = atomParser.getEntry(i);
            assertEquals(entryTitles[i],entry.getTitle());
            assertEquals(entryIDs[i],entry.getId());
            assertEquals(entryUpdateds[i],entry.getUpdated());
            assertEquals(entryAuthors[i],entry.getAuthor());
        }
    }

    /**
     * Test of getAllEntries method, of class AtomParser.
     */
    public void testGetAllEntries() {
        List listOfAllEntries = atomParser.getAllEntries();
        int n = listOfAllEntries.size();
        for (int i = 0; i < n; i++) {
            AtomContentParser entry = (AtomContentParser) listOfAllEntries.get(i);
            assertEquals(entryTitles[i],entry.getTitle());
            assertEquals(entryIDs[i],entry.getId());
            assertEquals(entryUpdateds[i],entry.getUpdated());
            assertEquals(entryAuthors[i],entry.getAuthor());
        }
    }

    /**
     * Test of getNumberOfAttributes method, of class AtomParser.
     */
    public void testGetNumberOfAttributes() {
        int result = atomParser.getNumberOfAttributes();

	assertEquals(result,20);
    }

    /**
     * Test of getAttributeName method, of class AtomParser.
     */
    public void testGetAttributeName() {
        int n = atomParser.getNumberOfAttributes();
        for (int i = 0; i < n; i++) {
            String attributeName = atomParser.getAttributeName(i);
            assertEquals(contentAttributeNames[i],attributeName);
        }
    }

    /**
     * Test of getAllAttributeNames method, of class AtomParser.
     */
    public void testGetAllAttributeNames() {
        List listOfAllAttributeNames = atomParser.getAllAttributeNames();
        int n = listOfAllAttributeNames.size();
        for (int i = 0; i < n; i++) {
            String attributeName = (String) listOfAllAttributeNames.get(i);
            assertEquals(contentAttributeNames[i],attributeName);
        }
    }

    /**
     * Test of getAttributeValue method, of class AtomParser.
     */
    public void testGetAttributeValue() {
        int n = atomParser.getNumberOfAttributes();
        for (int i = 0; i < n; i++) {
            String attributeValue = atomParser.getAttributeValue(i);
            assertEquals(contentAttributeValues[i],attributeValue);
        }
    }

    /**
     * Test of getAllAttributeValues method, of class AtomParser.
     */
    public void testGetAllAttributeValues() {
        List listOfAllAttributeValues = atomParser.getAllAttributeValues();
        int n = listOfAllAttributeValues.size();
        for (int i = 0; i < n; i++) {
            String attributeValue = (String) listOfAllAttributeValues.get(i);
            assertEquals(contentAttributeValues[i],attributeValue);
        }
    }

}
