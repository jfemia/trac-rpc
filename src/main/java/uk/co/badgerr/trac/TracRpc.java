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
   * Creates a milestone
   * @param name The name of the milestone
   * @param due The due date of the milestone. Can be null, to specify no due date
   * @throws TracRpcException 
   */
  public void createMilestone(String name, Date due) throws TracRpcException
  {
    HashMap<String,Date> msDate = new HashMap();
    if(due != null)
      msDate.put("due", due);
        
    Object[] params = new Object[]{name, msDate};
    Integer result = (Integer)this.call("ticket.milestone.create", params);
    if(result > 0) {
      throw new TracRpcException("ticket.milestone.create returned error " 
                                  + result.toString());
    }
  }
}
