package com.fonada.srana.test.repository;

import com.fonada.srana.test.entities.Thread2;
import com.fonada.srana.test.entities.Thread3;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Thread3Repository extends PagingAndSortingRepository<Thread3, String> {

}
