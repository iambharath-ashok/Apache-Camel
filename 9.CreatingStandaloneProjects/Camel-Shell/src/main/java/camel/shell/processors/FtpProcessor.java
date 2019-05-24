package camel.shell.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.exec.ExecBinding;

public class FtpProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		
		
		String body = exchange.getIn().getBody(String.class);
		System.out.println("=====================================");
		System.out.println(body);
	}

}
