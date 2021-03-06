package com.fleet.thieuduong.fleetapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fleet.thieuduong.fleetapp.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query(value = "select column_name from information_schema.columns where table_name = '#{#entityName}' order by ordinal_position", nativeQuery=true)
	public List<String> getColumnName();
}
