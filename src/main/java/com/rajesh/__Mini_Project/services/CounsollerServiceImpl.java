package com.rajesh.__Mini_Project.services;

import java.util.List;
import java.util.stream.Collectors;

import com.rajesh.__Mini_Project.dto.DashBordResponse;
import com.rajesh.__Mini_Project.entity.Counsellor;
import com.rajesh.__Mini_Project.entity.Enquiry;
import com.rajesh.__Mini_Project.repositories.CounsollerRepo;
import com.rajesh.__Mini_Project.repositories.EnquriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CounsollerServiceImpl implements CounsollerService {
	
	@Autowired
   private CounsollerRepo counsollerRepo;
	
	@Autowired
	private EnquriRepo enquriRepo;
 
	@Override
	public boolean register(Counsellor counsellor) {
		
		Counsellor save = counsollerRepo.save(counsellor);
		if(null!=save.getEmail()) {
			return true;
		}
		return false;
	}



	@Override
	public Counsellor login(String email, String pwd) {
		Counsellor counsoller= counsollerRepo.findByEmailAndPwd(email, pwd);
		return counsoller;
	}

	@Override
	public DashBordResponse getDashboardInfo(Integer counsellorId) {
		DashBordResponse bordResponse=new DashBordResponse();
		
		List<Enquiry> enquyriByCounsollerId = enquriRepo.getEnquyriByCounsollerId(counsellorId);
		int totalEnquyris = enquyriByCounsollerId.size();
		
		int enrolledEnqs = enquyriByCounsollerId.stream().filter(e->e.getEnqStatus().equalsIgnoreCase("Enrolled"))
		.collect(Collectors.toList())
		.size();
		
		int lostEnqs = enquyriByCounsollerId.stream().filter(r->r.getEnqStatus().equalsIgnoreCase("Lost"))
		.collect(Collectors.toList())
		.size();
		
		int openEnqs = enquyriByCounsollerId.stream().filter(d->d.getEnqStatus().equalsIgnoreCase("Open"))
		.collect(Collectors.toList())
		.size();
		
		bordResponse.setTotalEnq(totalEnquyris);
		bordResponse.setEnrollledEnq(enrolledEnqs);
		bordResponse.setOpenEnq(openEnqs);
		bordResponse.setLostEnq(lostEnqs);
	
		return bordResponse;
	}

	@Override
	public Counsellor findByEmail(String email) {
		Counsellor findByEmail = counsollerRepo.findByEmail(email);
		return findByEmail;
	}

}
