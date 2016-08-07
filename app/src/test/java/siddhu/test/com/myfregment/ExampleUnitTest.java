package siddhu.test.com.myfregment;

import org.junit.Test;

import siddhu.test.com.myfregment.Util.DateTimeUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
/*
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
*/

    @Test
    public void formatNewsApiDate_isCorrect() throws Exception {
        assertEquals(DateTimeUtils.correctOutputDate1, DateTimeUtils.formatNewsApiDate(DateTimeUtils.correctInputDate1));
    }
}