package server.request;

import java.util.Hashtable;

class ParsedRequestLine {
	final String method;
	final String uri;
	final String protocolVersion;
	final Hashtable<String, String> parameters;

	ParsedRequestLine(String method, String uri, String protocolVersion, Hashtable<String, String> parameters) {
		this.method = method;
		this.uri = uri;
		this.protocolVersion = protocolVersion;
		this.parameters = parameters;
	}
}
