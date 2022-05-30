package com.fonada.srana.test.repository;

import com.fonada.srana.test.entities.Thread1;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Thread1Repository extends PagingAndSortingRepository<Thread1, String> {

}
