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

/**
 * Configures the RPC connection
 * @author James Femia <badgerr@gmail.com>
 */
public class TracRpcConfiguration {
  private String serverAddress;
  private String basicUserName;
  private String basicPassword;

  /**
   * Gets the configured server address string
   * @return The server address set by {@link setServerAddress(String) setServerAddress}
   */
  public String getServerAddress() {
    return serverAddress;
  }

  /**
   * Sets the address of the Trac RPC, in URL form.
   * For example, http://my.project/trac/rpc
   * @param serverAddress The address of the Trac RPC
   */
  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }

  /**
   * Gets the configured user name string
   * @return The user name set by {@link setBasicUserName(String) setBasicUserName}
   */
  public String getBasicUserName() {
    return basicUserName;
  }

  /**
   * Sets the user name for basic HTTP authentication.
   * @param basicUserName The username to authenticate
   */
  public void setBasicUserName(String basicUserName) {
    this.basicUserName = basicUserName;
  }

  /**
   * Gets the configured password string
   * @return The password set by {@link setBasicPassword(String) setBasicPassword}
   */
  public String getBasicPassword() {
    return basicPassword;
  }

  /**
   * Sets the password for basic HTTP authentication.
   * @param basicPassword The password to authenticate with
   */
  public void setBasicPassword(String basicPassword) {
    this.basicPassword = basicPassword;
  }
}
