package org.etri.slice.core.config;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Dictionary;
import java.util.Enumeration;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Updated;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.service.cm.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true, managedservice="org.etri.slice.agent")
@Instantiate
public class AgentConfigurator {

	private static Logger s_logger = LoggerFactory.getLogger(AgentConfigurator.class);		
	
	private final PropertiesConfiguration m_config = new PropertiesConfiguration();
	private final PropertiesConfigurationLayout m_layout = new PropertiesConfigurationLayout();	
	
	@Validate
	public void init() {
		try {
			m_layout.load(m_config, new InputStreamReader(new FileInputStream("conf/slice.properties")));
		} 
		catch (Throwable e) {
			s_logger.error("ERR: failed to load properties - reason=" + e.getMessage());
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@Updated
	public void updated(Dictionary props) throws ConfigurationException {
		
		if (props == null) {
			return;
		}

		String pid = (String) props.remove("service.pid");
		if (pid == null) {
			return;
		}

		if (!pid.equals("org.etri.slice.agent")) {
			return;
		}		
		
		int updated = 0;
		Enumeration keys = props.keys();
		while ( keys.hasMoreElements() ) {
			String key = (String)keys.nextElement();
			if ( key.equals("component")) {
				continue;
			}
			
			String value = ((String)props.get(key)).trim();
			m_config.setProperty(key, value);
			updated++;
			s_logger.info("SET[" + key + " = " + value + "]"  );
		}
		
		try {
			if ( updated == 0 ) {
				return;
			}
			
			Writer writer = new FileWriter("conf/slice.properties", false);
			m_layout.save(m_config, writer);
			writer.flush();
			writer.close();
			s_logger.info("UPDATED: updated the agent configuratiions");			
		} 
		catch (Throwable e) {
			s_logger.error("ERR: failed to save properties - reason=" + e.getMessage());
		} 	
	}
}
