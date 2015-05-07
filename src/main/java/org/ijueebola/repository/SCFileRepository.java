package org.ijueebola.repository;

import org.ijueebola.model.SCFile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCFileRepository extends
		PagingAndSortingRepository<SCFile, Integer> {


}
