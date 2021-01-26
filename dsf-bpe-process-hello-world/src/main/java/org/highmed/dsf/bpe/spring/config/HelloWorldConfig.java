package org.highmed.dsf.bpe.spring.config;

import org.highmed.dsf.bpe.service.HelloWorld;
import org.highmed.dsf.fhir.client.FhirWebserviceClientProvider;
import org.highmed.dsf.fhir.task.TaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig
{
	@Autowired
	private FhirWebserviceClientProvider clientProvider;

	@Autowired
	private TaskHelper taskHelper;

	@Bean
	public HelloWorld helloWorld()
	{
		return new HelloWorld(clientProvider, taskHelper);
	}
}
