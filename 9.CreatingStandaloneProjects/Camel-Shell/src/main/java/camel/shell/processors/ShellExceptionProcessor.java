package camel.shell.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ShellExceptionProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Exception exception = exchange.getIn().getHeader(Exchange.EXCEPTION_CAUGHT, Exception.class);
		System.out.println("Acutal Exceptipn Message "  + exception.getMessage());
		System.out.println("Acutal Exceptipn Class "  + exception.getClass());

		String failedEndoint = (String) exchange.getProperty(Exchange.FAILURE_ENDPOINT);
		System.out.println("Failed Endpoint : " + failedEndoint);
		
		exchange.getIn().setBody("Exception in Bash Shell Route");

	}

}
