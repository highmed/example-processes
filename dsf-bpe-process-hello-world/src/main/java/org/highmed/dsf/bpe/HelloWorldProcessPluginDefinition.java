package org.highmed.dsf.bpe;

import static org.highmed.dsf.bpe.ConstantsHelloWorld.PROCESS_NAME_FULL_HELLO_WORLD;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.highmed.dsf.bpe.spring.config.HelloWorldConfig;
import org.highmed.dsf.fhir.resources.AbstractResource;
import org.highmed.dsf.fhir.resources.ActivityDefinitionResource;
import org.highmed.dsf.fhir.resources.QuestionnaireResource;
import org.highmed.dsf.fhir.resources.ResourceProvider;
import org.highmed.dsf.fhir.resources.StructureDefinitionResource;
import org.springframework.core.env.PropertyResolver;

import ca.uhn.fhir.context.FhirContext;

public class HelloWorldProcessPluginDefinition implements ProcessPluginDefinition
{
	public static final String VERSION = "0.8.0";
	public static final LocalDate RELEASE_DATE = LocalDate.of(2022, 8, 1);

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
	public LocalDate getReleaseDate()
	{
		return RELEASE_DATE;
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
		var qHelloWorld = QuestionnaireResource.file("fhir/Questionnaire/highmed-questionnaire-hello-world.xml");
		var tHelloWorld = StructureDefinitionResource.file("fhir/StructureDefinition/highmed-task-hello-world.xml");

		Map<String, List<AbstractResource>> resourcesByProcessKeyAndVersion = Map.of(
				PROCESS_NAME_FULL_HELLO_WORLD + "/" + VERSION, Arrays.asList(aHelloWorld, qHelloWorld, tHelloWorld));

		return ResourceProvider.read(VERSION, RELEASE_DATE,
				() -> fhirContext.newXmlParser().setStripVersionsFromReferences(false), classLoader, resolver,
				resourcesByProcessKeyAndVersion);
	}
}
