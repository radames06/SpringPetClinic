package com.jd.petclinic.services;

import com.jd.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitService extends CrudService<Visit, Long> {
}
