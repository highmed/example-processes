package org.highmed.dsf.bpe;

import static org.highmed.dsf.bpe.ConstantsBase.PROCESS_HIGHMED_URI_BASE;
import static org.highmed.dsf.bpe.HelloWorldProcessPluginDefinition.VERSION;

public interface ConstantsHelloWorld
{
	String PROFILE_HIGHMED_TASK_HELLO_WORLD = "http://highmed.org/fhir/StructureDefinition/task-hello-world";
	String PROFILE_HIGHMED_TASK_HELLO_WORLD_PROCESS_URI = PROCESS_HIGHMED_URI_BASE + "helloWorld/";
	String PROFILE_HIGHMED_TASK_HELLO_WORLD_PROCESS_URI_AND_LATEST_VERSION = PROFILE_HIGHMED_TASK_HELLO_WORLD_PROCESS_URI
			+ VERSION;
	String PROFILE_HIGHMED_TASK_HELLO_WORLD_MESSAGE_NAME = "helloWorldMessage";
}
