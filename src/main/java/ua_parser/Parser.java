/**
 * Copyright 2012 Twitter, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua_parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * Java implementation of <a href="https://github.com/ua-parser">UA Parser</a>
 *
 * @author Steve Jiang (@sjiang) &lt;gh at iamsteve com&gt;
 */
abstract public class Parser {

  private UserAgentParser uaParser;
  private OSParser osParser;
  private DeviceParser deviceParser;

  public Parser(InputStream regexYaml, boolean legacy) {
    initialize(regexYaml, legacy);
  }

  public Client parse(String agentString) {
    UserAgent ua = parseUserAgent(agentString);
    OS os = parseOS(agentString);
    Device device = deviceParser.parse(agentString);
    return new Client(ua, os, device);
  }

  public UserAgent parseUserAgent(String agentString) {
    return uaParser.parse(agentString);
  }

  public Device parseDevice(String agentString) {
    return deviceParser.parse(agentString);
  }

  public OS parseOS(String agentString) {
    return osParser.parse(agentString);
  }

  private void initialize(InputStream regexYaml, boolean legacy) {
    Yaml yaml = new Yaml(new SafeConstructor());
    @SuppressWarnings("unchecked")
    Map<String,List<Map<String,String>>> regexConfig = (Map<String,List<Map<String,String>>>) yaml.load(regexYaml);

    List<Map<String,String>> uaParserConfigs = regexConfig.get("user_agent_parsers");
    if (uaParserConfigs == null) {
      throw new IllegalArgumentException("user_agent_parsers is missing from yaml");
    }
    uaParser = UserAgentParser.fromList(uaParserConfigs, legacy);

    List<Map<String,String>> osParserConfigs = regexConfig.get("os_parsers");
    if (osParserConfigs == null) {
      throw new IllegalArgumentException("os_parsers is missing from yaml");
    }
    osParser = OSParser.fromList(osParserConfigs);

    List<Map<String,String>> deviceParserConfigs = regexConfig.get("device_parsers");
    if (deviceParserConfigs == null) {
      throw new IllegalArgumentException("device_parsers is missing from yaml");
    }
    deviceParser = DeviceParser.fromList(deviceParserConfigs);
  }
}
