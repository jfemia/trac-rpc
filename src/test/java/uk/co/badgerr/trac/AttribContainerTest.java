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

import java.util.HashMap;
import static junit.framework.Assert.assertNotNull;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests the AttribContainer class
 * @author James Femia <badgerr@gmail.com>
 */
public class AttribContainerTest extends TestCase
{
  public AttribContainerTest( String testName )
  {
    super( testName );
  }

  public static Test suite()
  {
    return new TestSuite( AttribContainerTest.class );
  }
  
  public void testCanConstructAttribContainer()
  {
    AttribContainer a = new AttribContainer();
    assertNotNull(a);
  }
  
  public void testGetAttribsNeverNull()
  {
    AttribContainer a = new AttribContainer();
    assertNotNull(a.getAttribs());
    a.setAttribs(null);
    assertNotNull(a.getAttribs());
  }
  
  public void testGetAttribStringNeverNull()
  {
    AttribContainer a = new AttribContainer();
    HashMap<String, Object> map = new HashMap();
    map.put("Test", "foo");
    a.setAttribs(map);
    assertEquals(a.getAttribString("nonexistant"), "");
    assertEquals(a.getAttribString("Test"), "foo");
    assertTrue(map.equals(a.getAttribs()));
  }
}
