package org.camunda.devoxx.WinAShirt;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "WinAShirt-Wjax";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process-wjax.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process-wjax.bpmn")
  public void testHappyPath() {
	  //ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  
	  // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
  }
  @Test
  @Deployment (resources = "process-wjax.bpmn")
  public void testCheckQuestion4FalseAnswer() {
	  ProcessInstance processInstance = runtimeService()
			  .createProcessInstanceByKey(PROCESS_DEFINITION_KEY)
			  .startAfterActivity("Task_1swggb7")
			  .setVariable("questionFour", "f1")
			  .execute();
	  
	  assertThat(processInstance).hasPassed("EndEvent_15e7g4u");
	  
  }
  
  @Test
  @Deployment (resources = "process-wjax.bpmn")
  public void testquestionfourcorrectsecondanswer() {
	  ProcessInstance processInstance = runtimeService()
			  .createProcessInstanceByKey(PROCESS_DEFINITION_KEY)
			  .startAfterActivity("Task_1swggb7")
			  .setVariable("questionFour", "b2")
			  .execute();
	  
	  assertThat(processInstance).isWaitingAt("Task_0cdxy3o");  
  }
  
  
}
