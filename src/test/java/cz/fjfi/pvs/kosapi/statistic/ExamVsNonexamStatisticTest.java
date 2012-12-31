package cz.fjfi.pvs.kosapi.statistic;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cz.fjfi.pvs.kosapi.statistic.ExamVsNonexamStatistic;
import cz.fjfi.pvs.kosapi.web.KosAtomReader;

public class ExamVsNonexamStatisticTest{
	
	private final String credit = "CREDIT";
	private final String creditExam = "CREDIT_EXAM";
	
	// TODO: should use Map<String, Double> instead of List<Double>
	private final List<Double> expectedResult = new ArrayList<Double>(Arrays.asList(2.0, 8.0));
	private final double equalityPrecision = 1e-5;
	
	@Test
	public void verifyExamRatio()
	{
		// TODO: read courses.xml (in resources) to mockingResult
		final String mockingResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
+ "<atom:feed xmlns=\"http://kosapi.feld.cvut.cz/schema/3\" xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:osearch=\"http://a9.com/-/spec/opensearch/1.1/\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xml:base=\"https://kosapi.fit.cvut.cz/api/3b/\" xml:lang=\"en\"><atom:id>https://kosapi.fit.cvut.cz/api/3b/courses</atom:id><atom:updated>2012-12-20T23:03:22.07</atom:updated><atom:link rel=\"next\" href=\"courses?offset=10&amp;limit=10\"/><atom:entry><atom:title>Foreign Language - Spanish 5</atom:title><atom:id>urn:cvut:kos:course:23368305</atom:id><atom:updated>2004-11-28T14:13:42.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JS5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JS5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - španělština 5</name><name xml:lang=\"en\">Foreign Language - Spanish 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Spanish 8</atom:title><atom:id>urn:cvut:kos:course:23368605</atom:id><atom:updated>2004-11-28T14:14:28.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JS8/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JS8</code><completion>CREDIT_EXAM</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - španělština 8</name><name xml:lang=\"en\">Foreign Language - Spanish 8</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 5</atom:title><atom:id>urn:cvut:kos:course:23368705</atom:id><atom:updated>2004-11-28T14:16:03.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - švédština 5</name><name xml:lang=\"en\">Foreign Language - Swedish 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 6</atom:title><atom:id>urn:cvut:kos:course:23368805</atom:id><atom:updated>2004-11-28T14:16:17.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV6/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV6</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - švédština 6</name><name xml:lang=\"en\">Foreign Language - Swedish 6</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 7</atom:title><atom:id>urn:cvut:kos:course:23368905</atom:id><atom:updated>2004-11-28T14:16:32.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV7/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV7</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - švédština 7</name><name xml:lang=\"en\">Foreign Language - Swedish 7</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:title>Foreign Language - Swedish 8</atom:title><atom:id>urn:cvut:kos:course:23369005</atom:id><atom:updated>2004-11-28T14:16:48.0</atom:updated><atom:author><atom:name>f6carsky</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JV8/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JV8</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - švédština 8</name><name xml:lang=\"en\">Foreign Language - Swedish 8</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369205</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX2/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX2</code><completion>CREDIT</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplní 2</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369305</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX3/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX3</code><completion>CREDIT</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplní 3</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369405</atom:id><atom:updated>2001-04-23T15:16:30.0</atom:updated><atom:author><atom:name>f6spotak</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX4/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX4</code><completion>CREDIT_EXAM</completion><credits>3</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">Jazyk - doplní 4</name><range>0+4</range><state>APPROVED</state></atom:content></atom:entry><atom:entry><atom:id>urn:cvut:kos:course:23369505</atom:id><atom:updated>2001-04-08T18:55:20.0</atom:updated><atom:author><atom:name>f6</atom:name></atom:author><atom:link rel=\"self\" href=\"courses/15JX5/\"/><atom:content atom:type=\"xml\" xsi:type=\"course\"><approvalDate>1998-07-13</approvalDate><code>15JX5</code><completion>CREDIT</completion><credits>2</credits><department xlink:href=\"divisions/16115/\">Department of Languages and Humanities</department><name xml:lang=\"cs\">JAZYK - doplní 5</name><range>0+2</range><state>APPROVED</state></atom:content></atom:entry><osearch:itemsPerPage>10</osearch:itemsPerPage><osearch:startIndex>0</osearch:startIndex></atom:feed>";
		KosAtomReader kosReader = mock(KosAtomReader.class);
		when(kosReader.getKosResponse()).thenReturn(mockingResult);
		String kosResponse = kosReader.getKosResponse();
		ExamVsNonexamStatistic examVsNonexams = new ExamVsNonexamStatistic(kosResponse, "completion");
		Map<String, Double> result = examVsNonexams.getStatisticValues();
		double creditNumber = result.get(credit);
		double examNumber = result.get(creditExam);
		Assert.assertEquals(expectedResult.get(0), examNumber, equalityPrecision);
		Assert.assertEquals(expectedResult.get(1), creditNumber, equalityPrecision);
	}
}
