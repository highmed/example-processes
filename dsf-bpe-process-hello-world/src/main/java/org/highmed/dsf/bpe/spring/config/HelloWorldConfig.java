package org.highmed.dsf.bpe.spring.config;

import org.highmed.dsf.bpe.service.HelloWorld;
import org.highmed.dsf.bpe.service.LogUserTaskResponse;
import org.highmed.dsf.fhir.authorization.read.ReadAccessHelper;
import org.highmed.dsf.fhir.client.FhirWebserviceClientProvider;
import org.highmed.dsf.fhir.task.TaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;

@Configuration
public class HelloWorldConfig
{
	@Autowired
	private FhirWebserviceClientProvider clientProvider;

	@Autowired
	private TaskHelper taskHelper;

	@Autowired
	private ReadAccessHelper readAccessHelper;

	@Autowired
	private FhirContext fhirContext;

	@Bean
	public HelloWorld helloWorld()
	{
		return new HelloWorld(clientProvider, taskHelper, readAccessHelper);
	}

	@Bean
	public LogUserTaskResponse logUserTaskResponse()
	{
		return new LogUserTaskResponse(clientProvider, taskHelper, readAccessHelper, fhirContext);
	}
}
