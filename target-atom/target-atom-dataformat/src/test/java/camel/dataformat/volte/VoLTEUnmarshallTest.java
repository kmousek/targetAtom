package camel.dataformat.volte;

import java.util.ArrayList;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.ktds.targetatom.cdr.transform.VoLTETransformer;
import com.ktds.targetatom.cdr.volte.VoLTEBody;

import junit.framework.Assert;

public class VoLTEUnmarshallTest extends CamelTestSupport {
	private static final String URI_MOCK_RESULT = "mock:result";
	private static final String URI_DIRECT_START = "direct:start";
	private static final String URI_FILE_START = "file:/WRK/PRC/WLSS_NRAT/VOVLTE/MOKD1";
	
	private static final String TEST_HEADER = "        SLTEB   ST0SCP01KTFSLHC1                06062019050223533452184    KTFHAD10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            1";
	private static final String TEST_RECORD = "01006010981479614500867801107051.103.64.254   044TES lte.ktfwing.com                                                                                     4110.70.226.1   110.70.236.1   036  020045008 08-79060003E8000003E800        1872414641                                                                                                54F08002A2D003  54F08002A2D003        201905022214152019050223533400000000002737940000000007408640000059594993  CR1                                                             201905022353272019050223533400000000000069980000000000633195      00000006          0000000200                    08-79060003E8000003E800                                                                                                                                                      S0100601098947901450087460173966               044TES lte.ktfwing.com                                                                                     4110.70.130.1   110.70.234.1   236  020045008 08-79060003E8000003E800        1372140229                                                                                                54F0800106DB02  54F0800106DB02        201905022353312019050223571300000000000053260000000000001659000002224993  CR1                                                             201905022353312019050223571300000000000053260000000000001659      00000222          0000000002                    08-79060003E8000003E800        2001:e60:1028:fff6:0:0:0:0                                                                                                                    2";
    private static final String TEST_FOOTER = " SLTEB_FW4GCLTE_ID0606_T20190502235802.DAT      20190502235834571830005000000500000008000004001600KTFTAL10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     3";
    
    @Produce(uri = "direct:start")
	private ProducerTemplate template;
	
	@EndpointInject(uri = "mock:result")
	private MockEndpoint result;
	
	@Test
	public void testUnMarshall() throws Exception {
		StringBuffer buff = new StringBuffer();
        buff.append(TEST_HEADER).append(TEST_RECORD).append(TEST_FOOTER);
        
        result.expectedMessageCount(1);
        
        template.sendBody(URI_DIRECT_START, buff.toString());
        
		
		//template.sendBody(URI_FILE_START);
		ArrayList arraylist = (ArrayList) result.getReceivedExchanges().get(0).getIn().getBody();
		
		for (int i=0;i<arraylist.size();i++) {
			VoLTEBody tempBody = (VoLTEBody) arraylist.get(i);
			log.debug("Service_charging_type :::: {}", tempBody.getService_charging_type());
			//Assert.assertEquals("499", tempBody.getService_category_id());
			Assert.assertEquals("3  ", tempBody.getService_charging_type());
		}
	}
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		RouteBuilder routeBuilder = new RouteBuilder() {
		
		@Override
		public void configure() throws Exception {
			DataFormat bindyBody = new BindyFixedLengthDataFormat(VoLTEBody.class);
			
			from(URI_DIRECT_START)
			//from(URI_FILE_START)
			.transform().method(VoLTETransformer.class, "setRecordStructure")
			.unmarshal(bindyBody)
			.log("Body :::::: ${body}")
			.to(URI_MOCK_RESULT);
			}
		};
	
		return routeBuilder;
	}
}
