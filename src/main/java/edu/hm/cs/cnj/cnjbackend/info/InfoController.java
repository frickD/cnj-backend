package edu.hm.cs.cnj.cnjbackend.info;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
	
	private final static String APPLICATIONPROPERTIES = "message.properties";
	private final static String MESSAGEPROPERTY = "message";
	
	@RequestMapping("/info")
	public Info greeting() {
		Properties prop = new Properties();
		InputStream input = null;
		String response = "";

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream(APPLICATIONPROPERTIES);
			prop.load(input);
			response = prop.getProperty(MESSAGEPROPERTY);

		} catch (IOException ex) {
			ex.printStackTrace();
			response = "Cloud not read from " + APPLICATIONPROPERTIES;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					response = "Property " + MESSAGEPROPERTY + "" + APPLICATIONPROPERTIES;
					e.printStackTrace();
				}
			}
		}
		Info info = new Info();
		info.setMessage(response);
		return info;
	}
}
