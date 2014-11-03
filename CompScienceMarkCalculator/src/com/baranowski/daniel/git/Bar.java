package com.baranowski.daniel.git;

import java.awt.*;

/**
 * A bar that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Chris Phillips (adapted from the Square class of Kolling and Barnes)
 * @version 1.1 (October 2005) using an enum for Colour
 */ 

public class Bar
{
	private int height;
	int width;
	private int xPosition;
	private int yPosition;
	private Colour colour;
	private boolean isVisible;

	/**
	 * Create a new bar at default position with default colour.
	 */
	public Bar()
	{
		height = 30;
		width = 60;
		xPosition = 60;
		yPosition = 50;
		colour = Colour.RED;
		isVisible = false;
	}

	/**
	 * Make this bar visible. If it was already visible, do nothing.
	 */
	public void makeVisible()
	{
		isVisible = true;
		draw();
	}

	/**
	 * Make this bar invisible. If it was already invisible, do nothing.
	 */
	public void makeInvisible()
	{
		erase();
		isVisible = false;
	}

	/**
	 * Move the bar a few pixels to the right.
	 */
	public void moveRight()
	{
		moveHorizontal(20);
	}

	/**
	 * Move the bar a few pixels to the left.
	 */
	public void moveLeft()
	{
		moveHorizontal(-20);
	}

	/**
	 * Move the bar a few pixels up.
	 */
	public void moveUp()
	{
		moveVertical(-20);
	}

	/**
	 * Move the bar a few pixels down.
	 */
	public void moveDown()
	{
		moveVertical(20);
	}

	/**
	 * Move the bar horizontally by 'distance' pixels.
	 * @param distance  the horizontal distance to be moved
	 */
	public void moveHorizontal(int distance)
	{
		erase();
		xPosition += distance;
		draw();
	}

	/**
	 * Move the bar vertically by 'distance' pixels.
	 * @param distance  the vertical distance to be moved
	 */
	public void moveVertical(int distance)
	{
		erase();
		yPosition += distance;
		draw();
	}

	/**
	 * Slowly move the bar horizontally by 'distance' pixels.
	 * @param distance  the horizontal distance to be moved
	 */
	public void slowMoveHorizontal(int distance)
	{
		int delta;

		if(distance < 0) 
		{
			delta = -1;
			distance = -distance;
		}
		else 
		{
			delta = 1;
		}

		for(int i = 0; i < distance; i++)
		{
			xPosition += delta;
			draw();
		}
	}

	/**
	 * Slowly move the bar vertically by 'distance' pixels.
	 * @param distance  the vertical distance to be moved
	 */
	public void slowMoveVertical(int distance)
	{
		int delta;

		if(distance < 0) 
		{
			delta = -1;
			distance = -distance;
		}
		else 
		{
			delta = 1;
		}

		for(int i = 0; i < distance; i++)
		{
			yPosition += delta;
			draw();
		}
	}

	/**
	 * Change the size to the new size (in pixels). Size must be >= 0.
	 * @param newWidth   the desired width for the bar
	 * @param newHeight  the desired height for the bar
	 */
	public void changeSize(int newWidth, int newHeight)
	{
		erase();
		width = newWidth;
		height = newHeight;
		draw();
	}

	/**
	 * Change the colour.
	 * @param newColour  the  colour for the bar
	 */
	public void changeColour(Colour newColour)
	{
		colour = newColour;
		draw();
	}

	/**
	 * Draw the bar with current specifications on screen.
	 */
	private void draw()
	{
		if(isVisible) {
			Canvas canvas = Canvas.getCanvas();
			canvas.draw(this, colour.toString().toLowerCase(),
					new Rectangle(xPosition, yPosition, width, height));
			canvas.wait(10);
		}
	}

	/*
	 * Erase the bar on screen.
	 */
	private void erase()
	{
		if(isVisible) {
			Canvas canvas = Canvas.getCanvas();
			canvas.erase(this);
		}
	}
}
