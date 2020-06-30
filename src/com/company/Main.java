package com.company;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import processing.core.PApplet;   //Download processing add
import processing.core.PSurface;
//import processing.core.PSurface;

public class Main extends PApplet{

    int angle = 0;
    private PApplet sketch;

    ProjectorSketch projector;
    ControlP5 cp51;
    // Mixer mixer;
    public float boxValue = 23;

    //Size for main
    int w=1280; //non fullscreen width
    int h=720; //non fullscreen height
    boolean fullscreen=false; //fullscreen

    //
    int myColorBackground = color(128);

    int sliderValue = 100;
    //

    public void settings() {
        // size(300, 300, JAVA2D);
        size(700,400);

        //   fullScreen(1);
        //   smooth(4);


        println("Main's  sketchPath: \t\"" + sketchPath("") + "\"");
        println("Main's  dataPath: \t\"" + dataPath("") + "\"\n");



    }

    public void setup() {
        //  noLoop();
        // frameRate(60);
        // stroke(-1);
        //  strokeWeight((float) 1.5);
        surface.setResizable(true);
        this.surface.setTitle("THE MIXER");
        projector = new ProjectorSketch();
        gui();
        runSketch( new String[] {
                        "--display=1",
                        "--location=" + (displayWidth>>2) + ',' + (displayHeight>>3),
                        "--sketch-path=" + sketchPath(""),
                        "" }
                , projector = new ProjectorSketch() );
        //projector.getSurface().setVisible(false);   //set window visible

        //
        // mixer= new Mixer();
        // mixer.cp51 = new ControlP5(this);


    }
    public void gui(){

        // By default all controllers are stored inside Tab 'default'
        // add a second tab with name 'extra'
        cp51 = new ControlP5(this);
        cp51.addTab("extra")
                .setColorBackground(color(0, 160, 100))
                .setColorLabel(color(255))
                .setColorActive(color(255,128,0))
        ;

        // if you want to receive a controlEvent when
        // a  tab is clicked, use activeEvent(true)

        cp51.getTab("default")
                .activateEvent(true)
                .setLabel("my default tab")
                .setId(1)
        ;

        cp51.getTab("extra")
                .activateEvent(true)
                .setId(2)
        ;


        // create a few controllers

        cp51.addButton("button")
                .setBroadcast(false)
                .setPosition(100,100)
                .setSize(80,40)
                .setValue(1)
                .setBroadcast(true)
                .getCaptionLabel().align(CENTER,CENTER)
        ;

        cp51.addButton("buttonValue")
                .setBroadcast(false)
                .setPosition(220,100)
                .setSize(80,40)
                .setValue(2)
                .setBroadcast(true)
                .getCaptionLabel().align(CENTER,CENTER)
        ;

        cp51.addSlider("slider")
                .setBroadcast(false)
                .setRange(100,200)
                .setValue(128)
                .setPosition(100,160)
                .setSize(200,20)
                .setBroadcast(true)
        ;

        cp51.addSlider("sliderValue")
                .setBroadcast(false)
                .setRange(0,255)
                .setValue(128)
                .setPosition(100,200)
                .setSize(200,20)
                .setBroadcast(true)
        ;

        // arrange controller in separate tabs

        cp51.getController("sliderValue").moveTo("extra");
        // cp51.getController("sliderValue").moveTo("default");
        cp51.getController("slider").moveTo("global");

        // Tab 'global' is a tab that lies on top of any
        // other tab and is always visible

    }

    public  void controlEvent(ControlEvent theControlEvent) {
        if (theControlEvent.isTab()) {
            println("got an event from tab : "+theControlEvent.getTab().getName()+" with id "+theControlEvent.getTab().getId());
        }
//      println("got an EVENT : "+theControlEvent.getTab().getName()+" with id "+theControlEvent.getTab().getId());
    }

    public  void slider(int theColor) {
        myColorBackground = color(theColor);
        println("a slider event. setting background to "+theColor);
        projector.mychannel=3;
        System.out.println("TRA: " +  projector.myHouse.rooms[0].transpose);
        System.out.println("TRA: " +  projector.myHouse.rooms[0].instances[1].m1val1);
        System.out.println("TRA: " +  projector.myHouse.rooms[0].instances[1].y);
        System.out.println("m1val: " +  projector.tempo);
        projector.shedMidi.sendMidiVol(1,64);//GGG
        //Gd
    }


    public    void keyPressed() {
        if(keyCode==TAB) {
            cp51.getTab("extra").bringToFront();
        }
    }


    public void draw() {
        background(myColorBackground);
        fill(sliderValue);
        rect(0,0,width,100);

        //  background(myColorBackground);
        //   fill(sliderValue);
        // rect(0,0,width,100);
    }




  /* public void mousePressed() {
//        projector.getSurface().setVisible(true);
    }*/

    static final void removeExitEvent(final PSurface surf) {
        final java.awt.Window win
                = ((processing.awt.PSurfaceAWT.SmoothCanvas) surf.getNative()).getFrame();

        for (final java.awt.event.WindowListener evt : win.getWindowListeners())
            win.removeWindowListener(evt);
    }


    public static void main(String[] args) {
        // write your code here
        //String[] appletArgs = new String[] { "Main" };
        String[] processingArgs = {"Main"};
        //Main mySketch = new Main();
        //PApplet.runSketch(processingArgs, mySketch);
        Mixer mixerSketch;
        ProjectorSketch projectorSketch;
        mixerSketch =new Mixer();
        projectorSketch=new ProjectorSketch();
        mixerSketch.setOtherWindowRef(projectorSketch);
        projectorSketch.setOtherWindowRef(mixerSketch);
        projectorSketch.runIt();
        mixerSketch.runIt();

    }




}
