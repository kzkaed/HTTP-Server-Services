package server;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {
	public static final String PORT = server.constants.Constants.PORT_DEFAULT;
	public static final String PUBLIC_DIR = server.constants.Constants.PUBLIC_DIR_DEFAULT;

	public Map<String,String> parse(String[] args) {
		Map<String, String> context = new HashMap<String, String>();
		context.put("Port", PORT);
		context.put("Public Directory", PUBLIC_DIR);
		
		if(args.length > 0 )
			context.put("Public Directory", args[0]);
		if(args.length > 1)
			context.put("Public Directory", args[1]);
		return context;
	}

}
