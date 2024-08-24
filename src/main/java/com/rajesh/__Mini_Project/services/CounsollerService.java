package com.rajesh.__Mini_Project.services;


import com.rajesh.__Mini_Project.dto.DashBordResponse;
import com.rajesh.__Mini_Project.entity.Counsellor;

public interface CounsollerService {
	
	public boolean register(Counsellor counsellor);

	public Counsellor login(String email, String pwd);

	public DashBordResponse getDashboardInfo(Integer counsellorId);
	
	public Counsellor findByEmail(String email);

}
