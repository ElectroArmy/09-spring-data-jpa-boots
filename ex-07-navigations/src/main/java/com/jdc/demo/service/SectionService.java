package com.jdc.demo.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.demo.service.entity.Section;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
@Transactional(readOnly = true)
public class SectionService {

	@PersistenceContext
	private EntityManager em;
	
	public Section findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Section.class, id);
	}


	public Integer save(Section form) {
		// TODO Auto-generated method stub
		return em.merge(form).getId();
	}
	
	public Object search(Optional<LocalDate> dateFrom, Optional<String> teacher, Optional<String> course) {
		
		var sb = new StringBuffer("select s from Section s where 1 = 1");
		var params = new HashMap<String, Object>();
		
		if (dateFrom.isPresent()) {
			sb.append(" and s.startDate >= :from");
			params.put("from", dateFrom.get());
		}
		
		// Method reference for filter String
		if (teacher.filter(StringUtils::hasLength).isPresent()) {
			sb.append(" and lower(s.teacher.name) like lower(:teacher)");
			params.put("teacher", teacher.get().concat("%"));
		}
		
		if (course.filter(StringUtils::hasLength).isPresent()) {
			sb.append(" and lower(s.course.name) like lower(:course)");
			params.put("course", course.get().concat("%"));
		}
		
		var query = em.createQuery(sb.toString(), Section.class);
		
		for (var key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}
		
	
}
