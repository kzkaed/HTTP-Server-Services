package server;

import static org.junit.Assert.*;
import java.util.Map;

import org.junit.*;

public class ArgsParserTest {
		
		@Test
		public void testDefaultGlobalVarialbesAreEqual(){
			
			assertEquals(ArgsParser.PORT,server.Constants.PORT_DEFAULT);
			assertEquals(ArgsParser.PUBLIC_DIR,server.Constants.PUBLIC_DIR_DEFAULT);
			
		}
	
		@Test
		public void testDefaultsAdded(){
			ArgsParser parser = new ArgsParser();
			Map<String, String> context = parser.parse(new String[0]);
			assertEquals(context.get("Port"), ArgsParser.PORT);
			assertEquals(context.get("Public Directory"), ArgsParser.PUBLIC_DIR);

		}
		
		@Test
		public void testPort(){
			String[] args = {"5000"};
			ArgsParser parser = new ArgsParser();
			Map<String, String> context = parser.parse(args);
			assertEquals(context.get("Port"), "5000");
		}
		
		@Test
		public void testPublicDocumentFolder(){
			String directory = "directory"; 
			String[] args = {"5000", directory};
			ArgsParser parser = new ArgsParser();
			assertEquals(parser.parse(args).get("Public Directory"), directory);
		}
		
	
}
