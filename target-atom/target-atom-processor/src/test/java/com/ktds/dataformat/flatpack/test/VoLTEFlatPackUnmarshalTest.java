/*
 * package com.ktds.dataformat.flatpack.test;
 * 
 * import java.util.ArrayList; import java.util.List; import java.util.Map;
 * 
 * import org.apache.camel.EndpointInject; import org.apache.camel.Exchange;
 * import org.apache.camel.Produce; import org.apache.camel.ProducerTemplate;
 * import org.apache.camel.builder.RouteBuilder; import
 * org.apache.camel.component.flatpack.DataSetList; import
 * org.apache.camel.component.mock.MockEndpoint; import
 * org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat; import
 * org.apache.camel.model.dataformat.FlatpackDataFormat; import
 * org.apache.camel.spi.DataFormat; import
 * org.apache.camel.test.junit4.CamelTestSupport; import
 * org.apache.commons.lang3.builder.ToStringBuilder; import
 * org.apache.commons.lang3.builder.ToStringStyle; import org.json.JSONObject;
 * import org.junit.FixMethodOrder; import org.junit.Test; import
 * org.junit.runners.MethodSorters; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.test.annotation.DirtiesContext;
 * 
 * import com.ktds.targetatom.util.CdrUtils; import
 * com.targetatom.ktds.dao.volte.input.VoLTEBody; import
 * com.targetatom.ktds.dao.volte.input.VoLTETransformer;
 * 
 * import junit.framework.Assert;
 * 
 * @PropertySource(value = {"classpath:application.properties",
 * "classpath:camel-properties.properties"})
 * 
 * @FixMethodOrder(MethodSorters.NAME_ASCENDING) public class
 * VoLTEFlatPackUnmarshalTest extends CamelTestSupport { protected final Logger
 * log = LoggerFactory.getLogger(getClass()); private static final String
 * URI_DIRECT_UNMARSHAL = "direct:unmarshal"; private static final String
 * URI_UNMARSHAL_RESULT = "mock:unmarshal_result"; private static final String
 * URI_DIRECT_MARSHAL = "direct:marshal"; private static final String
 * URI_MARSHAL_RESULT = "mock:marshal_result";
 * 
 * private static final String TEST_HEADER =
 * "        SLTEB   ST0SCP01KTFSLHC1                06062019050223533452184    KTFHAD10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            1"
 * ; private static final String TEST_RECORD =
 * "01006010981479614500867801107051.103.64.254   044TES lte.ktfwing.com                                                                                     4110.70.226.1   110.70.236.1   036  020045008 08-79060003E8000003E800        1872414641                                                                                                54F08002A2D003  54F08002A2D003        201905022214152019050223533400000000002737940000000007408640000059594993  CR1                                                             201905022353272019050223533400000000000069980000000000633195      00000006          0000000200                    08-79060003E8000003E800                                                                                                                                                      S"
 * +
 * "0100601098947901450087460173966               044TES lte.ktfwing.com                                                                                     4110.70.130.1   110.70.234.1   236  020045008 08-79060003E8000003E800        1372140229                                                                                                54F0800106DB02  54F0800106DB02        201905022353312019050223571300000000000053260000000000001659000002224993  CR1                                                             201905022353312019050223571300000000000053260000000000001659      00000222          0000000002                    08-79060003E8000003E800        2001:e60:1028:fff6:0:0:0:0                                                                                                                    2"
 * ; private static final String TEST_FOOTER =
 * " SLTEB_FW4GCLTE_ID0606_T20190502235802.DAT      20190502235834571830005000000500000008000004001600KTFTAL10                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     3"
 * ;
 * 
 * @Produce(uri = URI_DIRECT_UNMARSHAL) private ProducerTemplate
 * unmarshalTemplate;
 * 
 * @EndpointInject(uri = URI_UNMARSHAL_RESULT) private MockEndpoint
 * unmarshalResult;
 * 
 * @Produce(uri = URI_DIRECT_MARSHAL) private ProducerTemplate marshalTemplate;
 * 
 * @EndpointInject(uri = URI_MARSHAL_RESULT) private MockEndpoint marshalResult;
 * 
 * private static DataSetList listUnmarshal;
 * 
 * @Test public void test1_UnMarshal() throws Exception { StringBuffer buff =
 * new StringBuffer();
 * buff.append(TEST_HEADER).append(TEST_RECORD).append(TEST_FOOTER);
 * 
 * unmarshalResult.expectedMessageCount(1);
 * 
 * unmarshalTemplate.sendBody(URI_DIRECT_UNMARSHAL, buff.toString());
 * assertMockEndpointsSatisfied();
 * 
 * DataSetList list =
 * unmarshalResult.getExchanges().get(0).getIn().getBody(DataSetList.class);
 * log.info("result count={}", list.size()); log.info("error count={}",
 * list.getErrorCount());
 * 
 * // Store unmarshal result to test2_marshal listUnmarshal = list;
 * 
 * 
 * for(Object error : list.getErrors()) { log.info("error={}", error); }
 * assertEquals(0, list.getErrorCount());
 * 
 * 
 * for (int i = 0; i < list.size(); i++) { Map<String, Object> item =
 * list.get(i); log.info("result[{}]={}", i, item); String recordType =
 * item.get("record_type").toString(); log.info("recordType={}", recordType);
 * if("1".equals(recordType)) { log.info("[{}]header={}", i, new
 * JSONObject(item).toString(2)); } else if("3".equals(recordType)) {
 * log.info("[{}]trailer={}", i, new JSONObject(item).toString(2)); // Current
 * FlatPack doesn't support trailer marshal } else { log.info("[{}]body={}", i,
 * new JSONObject(item).toString(2)); assertEquals("499",
 * list.get(i).get("service_category_id")); } } }
 * 
 * @Test public void test2_Marshal() throws Exception { log.info("source={}",
 * listUnmarshal); marshalTemplate.sendBody(URI_DIRECT_MARSHAL, listUnmarshal);
 * assertMockEndpointsSatisfied();
 * 
 * log.info("count={}", marshalResult.getReceivedCounter());
 * log.info("exchange={}", marshalResult.getReceivedExchanges()); String result
 * = marshalResult.getExchanges().get(0).getIn().getBody(String.class);
 * log.info("result=[{}]", result); log.info("expect length={}",
 * TEST_RECORD.length()); log.info("Result length={}", result.length());
 * assertEquals(TEST_RECORD, result); }
 * 
 * @Override protected RouteBuilder createRouteBuilder() throws Exception {
 * RouteBuilder routeBuilder = new RouteBuilder() {
 * 
 * @Override public void configure() throws Exception { FlatpackDataFormat df =
 * new FlatpackDataFormat();
 * df.setDefinition("spring/dataformat/volte/VOLTE_INPUT_FORMAT.xml");
 * df.setFixed(true);
 * 
 * from(URI_DIRECT_UNMARSHAL) .setHeader("cdrFormat", constant("volte"))
 * .setHeader("cdrHeaderLength", constant("800")) .setHeader("cdrRecordLength",
 * constant("800")) .transform().method(CdrUtils.class, "addNewLine")
 * .unmarshal(df) .log("Body :::::: ${body}") .to(URI_UNMARSHAL_RESULT);
 * 
 * from(URI_DIRECT_MARSHAL) .marshal(df) // .convertBodyTo(String.class)
 * .transform().method(CdrUtils.class, "removeNewLine") .to(URI_MARSHAL_RESULT);
 * } };
 * 
 * return routeBuilder; } }
 */