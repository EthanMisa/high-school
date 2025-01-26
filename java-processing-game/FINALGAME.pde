int width=1300;
int height=800;
int squareX=650;
int squareY=480;
int squareSize=50;
int speed=100;
int level=0;
boolean arrayRandom=true;
PFont font;
PFont pacman;
int[] answersChosen={0,0,0,0,0,0,0,0};
PImage background;
PImage bitcoin;
PImage dino;
PImage brendan;
PImage instructions;
PImage santi;
PImage rahil;
PImage squareImage;
PImage instructions2;
int ghost1=(int)(random(0,1300));
int ghost2=(int)(random(0,1300));
int ghost3=(int)(random(0,1300));
int ghost4=(int)(random(0,1300));
int score=0;
int noOfAnswersCorrect=0;
//all possible spots for answers
int[][] possibleSpots={{150,180},{150,480},{250,180},{250,480},{450,280},{550,380},{750,180},{750,380},{850,180},{1050,280},{1150,180},{1150,480}};
int[][] originalPossibleSpots={{150,180},{150,480},{250,180},{250,480},{450,280},{550,380},{750,180},{750,380},{850,180},{1050,280},{1150,180},{1150,480}};
//random list of locations for blocks
int[] actualSpots={-1,-1,-1,-1,-1,-1,-1,-1};
//all questions and answers and fake answers
String[][] possibleQuestions={{"Input","Processing","Storage","Output","Software","Hardware","Logic","Electricity","Four main parts of a computer"},
                      {"Motherboard","CPU","BUS","RAM","ROM","SSD","Microphone","Expansion \nSlots","Processing Components of a Computer"},
                      {"ROM","HDD","SSD","Optical \nDrive","Power \nSupply","8GB","Motherboard","Airpods","Storage Components of a Computer"},
                      {"5HZ","3KHZ","8MHZ","1 GHZ","2GB","7TB","8MB","12 Bits","Examples of Clock Speed"},
                      {"Mouse","Keyboard","Microphone","Camera","Monitor","Airpods","Speaker","HDMI","Input Devices"},
                      {"Monitor","Airpods","Printer","Projector","Number \nPad","Mouse","CPU","QWERTY","Output Devices"},
                      {"iCloud","Google \nDrive","Drop \nBox","Microsoft\n OneDrive","Trojan \nVirus","Microsoft \nWord","Firewall","Airdrop","Examples of Cloud Storages"},
                      {"Windows","Mac OS","Linux","Ubuntu","Apple","iCloud","Nintendo","Google Drive","Examples of Operating Systems"},
                      {"Python","Java","Ruby","C++","Coffee","Python++","Processing","repl.it","Examples of Programming Languages"},
                      {"12 BITS","1MB","8GB","2TB","12MHZ","56KHZ","3GHZ","1THZ","Storage Sizes"},
                      {"01001010","11111111","00000001","10101001","10012010","SLATT!!","122345678","6942029","Examples of Binary"},
                      {"420","69","666","1234","This","Is","Not","Correct","Examples of Decimal"}};
