package org.highmed.dsf.bpe;

import static org.highmed.dsf.bpe.ConstantsHelloWorld.PROCESS_NAME_FULL_HELLO_WORLD;
import static org.highmed.dsf.bpe.HelloWorldProcessPluginDefinition.VERSION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.highmed.dsf.fhir.resources.ResourceProvider;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

import ca.uhn.fhir.context.FhirContext;

public class HelloWorldProcessPluginDefinitionTest
{
	@Test
	public void testResourceLoading() throws Exception
	{
		ProcessPluginDefinition definition = new HelloWorldProcessPluginDefinition();
		ResourceProvider provider = definition.getResourceProvider(FhirContext.forR4(), getClass().getClassLoader(),
				new StandardEnvironment());
		assertNotNull(provider);

		var helloWorld = provider.getResources(PROCESS_NAME_FULL_HELLO_WORLD + "/" + VERSION,
				s -> ResourceProvider.empty());
		assertNotNull(helloWorld);
		assertEquals(3, helloWorld.count());
	}
}
