package camel.shell.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.exec.ExecBinding;

public class FtpPreProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		//exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_EXECUTABLE, "ftp");
		exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_ARGS, "USER K5PH3BX8");
		exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_ARGS, "PASS K5PH3BX8");
		//exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_ARGS, "XCSHEPWF");
		//exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_ARGS, "K5PH3BX8");
		//exchange.getIn().setHeader(ExecBinding.EXEC_COMMAND_ARGS, "K5PH3BX8\"");
		Object body = exchange.getIn().getBody();
		
		System.out.println("**********************************8");
		System.out.println(body);
	}

}
