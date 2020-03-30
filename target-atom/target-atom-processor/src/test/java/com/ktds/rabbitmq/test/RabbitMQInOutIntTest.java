package com.ktds.rabbitmq.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.apache.camel.impl.JndiRegistry;
import org.junit.Test;

import com.ktds.rabbitmq.test.testbeans.TestNonSerializableObject;
import com.ktds.rabbitmq.test.testbeans.TestPartiallySerializableObject;
import com.ktds.rabbitmq.test.testbeans.TestSerializableObject;

public class RabbitMQInOutIntTest extends AbstractRabbitMQIntTest {

    public static final String ROUTING_KEY = "rk5";
    public static final long TIMEOUT_MS = 2000;
    private static final String EXCHANGE = "ex5";
    private static final String EXCHANGE_NO_ACK = "ex5.noAutoAck";

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Produce(uri = "direct:rabbitMQ")
    protected ProducerTemplate directProducer;

    @EndpointInject(uri = "rabbitmq:localhost:5672/" + EXCHANGE + "?threadPoolSize=1&exchangeType=direct&username=guest&password=guest"
                    + "&autoAck=true&queue=q4&routingKey=" + ROUTING_KEY
                    + "&transferException=true&requestTimeout=" + TIMEOUT_MS + "&autoDelete=false")
    private Endpoint rabbitMQEndpoint;

    @EndpointInject(uri = "rabbitmq:localhost:5672/" + EXCHANGE_NO_ACK + "?threadPoolSize=1&exchangeType=direct&username=guest&password=guest"
            + "&autoAck=false&autoDelete=false&durable=false&queue=q5&routingKey=" + ROUTING_KEY
            + "&transferException=true&requestTimeout=" + TIMEOUT_MS
            + "&queueArgs=#queueArgs")
    private Endpoint noAutoAckEndpoint;
    
    
    @EndpointInject(uri = "rabbitmq:localhost:5672/" + EXCHANGE + "?threadPoolSize=1&exchangeType=direct&username=guest&password=guest"
            + "&autoAck=true&queue=qTest&routingKey=" + ROUTING_KEY
            + "&transferException=true&requestTimeout=" + TIMEOUT_MS + "&autoDelete=false")
    private Endpoint rabbitMQEndpoint2;
    

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;

    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();

        HashMap<String, Object> queueArgs = new HashMap<>();
        queueArgs.put("x-expires", 60000);
        jndi.bind("queueArgs", queueArgs);

        return jndi;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {

                from("direct:rabbitMQ").id("producingRoute").setHeader("routeHeader", simple("routeHeader")).inOut(rabbitMQEndpoint);

                from(rabbitMQEndpoint).id("consumingRoute").log("Receiving message").process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                    	
                    	System.out.println("exchange.getIn().getHeaders() : " + exchange.getIn().getHeaders());
                    	System.out.println("exchange.getIn().getBody() : " + exchange.getIn().getBody());
                    	
                        if (exchange.getIn().getBody(TestSerializableObject.class) != null) {
                        	System.out.println("if (exchange.getIn().getBody(TestSerializableObject.class) != null) {");
                            TestSerializableObject foo = exchange.getIn().getBody(TestSerializableObject.class);
                            foo.setDescription("foobar");
                            
                            System.out.println("foo.getName() : " + foo.getName());
                            System.out.println("foo.getDescription() : " + foo.getDescription());
                            
                        } else if (exchange.getIn().getBody(TestPartiallySerializableObject.class) != null) {
                        	System.out.println("} else if (exchange.getIn().getBody(TestPartiallySerializableObject.class) != null) {");
                            TestPartiallySerializableObject foo = exchange.getIn().getBody(TestPartiallySerializableObject.class);
                            foo.setNonSerializableObject(new TestNonSerializableObject());
                            foo.setDescription("foobar");
                            
                            System.out.println("foo.getName() : " + foo.getName());
                            System.out.println("foo.getDescription() : " + foo.getDescription());
                            
                        } else if (exchange.getIn().getBody(String.class) != null) {
                        	System.out.println("} else if (exchange.getIn().getBody(String.class) != null) {");
                            if (exchange.getIn().getBody(String.class).contains("header")) {
                            	System.out.println("if (exchange.getIn().getBody(String.class).contains(\"header\")) {");
                                assertEquals(exchange.getIn().getHeader("String"), "String");
                                assertEquals(exchange.getIn().getHeader("routeHeader"), "routeHeader");
                            }

                            if (exchange.getIn().getBody(String.class).contains("Exception")) {
                                throw new IllegalArgumentException("Boom");
                            }

                            if (exchange.getIn().getBody(String.class).contains("TimeOut")) {
                                Thread.sleep(TIMEOUT_MS * 2);
                            }

                            exchange.getIn().setBody(exchange.getIn().getBody(String.class) + " response");
                        }

                    }
                });

//                from("direct:rabbitMQNoAutoAck").id("producingRouteNoAutoAck").setHeader("routeHeader", simple("routeHeader")).inOut(noAutoAckEndpoint);
//
//                from(noAutoAckEndpoint).id("consumingRouteNoAutoAck")
//                        .to(resultEndpoint)
//                        .throwException(new IllegalStateException("test exception"));
            }
        };
    }

//    @Test
//    public void inOutRaceConditionTest1() throws InterruptedException, IOException {
//        String reply = template.requestBodyAndHeader("direct:rabbitMQ", "test1", RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, String.class);
//        assertEquals("test1 response", reply);
//    }

//    @Test
//    public void inOutRaceConditionTest2() throws InterruptedException, IOException {
//        String reply = template.requestBodyAndHeader("direct:rabbitMQ", "test2", RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, String.class);
//        assertEquals("test2 response", reply);
//    }
//
//    @Test
//    public void headerTest() throws InterruptedException, IOException {
//        Map<String, Object> headers = new HashMap<>();
//
//        TestSerializableObject testObject = new TestSerializableObject();
//        testObject.setName("header");
//
//        headers.put("String", "String");
//        headers.put("Boolean", new Boolean(false));
//
//        // This will blow up the connection if not removed before sending the message
//        headers.put("TestObject1", testObject);
//        // This will blow up the connection if not removed before sending the message
//        headers.put("class", testObject.getClass());
//        // This will mess up de-serialization if not removed before sending the message
//        headers.put("CamelSerialize", true);
//
//        // populate a map and an arrayList
//        Map<Object, Object> tmpMap = new HashMap<>();
//        List<String> tmpList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            String name = "header" + i;
//            tmpList.add(name);
//            tmpMap.put(name, name);
//        }
//        // This will blow up the connection if not removed before sending the message
//        headers.put("arrayList", tmpList);
//        // This will blow up the connection if not removed before sending the message
//        headers.put("map", tmpMap);
//
//        String reply = template.requestBodyAndHeaders("direct:rabbitMQ", "header", headers, String.class);
//        assertEquals("header response", reply);
//    }
//
    @Test
    public void serializeTest() throws InterruptedException, IOException {
        TestSerializableObject foo = new TestSerializableObject();
        foo.setName("foobar");
        
        System.out.println("RabbitMQConstants.EXCHANGE_NAME : " + RabbitMQConstants.EXCHANGE_NAME);
        System.out.println("EXCHANGE : " + EXCHANGE);

        TestSerializableObject reply = template.requestBodyAndHeader("direct:rabbitMQ", foo, RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, TestSerializableObject.class);
        assertEquals("foobar", reply.getName());
        assertEquals("foobar", reply.getDescription());
    }
//
//    @Test
//    public void partiallySerializeTest() throws InterruptedException, IOException {
//        TestPartiallySerializableObject foo = new TestPartiallySerializableObject();
//        foo.setName("foobar");
//
//        try {
//            template.requestBodyAndHeader("direct:rabbitMQ", foo, RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, TestPartiallySerializableObject.class);
//        } catch (CamelExecutionException e) {
//            // expected
//        }
//        // Make sure we didn't crash the one Consumer thread
//        String reply2 = template.requestBodyAndHeader("direct:rabbitMQ", "partiallySerializeTest1", RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, String.class);
//        assertEquals("partiallySerializeTest1 response", reply2);
//    }
//
//    @Test
//    public void testSerializableObject() throws IOException {
//        TestSerializableObject foo = new TestSerializableObject();
//        foo.setName("foobar");
//
//        byte[] body = null;
//        try (ByteArrayOutputStream b = new ByteArrayOutputStream(); ObjectOutputStream o = new ObjectOutputStream(b);) {
//            o.writeObject(foo);
//            body = b.toByteArray();
//        }
//
//        TestSerializableObject newFoo = null;
//        try (InputStream b = new ByteArrayInputStream(body); ObjectInputStream o = new ObjectInputStream(b);) {
//            newFoo = (TestSerializableObject) o.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//        }
//        assertEquals(foo.getName(), newFoo.getName());
//    }
//
//    @Test
//    public void inOutExceptionTest() {
//        try {
//            template.requestBodyAndHeader("direct:rabbitMQ", "Exception", RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, String.class);
//            fail("This should have thrown an exception");
//        } catch (CamelExecutionException e) {
//            assertEquals(e.getCause().getClass(), IllegalArgumentException.class);
//        } catch (Exception e) {
//            fail("This should have caught CamelExecutionException");
//        }
//    }
//
//    @Test
//    public void inOutTimeOutTest() {
//        try {
//            template.requestBodyAndHeader("direct:rabbitMQ", "TimeOut", RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, String.class);
//            fail("This should have thrown a timeOut exception");
//        } catch (CamelExecutionException e) {
//            // expected
//        } catch (Exception e) {
//            fail("This should have caught CamelExecutionException");
//        }
//    }
//
//    @Test
//    public void inOutNullTest() {
//        template.requestBodyAndHeader("direct:rabbitMQ", null, RabbitMQConstants.EXCHANGE_NAME, EXCHANGE, Object.class);
//    }
//
//    @Test
//    public void messageAckOnExceptionWhereNoAutoAckTest() throws Exception {
//        Map<String, Object> headers = new HashMap<>();
//        headers.put(RabbitMQConstants.EXCHANGE_NAME, EXCHANGE_NO_ACK);
//        headers.put(RabbitMQConstants.ROUTING_KEY, ROUTING_KEY);
//
//        resultEndpoint.expectedMessageCount(1);
//
//        try {
//            template.requestBodyAndHeaders("direct:rabbitMQNoAutoAck", "testMessage", headers, String.class);
//            fail("This should have thrown an exception");
//        } catch (CamelExecutionException e) {
//            if (!(e.getCause() instanceof IllegalStateException)) {
//                throw e;
//            }
//        }
//
//        resultEndpoint.assertIsSatisfied();
//        resultEndpoint.reset();
//
//        resultEndpoint.expectedMessageCount(0);
//
//        context.stop(); //On restarting the camel context, if the message was not acknowledged the message would be reprocessed
//        context.start();
//
//        resultEndpoint.assertIsSatisfied();
//    }

}