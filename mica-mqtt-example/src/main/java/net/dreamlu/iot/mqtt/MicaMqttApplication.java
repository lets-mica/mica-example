/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
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

package net.dreamlu.iot.mqtt;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.ByteBufferUtil;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动入口
 *
 * @author L.cm
 */
@Slf4j
@SpringBootApplication
public class MicaMqttApplication{

	public static void main(String[] args) {
		SpringApplication.run(MicaMqttApplication.class, args);
	}

	@Bean
	public IMqttMessageListener mqttMessageListener() {
		return (context, clientId, message) -> {
			System.out.println(ByteBufferUtil.toString(message.getPayload()));
		};
	}

}
