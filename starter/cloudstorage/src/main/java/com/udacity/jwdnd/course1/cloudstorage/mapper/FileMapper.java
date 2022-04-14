package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> displayFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileByFileId(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByFileName(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId} AND filename = #{fileName}")
    File getFileByUserIdAndFileName(Integer userId, String fileName);


    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId} AND userid = #{userId}")
    void deleteFile(Integer fileId, Integer userId);
}
