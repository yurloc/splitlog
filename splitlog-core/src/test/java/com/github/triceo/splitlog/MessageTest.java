package com.github.triceo.splitlog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMessage() {
        final Collection<String> raw = Collections.emptyList();
        new Message(raw);
    }

    @Test
    public void testEqualsMessage() {
        final String[] lines = new String[] { "Test", "Test2", "Test3" };
        final Collection<String> raw1 = Arrays.asList(lines);
        final Collection<String> raw2 = Arrays.asList(lines);
        final Collection<String> raw3 = Arrays.asList(lines).subList(1, 3);
        Assert.assertEquals(new Message(raw1), new Message(raw1));
        Assert.assertEquals(new Message(raw1), new Message(raw2));
        Assert.assertNotEquals(new Message(raw1), new Message(raw3));
    }

    @Test
    public void testEqualsTag() {
        final String line = "Test";
        final Message msg1 = new Message(line);
        final Message msg2 = new Message(line);
        final Message msg3 = new Message(line + "2");
        Assert.assertEquals(msg1, msg1);
        Assert.assertEquals(msg1, msg2);
        Assert.assertNotEquals(msg1, msg3);
    }

    @Test
    public void testGetSeverity() {
        final String[] lines = new String[] { "Test", "Test2", "Test3" };
        final Collection<String> raw = Arrays.asList(lines);
        final Message msg = new Message(raw, Calendar.getInstance().getTime(), MessageSeverity.INFO);
        Assert.assertEquals(MessageSeverity.INFO, msg.getSeverity());
    }

    @Test
    public void testGetType() {
        final String[] lines = new String[] { "Test", "Test2", "Test3" };
        final Collection<String> raw = Arrays.asList(lines);
        final Message msg = new Message(raw, Calendar.getInstance().getTime(), MessageType.LOG);
        Assert.assertEquals(MessageType.LOG, msg.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMessage() {
        final Collection<String> raw = null;
        new Message(raw);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullTag() {
        final String raw = null;
        new Message(raw);
    }

    @Test
    public void testTag() {
        final String line = "Test";
        final Message msg = new Message(line);
        Assert.assertEquals(MessageSeverity.UNKNOWN, msg.getSeverity());
        Assert.assertEquals(MessageType.TAG, msg.getType());
        Assert.assertEquals(1, msg.getLines().size());
        Assert.assertEquals(line, msg.getLines().get(0));
    }

}
