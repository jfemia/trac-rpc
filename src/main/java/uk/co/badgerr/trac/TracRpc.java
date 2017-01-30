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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * Trac XML RPC wrapper class
 * @author James Femia <badgerr@gmail.com>
 */
public class TracRpc {
  private final XmlRpcClient rpcClient;
  
  /**
   * Construct a TracRpc object to operate on a Trac instance
   * @param config Connection configuration
   * @throws TracRpcException 
   */
  public TracRpc(TracRpcConfiguration config) throws TracRpcException
  {
    try {
      XmlRpcClientConfigImpl rpcConfig = new XmlRpcClientConfigImpl();
      rpcConfig.setServerURL(new URL(config.getServerAddress()));
      rpcConfig.setBasicUserName(config.getBasicUserName());
      rpcConfig.setBasicPassword(config.getBasicPassword());

      this.rpcClient = new XmlRpcClient();
      this.rpcClient.setConfig(rpcConfig);
    }
    catch(MalformedURLException e) {
      //rethrow as a TracRpcException
      throw new TracRpcException(e.getMessage());
    }
  }
  
  /**
   * Manually call a function in the Trac RPC
   * @param func Name of the function to call
   * @param params Parameters to pass to the function
   * @return The result of the function call
   * @throws TracRpcException 
   */
  public Object call(String func, Object[] params) throws TracRpcException
  {
    try {
      return rpcClient.execute(func, params);
    }
    catch(XmlRpcException e) {
      throw new TracRpcException(e.getMessage());
    }
  }
  
  /**
   * Gets ticket information
   * @param id the ticket to query
   * @return a {@link Ticket Ticket} object populated with info
   * @throws TracRpcException 
   */
  public Ticket getTicket(Integer id) throws TracRpcException
  {
    Object[] params = new Object[]{id};
    Object[] result = (Object[])this.call("ticket.get", params);
    
    if(result.length >= 4) {
      Ticket t = new Ticket();
      t.setId((Integer)result[0]);
      t.setAttribs((HashMap)result[3]);
      return t;
    }
    
    throw new TracRpcException("Unexpected response to ticket.get");
  }
  
  /**
   * Sends a populated Ticket instance to Trac to create the ticket
   * @param t ticket to create
   * @param notify whether to send notification emails
   * @throws TracRpcException 
   */
  public void createTicket(Ticket t, Boolean notify) throws TracRpcException
  {
    Object[] params = new Object[]{t.getSummary(), t.getDescription(), 
                                   t.getAttribs(), notify};
    Integer id = (Integer)this.call("ticket.create", params);
    t.setId(id);
  }
  
  /**
   * Creates a milestone
   * @param m populated milestone object
   * @throws TracRpcException 
   */
  public void createMilestone(Milestone m) throws TracRpcException
  {
    Object[] params = new Object[]{m.getName(), m.getAttribs()};
    Integer result = (Integer)this.call("ticket.milestone.create", params);
    if(result > 0) {
      throw new TracRpcException("ticket.milestone.create returned error " 
                                  + result.toString());
    }
  }
  
  /**
   * Gets a milestone by name
   * @param name name of the milestone to get
   * @return a Milestone object populated with milestone attributes
   * @throws TracRpcException 
   */
  public Milestone getMilestone(String name) throws TracRpcException
  {
    Object[] params = new Object[]{name};
    HashMap result = (HashMap)this.call("ticket.milestone.get", params);
    Milestone m = new Milestone();
    m.setAttribs(result);
    return m;
  }
  
  /**
   * Updates attributes on an existing milestone
   * @param m milestone with updated values
   * @throws TracRpcException 
   */
  public void updateMilestone(Milestone m) throws TracRpcException
  {
    Object[] params = new Object[]{m.getName(), m.getAttribs()};
    Integer result = (Integer)this.call("ticket.milestone.update", params);
    if(result > 0) {
      throw new TracRpcException("ticket.milestone.update returned error " 
                                  + result.toString());
    }
  }
  
  /**
   * Sets the contents of a wiki page, creating it if necessary, or overwriting
   * existing contents.
   * @param name the name of the page (may contain slashes for nested)
   * @param content the new content of the page
   * @throws TracRpcException 
   */
  public void setWikiPage(String name, String content) throws TracRpcException
  {
    Object[] params = new Object[]{name,content,new HashMap()};
    Boolean result = (Boolean)this.call("wiki.putPage", params);
    if(!result) {
      throw new TracRpcException("wiki.putPage returned false");
    }
  }
  
  /**
   * Gets the contents of a wiki page
   * @param name name of the page to get
   * @return contents
   * @throws TracRpcException
   */
  public String getWikiPage(String name) throws TracRpcException
  {
    Object[] params = new Object[]{name};
    return (String)this.call("wiki.getPage", params);
  }
  
  /**
   * Appends text to an existing wiki page, or create the page if doesn't exist
   * @param name name of the page
   * @param extraContent content to add to the page
   * @throws TracRpcException 
   */
  public void appendWikiPage(String name, String extraContent) throws TracRpcException
  {
    String content = this.getWikiPage(name);
    content += extraContent;
    this.setWikiPage(name, content);
  }
}
