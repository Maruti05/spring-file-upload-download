package com.maruti.fileuploaddownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maruti.fileuploaddownload.model.Attachment;

@Repository
public interface FileUploadDownloadRepo extends JpaRepository<Attachment, String>{

}
