package com.project.shopapp.services.helper.file_management;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileValidationService {
    public static ResponseEntity<?> validFile(MultipartFile file, String contentType) {
        if (file == null) {
            return ResponseEntity.badRequest().body(FileValidateStatus.NULL);
        } else if (file.getSize() == 0){
            return ResponseEntity.badRequest().body(FileValidateStatus.EMPTY);
        } else if (file.getSize() > 10*1024){
            return ResponseEntity.badRequest().body(FileValidateStatus.OVERSIZED);
        }
        else {
            String fileContentType = file.getContentType();
            if (fileContentType == null || !fileContentType.startsWith(contentType)){
                return ResponseEntity.badRequest().body(FileValidateStatus.INVALID_CONTENT_TYPE);
            }
            else {
                return ResponseEntity.ok().body(FileValidateStatus.VALID);
            }
        }
    }
}

class FileValidateStatus {
    protected final static String NULL = "Cannot resolve a null file";
    protected final static String EMPTY = "Cannot resolve an empty file";
    protected final static String OVERSIZED = "Cannot resolve a file > 1MB";
    protected final static String INVALID_CONTENT_TYPE = "Cannot resolve a file with invalid content type";
    protected final static String VALID = "VALID";
}
