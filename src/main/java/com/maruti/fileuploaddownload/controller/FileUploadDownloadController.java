package com.maruti.fileuploaddownload.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maruti.fileuploaddownload.model.Attachment;
import com.maruti.fileuploaddownload.responsedto.AttchmentResponseDto;
import com.maruti.fileuploaddownload.service.FileUploadDownloadService;

@RestController
@RequestMapping("fileOp/api/v1")
public class FileUploadDownloadController {

	private FileUploadDownloadService fileUploadDownloadService;

	public FileUploadDownloadController(FileUploadDownloadService fileUploadDownloadService) {
		this.fileUploadDownloadService = fileUploadDownloadService;
	}
	
	@PostMapping(value = "upload")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
		
		Attachment attachment= fileUploadDownloadService.saveFileToDB(file);
		String downloadURL=ServletUriComponentsBuilder.fromCurrentContextPath().path("/fileOp/api/v1/download/")
				.path(attachment.getId())
				.toUriString();
		return ResponseEntity.ok(new AttchmentResponseDto(attachment.getFineName(), downloadURL, attachment.getFileType(), file.getSize()));
		
	}
	
	@GetMapping("/download/{fileid}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileid) throws Exception{
		Attachment attachment=fileUploadDownloadService.getFile(fileid);
		System.err.println(attachment.getFineName());
		return ResponseEntity.ok()
							.contentType(MediaType.parseMediaType(attachment.getFileType()))
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+attachment.getFineName()+"\"")
							.body(new ByteArrayResource(attachment.getData()));
		
	}
}
