package com.rajesh.__Mini_Project.repositories;

import com.rajesh.__Mini_Project.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CounsollerRepo extends JpaRepository<Counsellor, Integer>{

	public Counsellor findByEmailAndPwd(String email, String pwd);
	
	public Counsellor findByEmail(String email);
}
