/**
 *
 * Copyright (c) 2017-2017 SLICE project team (yhsuh@etri.re.kr)
 * http://slice.etri.re.kr
 *
 * This file is part of The SLICE components
 *
 * This Program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with The SLICE components; see the file COPYING.  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.etri.slice.api.rule;

import java.io.IOException;
import java.util.Collection;

public interface RuleSet {
	
	String getId();
	
	void setRuleSetHeaer(RuleSetHeader header);	
	
	RuleSetHeader getRuleSetHeader();
	
	void addRule(Rule rule) throws RuleExistsException;
	
	Rule getRule(String id) throws RuleNotFoundException;
	
	Rule removeRule(String id) throws RuleNotFoundException;
	
	Rule replaceRule(Rule newRule) throws RuleNotFoundException;
	
	Collection<Rule> getRules();
	
	void addDeclare(Declare declare);
	
	Collection<Declare> getDeclares();
		
	void saveToFile() throws IOException;
}
