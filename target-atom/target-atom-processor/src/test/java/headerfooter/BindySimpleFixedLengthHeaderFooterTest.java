package headerfooter;

import java.util.Map;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.model.dataformat.BindyDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.ktds.targetatom.cdr.kr.KRBody;
import com.ktds.targetatom.cdr.kr.KRHeader;
import com.ktds.targetatom.cdr.kr.KRTailer;


/**
 * This test validates that header and footer records are successfully marshalled / unmarshalled in conjunction
 * with the primary data records defined for the bindy data format.
 */
public class BindySimpleFixedLengthHeaderFooterTest extends CamelTestSupport {

    public static final String URI_DIRECT_MARSHALL               = "direct:marshall";
    public static final String URI_DIRECT_UNMARSHALL             = "direct:unmarshall";
    public static final String URI_MOCK_MARSHALL_RESULT          = "mock:marshall-result";
    public static final String URI_MOCK_UNMARSHALL_RESULT        = "mock:unmarshall-result";
    
    private static final String TEST_HEADER = "H20190312000500GIDRO01LPNPM            \r\n";
    private static final String TEST_RECORD = "R0000001269028709249         D2019031100692806214S2019031102097638021                    00002240000022400000000020190311234202201903132342028072      15523153120055990602                                                                                                            A남편위해 벗는 일본주부                                                                                                          2    OTM10001                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  \r\n";
    private static final String TEST_FOOTER = "T0000013201903120005000000039163            \r\n";

    /*private static final String TEST_HEADER = "101-08-2009\r\n";
    private static final String TEST_RECORD = "10A9  PaulineM    ISINXD12345678BUYShare000002500.45USD01-08-2009\r\n";
    private static final String TEST_FOOTER = "9000000001\r\n";*/

    
    @EndpointInject(uri = URI_MOCK_MARSHALL_RESULT)
    private MockEndpoint marshallResult;

    @EndpointInject(uri = URI_MOCK_UNMARSHALL_RESULT)
    private MockEndpoint unmarshallResult;

    // *************************************************************************
    // TESTS
    // *************************************************************************
    
    @SuppressWarnings("unchecked")
    @Test
    public void testUnmarshallMessage() throws Exception {

        StringBuffer buff = new StringBuffer();
        buff.append(TEST_HEADER).append(TEST_RECORD).append(TEST_FOOTER);
        
        System.out.println("INPUT RECORD : \n" + buff.toString());
        unmarshallResult.expectedMessageCount(1);
        
        template.sendBody(URI_DIRECT_UNMARSHALL, buff.toString());
        
        unmarshallResult.assertIsSatisfied();
        System.out.println("AAAAAAAAAAAAAAA");
        // check the model
        Exchange exchange = unmarshallResult.getReceivedExchanges().get(0);
        KRBody format = (KRBody) exchange.getIn().getBody();
        //Order order = (Order) exchange.getIn().getBody();
        //assertEquals(10, order.getOrderNr());
        assertEquals("R", format.getsType());
        /*
        // the field is not trimmed
        assertEquals("  Pauline", order.getFirstName());
        assertEquals("M    ", order.getLastName());
        */
        Map<String, Object> receivedHeaderMap = 
            (Map<String, Object>) exchange.getIn().getHeader(BindyFixedLengthDataFormat.CAMEL_BINDY_FIXED_LENGTH_HEADER);
        
        Map<String, Object> receivedFooterMap = 
            (Map<String, Object>) exchange.getIn().getHeader(BindyFixedLengthDataFormat.CAMEL_BINDY_FIXED_LENGTH_FOOTER);
        
        assertNotNull(receivedHeaderMap);
        assertNotNull(receivedFooterMap);
        
        KRHeader receivedHeader = (KRHeader) receivedHeaderMap.get(KRHeader.class.getName());
        KRTailer receivedFooter = (KRTailer) receivedFooterMap.get(KRTailer.class.getName());
        
        assertNotNull(receivedHeader);
        assertNotNull(receivedFooter);
        
        /*OrderHeader expectedHeader = new OrderHeader();
        Calendar calendar = new GregorianCalendar();
        calendar.set(2009, 7, 1, 0, 0, 0);
        calendar.clear(Calendar.MILLISECOND);
        expectedHeader.setRecordDate(calendar.getTime());
        
        assertEquals(receivedHeader.getRecordType(), expectedHeader.getRecordType());   
        assertTrue(receivedHeader.getRecordDate().equals(expectedHeader.getRecordDate()));*/
    }
    
    /**
     * Verifies that header & footer provided as part of message body are marshalled successfully
     */

    // *************************************************************************
    // ROUTES
    // *************************************************************************
    
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        RouteBuilder routeBuilder = new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                BindyDataFormat bindy = new BindyDataFormat();
                bindy.setClassType(KRBody.class);
                bindy.setLocale("en-us");
                bindy.setType(BindyType.Fixed);

                from(URI_DIRECT_UNMARSHALL)
                    .unmarshal().bindy(BindyType.Fixed, KRBody.class)
                    .to(URI_MOCK_UNMARSHALL_RESULT);
            }
        };
        
        return routeBuilder;
    }
}