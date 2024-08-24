package com.rajesh.__Mini_Project.services;

import com.rajesh.__Mini_Project.dto.ViewEnqiFilterRequest;
import com.rajesh.__Mini_Project.entity.Enquiry;

import java.util.List;



public interface EnquriService {

	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception;

	public List<Enquiry> getAllEnquiries(Integer counsellorId);

	public List<Enquiry> getEnquiresWithFilter(ViewEnqiFilterRequest filterReq, Integer counsellorId);

	public Enquiry getEnquriyById(Integer enqId);
}