//list of questions in random order
int[] actualQuestions={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
int noOfAnswersChosen=0;
boolean changeLevel=false;
String[] answersChosenByUser={"","","",""};
int lives=1;
boolean nextLevel=false;
int buttonX=300, buttonY=100;
boolean mainMenu=true;
int menuX=130, menuY=90, instructionsX=130, instructionsY=90, customizeX=130, customizeY=90;
int textMenu=60, textInstructions=60, textCustomize=60;
boolean start=false;
boolean instruction=false;
int continueSize=40;
boolean customize=false;
int dinoSize=120, brendanSize=120, santiSize=120, rahilSize=120;
void reset(){
  squareX=650;
  squareY=480;
  arrayRandom=true;
  for(int i=0;i<answersChosen.length;i++){
    answersChosen[i]=0;
  }
  noOfAnswersCorrect=0;
  for(int x=0;x<12;x++){
    for(int y=0;y<2;y++){
      possibleSpots[x][y]=originalPossibleSpots[x][y];
    }
  }
  for(int a=0;a<8;a++){
    actualSpots[a]=-1;
  }
  for(int b=0;b<12;b++){
    actualQuestions[b]=-1;
  }
  for(int c=0;c<4;c++){
    answersChosenByUser[c]="";
  }
  noOfAnswersChosen=0;
  nextLevel=false;
}

void setup(){
  size(1300,800);
  font=createFont("retro_computer_personal_use.ttf",1);
  pacman=createFont("PAC-FONT.TTF",1);
  background=loadImage("matrixBackground.jpg");
  bitcoin=loadImage("bitcoin.png");
  dino=loadImage("DINO-page-001.png");
  brendan=loadImage("IMG_0928.png");
  instructions=loadImage("Screen Shot 2020-01-15 at 1.54.36 PM.png");
  santi=loadImage("santi.png");
  rahil=loadImage("rahil.png");
  squareImage=dino;
  instructions2=loadImage("instructions2.png");
}

void draw(){
  if(level>=12){
    start=false;
    instruction=false;
    customize=false;
    mainMenu=true;
    level=0;
    lives=5;
    score=0;
  }
  if(mainMenu){
    background();
    mainMenu();
  }
  if(instruction){
    mainMenu=false;
    background();
    instructions();
  }
  if(customize){
    instruction=false;
    background();
    mainMenu=false;
    customize();
  }
  if(start){
    background();
    instruction=false;
    customize=false;
    if(changeLevel==true){
      reset();
      changeLevel=false;
    }
    playBackground();
    maze();
    square();
    if(arrayRandom){
      randomArray();
      arrayRandom = false;
    }
    drawAnswersOnMaze(answersChosen);
    itemBox();
    pickedBlock();
    drawAnswerOnBottom(answersChosen);
    checkAnswers();
    changeLevel();
    life();
  }
}

void background(){
  background.resize(width,height);
  imageMode(CORNER);
  image(background,0,0);
}
void playBackground(){
  rectMode(CORNER);
  strokeWeight(10);
  stroke(0);
  fill(#1F710E);
  //maze
  rect(40,80,1220,500,15);
  //item list
  rect(40,600,1220,180,15);
  textSize(20);
  fill((int)(random(100,200)),(int)(random(100,200)),(int)(random(100,200)));
  text("Created by: Ethan Misa",170,790);
}

void maze(){
  fill(#1F710E);
  rectMode(CORNER);
  //outline of maze
  strokeWeight(8);
  stroke(255);
  rect(100,130,1100,400,10);
  //walls inside
  strokeCap(PROJECT);
  stroke(255);
  strokeWeight(5);
  int[] lines={200,130,200,230,200,430,200,530,300,230,300,330,400,130,400,330,
  400,430,400,530,500,230,500,330,600,330,600,430,700,130,700,230,700,330,700,
  430,800,130,800,230,800,330,800,430,900,230,900,330,900,430,900,530,1000,130,
  1000,230,1000,330,1000,430,1100,230,1100,330,200,230,300,230,500,230,600,230,
  800,230,900,230,400,330,500,330,1000,330,1100,330,100,330,200,330,200,430,300,
  430,500,430,600,430,700,430,800,430,1100,430,1200,430};
  for(int i=0;i<lines.length-3;i++){
    if(i%4==0){
      line(lines[i],lines[i+1],lines[i+2],lines[i+3]); }
  }
}

void square(){
  rectMode(CENTER);
  fill(#FF00EF);
  strokeWeight(0);
  imageMode(CENTER);
  dino.resize(70,70);
  santi.resize(70,70);
  rahil.resize(70,70);
  brendan.resize(70,70);
  image(squareImage,squareX,squareY);
}
boolean lineDown(){
  boolean returnStatement=true;
  int[][] down={{150,280},{250,180},{250,380},{450,280},{550,180},{550,380},{750,380},{850,180},{1050,280},{1150,380}};
  for(int i=0;i<down.length;i++){
    if(squareX==down[i][0]&&squareY==down[i][1]){
      returnStatement=false;
    }
  }
  for(int x=1;x<=11;x++){
    if(squareX==50+100*x&&squareY==480){
      returnStatement=false;
    }
  }
  return returnStatement;
}
boolean lineUp(){
  boolean returnStatement=true;
  int[][] down={{150,380},{250,280},{250,480},{450,380},{550,280},{550,480},{750,480},{850,280},{1050,380},{1150,480}};
  for(int i=0;i<down.length;i++){
    if(squareX==down[i][0]&&squareY==down[i][1]){
      returnStatement=false;
    }
  }
  for(int x=1;x<=11;x++){
    if(squareX==50+100*x&&squareY==180){
      returnStatement=false;
    }
  }
  return returnStatement;
}
boolean lineLeft(){
  boolean returnStatement=true;
  int[][] down={{250,180},{250,480},{350,280},{450,180},{450,280},{450,480},{550,280},{650,380},{750,180},{750,380},{850,180},{850,380},{950,280},{950,480},{1050,180},{1050,380},{1150,280}};
  for(int i=0;i<down.length;i++){
    if(squareX==down[i][0]&&squareY==down[i][1]){
      returnStatement=false;
    }
  }
  for(int x=1;x<=4;x++){
    if(squareX==150&&squareY==100*x+80){
      returnStatement=false;
    }
  }
  return returnStatement;
}
boolean lineRight(){
  boolean returnStatement=true;
  int[][] down={{150,180},{150,480},{250,280},{350,180},{350,280},{350,480},{450,280},{550,380},{650,180},{650,380},{750,180},{750,380},{850,280},{850,480},{950,180},{950,380},{1050,280}};
  for(int i=0;i<down.length;i++){
    if(squareX==down[i][0]&&squareY==down[i][1]){
      returnStatement=false;
    }
  }
  for(int x=1;x<=4;x++){
    if(squareX==1150&&squareY==100*x+80){
      returnStatement=false;
    }
  }
  return returnStatement;
}
void keyPressed(){ 
  if(key==CODED){ 
    if(keyCode==LEFT&&lineLeft()){    
      squareX-=speed;
    }
    if(keyCode==RIGHT&&lineRight()){   
      squareX+=speed;
    }
    if(keyCode==UP&&lineUp()){
      squareY-=speed;
    }
    if(keyCode==DOWN&&lineDown()){
      squareY+=speed;
    }
    
  }
}
//creates blocks for answers chosen
void itemBox(){
  strokeWeight(0);
  fill(255);
  rectMode(CENTER);
  for(int i=1;i<=4;i++){
    rect(1220/5*i+40,690,100,50);
  }
  textSize(60);
  text(score,100,710);
}

//makes order for answers and blocks
void randomArray(){
  for(int i=0;i<actualSpots.length;i++){
    actualSpots[i]=(int)random(0,12);
    if(checkArray(actualSpots)==true){
      i=i-1;
    }
  }
  for(int x=0;x<actualQuestions.length;x++){
    actualQuestions[x]=(int)random(0,12);
    if(checkArray(actualQuestions)==true){
      x=x-1;
    }
  }
}
//checks if array has no repeating numbers
boolean checkArray(int [] array){
  boolean returnArray=false;
  for(int i=0;i<array.length;i++){
    for(int x=i+1;x<array.length;x++){
      if(array[i]==array[x]&&array[i]!=-1){
        returnArray=true;
      }
    }
  }
  return returnArray;
}
void drawAnswersOnMaze(int[] a){
  textFont(font);
  fill(255);
  textAlign(CENTER);
  textSize(40);
  text(possibleQuestions[level][8],1300/2,50); 
  fill(255);
  for(int i=0;i<8;i++){
    if(a[i]==0){
      if(possibleQuestions[level][i].length()<7){
        textSize(15);
      }
      else{
        textSize(11);
      }
      text(possibleQuestions[level][i],possibleSpots[actualSpots[i]][0],possibleSpots[actualSpots[i]][1]);
    }
  } 
}


void pickedBlock(){
  if(noOfAnswersChosen<4){
    for(int i=0;i<8;i++){
      if(squareX==possibleSpots[actualSpots[i]][0]&&squareY==possibleSpots[actualSpots[i]][1]){
          answersChosen[i]=1;
          answersChosenByUser[noOfAnswersChosen]=possibleQuestions[level][i];
          possibleSpots[actualSpots[i]][0]=0;
          possibleSpots[actualSpots[i]][1]=0;
          noOfAnswersChosen++;
      }
    }
  }
} 


void drawAnswerOnBottom(int [] a){
  for(int x=0;x<8;x++){
    if(a[x]==1){
      fill(0);
      for(int i=0;i<4;i++){
        if(answersChosenByUser[i].length()<7){
          textSize(15);
        }
        else if(answersChosenByUser[i].length()<=12){
          textSize(11);
        }
        text(answersChosenByUser[i],1220/5*(i+1)+40,690);
      }    
    }
  }
}

void checkAnswers(){
  noOfAnswersCorrect=0;
  for(int x=0;x<4;x++){
    for(int i=0;i<4;i++){
      if(answersChosenByUser[x].equals(possibleQuestions[level][i])){
        noOfAnswersCorrect++;      
      }
    }
  }
  noOfAnswersCorrect=noOfAnswersCorrect+noOfAnswersCorrect-noOfAnswersCorrect;
}

void changeLevel(){
  continueSize=40;
  if(!answersChosenByUser[0].equals("")&&!answersChosenByUser[1].equals("")&&!answersChosenByUser[2].equals("")&&!answersChosenByUser[3].equals("")&&noOfAnswersCorrect==4){
    buttonX=300;
    buttonY=100;
    fill(0,0,0,150);
    rectMode(CORNER);
    rect(0,0,1300,800);
    rectMode(CENTER);
    fill(98);
    rect(width/2,height/2,800,600);
    fill((int)random(80,200),(int)random(80,200),(int)random(80,200));
    textAlign(CENTER);
    textSize((int)random(50,130));
    text("!!Correct!!",width/2,300);
    fill(#27CB4C);
    if(mouseX>width/2-250&&mouseX<width/2+250&&mouseY>500-50&&mouseY<500+50){
      buttonX=(int)(250*1.5);
      buttonY=(int)(100*1.5);
      continueSize=(int)(40*1.3);
    }
    rect(width/2,500,buttonX,buttonY);
    textSize(continueSize);
    fill(255);
    if(level<=10){
      text("Continue",width/2,510);
    }
    if(level>=11){
      text("End",width/2,510);
      textSize(20);
      fill(255);
      text("Your final score is: "+score,width/2,height/2-200);
    }
    if(nextLevel){
      changeLevel=true;
      level++;
      score++;
    }
  } 
  else{
    if(!answersChosenByUser[0].equals("")&&!answersChosenByUser[1].equals("")&&!answersChosenByUser[2].equals("")&&!answersChosenByUser[3].equals("")){
      buttonX=300;
      buttonY=100;
      fill(0,0,0,150);
      rectMode(CORNER);
      rect(0,0,1300,800);
      rectMode(CENTER);
      fill(98);
      rect(width/2,height/2,800,600);
      fill((int)random(190,255),(int)random(100,200),(int)random(80,200));
      textAlign(CENTER);
      textSize((int)random(50,130));
      text("!!FAIL!!",width/2,300);
      fill(#27CB4C);
      if(mouseX>width/2-250&&mouseX<width/2+250&&mouseY>500-50&&mouseY<500+50){
        buttonX=(int)(250*1.5);
        buttonY=(int)(100*1.5);
        continueSize=(int)(40*1.3);
      }
      rect(width/2,500,buttonX,buttonY);
      textSize(continueSize);
      fill(255);
      if(lives>1){
        text("Continue",width/2,510);
        level=11;
      }
      else{
        text("End",width/2,510);
        textSize(20);
        fill(255);
        text("Your final score is: "+score,width/2,height/2-200);
        
      }
      for(int i=0;i<4;i++){
        textAlign(CENTER);
        textSize(20);
        fill(255,0,0);
        text(possibleQuestions[level][i],350+200*i,650);
      }
      if(nextLevel){
        changeLevel=true;
        level++;
        lives--;
      }
    }
  }
}

void mousePressed(){
  if(mouseX>width/2-250&&mouseX<width/2+250&&mouseY>500-50&&mouseY<500+50&&!answersChosenByUser[0].equals("")
  &&!answersChosenByUser[1].equals("")&&!answersChosenByUser[2].equals("")&&!answersChosenByUser[3].equals("")){
    nextLevel=true;
  }
  if(mouseX<(width/2)+(menuX/2)&&mouseX>(width/2)-(menuX/2)&&mouseY<300+(menuY/2)&&mouseY>300-(menuY/2)){
    start=true;
  }
  if(mouseX<(width/2)+(instructionsX/2)&&mouseX>(width/2)-(instructionsX/2)&&mouseY<450+(instructionsY/2)&&mouseY>450-(instructionsY/2)&&mainMenu){
    instruction=true;
  }
  if(mouseX>5&&mouseX<85&&mouseY>5&&mouseY<85&&instruction){
      mainMenu=true;
      instruction=false;
  }
  if(mouseX<(width/2)+(customizeX/2)&&mouseX>(width/2)-(customizeX/2)&&mouseY<600+(customizeY/2)&&mouseY>600-(customizeY/2)&&mainMenu){
    customize=true; 
    mainMenu=false;
    instruction=false;
  }
  if(mouseX>160&&mouseX<360&&mouseY>400&&mouseY<600&&customize){
    squareImage=dino;
    mainMenu=true;
    customize=false;
    instruction=false;
  }
  if(mouseX>420&&mouseX<620&&mouseY>400&&mouseY<600&&customize){
    squareImage=brendan;
    mainMenu=true;
    customize=false;
    instruction=false;
  }
  if(mouseX>680&&mouseX<880&&mouseY>400&&mouseY<600&&customize){
    squareImage=santi;
        mainMenu=true;
    customize=false;
  }
  if(mouseX>940&&mouseX<1140&&mouseY>400&&mouseY<600&&customize){
    squareImage=rahil;
    mainMenu=true;
    customize=false;
  }
  if(mouseX>5&&mouseX<85&&mouseY>5&&mouseY<85&&customize){
    mainMenu=true;
    customize=false;
  
  }
}
void life(){
  imageMode(CENTER);
  bitcoin.resize(30,30);
  for(int i=0;i<lives;i++){
    image(bitcoin,550+(i*50),750);
  }
  if(lives<=0){
    start=false;
    reset();
    instruction=false;
    customize=false;
  }
}
void mainMenu(){
  menuX=300;
  menuY=130;
  instructionsX=300;
  instructionsY=130;
  customizeX=300;
  customizeY=130;
  textMenu=60;
  textInstructions=25;
  textCustomize=35;
  textFont(pacman);
  textSize(120);
  fill((int)(random(100,200)),(int)(random(100,200)),(int)(random(100,200)));
  text("noogletman",width/2,150);
  ghost1+=10;
  if(ghost1>1300){
    ghost1=0;
  }
  ghost2-=10;
  if(ghost2<0){
    ghost2=1300;
  }
  ghost3+=10;
  if(ghost3>1300){
    ghost3=0;
  }
  ghost4+=10;
  if(ghost4>800){
    ghost4=0;
  }
  text("9",ghost2,700);
  text("9",ghost1,300);
  text("9",ghost3,ghost4);
  text("9",ghost4,ghost3-500);
  for(int i=1;i<=7;i++){
    text("9",(int)(random(0,250*i)),(int)(random(0,250*i-1)));
  }
  if(mouseX<(width/2)+(menuX/2)&&mouseX>(width/2)-(menuX/2)&&mouseY<300+(menuY/2)&&mouseY>300-(menuY/2)&&mainMenu){
    menuX=(int)(300*1.2);
    menuY=(int)(130*1.2);
    textMenu=(int)(60*1.2);
  }
    if(mouseX<(width/2)+(instructionsX/2)&&mouseX>(width/2)-(instructionsX/2)&&mouseY<450+(instructionsY/2)&&mouseY>450-(instructionsY/2)&&mainMenu){
    instructionsX=(int)(300*1.2);
    instructionsY=(int)(130*1.2);
    textInstructions=(int)(25*1.2);
  }
    if(mouseX<(width/2)+(customizeX/2)&&mouseX>(width/2)-(customizeX/2)&&mouseY<600+(customizeY/2)&&mouseY>600-(customizeY/2)&&mainMenu){
    customizeX=(int)(300*1.2);
    customizeY=(int)(130*1.2);
    textCustomize=(int)(35*1.2);
  }
  rectMode(CENTER);
  fill(0);
  stroke(255);
  strokeWeight(1);
  rect(width/2,300,menuX,menuY,90);
  rect(width/2,450,instructionsX,instructionsY,90);
  rect(width/2,600,customizeX,customizeY,90);
  textFont(font);
  fill(255);
  textSize(textMenu);
  textAlign(CENTER); 
  text("Start",width/2,320);
  textSize(textInstructions);
  text("Instructions",width/2,455);
  textSize(textCustomize);
  text("Customize",width/2,610);
  textSize(20);
  fill((int)(random(100,200)),(int)(random(100,200)),(int)(random(100,200)));
  text("Created by: Ethan Misa",170,790);
}

void instructions(){
    fill(#1F710E);
    imageMode(CENTER);
    fill(0);
    rect(width/2,height/2,width-150,height-150,40);
    instructions.resize(width-200,height-200);
    image(instructions,width/2,height/2);
    imageMode(CORNER);
    instructions2.resize(400,400);
    image(instructions2,width-400,height-400);
    ellipseMode(CENTER);
    fill(255);
    noStroke();
    ellipse(50,50,85,85);
    fill(0);
    stroke(0);
    strokeWeight(10);
    line(25,25,75,75);
    line(75,25,25,75);
    textSize(20);
    fill((int)(random(100,200)),(int)(random(100,200)),(int)(random(100,200)));
    text("Created by: Ethan Misa",170,790);
}

void customize(){
  dinoSize=200;
  brendanSize=200;
  santiSize=200;
  rahilSize=200;
  fill(255);
  textAlign(CENTER);
  textSize(70);
  text("Choose your character!",width/2,200);
  if(mouseX>160&&mouseX<360&&mouseY>400&&mouseY<600){
    dinoSize=300;
  }
  if(mouseX>420&&mouseX<620&&mouseY>400&&mouseY<600){
    brendanSize=300;
  }
  if(mouseX>680&&mouseX<880&&mouseY>400&&mouseY<600){
    santiSize=300;
  }
  if(mouseX>940&&mouseX<1140&&mouseY>400&&mouseY<600){
    rahilSize=300;
  }
  dino.resize(dinoSize,dinoSize);
  brendan.resize(brendanSize,brendanSize);
  santi.resize(santiSize,santiSize);
  rahil.resize(rahilSize,rahilSize);
  imageMode(CENTER);
  image(dino,260,500);
  image(brendan,520,500);
  image(santi,780,500);
  image(rahil,1040,500);
  ellipseMode(CENTER);   
  fill(255);
  noStroke();
  ellipse(50,50,85,85);
  fill(0);
  stroke(0);
  strokeWeight(10);
  line(25,25,75,75);
  line(75,25,25,75);
  textSize(20);
  fill((int)(random(100,200)),(int)(random(100,200)),(int)(random(100,200)));
  text("Created by: Ethan Misa",170,790);
}
