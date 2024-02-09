
package com.molcon.henkelJpaWithMsSql.repo;

import com.molcon.henkelJpaWithMsSql.entity.JpaHenkel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


public interface JpaRepo extends JpaRepository<JpaHenkel, String> {
}

//It contains the full API of CrudRepository and PagingAndSortingRepository.
// For example, it contains flush(), saveAndFlush(), saveAllAndFlush(), deleteInBatch(), etc along with the methods that are available in CrudRepository.