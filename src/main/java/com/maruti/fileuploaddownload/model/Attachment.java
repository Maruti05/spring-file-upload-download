package com.maruti.fileuploaddownload.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	private String fineName;
	
	private String fileType;
	
	@Lob
	private byte[] data;

	public Attachment(String fineName, String fileType, byte[] data) {
		super();
		this.fineName = fineName;
		this.fileType = fileType;
		this.data = data;
	}
	
	
}
