/*
 * generated by Xtext
 */
package org.etri.slice.tools.adl.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.etri.slice.tools.adl.parser.antlr.internal.InternalDomainmodelParser;
import org.etri.slice.tools.adl.services.DomainmodelGrammarAccess;

public class DomainmodelParser extends AbstractAntlrParser {

	@Inject
	private DomainmodelGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalDomainmodelParser createParser(XtextTokenStream stream) {
		return new InternalDomainmodelParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "DomainModel";
	}

	public DomainmodelGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(DomainmodelGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}