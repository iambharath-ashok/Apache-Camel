# Timer Component


-	Kafka and Jms and file is event based ... 
-	Route will gets triggered when message has been pushed or file has been pushed
-	Unlike that Timer component will trigger automatically based on the time


### Implementation of Time Component

	Code Snippet:
	
		public class Rest2DBRoute extends RouteBuilder {


			public void configure() throws Exception {

				onException(PSQLException.class, Exception.class).handled(true).log("Exception While inserting messages.").process(new ExceptionProcessor());

				from("timer:learnTimer?period=10s")
						.to("log:?level=INFO&showBody=true")
						.setHeader(Exchange.HTTP_METHOD, constant("GET"))
						.setHeader(Exchange.HTTP_URI, simple("https://restcountries.eu/rest/v2/alpha/us"))
						.to("https://restcountries.eu/rest/v2/alpha/us").convertBodyTo(String.class)
						.to("log:?level=INFO&showBody=true")
						.process(new InsertProcessor())
						.to("jdbc:myDataSource")
						.to("sql:select * from country_capital?dataSource=myDataSource");

			}
		}
	
	
	
-	learnTimer is the Timer Name 
-	Period=10 is the interval of retriggering the route


	
