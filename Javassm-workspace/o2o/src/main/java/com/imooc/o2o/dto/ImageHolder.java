package com.imooc.o2o.dto;

import java.io.InputStream;

/**
* @author xf
* @version 2018年8月22日 下午4:31:19
*/
public class ImageHolder {
	private String imageName;
	private InputStream image;
	
	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}
	
	
}
