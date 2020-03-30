package com.ktds.rabbitmq.test.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.apache.camel.impl.JndiRegistry;
import org.junit.Test;

import com.ktds.rabbitmq.test.AbstractRabbitMQIntTest;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class RabbitMQConsumerIntTest extends AbstractRabbitMQIntTest {

    private static final String EXCHANGE = "ex1";
    private static final String HEADERS_EXCHANGE = "ex8";
    private static final String QUEUE = "q1";
    private static final String MSG = "hello world";

    @EndpointInject(uri = "rabbitmq:localhost:5672/" + EXCHANGE + "?username=guest&password=guest")
    private Endpoint from;

    @EndpointInject(uri = "mock:result")
    private MockEndpoint to;

    @EndpointInject(uri = "rabbitmq:localhost:5672/" + HEADERS_EXCHANGE + "?username=guest&password=guest&exchangeType=headers&queue=" + QUEUE + "&bindingArgs=#bindArgs")
    private Endpoint headersExchangeWithQueue;

    @EndpointInject(uri = "rabbitmq:localhost:5672/" + "ex7" + "?username=guest&password=guest&exchangeType=headers&autoDelete=false&durable=true&queue=q7&arg.binding.fizz=buzz")
    private Endpoint headersExchangeWithQueueDefiniedInline;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from(from).to(to);
                from(headersExchangeWithQueue).to(to);
                from(headersExchangeWithQueueDefiniedInline).to(to);
            }
        };
    }

    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry jndi = super.createRegistry();

        Map<String, Object> bindingArgs = new HashMap<>();
        jndi.bind("bindArgs", bindingArgs);

        return jndi;
    }

    @Test
    public void sentMessageIsReceived() throws InterruptedException, IOException, TimeoutException {

        to.expectedMessageCount(1);
        to.expectedHeaderReceived(RabbitMQConstants.REPLY_TO, "myReply");

        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.replyTo("myReply");

        Channel channel = connection().createChannel();
        channel.basicPublish(EXCHANGE, "", properties.build(), MSG.getBytes());

        to.assertIsSatisfied();
    }

//    @Test
//    public void sentMessageIsDeliveryModeSet() throws InterruptedException, IOException, TimeoutException {
//
//        to.expectedMessageCount(1);
//        to.expectedHeaderReceived(RabbitMQConstants.DELIVERY_MODE, 1);
//
//        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
//        properties.deliveryMode(1);
//
//        Channel channel = connection().createChannel();
//        channel.basicPublish(EXCHANGE, "", properties.build(), MSG.getBytes());
//
//        to.assertIsSatisfied();
//    }
//
//    @Test
//    public void sentMessageWithTimestampIsReceived() throws InterruptedException, IOException, TimeoutException {
//        Date timestamp = currentTimestampWithoutMillis();
//
//        to.expectedMessageCount(1);
//        to.expectedHeaderReceived(RabbitMQConstants.TIMESTAMP, timestamp);
//
//
//        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
//        properties.timestamp(timestamp);
//
//        Channel channel = connection().createChannel();
//        channel.basicPublish(EXCHANGE, "", properties.build(), MSG.getBytes());
//
//        to.assertIsSatisfied();
//    }
//
//    /**
//     * Tests the proper rabbit binding arguments are in place when the headersExchangeWithQueue is created.
//     * Should only receive messages with the header [foo=bar]
//     */
//    @Test
//    public void sentMessageIsReceivedWithHeadersRouting() throws InterruptedException, IOException, TimeoutException {
//        //should only be one message that makes it through because only
//        //one has the correct header set
//        to.expectedMessageCount(1);
//
//        Channel channel = connection().createChannel();
//        channel.basicPublish(HEADERS_EXCHANGE, "", propertiesWithHeader("foo", "bar"), MSG.getBytes());
//        channel.basicPublish(HEADERS_EXCHANGE, "", null, MSG.getBytes());
//        channel.basicPublish(HEADERS_EXCHANGE, "", propertiesWithHeader("foo", "bra"), MSG.getBytes());
//
//        to.assertIsSatisfied();
//    }
//
//    @Test
//    public void sentMessageIsReceivedWithHeadersRoutingMultiValueMapBindings() throws Exception {
//        to.expectedMessageCount(3);
//
//        Channel channel = connection().createChannel();
//        channel.basicPublish("ex7", "", propertiesWithHeader("fizz", "buzz"), MSG.getBytes());
//        channel.basicPublish("ex7", "", propertiesWithHeader("fizz", "buzz"), MSG.getBytes());
//        channel.basicPublish("ex7", "", propertiesWithHeader("fizz", "buzz"), MSG.getBytes());
//        channel.basicPublish("ex7", "", propertiesWithHeader("fizz", "nope"), MSG.getBytes());
//
//        to.assertIsSatisfied();
//    }
//
//    private AMQP.BasicProperties propertiesWithHeader(String headerName, String headerValue) {
//        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
//        properties.headers(Collections.singletonMap(headerName, headerValue));
//        return properties.build();
//    }
//
//    private Date currentTimestampWithoutMillis() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }
}

