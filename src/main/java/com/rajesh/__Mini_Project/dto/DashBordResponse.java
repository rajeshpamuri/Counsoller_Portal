package com.rajesh.__Mini_Project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBordResponse {
	private Integer totalEnq;
	private Integer openEnq;
	private Integer enrollledEnq;
	private Integer lostEnq;

}
