package com.flur.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;


/**
 * 文件操作工具类
 * 
 * @author wx
 *
 */
public class FileUtil {

	/**
	 * 判断上传文件是否是图片
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isImage(MultipartFile file) {
		String type = file.getContentType();
		return type.equals("image/png") || type.equals("image/jpeg") || type.equals("image/gif") || type.equals("image/x-png") || type.equals("image/pjpeg");
	}

	/**
	 * 读取图片宽高
	 * 
	 * @param bytes
	 * @return Map<String integer>
	 * @author huhd  修改haimanchen 2014年11月25日
	 * @version 1.0 2014年11月12日
	 */
	public static Map<String, Integer> getImageSize(byte[] bytes) {
		Map<String, Integer> res = new HashMap<String, Integer>();
		try {
			InputStream is = new ByteArrayInputStream(bytes);
			BufferedImage img = ImageIO.read(is);
			int w = img.getWidth();
			int h = img.getHeight();
			res.put("width", w);
			res.put("height", h);
		} catch (Exception e) {
			res.put("width", 0);
			res.put("height", 0);
		}
		return res;
	}

	/**
	 * 读取文件文本
	 * 
	 * @param file
	 * @return
	 */
	public static String getTextFromFile(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			return new String(inputStramToByte(in));
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 读取文件文本
	 * 
	 * @param file
	 * @return
	 */
	public static String getTextFromFile(File file, String charset) {
		try {
			FileInputStream in = new FileInputStream(file);
			return new String(inputStramToByte(in), charset);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 读取文件byte数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getBytesFromFile(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			return inputStramToByte(in);
		} catch (Exception e) {

		}
		return new byte[0];
	}

	/**
	 * 输入流转为byte数组
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] inputStramToByte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024 * 8];
		int rc = 0;
		while ((rc = inStream.read(buff)) != -1) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;

	}

	/**
	 * 修改图片大小
	 * 
	 * @param stream
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static byte[] resizeImage(InputStream stream, int width, int height) throws Exception {
		BufferedImage srcImage;
		srcImage = ImageIO.read(stream);
		if (width > 0 || height > 0) {
			srcImage = resize(srcImage, width, height);
		}
		// ImageIO.write(srcImage, "png", new File("F:/123.png"));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(srcImage, "jpg", bos);
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 用jpg编码图片文件
	 * 
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	public static byte[] encodeImage(InputStream stream) throws Exception {
		BufferedImage srcImage = ImageIO.read(stream);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(srcImage, "jpg", bos);
		bos.close();
		return bos.toByteArray();
	}

	private static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// 创建一个空的目标图片
		BufferedImage target = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
		// 将源文件缩放
		Image sourceImg = source.getScaledInstance(targetW, targetH, Image.SCALE_SMOOTH);
		// 将缩放后的图片绘制到目标图片上
		target.getGraphics().drawImage(sourceImg, 0, 0, null);
		return target;
	}

	/**
	 * 处理图片裁剪
	 * 
	 * @param cutX
	 * @param cutY
	 * @param width
	 * @param height
	 */
	public static byte[] executeImgCut(InputStream fileInput, int cutX, int cutY, int width, int height) throws Exception {
		// 创建一个空的目标图片
		BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 将源文件缩放
		Image sourceImg = ImageIO.read(fileInput);
		target.getGraphics().drawImage(sourceImg, 0, 0, width, height, cutX, cutY, cutX + width, cutY + height, null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(target, "jpg", bos);
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 删除目录下的子文件
	 * 
	 * @param file
	 */
	public static void deleteChildrenFiles(File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			FileUtil.deleteFiles(f);
		}
	}

	/**
	 * 删除文件，如果是文件夹，子文件一并删除
	 * 
	 * @param file
	 */
	public static void deleteFiles(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					FileUtil.deleteFiles(files[i]);
				}
			}
			file.delete();
		}
	}

	/**
	 * 创建文件所属的目录
	 * 
	 * @param filePath
	 */
	public static void makeFileDirs(String filePath) {
		String dir = filePath.substring(0, filePath.lastIndexOf(File.separator));
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}

	/**  
	 * 转换文件大小  
	 * */
	public static String FormentFileSize(Object size) {
		long fileS = Long.parseLong(size + "");
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	  * 添加图片水印
	  * @param file 上传的文件
	  * @param waterImg  水印图片路径，如：C://myPictrue//logo.png
	  * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	  * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	  * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 */
	public final static byte[] pressImage(MultipartFile file, String waterImg, int x, int y, float alpha) {
		try {
			Image image = ImageIO.read(file.getInputStream());
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);

			Image waterImage = ImageIO.read(new URL(waterImg)); // 水印文件

			int width_1 = waterImage.getWidth(null);
			int height_1 = waterImage.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			int widthDiff = width - width_1;
			int heightDiff = height - height_1;
			if (x < 0) {
				x = widthDiff / 2;
			} else if (x > widthDiff) {
				x = widthDiff;
			}
			if (y < 0) {
				y = heightDiff / 2;
			} else if (y > heightDiff) {
				y = heightDiff;
			}
			g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
			g.dispose();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", bos);
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加文字水印
	 * 
	 * @param pressText 水印文字， 如：中国证券网
	 * 
	 * @param fontName 字体名称， 如：宋体
	 * 
	 * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	 * 
	 * @param fontSize 字体大小，单位为像素
	 * 
	 * @param color 字体颜色
	 * 
	 * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * 
	 * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * 
	 * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 */
	public static byte[] pressText(MultipartFile file, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
		try {
			Image image = ImageIO.read(file.getInputStream());
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			int width_1 = fontSize * getLength(pressText);
			int height_1 = fontSize;
			int widthDiff = width - width_1;
			int heightDiff = height - height_1;
			if (x < 0) {
				x = widthDiff / 2;
			} else if (x > widthDiff) {
				x = widthDiff;
			}
			if (y < 0) {
				y = heightDiff / 2;
			} else if (y > heightDiff) {
				y = heightDiff;
			}

			g.drawString(pressText, x, y + height_1);
			g.dispose();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", bos);
			bos.close();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	      * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	      * @param text
	      * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	public static String getFileExtension(MultipartFile file) {
		return getFileExtension(file.getOriginalFilename());
	}

	public static String getFileExtension(String fileName) {
		int lastPeriod = fileName.lastIndexOf(".");
		if (lastPeriod == -1)
			return null;
		if ((lastPeriod + 1) > (fileName.length() - 1))
			return null;
		return fileName.substring(lastPeriod + 1);
	}

	
}
