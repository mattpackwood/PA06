/* Matt Packwood, Orchard Ridge Campus, Monday Evening Class, Fall Semester 2003
 *
 * PA06: Modify PA05 with repetition logic from C8, selection logic and
 * ActionListener event handling from C7 and Adjustment Listener event handling
 * from C6 to animate a jack O' lantern (JOL) display per specs on the screen
 * of the computer figure from previous PExs
 *
 * Use Dimension class and getSize/setSize methods to adjust applet screen
 * to spec requirements of shape and minimal size.  
 * use loops for animation display and frame delay.
 * use events and selection logic to allow user to control animation
 * start/stop, cycles and frame delay;
 */
import java.awt.*;
import java.applet.*;
import java.awt.event.*; // event lib
 
public class CmptrL6 extends Applet
		implements AdjustmentListener, ActionListener { // dual event handling
	Scrollbar cycleSB, delaySB; // user loop controls
	Button goB; // animate button
	boolean goF= false; // animate switch
	int bX, bY, bS; // computer base vars
	int qS, hS, dS; // CPU size ratio vars	
	int msX, msY; // monitor screen UL base (screen origin)	
	int msW, msH; // monitor screen dims 	
	int jX, jY, jS; // JOL base vars	
	int sW, sH; // applet screen dims	
	int wH= 54; // space for widgets at top of screen...	
	int cycleV= 1, delayV= 1; // init to mid control vals	
	final int MIN_SZ= 288; // min screen sz	
	final int MIN_JOL_SZ= 27; // min pumpkin sz  	
	Graphics g; // global ref for Graphic obj
public void init ( ) {
	Dimension size= getSize ( ); // get screen size
	sW= size.width; sH= size.height; // set screen constraints
// force min screen dim here.....
// force screen width >= screen height here....
	if (sW < MIN_SZ) // insure min screen dims scrn
		sW= MIN_SZ; 
	if (sH < MIN_SZ) 
		sH= MIN_SZ;
	
	setSize (sW, sH); // adj screen to changes (if any....)
	bS= (sH - wH -36 ) / 5; // max CPU size....
	bX= 18; // 1/4 inch from left edge
	bY= sH- bS- 18; // 1/4 inch above bot edge
	setBackground (Color.white); // BG color
	goB= new Button ("Animate");
	add (goB);
	goB.addActionListener (this);
	add (new Label ("Cycles>>")); // cylce control widget  
	cycleSB= new Scrollbar (Scrollbar.HORIZONTAL, 1, 1, 1, 10);
	add (cycleSB);
	cycleSB.addAdjustmentListener (this);
 	add (new Label ("Delay>>")); // frame delay control widget  
	delaySB= new Scrollbar (Scrollbar.HORIZONTAL, 1, 1, 1, 4);
	add (delaySB);
	delaySB.addAdjustmentListener (this);
  	}
public void paint (Graphics s) {
	g= s; // set Graphic obj ref global
	showStatus ("cycles="+ (cycleV * 10) + ", delay="+delayV+ ", sW="+ sW+ ", sH= "+sH); // feedback per specs...);
	dsplyCmptr ( ); // implied monitor screen reset
	dsplyMaxJOL ( ); // dsply max size JOL
	if (goF) { // false on initial paint & browser repaints
		animate ( ); // run animation cycles
		goF= false; // block animate on browser repaints
		dsplyMaxJOL ( ); // static dsply after animate run
		}
	}
private void dsplyCmptr ( ) { // PA4
	calcRatios ( );
//PA4 computer display code here..
	dsplyDsply ( ); 
	g.setColor (Color.yellow);
	g.fillRect (bX, bY-bS, dS+dS+bS, bS); // CPU
	g.fillRect (bX, bY, dS+dS, bS); // Keyboard
	g.fillOval (bX+dS+dS+qS, bY+qS, hS, hS); // Mouse
	g.setColor (Color.black);
	g.drawRect (bX, bY-bS, dS+dS+bS, bS); // CPU outline
	g.drawRect (bX, bY, dS+dS, bS); // Keyboard outline
	g.drawOval (bX+dS+dS+qS, bY+qS, hS, hS); // Mouse outline
	g.drawLine (bX+dS+dS, bY+hS, bX+dS+dS+qS, bY+hS); // Mouse cord
	g.fillRect (bX+dS+hS, bY-hS-qS, dS, qS); // DVD slot
	g.fillRect (bX+qS, bY+qS, dS+bS, hS); // Keys
	g.fillArc (bX+dS+dS+qS, bY+qS, hS, hS, 45, 90); // Mouse 1
	g.fillArc (bX+dS+dS+qS, bY+qS, hS, hS, 225, 90); // Mouse 2
	}
private void calcRatios ( ) {
// PA4 code here...
	qS= Math.round (bS/4.0f); // calc ratio vals
	hS= Math.round (bS/2.0f); // calc ratio vals
	dS= Math.round (bS*2.0f); // calc ratio vals
// calc monitor screen msW,msH,msX,msY here.... 
	msW= dS+bS+hS;
	msH= dS+hS;
	msX= bX+hS+qS;
	msY= bY-dS-bS-hS-qS;
	}
private void dsplyDsply ( ) { // PEx4 w/black screen
// PEx4 code w/change to black screen
	g.setColor (Color.yellow);
	g.fillRect (bX+hS, bY-dS-dS, dS+dS, dS+bS); // Monitor
	g.setColor (Color.black);
	g.drawRect (bX+hS, bY-dS-dS, dS+dS, dS+bS); // Monitor Outline
	g.setColor (Color.black);
	g.fillRect (bX+hS+qS, bY-dS-bS-hS-qS, dS+bS+hS, dS+hS); // Screen
	}
private void dsplyMaxJOL ( ) {
	jS= msH; // set to max size JOL vals...
	jX= hS;
	jY= 0;
	dsplyJOL ( );
	}
private void dsplyJOL ( ) { // display JOL within monitor screen
// define/compute JOL work ratio vars here....
	int jS2= Math.round (jS*0.2f);
	int jS3= Math.round (jS*0.3f);
	int jS35=Math.round (jS*0.35f);
	int jS4= Math.round (jS*0.4f);
	int jS6= Math.round (jS*0.6f);
// JOL display code  here..see design layout..
		g.setColor (Color.red);
		g.fillOval (msX+jX, msY+jY, jS, jS);
		g.setColor (Color.yellow);
		g.fillArc (msX+jX+jS2, msY+jY+jS2, jS3, jS4, 135, 90);
		g.fillArc (msX+jX+jS2+jS3, msY+jY+jS2, jS3, jS4, -45, 90);
		g.fillArc (msX+jX+jS35, msY+jY+jS3-jS2, jS3, jS3+jS2, 240, 60);
		g.fillArc (msX+jX+jS2, msY+jY+jS4+jS3, jS4+jS2, jS2, -45, 270);
//	if (room below && to right of JOL)
//		random select and display msg String w/white text
		if ((jY+jS+9)<=msH)
		{
		g.setColor (Color.white);
		int message = (int) ((Math.random()*3)+1 );
		switch (message) { // no breaks == fall-thru
			case 4: 	g.drawString ("BANG!", msX+jX, msY+jY+jS+9);
						break;
			case 3: 	g.drawString ("CRASH!", msX+jX, msY+jY+jS+9);   
						break;
			case 2: 	g.drawString ("BOOM!", msX+jX, msY+jY+jS+9);
						break;			
			case 1: 	g.drawString ("CLANG!", msX+jX, msY+jY+jS+9);
			} // end switch; minus values ignored
		}
	}
private void animate ( ) {	
// animate logic code here..see logic diagram		}
		dsplyDsply ( ); // erase image in prep for next draw
		for ( int animateCount =1 ; animateCount<= cycleV*10; animateCount++) { // start display loop...
			jS= (int) ( ((msH-MIN_JOL_SZ) * Math.random ( ) ) + MIN_JOL_SZ ) ;
			jX= (int) ( (msW-jS) * Math.random ( ) );
			jY= (int) ( (msH-jS) * Math.random ( ) );
			dsplyJOL ( ); // draw JOL
			delay ( ); // delay for visibility
			dsplyDsply ( ); // erase image in prep for next draw
			} // end display loop
	}
private void delay ( ) { // delay loop for visibility; delayV in  secs
	long startTime= System.currentTimeMillis ( ); // in 1/1000 sec units 
	while ((System.currentTimeMillis ( )- startTime) < (delayV* 1000))  
		continue; // stop when time diff >= delayLmt
	}
public void actionPerformed (ActionEvent e) { // animate button event
	goF= true; // set to animate
	repaint ( ); // update display
	}
public void adjustmentValueChanged (AdjustmentEvent e) { // SB events
	cycleV= cycleSB.getValue ( ); // new cycle value
	delayV= delaySB.getValue ( ); // new delay value
	showStatus ("cycles= "+ cycleV* 10+ ", delay= "+ delayV); // update cntrl dsply
	}
}
