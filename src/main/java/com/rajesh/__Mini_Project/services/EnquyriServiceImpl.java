package com.rajesh.__Mini_Project.services;

import java.util.List;

import com.rajesh.__Mini_Project.dto.ViewEnqiFilterRequest;
import com.rajesh.__Mini_Project.entity.Counsellor;
import com.rajesh.__Mini_Project.entity.Enquiry;
import com.rajesh.__Mini_Project.repositories.CounsollerRepo;
import com.rajesh.__Mini_Project.repositories.EnquriRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import io.micrometer.common.util.StringUtils;
@Service
public class EnquyriServiceImpl implements EnquriService {
	
	@Autowired
	private CounsollerRepo counsollerRepo;
	
	@Autowired
	private EnquriRepo enquriRepo;

	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
		 Counsellor counsellor = counsollerRepo.findById(counsellorId).orElse(null);
		 if(counsellor.getCounsellorId()==null) {
			throw new Exception("No counsoler Found"); 
		 }
		 enq.setCounsellor(counsellor);
		 Enquiry enquiry = enquriRepo.save(enq);

		 if(enquiry.getEnqid()!=null) {
			return true; 
		 }
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		 List<Enquiry> enquyriByCounsollerId = enquriRepo.getEnquyriByCounsollerId(counsellorId);
		return enquyriByCounsollerId;
	}
	 
	@Override
	public Enquiry getEnquriyById(Integer enqId) {
		 return enquriRepo.findById(enqId).orElse(null);
	    
	}

	@Override
	public List<Enquiry> getEnquiresWithFilter(ViewEnqiFilterRequest filterReq, Integer counsellorId) {
		
		Enquiry enq=new Enquiry();
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassMode(filterReq.getClassMode());
		}
		if(StringUtils.isNotEmpty(filterReq.getCourceName())) {
			enq.setCourceName(filterReq.getCourceName());
		}
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
			enq.setEnqStatus(filterReq.getEnqStatus());
		}
		Counsellor orElse = counsollerRepo.findById(counsellorId).orElse(null);
		enq.setCounsellor(orElse);
		Example<Enquiry> of = Example.of(enq);
		List<Enquiry> enqList = enquriRepo.findAll(of);
		
		return enqList;
	}

	


}
