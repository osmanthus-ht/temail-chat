/*
 * MIT License
 *
 * Copyright (c) 2019 Syswin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.syswin.temail.usermail.rocketmq;

import com.syswin.library.messaging.MqProducer;
import com.syswin.temail.data.consistency.application.TemailMqSender;
import com.syswin.temail.usermail.core.IMqAdapter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqAutoConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(RocketMqAutoConfiguration.class);

  @Bean(initMethod = "init", destroyMethod = "destroy")
  @ConditionalOnProperty(name = "spring.rocketmq.sender", havingValue = "ROCKETMQ")
  public IMqAdapter imq(RocketMqProperties rocketMqProperties) {
    LOGGER.info("MQAdapter [rocketmq] started");
    return new RocketMqAdapter(rocketMqProperties.getProducerGroup(), rocketMqProperties.getHost());
  }

  @Bean
  @ConditionalOnProperty(name = "spring.rocketmq.sender", matchIfMissing = true, havingValue = "DB")
  public IMqAdapter dbImq(TemailMqSender temailMqSender) {
    LOGGER.info("MQAdapter [DB] started");
    return new DbAdapter(temailMqSender);
  }

  @Bean
  @ConditionalOnProperty(name = "spring.rocketmq.sender", havingValue = "libraryMessage")
  public IMqAdapter libraryMessagingMqAdapter(Map<String, MqProducer> mqProducers,
      RocketMqProperties rocketMqProperties) {
    LOGGER.info("MQAdapter [libraryMessage] started");
    return new LibraryMessagingMqAdapter(mqProducers, rocketMqProperties);
  }

}
