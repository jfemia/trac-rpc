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
import java.util.HashMap;

/**
 * Represents a Trac Ticket
 * @author James Femia <badgerr@gmail.com>
 */
public class Ticket {
  private Integer id = null;
  private HashMap<String, Object> attribs;
  
  /**
   * Construct an empty ticket
   */
  public Ticket()
  {
    this.attribs = new HashMap();
  }

  /**
   * Gets the ticket number
   * @return the ticked id number. may be null if this is a new ticket.
   */
  public Integer getId() 
  {
    return id;
  }

  /**
   * For setting ID. Ignored when creating a ticket.
   * @param id 
   */
  public void setId(Integer id) 
  {
    this.id = id;
  }
  
  /**
   * Gets the creation date of the ticket
   * @return 
   */
  public Date getCreated()
  {
    return (Date)this.attribs.get("time");
  }
  
  /**
   * Sets the creation date of the ticket. Only functions when RPC user
   * has the TICKET_ADMIN permission in Trac.
   * @param dt 
   */
  public void setCreated(Date dt)
  {
    this.attribs.put("time", dt);
  }
  
  /**
   * Gets the modified date of the ticket
   * @return 
   */
  public Date getModified()
  {
    return (Date)this.attribs.get("changetime");
  }

  /**
   * Get the entire collection of ticket attributes,
   * useful when there are custom fields.
   * @return 
   */
  public HashMap<String, Object> getAttribs() 
  {
    return this.attribs;
  }

  /**
   * Set all of the ticket attributes. This will completely replace
   * existing attributes.
   * @param attribs 
   */
  public void setAttribs(HashMap<String, Object> attribs) 
  {
    if(attribs != null)
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
  
  /**
   * Gets the ticket summary (title)
   * @return summary
   */
  public String getSummary()
  {
    return this.getAttribString("summary");
  }
  
  /**
   * Sets the ticket summary (title)
   * @param summary The text to put in the summary
   */
  public void setSummary(String summary)
  {
    this.attribs.put("summary", summary);
  }
  
  /**
   * Gets the ticket description text (may include wiki formatting and/or html)
   * @return ticket description
   */
  public String getDescription()
  {
    return this.getAttribString("description");
  }
  
  /**
   * Sets the ticket description text
   * @param desc description text
   */
  public void setDescription(String desc)
  {
    this.attribs.put("description", desc);
  }
  
  /**
   * Gets the users CC'ed on this ticket (as usernames)
   * @return cc
   */
  public String getCC()
  {
    return this.getAttribString("cc");
  }
  
  /**
   * Sets usernames to CC on this ticket
   * @param cc users
   */
  public void setCC(String cc)
  {
    this.attribs.put("cc", cc);
  }
  
  /**
   * Gets the owner of this ticket (username)
   * @return user name or empty string if nobody owns it
   */
  public String getOwner()
  {
    return this.getAttribString("owner");
  }
  
  /**
   * Sets the owner of this ticket (username)
   * @param owner user to give the ticket to
   */
  public void setOwner(String owner)
  {
    this.attribs.put("owner", owner);
  }
  
  /**
   * Gets the creator of this ticket (username)
   * @return reporting user
   */
  public String getReporter()
  {
    return this.getAttribString("reporter");
  }
  
  /**
   * Sets the creator of this ticket (username)
   * @param reporter user name of reporter
   */
  public void setReporter(String reporter)
  {
    this.attribs.put("reporter", reporter);
  }
  
  /**
   * Gets the ticket priority
   * @return priority
   */
  public String getPriority()
  {
    return this.getAttribString("priority");
  }
  
  /**
   * Sets the ticket priority
   * @param priority 
   */
  public void setPriority(String priority)
  {
    this.attribs.put("priority", priority);
  }
  
  /**
   * Gets the ticket type
   * @return type
   */
  public String getType()
  {
    return this.getAttribString("type");
  }
  
  /**
   * Sets the ticket type
   * @param type 
   */
  public void setType(String type)
  {
    this.attribs.put("type", type);
  }
  
  /**
   * Gets the ticket severity
   * @return severity
   */
  public String getSeverity()
  {
    return this.getAttribString("severity");
  }
  
  /**
   * Sets the ticket severity
   * @param severity 
   */
  public void setSeverity(String severity)
  {
    this.attribs.put("severity", severity);
  }
  
  /**
   * Gets the ticket version
   * @return version
   */
  public String getVersion()
  {
    return this.getAttribString("version");
  }
  
  /**
   * Sets the ticket version
   * @param version 
   */
  public void setVersion(String version)
  {
    this.attribs.put("version", version);
  }
  
  /**
   * Gets the ticket component
   * @return component
   */
  public String getComponent()
  {
    return this.getAttribString("component");
  }
  
  /**
   * Sets the ticket component
   * @param component 
   */
  public void setComponent(String component)
  {
    this.attribs.put("component", component);
  }
  
  /**
   * Gets the ticket milestone
   * @return milestone
   */
  public String getMilestone()
  {
    return this.getAttribString("milestone");
  }
  
  /**
   * Sets the ticket milestone
   * @param milestone 
   */
  public void setMilestone(String milestone)
  {
    this.attribs.put("milestone", milestone);
  }
}
