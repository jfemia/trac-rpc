/*
 * The MIT License
 *
 * Copyright 2017 James Femia <badgerr@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.co.badgerr.trac;

import java.util.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests the BasicStruct class
 * @author James Femia <badgerr@gmail.com>
 */
public class MilestoneTest extends TestCase
{
  public MilestoneTest( String testName )
  {
    super( testName );
  }

  public static Test suite()
  {
    return new TestSuite( MilestoneTest.class );
  }
  
  public void testCanConstructMilestone()
  {
    Milestone m = new Milestone();
    assertNotNull(m);
    
    Date now = new Date();
    m = new Milestone("Test", now);
    assertNotNull(m);
    assertEquals(m.getName(), "Test");
    assertEquals(m.getDue(), now);
  }
  
  public void testMilestoneDue()
  {
    Milestone m = new Milestone();
    assertNull(m.getDue());
    m.setDue(new Date());
    assertNotNull(m.getDue());
    m.setDue(null);
    assertNull(m.getDue());
  }
  
  public void testMilestoneDesc()
  {
    Milestone m = new Milestone();
    assertEquals(m.getDescription(), "");
    m.setDescription("foo");
    assertEquals(m.getDescription(), "foo");
    m.setDescription(null);
    assertEquals(m.getDescription(), "");
  }
  
  public void testMilestoneComplete()
  {
    Milestone m = new Milestone();
    assertFalse(m.getCompleted());
    m.setCompleted(true);
    assertTrue(m.getCompleted());
  }
}
