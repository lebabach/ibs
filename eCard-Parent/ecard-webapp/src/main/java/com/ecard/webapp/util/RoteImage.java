package com.ecard.webapp.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class RoteImage {

	public static double getRotation(double rotation) {

		if (rotation == 90 || rotation == 180 || rotation == 270 || rotation == 0) {
			return rotation;
		}
		return getRotation((double) (rotation - 360));

	}

	public static byte[] roteImageWithArc(String data, double rotation) throws IOException {
		rotation = getRotation(rotation);
		byte[] fImage = null;
		switch ((int) rotation) {
		case 90:
			fImage = rote90(data);
			break;
		case 180:
			fImage = rote180(data);
			break;
		case 270:
			fImage = rote270(data);
			break;
		case 0:
			fImage = rote0(data);
			break;
		default:
			fImage = null;
			break;
		}
		return fImage;
	}

	public static byte[] rote180(String data) throws IOException {

		BufferedImage bufferedImage = base64StringToImg(data);
		AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
		tx.translate(-bufferedImage.getWidth(null), -bufferedImage.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		bufferedImage = op.filter(bufferedImage, null);
		return compressAndShow(resizeAdvange(bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight()), 0.3f);
	}

	public static byte[] rote270(String data) throws IOException {
		BufferedImage image = base64StringToImg(data);

		Image rotatedImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();
		// rote 90
		g2d.rotate(Math.toRadians(270));
		g2d.drawImage(image, -image.getWidth(), 0, null);
		g2d.dispose();
		// Random name
		// ImageIO.write(resize((BufferedImage)
		// rotatedImage,rotatedImage.getWidth(null),rotatedImage.getHeight(null)),
		// "jpg", new File("C:\\out270.jpg"));
		return compressAndShow(
				resizeAdvange((BufferedImage) rotatedImage, rotatedImage.getWidth(null), rotatedImage.getHeight(null)),
				0.3f);
	}

	public static byte[] rote90(String data) throws IOException {

		BufferedImage image = base64StringToImg(data);
		Image rotatedImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		Graphics2D g2d = (Graphics2D) rotatedImage.getGraphics();
		// rote 90
		g2d.rotate(Math.toRadians(90));
		g2d.drawImage(image, 0, -rotatedImage.getWidth(null), null);
		g2d.dispose();
		// Random name

		// ImageIO.write(resize((BufferedImage)
		// rotatedImage,rotatedImage.getWidth(null),rotatedImage.getHeight(null)),
		// "jpg", new File("C:\\out90.jpg"));
		return compressAndShow(
				resizeAdvange((BufferedImage) rotatedImage, rotatedImage.getWidth(null), rotatedImage.getHeight(null)),
				0.3f);

	}

	public static BufferedImage base64StringToImg(final String base64String) {
		try {
			return ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(base64String)));
		} catch (final IOException ioe) {
			throw new UncheckedIOException(ioe);
		}
	}

	public static byte[] rote0(String pathToImage) throws IOException {
		BufferedImage image = base64StringToImg(pathToImage);

		// Random name
		// ImageIO.write(resize((BufferedImage)
		// image,image.getWidth(null),image.getHeight(null)), "jpg", new
		// File("C:\\0.jpg"));
		return compressAndShow(resizeAdvange((BufferedImage) image, image.getWidth(null), image.getHeight(null)), 0.3f);
	}

	public static byte[] compressAndShow(BufferedImage image, float quality) throws IOException {
		// Get a ImageWriter for jpeg format.
		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
		if (!writers.hasNext())
			throw new IllegalStateException("No writers found");
		ImageWriter writer = (ImageWriter) writers.next();
		// Create the ImageWriteParam to compress the image.
		ImageWriteParam param = writer.getDefaultWriteParam();
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);
		// The output will be a ByteArrayOutputStream (in memory)
		ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
		ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(image, null, null), param);
		ios.flush(); // otherwise the buffer size will be zero!
		// From the ByteArrayOutputStream create a RenderedImage.
		//ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
		//RenderedImage out = ImageIO.read(in);
		return bos.toByteArray();
	}

	public static BufferedImage resizeAdvange(BufferedImage img, int Width, int Height) {
		int newWidth = Width;
		int newHeight = Height;
		/*double percentHeightWidth = 0;
		if (Width > Height) {
			percentHeightWidth = (double) Width / Height;
			newHeight = (int) (newWidth / percentHeightWidth);
		} else if (Width < Height) {
			percentHeightWidth =(double) Height / Width;
			newWidth = (int) (newHeight / percentHeightWidth);
		}*/

		@SuppressWarnings("static-access")
		Image tmp = img.getScaledInstance(newWidth, newHeight, img.TYPE_USHORT_565_RGB);
		BufferedImage dimg = new BufferedImage(newWidth, newHeight, img.getType());

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	
	public static String ConvertByteArrayToString64(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
		return sb.toString();
	}

}
