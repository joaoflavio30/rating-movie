package com.joaoflaviofreitas.openlabsdemo.data;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
