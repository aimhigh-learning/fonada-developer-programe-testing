package com.fonada.srana.test.repository;

import com.fonada.srana.test.entities.Thread2;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Thread2Repository extends PagingAndSortingRepository<Thread2, String> {

}
