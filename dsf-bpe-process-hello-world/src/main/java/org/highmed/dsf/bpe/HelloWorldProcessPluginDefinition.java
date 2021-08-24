package org.highmed.dsf.bpe;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.highmed.dsf.bpe.spring.config.HelloWorldConfig;
import org.highmed.dsf.fhir.resources.AbstractResource;
import org.highmed.dsf.fhir.resources.ActivityDefinitionResource;
import org.highmed.dsf.fhir.resources.ResourceProvider;
import org.highmed.dsf.fhir.resources.StructureDefinitionResource;
import org.springframework.core.env.PropertyResolver;

import ca.uhn.fhir.context.FhirContext;

public class HelloWorldProcessPluginDefinition implements ProcessPluginDefinition
{
	public static final String VERSION = "0.5.0";

	@Override
	public String getName()
	{
		return "dsf-bpe-process-hello-world";
	}

	@Override
	public String getVersion()
	{
		return VERSION;
	}

	@Override
	public Stream<String> getBpmnFiles()
	{
		return Stream.of("bpe/helloWorld.bpmn");
	}

	@Override
	public Stream<Class<?>> getSpringConfigClasses()
	{
		return Stream.of(HelloWorldConfig.class);
	}

	@Override
	public ResourceProvider getResourceProvider(FhirContext fhirContext, ClassLoader classLoader,
			PropertyResolver resolver)
	{
		var aHelloWorld = ActivityDefinitionResource.file("fhir/ActivityDefinition/highmed-helloWorld.xml");
		var tHelloWorld = StructureDefinitionResource.file("fhir/StructureDefinition/highmed-task-hello-world.xml");

		Map<String, List<AbstractResource>> resourcesByProcessKeyAndVersion = Map.of("highmedorg_helloWorld/" + VERSION,
				Arrays.asList(aHelloWorld, tHelloWorld));

		return ResourceProvider.read(VERSION, () -> fhirContext.newXmlParser().setStripVersionsFromReferences(false),
				classLoader, resolver, resourcesByProcessKeyAndVersion);
	}
}
