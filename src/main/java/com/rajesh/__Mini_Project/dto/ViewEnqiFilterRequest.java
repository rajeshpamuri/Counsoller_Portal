package com.rajesh.__Mini_Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewEnqiFilterRequest {
	
    private String courceName;
    private String classMode;
    private String enqStatus;
    
}
