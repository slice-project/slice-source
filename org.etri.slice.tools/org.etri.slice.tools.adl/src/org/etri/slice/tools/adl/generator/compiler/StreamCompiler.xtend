package org.etri.slice.tools.adl.generator.compiler

import com.google.inject.Inject
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.compiler.ImportManager
import org.etri.slice.tools.adl.domainmodel.AgentDeclaration
import org.etri.slice.tools.adl.domainmodel.DataType
import org.etri.slice.tools.adl.domainmodel.Event
import org.etri.slice.tools.adl.generator.GeneratorUtils

class StreamCompiler {
	
	@Inject extension IQualifiedNameProvider
	@Inject extension GeneratorUtils
	
	def compileStream(JvmTypeReference it, AgentDeclaration agent) '''
		«val importManager = new ImportManager(true)» 
		«val body = body(importManager)»
		«IF agent.eContainer !== null»
			package org.etri.slice.agents.«agent.eContainer.fullyQualifiedName».«agent.name.toLowerCase».stream;
		«ENDIF»
		
		import org.apache.edgent.topology.TStream;
		import org.apache.felix.ipojo.annotations.Component;
		import org.apache.felix.ipojo.annotations.Instantiate;
		import org.apache.felix.ipojo.annotations.Provides;
		import org.etri.slice.api.perception.EventStream;	
		«FOR i:importManager.imports»
			import «i»;
		«ENDFOR»
		
		«body»
		
	'''	
	
	def compileStream(DataType it, AgentDeclaration agent) '''
		«val importManager = new ImportManager(true)» 
		«val body = body(importManager)»
		«IF agent.eContainer !== null»
			package org.etri.slice.agents.«agent.eContainer.fullyQualifiedName».«agent.name.toLowerCase».stream;
		«ENDIF»
		
		import org.apache.edgent.topology.TStream;
		import org.apache.felix.ipojo.annotations.Component;
		import org.apache.felix.ipojo.annotations.Instantiate;
		import org.apache.felix.ipojo.annotations.Provides;
		import org.etri.slice.api.perception.EventStream;	
		«FOR i:importManager.imports»
			import «i»;
		«ENDFOR»
		
		«body»
		
	'''
	
	dispatch def body(JvmTypeReference it, ImportManager importManager) '''
		@Component(publicFactory=false, immediate=true)
		@Provides
		@Instantiate(name=«importManager.serialize(it.type)»Stream.SERVICE_NAME)
		public class «simpleName»Stream implements EventStream<«simpleName»> {

			public static final String SERVICE_NAME = "«simpleName»Stream";
			
			@Override
			public TStream<«simpleName»> process(TStream<«simpleName»> stream) {
				return stream;
			}

		}
	'''	
	
	dispatch def body(Event it, ImportManager importManager) '''
		@Component(publicFactory=false, immediate=true)
		@Provides
		@Instantiate(name=«importManager.serialize(toJvmGenericType(fullyQualifiedName, "event"))»Stream.SERVICE_NAME)
		public class «name»Stream implements EventStream<«name»> {

			public static final String SERVICE_NAME = "«name»Stream";
			
			@Override
			public TStream<«name»> process(TStream<«name»> stream) {
				return stream;
			}

		}
	'''		
}
