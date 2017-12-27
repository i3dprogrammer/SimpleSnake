/*     */ package dev.game;
/*     */ 
/*     */ import dev.game.display.Display;
/*     */ import dev.game.input.KeyManager;
/*     */ import dev.game.tail.Pair;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferStrategy;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Game implements Runnable
/*     */ {
/*     */   private Thread thread;
/*  16 */   private boolean running = false;
/*     */   
/*     */   private BufferStrategy bs;
/*     */   
/*     */   private Graphics g;
/*     */   
/*     */   private Display display;
/*     */   
/*     */   private int m_width;
/*     */   private int m_height;
/*     */   private String m_title;
/*     */   private KeyManager keyManager;
/*  28 */   private int m_scale = 30;
/*  29 */   private int m_x = 0;
/*  30 */   private int m_y = 0;
/*  31 */   private int m_xspeed = 0;
/*  32 */   private int m_yspeed = 0;
/*  33 */   private List<Pair<Integer, Integer>> tail = new java.util.ArrayList();
/*     */   
/*     */   private int m_foodX;
/*     */   
/*     */   private int m_foodY;
/*     */   
/*     */   private boolean m_died;
/*     */   
/*     */ 
/*     */   public Game(String title, int width, int height)
/*     */   {
/*  44 */     this.m_title = title;
/*  45 */     this.m_width = width;
/*  46 */     this.m_height = height;
/*  47 */     this.keyManager = new KeyManager();
/*     */   }
/*     */   
/*     */   private void init() {
/*  51 */     this.display = new Display(this.m_title, this.m_width, this.m_height);
/*  52 */     this.display.getFrame().addKeyListener(this.keyManager);
/*  53 */     this.display.getCanvas().addKeyListener(this.keyManager);
/*  54 */     updateFood();
/*  55 */     changeSnakeSpeed(1, 0);
/*     */   }
/*     */   
/*     */   private void update() {
/*  59 */     this.m_x += this.m_xspeed;
/*  60 */     this.m_y += this.m_yspeed;
/*     */     
/*  62 */     for (int i = 0; i < this.tail.size(); i++)
/*     */     {
/*  64 */       if ((((Integer)((Pair)this.tail.get(i)).First).intValue() == this.m_x) && (((Integer)((Pair)this.tail.get(i)).Second).intValue() == this.m_y))
/*     */       {
/*  66 */         this.m_died = true;
/*     */       }
/*     */     }
/*     */     
/*  70 */     if (this.m_x + this.m_xspeed > this.m_width)
/*  71 */       this.m_x = 0;
/*  72 */     if (this.m_x + this.m_xspeed < -this.m_scale)
/*  73 */       this.m_x = (this.m_width - this.m_scale);
/*  74 */     if (this.m_y + this.m_yspeed > this.m_height)
/*  75 */       this.m_y = 0;
/*  76 */     if (this.m_y + this.m_yspeed < -this.m_scale) {
/*  77 */       this.m_y = (this.m_height - this.m_scale);
/*     */     }
/*  79 */     if ((this.m_foodX == this.m_x) && (this.m_foodY == this.m_y)) {
/*  80 */       if (this.tail.size() == 0)
/*  81 */         this.tail.add(new Pair(Integer.valueOf(this.m_x), Integer.valueOf(this.m_y)));
/*  82 */       this.tail.add(new Pair(Integer.valueOf(this.m_x), Integer.valueOf(this.m_y)));
/*  83 */       updateFood();
/*     */     }
/*     */     
/*  86 */     for (int i = 0; i < this.tail.size() - 1; i++) {
/*  87 */       this.tail.set(i, (Pair)this.tail.get(i + 1));
/*     */     }
/*  89 */     if (this.tail.size() > 0) {
/*  90 */       this.tail.set(this.tail.size() - 1, new Pair(Integer.valueOf(this.m_x), Integer.valueOf(this.m_y)));
/*     */     }
/*     */   }
/*     */   
/*     */   private void draw() {
/*  95 */     this.bs = this.display.getCanvas().getBufferStrategy();
/*  96 */     if (this.bs == null)
/*     */     {
/*  98 */       this.display.getCanvas().createBufferStrategy(3);
/*  99 */       return;
/*     */     }
/* 101 */     this.g = this.bs.getDrawGraphics();
/*     */     
/* 103 */     this.g.clearRect(0, 0, this.m_width, this.m_height);
/*     */     
/* 105 */     this.g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
/*     */     
/* 107 */     if (this.m_died)
/*     */     {
/* 109 */       this.g.setColor(Color.red);
/*     */       
/* 111 */       this.tail.clear();
/* 112 */       this.m_died = false;
/*     */     }
/*     */     
/* 115 */     this.g.fillRect(this.m_x, this.m_y, this.m_scale, this.m_scale);
/* 116 */     for (Pair<Integer, Integer> part : this.tail) {
/* 117 */       this.g.fillRect(((Integer)part.First).intValue(), ((Integer)part.Second).intValue(), this.m_scale, this.m_scale);
/*     */     }
/*     */     
/* 120 */     this.g.setColor(Color.green);
/* 121 */     this.g.fillRect(this.m_foodX, this.m_foodY, this.m_scale, this.m_scale);
/*     */     
/* 123 */     this.bs.show();
/* 124 */     this.g.dispose();
/*     */   }
/*     */   
/*     */   public void run() {
/* 128 */     init();
/* 129 */     int fps = 10;
/* 130 */     while (this.running) {
/*     */       try {
/* 132 */         Thread.sleep(1000 / fps);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 135 */         e.printStackTrace();
/*     */       }
/* 137 */       draw();
/* 138 */       update();
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateFood() {
/* 143 */     Random x = new Random();
/* 144 */     int xstep = this.m_width / this.m_scale;
/* 145 */     int ystep = this.m_height / this.m_scale;
/* 146 */     this.m_foodX = (this.m_scale * x.nextInt(xstep));
/* 147 */     this.m_foodY = (this.m_scale * x.nextInt(ystep));
/*     */   }
/*     */   
/*     */   public synchronized void start() {
/* 151 */     if (this.running)
/* 152 */       return;
/* 153 */     this.running = true;
/* 154 */     this.thread = new Thread(this);
/* 155 */     this.thread.start();
/*     */   }
/*     */   
/*     */   public synchronized void stop() throws InterruptedException {
/* 159 */     this.thread.join();
/*     */   }
/*     */   
/*     */   public void changeSnakeSpeed(int _xStep, int _yStep) {
/* 163 */     this.m_xspeed = (this.m_scale * _xStep);
/* 164 */     this.m_yspeed = (this.m_scale * _yStep);
/*     */   }
/*     */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Snake.jar!\dev\game\Game.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */