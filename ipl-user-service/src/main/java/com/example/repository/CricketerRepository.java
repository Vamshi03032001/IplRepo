package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Cricketer;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer,Integer> {
	
	@Query(value="select * from cricketer c where c.team_team_id=:teamId",nativeQuery=true)
	List<Cricketer> findByTeamId(@Param("teamId") int teamId);

}
