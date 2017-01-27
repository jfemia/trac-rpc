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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class TracRpcTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TracRpcTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TracRpcTest.class );
    }

    public void testCanConstructTracRpcWithValidConfig()
    {
      TracRpcConfiguration config = new TracRpcConfiguration();
      config.setServerAddress("http://localhost/trac/rpc");
      try {
        TracRpc rpc = new TracRpc(config);
        assertNotNull(rpc);
      }
      catch(TracRpcException e) {
        fail(e.getMessage());
      }
    }
    
    public void testCannotConstructTracRpcWithInvalidConfig()
    {
      TracRpcConfiguration config = new TracRpcConfiguration();
      try {
        TracRpc rpc = new TracRpc(config);
        fail("Should not have reached this line without throwing");
      }
      catch(TracRpcException e) {
        assertNotNull(e);
      }
    }
}
