package camel.shell;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.exec.ExecBinding;

import camel.shell.processors.ExceProcessor;

public class ShellRoute extends RouteBuilder {

	@Override
	public void configure()  {
		
			onException(Exception.class).handled(true).process(exchange -> {
				Exception exception = exchange.getIn().getHeader(Exchange.EXCEPTION_CAUGHT, Exception.class);
				System.out.println("Acutal Exceptipn Message "  + exception.getMessage());
				System.out.println("Acutal Exceptipn Class "  + exception.getClass());

				String failedEndoint = (String) exchange.getProperty(Exchange.FAILURE_ENDPOINT);
				System.out.println("Failed Endpoint : " + failedEndoint);
				
				exchange.getIn().setBody("Exception in Bash Shell Route");
			})
			//.to("direct:error")
			.log(LoggingLevel.WARN, "Exception in Shell Route");
			
			from("file:data/shell?initialDelay=1000&delay=1000&useFixedDelay=true&runLoggingLevel=TRACE&startingDirectoryMustExist=true"
					+ "&autoCreate=true&maxMessagesPerPoll=1&eagerMaxMessagesPerPoll=true&delete=false&readLockLoggingLevel=OFF"
					+ "&readLockRemoveOnRollback=false&readLockRemoveOnCommit=false&idempotent=true&idempotentKey=${file:onlyname}&recursive=false")
			.to("log:?level=INFO&showAll=true&multiline=true")
			.to("direct:execftp");
			
			from("direct:execftp")
			.to("log:?level=INFO&showAll=true&multiline=true")
			
			.recipientList()
			.simple("exec:bash?args=mnftpscript.sh ${headers.CamelFilePath}")
			.setHeader("exitstatus", simple("${headers.CamelExecExitValue}"))
			.to("log:?level=INFO&showAll=true&multiline=true")
			.log("\n***********************************************************${header.exitstatus}")
			//	.process(new ExceProcessor())
			.setHeader("ShellResult",simple("${body}"))
			.log("Shell Result: -----------------------------${header.ShellResult}")
			.choice()
				.when(header("ShellResult").convertToString().contains("Not connected."))
				.log("-------------success notification---------------------------------")
				//.to("direct:error")
			.otherwise()
				.log("-------------failure notification---------------------------------")
				//.throwException(RuntimeException.class, "Error in FTP")
			.end()
			.log("\n=======================================================================================")
			.to("log:?level=INFO&showAll=true&multiline=true");
			
			
			
			
			from("direct:error")
			.log("${body}")
			//.throwException(RuntimeException.class, "Error at ftp")
			.to("mock:erroroutput");
		
	}

}
