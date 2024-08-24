package com.rajesh.__Mini_Project.repositories;

import java.util.List;

import com.rajesh.__Mini_Project.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface EnquriRepo extends JpaRepository<Enquiry, Integer> {
	
	@Query(value =" select * from Enquiry_tbl where counsellor_Id=:counsollerId ",nativeQuery = true )
	public List<Enquiry> getEnquyriByCounsollerId(Integer counsollerId);

}
