package com.example.bakuguide.repository;

import com.example.bakuguide.model.Landmark;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    List<Landmark> findTop4ByOrderByIdAsc();

    @Query("select distinct l.category from Landmark l order by l.category")
    List<String> findAllCategories();

    @Query("""
           select l from Landmark l
           where (:category is null or lower(l.category) = lower(:category))
             and (:q is null
                  or lower(l.name) like lower(concat('%', :q, '%'))
                  or lower(l.description) like lower(concat('%', :q, '%')))
           """)
    List<Landmark> search(@Param("category") String category,
                          @Param("q") String q,
                          Sort sort);
}
