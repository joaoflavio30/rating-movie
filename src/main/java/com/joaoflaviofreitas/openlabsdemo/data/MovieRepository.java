package com.joaoflaviofreitas.openlabsdemo.data;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
