package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFileByName(String fileName) {
        return fileMapper.getFileByFileName(fileName);
    }

    public File getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    public File getFileByUserIdAndFileName(Integer userId, String fileName) {
        return fileMapper.getFileByUserIdAndFileName(userId, fileName);
    }

    public void addFile(File file) {
        this.fileMapper.addFile(file);
    }

    public void deleteFile(Integer fileId, Integer userId) {
        fileMapper.deleteFile(fileId, userId);
    }

    public List<File> displayFiles(Integer userId) {
        return fileMapper.displayFiles(userId);
    }

    public Boolean isFileNameAvailable(String fileName) {
        return fileMapper.getFileByFileName(fileName) == null;
    }

    public Boolean isFileNameAvailable(Integer userId, String fileName) {
        return fileMapper.getFileByUserIdAndFileName(userId, fileName) == null;
    }
}
