package camel.shell.processors;

import java.io.File;
import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.exec.ExecResult;

public class ExceProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		ExecResult body = exchange.getIn().getBody(ExecResult.class);
		byte[] buffer= new byte[5000];
		body.getStdout().read(buffer);
		String output = body.toString();
		System.out.println(output);
		File outFile = body.getCommand().getOutFile();
		System.out.println(outFile);
		System.out.println(Arrays.toString(buffer));
	}

}
