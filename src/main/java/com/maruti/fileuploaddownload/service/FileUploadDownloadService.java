package com.maruti.fileuploaddownload.service;

import org.springframework.web.multipart.MultipartFile;

import com.maruti.fileuploaddownload.model.Attachment;

public interface FileUploadDownloadService {

	Attachment saveFileToDB(MultipartFile file);

	Attachment getFile(String fileid) throws Exception;

}
