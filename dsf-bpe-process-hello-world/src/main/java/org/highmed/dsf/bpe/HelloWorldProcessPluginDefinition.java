package org.highmed.dsf.bpe;

import static org.highmed.dsf.bpe.ConstantsHelloWorld.PROCESS_NAME_FULL_HELLO_USER;
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
	public static final String VERSION = "0.9.0";
	public static final LocalDate RELEASE_DATE = LocalDate.of(2022, 10, 24);

	@Override
	public String getName()
	{
		return "dsf-bpe-process-hello-world-and-user";
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
		return Stream.of("bpe/helloWorld.bpmn", "bpe/helloUser.bpmn");
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
		var aHelloUser = ActivityDefinitionResource.file("fhir/ActivityDefinition/highmed-helloUser.xml");
		var aHelloWorld = ActivityDefinitionResource.file("fhir/ActivityDefinition/highmed-helloWorld.xml");

		var qHelloWorld = QuestionnaireResource.file("fhir/Questionnaire/highmed-questionnaire-hello-user.xml");

		var tHelloUser = StructureDefinitionResource.file("fhir/StructureDefinition/highmed-task-hello-user.xml");
		var tHelloWorld = StructureDefinitionResource.file("fhir/StructureDefinition/highmed-task-hello-world.xml");

		Map<String, List<AbstractResource>> resourcesByProcessKeyAndVersion = Map.of(
				PROCESS_NAME_FULL_HELLO_USER + "/" + VERSION, Arrays.asList(aHelloUser, qHelloWorld, tHelloUser),
				PROCESS_NAME_FULL_HELLO_WORLD + "/" + VERSION, Arrays.asList(aHelloWorld, tHelloWorld));

		return ResourceProvider.read(VERSION, RELEASE_DATE,
				() -> fhirContext.newXmlParser().setStripVersionsFromReferences(false), classLoader, resolver,
				resourcesByProcessKeyAndVersion);
	}
}
