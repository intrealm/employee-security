package com.sapient.tms.dao.impl;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.tms.repository.RouteRepository;
import com.sapient.tms.repository.RouteSequenceRepository;
import com.sapient.tms.repository.SosEntityRepo;
import com.sapient.tms.user.entity.SOSEntity;

@Component
@Transactional
public class RaiseSosDaoImpl {
	
	@PersistenceContext
	private EntityManager em;

	public boolean createSos(String userName, int routeId) {
		try {
			SOSEntity entry = new SOSEntity();
			entry.setUserName(userName);
			entry.setRouteId(routeId);		
			entry.setResolved(false);
			entry.setRaisedOn(new Date(System.currentTimeMillis()));
			entry.setRaisedAt(new Time(System.currentTimeMillis()));
			save(entry);			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	private void save(SOSEntity entry) {
		em.persist(entry);
	}
}
