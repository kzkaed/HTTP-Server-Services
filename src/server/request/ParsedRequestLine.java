package server.request;

import java.util.Map;

class ParsedRequestLine {
	final String method;
	final String uri;
	final String protocolVersion;
	final Map<String, String> parameters;

	ParsedRequestLine(String method, String uri, String protocolVersion, Map<String, String> parameters) {
		this.method = method;
		this.uri = uri;
		this.protocolVersion = protocolVersion;
		this.parameters = parameters;
	}
}
