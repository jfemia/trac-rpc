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

/**
 * Contains attributes in a HashMap and provides accessors to it
 * and utility for reading strings (creating empty ones when needed)
 * @author James Femia <badgerr@gmail.com>
 */
public class AttribContainer {
  protected HashMap<String,Object> attribs;

  public AttribContainer()
  {
    attribs = new HashMap();
  }
  
  /**
   * Direct accessor to our attributes
   * @return 
   */
  public HashMap<String, Object> getAttribs() {
    return attribs;
  }

  /**
   * Overwrites all attributes with the specified hashmap
   * @param attribs 
   */
  public void setAttribs(HashMap<String, Object> attribs) {
    this.attribs = attribs;
  }
  
  /**
   * Get a named attribute string, or an empty string if attrib doesn't exist
   * @param name name of the attribute to get
   * @return a valid string, with attrib contents, or empty
   */
  public String getAttribString(String name)
  {
    String ret = (String)this.attribs.get(name);
    if(ret == null)
      ret = new String();
    return ret;
  }
}
