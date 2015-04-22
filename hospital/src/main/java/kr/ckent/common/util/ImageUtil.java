/*
 * Copyright (c) 1999-2005 (주)ACTSoft. All rights reserved.
 */

package kr.ckent.common.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 이미지 관련 유틸리티 
 * 
 * @author YoungHo Lee
 *
 */
public class ImageUtil {
	
	public static final int ROTATE_RIGHT_90 = 1;
	public static final int ROTATE_RIGHT_180 = 2;
	public static final int ROTATE_RIGHT_270 = 3;

	/**
	 * 
	 */
	public ImageUtil() {
		super();
	}
	
	/**
	 * 이미지를 회전 시킴
	 * 
	 * @param imageSource 원본 이미지 버퍼
	 * @param rotations 회전 각도 (0 : 그대로, 1 : 오른쪽으로 90도 회전, 2 : 오른쪽으로 180도 회전, 3 : 오른쪽으로 270도 회전)
	 * @return 회전된 이미지 버퍼
	 * @throws Exception
	 */
	public static final BufferedImage rotate(BufferedImage imageSource, int rotations) throws Exception {
		int rotationValue = rotations % 4;
		
		int width = 0;
		int height = 0;
		
		int tx = 0;
		int ty = 0;
		
		if(rotationValue % 2 != 0) {
			width = imageSource.getHeight();
			height = imageSource.getWidth();
		}
		else {
			width = imageSource.getWidth();
			height = imageSource.getHeight();
		}
		
		if(rotations>1){
			ty = height;
		}
		
		if(rotations > 0 && rotations < 3){
			tx = width;
		}
		
		BufferedImage imageTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = imageTarget.createGraphics();
		
		AffineTransform transform = new AffineTransform();
		transform.concatenate(AffineTransform.getTranslateInstance(tx, ty));
		transform.concatenate(AffineTransform.getRotateInstance((rotations*0.5) * Math.PI));
		
		g2d.drawImage((Image)imageSource, transform, null);
		
		return imageTarget;
	}
	
	/**
	 * 이미지 사이즈 변경
	 * 
	 * @param imageSource 원본 이미지 버퍼
	 * @param targetWidth 변경할 이미지 넓이
	 * @param targetHeight 변경할 이미지 높이
	 * @return 사이즈가 변경된 이미지
	 * @throws Exception
	 */
	public static final BufferedImage resize(BufferedImage imageSource, int targetWidth, int targetHeight) throws Exception {
		double scaleX = (double)targetWidth / imageSource.getWidth();
		double scaleY = (double)targetHeight / imageSource.getHeight();
		
		BufferedImage imageTarget = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = imageTarget.createGraphics();

		AffineTransform transform = new AffineTransform();
		transform.concatenate(AffineTransform.getScaleInstance(scaleX, scaleY));

		g2d.drawImage((Image)imageSource, transform, null);
		
		return imageTarget;
	}	

}
