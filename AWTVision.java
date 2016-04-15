package org.usfirst.frc.team1557.robot.vision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class AWTVision implements Runnable, VisionInterface {

	URL camera;
	JFrame window;
	BufferedImage frame;
	double angle;
	boolean active;
	int[] errors;
	int stepSize = 5;

	public boolean initCamera(String cam) {
		if (camera != null)
			return true;
		try {
			camera = new URL(cam);
			new Thread(this).start();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return camera != null;
	}

	public void run() {
		for (;;) {
			waitForActive();
			long time = System.currentTimeMillis();
			frame = null;
			fetchCameraFrame();
			if (frame != null) {
				findTarget();
				// drawGraph();
				// displayResults();
			}
			// System.out.println(System.currentTimeMillis() - time);
		}
	}

	synchronized void waitForActive() {
		while (!active) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized double getAngle() {
		return angle;
	}

	public synchronized void setAngle(double ang) {
		angle = ang;
	}

	public synchronized void startProcessing() {
		active = true;
		notifyAll();
	}

	public synchronized void stopProcessing() {
		active = false;
	}

	void fetchCameraFrame() {
		try {
			frame = ImageIO.read(camera);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void findTarget() {
		double width = frame.getWidth();
		double degreesPerPixel = FOV / width;
		setAngle((findBestLineOfSymmetry() * degreesPerPixel) - (FOV / 2));
	}

	void drawGraph() {
		Graphics g = frame.getGraphics();
		g.setColor(Color.BLACK);
		for (int i = 0; i < errors.length; i++) {
			g.fillOval(i, errors[i] / 1000, 5, 5);
		}
	}

	void displayResults() {
		if (window == null) {
			window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setSize(800, 600);
			window.setVisible(true);
		}
		window.getGraphics().drawImage(frame, 0, 0, null);
	}

	int findBestLineOfSymmetry() {
		int bestX = 0, bestError = Integer.MAX_VALUE;
		int start = frame.getWidth() / 4;
		int stop = frame.getWidth() * 3 / 4;

		// errors = new int[frame.getWidth()];

		for (int x = start; x < stop; x += stepSize) {
			int error = calcSymmetryError(x);
			// errors[x] = error;
			// System.out.println(error);
			if (error < bestError) {
				bestX = x;
				bestError = error;
			}
		}

		return bestX;
	}

	int calcSymmetryError(int x) {
		int error = 0;
		int height = frame.getHeight();
		int range = frame.getWidth() / 4;

		for (int y = 0; y < height; y += stepSize) {
			for (int dist = 1; dist <= range; dist += stepSize) {
				int left = frame.getRGB(x - dist, y);
				int right = frame.getRGB(x + dist, y);
				error += Math.abs(getRed(left) - getRed(right));
				error += Math.abs(getGreen(left) - getGreen(right));
				error += Math.abs(getBlue(left) - getBlue(right));
			}
		}

		return error;
	}

	int getRed(int color) {
		return (color >> 16) & 0xFF;
	}

	int getGreen(int color) {
		return (color >> 8) & 0xFF;
	}

	int getBlue(int color) {
		return (color) & 0xFF;
	}
}
