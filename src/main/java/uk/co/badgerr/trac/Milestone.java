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

/**
 * Represents a Trac Milestone
 * @author James Femia <badgerr@gmail.com>
 */
public final class Milestone extends BasicStruct {
  /**
   * Create an empty milestone
   */
  public Milestone()
  {
  }
  
  /**
   * Create a named milestone with a due date
   * @param name name of the milestone
   * @param due due date
   */
  public Milestone(String name, Date due)
  {
    this.setName(name);
    this.setDue(due);
  }

  /**
   * Gets the due date, or null if there isn't one
   * @return due date or null
   */
  public Date getDue() {
    return (Date)this.attribs.get("due");
  }

  /**
   * Sets the due date of this milestone
   * @param due due date
   */
  public void setDue(Date due) {
    this.attribs.put("due", due);
  }

  /**
   * Gets the description text for this milestone
   * @return description
   */
  public String getDescription() {
    return this.getAttribString("description");
  }

  /**
   * Sets the description text for this milestone
   * @param description new description
   */
  public void setDescription(String description) {
    this.attribs.put("description", description);
  }

  /**
   * Gets whether this milestone is flagged as completed (i.e. closed)
   * @return completion status
   */
  public Boolean getCompleted() {
    Boolean b = (Boolean)this.attribs.get("completed");
    if(b == null || !b)
      return false;
    return true;
  }

  /**
   * Sets whether this milestone is completed
   * @param completed 
   */
  public void setCompleted(Boolean completed) {
    this.attribs.put("completed", completed);
  }
}
