**Assignment 1 - Hello World**
___

The obligatory "HelloWorld" program.

My implementation of the HelloWorld program utilizes Swing and the
JDBC API (intended for the Connector/J JDBC Type 4 Driver in particular.)

This project consists of 2 Java files:

* WordCarousel.java
* CarouselUpdater.Java

**WordCarousel**

  The WordCarousel class does most of the work. Extending JPanel, the
  WordCarousel class retrieves a list of words from a predefined SQL database
  and displays them as a spinning "carousel".

  WordCarousel overrides the paint() method such that every time paint() is
  called, its list of words are drawn directly unto the panel at an angle
  proportional to the time since the last paint() call.

**CarouselUpdater**

 CarouselUpdater is a compact implementation of ActionListener that holds a
 reference to a WordCarousel. Any time the CarouselUpdater's actionPerformed()
 method is called, it will call the corresponding WordCarousel's repaint()
 method. This is useful in conjunction with a Swing Timer for autonomous updating.
