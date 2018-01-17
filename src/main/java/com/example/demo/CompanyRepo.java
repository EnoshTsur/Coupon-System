package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends CrudRepository<Company, Integer>{
	/***
	 * Find Company by name
	 * @param name
	 * @return
	 */
	@Query("SELECT t FROM Company t where t.compName = :name") 
    Company findByName(@Param("name") String name);
	
	/***
	 * Update Company
	 * @param password
	 * @param email
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Company t SET t.password = :password , t.email = :email WHERE t.id = :id")
	void updateCompany(@Param("password") String password,@Param("email") String email, @Param("id") int id);

	
}
