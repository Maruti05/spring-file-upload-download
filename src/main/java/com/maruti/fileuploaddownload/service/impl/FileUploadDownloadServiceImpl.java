package com.maruti.fileuploaddownload.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.maruti.fileuploaddownload.model.Attachment;
import com.maruti.fileuploaddownload.repository.FileUploadDownloadRepo;
import com.maruti.fileuploaddownload.service.FileUploadDownloadService;

@Service
public class FileUploadDownloadServiceImpl implements FileUploadDownloadService{
	
	private  FileUploadDownloadRepo fileUploadDownloadRepo;
	
	

	public FileUploadDownloadServiceImpl(FileUploadDownloadRepo fileUploadDownloadRepo) {
		this.fileUploadDownloadRepo = fileUploadDownloadRepo;
	}
	
	



	@Override
	public Attachment saveFileToDB(MultipartFile file) {
		Attachment attachment=null;
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new Exception("File name contains Invalid path");
			}
			attachment=new Attachment(fileName, file.getContentType(), file.getBytes());
		} catch (Exception e) {
			
		}
		
		return fileUploadDownloadRepo.save(attachment);
	}





	@Override
	public Attachment getFile(String fileid) throws Exception {
		
		return fileUploadDownloadRepo.findById(fileid).orElseThrow(()-> new Exception("FileNot found with ID: "+fileid));
	}

}
