package com.example.job;


import com.example.data.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Long> {
    List<Technology> findByArchivedFalse();

}
