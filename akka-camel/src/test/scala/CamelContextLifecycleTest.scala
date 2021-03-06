package se.scalablesolutions.akka.camel

import org.apache.camel.impl.{DefaultProducerTemplate, DefaultCamelContext}
import org.junit.Test
import org.scalatest.junit.JUnitSuite

class CamelContextLifecycleTest extends JUnitSuite with CamelContextLifecycle {
  @Test def shouldManageCustomCamelContext {
    assert(context === null)
    assert(template === null)
    init(new TestCamelContext)
    assert(!context.asInstanceOf[TestCamelContext].isStarted)
    assert(!template.asInstanceOf[DefaultProducerTemplate].isStarted)
    start
    assert(context.asInstanceOf[TestCamelContext].isStarted)
    assert(template.asInstanceOf[DefaultProducerTemplate].isStarted)
    stop
    assert(!context.asInstanceOf[TestCamelContext].isStarted)
    assert(!template.asInstanceOf[DefaultProducerTemplate].isStarted)
  }

  class TestCamelContext extends DefaultCamelContext
}