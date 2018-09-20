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
package org.etri.slice.core.inference;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.etri.slice.api.inference.WorkingMemory;
import org.etri.slice.commons.SliceContext;
import org.kie.api.runtime.Channel;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(publicFactory=false, immediate=true)
@Provides
@Instantiate
public class WorkingMemoryImpl implements WorkingMemory {

	private static Logger logger = LoggerFactory.getLogger(WorkingMemoryImpl.class);	
	
	@Requires
	private DroolsRuleEngine m_drools;
	
	private KieSession m_session;

	@Override
	public synchronized void insert(Object fact) {
		m_drools.getSessionLock().lock();
		m_session.insert(fact);
		insertFact(fact);
		m_drools.getSessionLock().unlock();
	}

	@Override
	public synchronized void delete(Object fact) {
		m_drools.getSessionLock().lock();
		FactHandle handle = m_session.getFactHandle(fact);
		m_session.delete(handle);
		m_drools.getSessionLock().unlock();
	}

	@Override
	public synchronized void update(Object before, Object after) {
		m_drools.getSessionLock().lock();
		FactHandle handle = m_session.getFactHandle(before);
		m_session.update(handle, after);
		m_drools.getSessionLock().unlock();
	}

	@Override
	public synchronized long getFactCount() {
		
		return m_session.getFactCount();
	}

	@Override
	public synchronized Collection<? extends Object> getObjects() {
		
		return m_session.getObjects();
	}

	@Override
	public synchronized Collection<? extends Object> getObjects(ObjectFilter filter) {
		
		return m_session.getObjects(filter);
	}

	@Override
	public synchronized void addServiceWrapper(String id, Object adaptor) {
		m_session.setGlobal(id, adaptor);
	}	

	@Override
	public synchronized void addEventAdaptor(String id, Channel adaptor) {
		m_session.registerChannel(id, adaptor);
	}
	
	@Validate
	public void start() {
		m_session = m_drools.getKieSession();
		logger.info("STARTED: SLICE Working Memory");
	}
	
	@Invalidate
	public void stop() {
		m_session.destroy();
	}
	
	private void insertFact(Object fact) {
		Class<?> cls = fact.getClass();		
		Field fieldlist[] = fact.getClass().getDeclaredFields();
		for ( Field field : fieldlist ) {
			if ( Modifier.isStatic(field.getModifiers()) ) {
				continue;
			}
			StringBuffer sbuff = new StringBuffer();
			sbuff.append(cls.getSimpleName());
			sbuff.append(".");
			sbuff.append(field.getName());
			field.setAccessible(true);
			Object obj = null;
			try {
				obj = field.get(fact);
			} 
			catch ( Exception ignored ) {	}
			if ( field.getType().isAnnotationPresent(SliceContext.class) ) {
				m_session.insert(obj);
				insertFact(obj);
			}			
		}		
	}	
}
