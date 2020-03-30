package camel.dataformat.volte;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ktds.targetatom.cdr.intcc.INTCCBody;
import com.ktds.targetatom.cdr.intcc.INTCCResultCdr;

public class SampleTest {

	@Test
	public void test() {
		INTCCBody org = new INTCCBody();
		INTCCResultCdr result = new INTCCResultCdr(org);
	}

}
