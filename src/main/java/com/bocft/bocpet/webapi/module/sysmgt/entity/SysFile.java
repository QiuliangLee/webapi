package com.bocft.bocpet.webapi.module.sysmgt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件信息表
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-14
 */
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件桶,即所在目录
     */
    private String bucket;


    /**
     * 文件名称（上传时候的文件名）
     */
    private String fileOriginName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件大小kb
     */
    private Long fileSizeKb;

    /**
     * 文件大小信息，计算后的
     */
    private String fileSizeInfo;

    /**
     * 存储到服务器上的名称（文件唯一标识id）
     */
    private String fileObjectName;

    /**
     * 存储路径
     */
    private String filePath;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建用户
     */
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSizeKb() {
        return fileSizeKb;
    }

    public void setFileSizeKb(Long fileSizeKb) {
        this.fileSizeKb = fileSizeKb;
    }

    public String getFileSizeInfo() {
        return fileSizeInfo;
    }

    public void setFileSizeInfo(String fileSizeInfo) {
        this.fileSizeInfo = fileSizeInfo;
    }

    public String getFileObjectName() {
        return fileObjectName;
    }

    public void setFileObjectName(String fileObjectName) {
        this.fileObjectName = fileObjectName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "SysFile{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", fileOriginName='" + fileOriginName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", fileSizeKb=" + fileSizeKb +
                ", fileSizeInfo='" + fileSizeInfo + '\'' +
                ", fileObjectName='" + fileObjectName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
