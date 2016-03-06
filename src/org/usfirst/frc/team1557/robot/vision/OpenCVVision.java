package org.usfirst.frc.team1557.robot.vision;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class OpenCVVision implements VisionInterface {
	// Many thanks to Exploding Bacon (1902) for the help they provided.
	// :^)

	private static boolean hasInitLibrary = false;

	private URL address = null;
	private boolean shouldRun = false;
	private boolean hasInitCamera = false;
	private double angleOff = 0;
	/**
	 * Not sure if this is the best way to calculate.
	 */
	private final double degreesPerPixel = (FOV) / (CAMERA_RESOLUTION[0]);

	static {
		if (!hasInitLibrary) {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			hasInitLibrary = true;
			System.out.println("OpenCV has been loaded");
		}
	}

	private Thread processingThread = new Thread(new Runnable() {

		@Override
		public void run() {
			while (shouldRun) {
				if (hasInitCamera && hasInitLibrary) {
					// Processing code
					Mat startImage = getImageInMat();
					// TODO: Canny operation?
					List<MatOfPoint> contours = findContours(startImage, new Scalar(0, 0, 0),
							new Scalar(360, 255, 255));
					List<Rect> rects = filterRectsBySize(0, 0, 1000, 1000, contours);
					Rect foundRect = findTargetWithoutSize(rects);
					setAngle(findAngle(findError(foundRect)[0]));
					// End processing code
				} else {
					System.out.println("Something has not been initialized." + "    Camera:" + hasInitCamera + "    "
							+ "Library:" + hasInitLibrary);
				}
			}

		}
	}, "OpenCV Processing Thread");

	@Override
	public boolean initCamera(String address) {
		try {
			this.address = new URL(address);
			hasInitCamera = true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			hasInitCamera = false;
		}
		if (hasInitCamera) {
			System.out.println("The Camera has been initialized.");
		} else {
			System.out.println("Obviously something went wrong with camera initialization.");
		}
		return hasInitCamera;
	}

	/**
	 * 
	 * @param xPosition
	 * @return
	 */
	protected double findAngle(double xPosition) {
		xPosition *= degreesPerPixel;
		return xPosition;
	}

	@Override
	public synchronized double getAngle() {
		return angleOff;
	}

	@Override
	public synchronized void setAngle(double d) {
		angleOff = d;
	}

	@Override
	public void startProcessing() {
		shouldRun = true;
		if (!processingThread.isAlive()) {
			processingThread.start();
		}
	}

	@Override
	public void stopProcessing() {
		shouldRun = false;
	}

	private Mat getImageInMat() {
		BufferedImage imageFromCamera = new BufferedImage(320, 240, BufferedImage.TYPE_3BYTE_BGR);
		try {
			imageFromCamera = ImageIO.read(address);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertBufferedImageToMat(imageFromCamera);
	}

	private Mat convertBufferedImageToMat(BufferedImage b) {
		Mat convertedMat = new Mat(b.getHeight(), b.getWidth(), CvType.CV_8UC3);
		byte[] data = ((DataBufferByte) b.getRaster().getDataBuffer()).getData();
		convertedMat.put(0, 0, data);
		return convertedMat;
	}

	/**
	 * The scalars used are in HSV format. H ranges from 0-360(rather than
	 * 0-180); S & V range from 0-255.
	 * 
	 * @param imageInMat
	 * @param min
	 *            A Scalar representing the lower bound.
	 * @param max
	 *            A Scalar representing the upper bound
	 * @return
	 */
	private List<MatOfPoint> findContours(Mat imageInMat, Scalar min, Scalar max) {
		List<MatOfPoint> contours = new ArrayList<>();
		/*
		 * Puts the hue values back into 0-360 format like most HSV formats.
		 */
		min.val[0] = min.val[0] * 2;
		max.val[0] = max.val[0] * 2;
		Imgproc.cvtColor(imageInMat, imageInMat, Imgproc.COLOR_BGR2HSV);
		Core.inRange(imageInMat, min, max, imageInMat);
		Imgproc.findContours(imageInMat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		return contours;
	}

	private List<Rect> filterRectsBySize(double minHeight, double minWidth, double maxHeight, double maxWidth,
			List<MatOfPoint> contours) {
		List<Rect> rects = new ArrayList<>();
		for (MatOfPoint mop : contours) {
			Rect r = Imgproc.boundingRect(mop);
			if ((r.height > minHeight && r.width > minWidth) && (r.height < maxHeight && r.width < maxWidth)) {
				rects.add(r);
			}
		}
		return rects;
	}

	/**
	 * Locates the rect whose size is closest to the expected size.
	 * 
	 * @param expectedSize
	 * @param rects
	 * @return
	 */
	private Rect findTargetWithSize(double[] expectedSize, List<Rect> rects) {
		double smallestError = Double.MAX_VALUE;
		Rect foundRect = new Rect();
		for (Rect r : rects) {
			if ((Math.abs(expectedSize[0] - r.size().width)
					+ Math.abs(expectedSize[1] - r.size().height)) < smallestError) {
				foundRect = r;
				smallestError = (Math.abs(expectedSize[0] - r.size().width)
						+ Math.abs(expectedSize[1] - r.size().height));
			}
		}
		return foundRect;
	}

	/**
	 * Locates the largest rect.
	 * 
	 * @param rects
	 * @return
	 */
	private Rect findTargetWithoutSize(List<Rect> rects) {
		double mostArea = 0;
		Rect foundRect = new Rect();
		for (Rect o : rects) {
			if (o.area() > mostArea) {
				mostArea = o.area();
				foundRect = o;
			}
		}
		return foundRect;
	}

	/**
	 * Finds the distance between the center of the given rect to the center of
	 * the camera. Arranged in quadrants like a graph.
	 * 
	 * @param r
	 * @return
	 */
	private double[] findError(Rect r) {
		double xError = 0, yError = 0;
		xError = ((CAMERA_RESOLUTION[0] / 2) - (r.x + r.width / 2)) * -1;
		yError = ((CAMERA_RESOLUTION[1] / 2) - (r.y + r.height / 2));
		return new double[] { xError, yError };
	}

	private Mat blurImage() {
		// TODO: Blur Image
		return null;
	}

	private List<MatOfPoint> filterContoursByArea(double minArea, double maxArea, List<MatOfPoint> contours) {
		// TODO: Filter contours by area
		return contours;
	}

	private List<Rect> filterRectByArea(double minArea, double maxArea, List<Rect> rects) {
		// TODO: Filter rects by area
		return rects;
	}
}
