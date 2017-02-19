package me.tyler.carousel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CarouselUpdater implements ActionListener
{
	WordCarousel carousel;
	
	public CarouselUpdater(WordCarousel carousel)
	{
		this.carousel = carousel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.carousel.repaint();
	}
}