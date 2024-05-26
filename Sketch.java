import processing.core.PApplet;

public class Sketch extends PApplet {
  //related arrays for the (x,y) coordinates of the snowflakes 
  float [] snowX = new float[15];
  float [] snowY = new float[15];
  int snowDiameter = 20; 

  //related variables for (x,y) coordinates of the blue player circle 
  float circleX = 50; 
  float circleY = 50; 

  //lives 
  int playerLives = 3;

  //delcare clue player WASD variables 
  boolean wPressed = false; 
  boolean aPressed = false;
  boolean sPressed = false; 
  boolean dPressed = false; 

  //declare snow speed variables 
  boolean upPressed = false; 
  boolean downPressed = false; 

  boolean [] ballHideStatus;
	
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
    ballHideStatus = new boolean[snowX.length];
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
    ballHideStatus = new boolean [snowX.length];

    //generate random x and y coordinates for the snowflakes 
    for (int i=0; i< snowX.length; i++) {
      snowX[i] = random (width);
      snowY[i] = random(height);
  }
}


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
  //background colour
  background (0); 

  //Draw snow
  snow();

  //draw rectangles indiating player lives 
  playerLives();

  //if player still has lives, blue player circle still appears 
  if (playerLives > 0) {
  bluePlayerCircle();
  }

  //move player circle 
  if (wPressed) {
    circleY--;
  }
  if(sPressed) {
    circleY++;
  }
  if(aPressed) {
    circleX--;
  }
  if(dPressed) {
    circleX++;
  }

  //snowfalkes dissapear when they are blocked 
  mouseClicked();

  
  // define other methods down here.
  public void snow() {
    for (int i = 0; i < snowX.length; i++) {
      if(!ballHideStatus[i]) {
        ellipse(snowX[i], snowY[i], 20, 20);

        if(dist(snowX[i], snowY[i], circleX, circleY) < 35) {
          playerLives--;
          snowY[i] = 0
          snowX[i] = random(width); 
          ballHideStatus[i] = true; 
        }
      }
    }
  
  
    fill (255);
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i]) 
      circle (snowX[i], snowY[i], snowDiameter);

      //if down arrow is pressed = faster 
      //if up arrow is pressed = slower 
      //if no arroe is pressed = normal 
      if (downPressed) {
        snowY[i] += 4; 
      }
      else if (upPressed) {
        snowY[i] += 0.5; 
      else {
        snowY[i] += 2; 
      }

      //reset snowflakes
      if(snowY [i] > height){
        snowY[i] = 0;
        ballHideStatus[i] = false;
      }
    }
  }

  public void playerLives () {
    for (int i = 0; i < playerLives; i++){
      float x = width - 50 - i * 50; 
      float y = 20; 
      fill (255); 
      rect (x,y, 30, 30);

} 

// game ends when all lives are losst 
if (playerLives <= 0) {
  background (255);
  }
}
    

/**
 * Draws a blue player cirlce 
 * @author: A. Surees
 */
public void bluePlayerCircle() {
  //blue player cirlce 
  fill (214, 245, 255); //blue 
  ellipse (circleX, circleY, 50, 50); 
}

/**
 * if up arroe is pressed, snow falls faster 
 * if down arrow is pressed, snow falls slower 
 * if the w key is pressed the boue player circle moves up 
 * if the a key is pressed the blue player circle moves left 
 * if the s key is pressed the blue player circle moves down 
 * if the d key is pressed the blue player circle moves right 
 * @author: A. Surees
 */
public void keyPressed () {
  circleX = constrain (circleX, 0, width); 
  circleY = constrain (circleY,0,height);

  if(keyCode == UP) {
    upPressed = true;
  }
  else if (keyCode == DOWN) {
    downPressed = true;
  }

  if (key == 'w' || key == 'W') {
    wPressed = true; 
  } 
  if (key == 'a' || key == 'A') {
    aPressed = true; 
  } 
  if (key == 's' || key == 'S') {
    sPressed = true; 
  }
  if (key == 'd' || key == 'D') {
    dPressed = true; 
  } 
}

public void keyReleased () {
  if(keyCode == UP) {
    upPressed = false;
  }
  else if (keyCode == DOWN) {
    downPressed = false;
  }

  if (key == 'w' || key == 'W') {
    wPressed = false; 
  } 
  if (key == 'a' || key == 'A') {
    aPressed = false; 
  } 
  if (key == 's' || key == 'S') {
    sPressed = false; 
  }
  if (key == 'd' || key == 'D') {
    dPressed = false; 
  } 
}

/**
 * Snowflakes dissapear when they are clicked
 */

 public void mouseClicked() {
  //make snowflakes dissapear when clicked 
  float clickRadius = 10;
  for (int i = 0; i < snowX.length; i++) {
    float distance = dist(snowX[i], snowY[i], mouseX, mouseY);
    if (distance < clickRadius && mousePressed) {
      ballHideStatus[i] = true; 
      }
    }
  }
}
