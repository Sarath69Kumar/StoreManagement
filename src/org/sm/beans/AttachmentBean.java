package org.sm.beans;

import java.sql.Blob;

public class AttachmentBean 
{
	private Long userId;
	private Long fileId;
	private String fileName;
	private byte[] fileData;
	private String description;
	private Blob blbFile;
	
	public AttachmentBean()
	{
		super();
	}
	
	public AttachmentBean(Long fileId, String fileName, String description)
	{
		this.fileId 	 = fileId;
		this.fileName 	 = fileName;
		this.description = description;
	}
	
	
	public AttachmentBean(Long fileId, String fileName, Blob blbFile, String description)
	{
		this.fileId 	 = fileId;
		this.fileName 	 = fileName;
		this.blbFile 	 = blbFile;
		this.description = description;
	}

	
	public Long getUserId() 
	{
		return userId;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	
	public Long getFileId() 
	{
		return fileId;
	}
	public void setFileId(Long fileId) 
	{
		this.fileId = fileId;
	}

	
	public String getFileName() 
	{
		return fileName;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	
	public byte[] getFileData() 
	{
		return fileData;
	}
	public void setFileData(byte[] fileData) 
	{
		this.fileData = fileData;
	}
	
	
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Blob getBlbFile()
	{
		return blbFile;
	}
	public void setBlbFile(Blob blbFile)
	{
		this.blbFile = blbFile;
	}
}
