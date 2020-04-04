package com.helpal.model.coord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordRepository extends JpaRepository<Coord, String>, CoordFilterRepository {
}
