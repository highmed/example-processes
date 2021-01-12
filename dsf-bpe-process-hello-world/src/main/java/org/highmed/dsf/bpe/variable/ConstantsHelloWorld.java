package org.highmed.dsf.bpe.variable;

import static org.highmed.dsf.bpe.ConstantsBase.PROCESS_URI_BASE;
import static org.highmed.dsf.bpe.HelloWorldProcessPluginDefinition.VERSION;

public interface ConstantsHelloWorld
{
	String HELLO_WORLD_TASK_PROFILE = "http://highmed.org/fhir/StructureDefinition/highmed-task-hello-world";
	String HELLO_WORLD_PROCESS_URI = PROCESS_URI_BASE + "helloWorld/";
	String HELLO_WORLD_PROCESS_URI_AND_LATEST_VERSION = HELLO_WORLD_PROCESS_URI + VERSION;
	String HELLO_WORLD_MESSAGE_NAME = "helloWorldMessage";
}
