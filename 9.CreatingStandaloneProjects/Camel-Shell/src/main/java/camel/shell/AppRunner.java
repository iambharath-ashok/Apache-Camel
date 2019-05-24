package camel.shell;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class AppRunner {
	
	public static void main(String[] args) {
		
		
		CamelContext cc = new DefaultCamelContext();
		ShellRoute sh = new ShellRoute();
		try {
			cc.addRoutes(sh);
			cc.start();
			Thread.sleep(500000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
