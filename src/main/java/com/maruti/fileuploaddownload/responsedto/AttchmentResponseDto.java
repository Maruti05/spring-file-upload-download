package com.maruti.fileuploaddownload.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttchmentResponseDto {

	private String fileName;
	
	private String  downloadURL;
	
	private String fileType;
	
	private long fileSize;
	
}
