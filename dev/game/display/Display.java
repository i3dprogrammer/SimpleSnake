/*    */ package dev.game.display;
/*    */ 
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ public class Display
/*    */ {
/*    */   private JFrame frame;
/*    */   private java.awt.Canvas canvas;
/*    */   private int m_width;
/*    */   private int m_height;
/*    */   private String m_title;
/*    */   
/*    */   public Display(String title, int width, int height)
/*    */   {
/* 15 */     this.m_title = title;
/* 16 */     this.m_width = width;
/* 17 */     this.m_height = height;
/*    */     
/* 19 */     createDisplay();
/*    */   }
/*    */   
/*    */   private void createDisplay() {
/* 23 */     this.frame = new JFrame(this.m_title);
/* 24 */     this.frame.setSize(this.m_width, this.m_height);
/* 25 */     this.frame.setResizable(false);
/* 26 */     this.frame.setDefaultCloseOperation(3);
/* 27 */     this.frame.setLocationRelativeTo(null);
/* 28 */     this.frame.setVisible(true);
/*    */     
/* 30 */     this.canvas = new java.awt.Canvas();
/* 31 */     this.canvas.setPreferredSize(new java.awt.Dimension(this.m_width, this.m_height));
/* 32 */     this.canvas.setMaximumSize(new java.awt.Dimension(this.m_width, this.m_height));
/* 33 */     this.canvas.setMinimumSize(new java.awt.Dimension(this.m_width, this.m_height));
/*    */     
/* 35 */     this.frame.add(this.canvas);
/* 36 */     this.frame.pack();
/*    */   }
/*    */   
/*    */   public JFrame getFrame() {
/* 40 */     return this.frame;
/*    */   }
/*    */   
/*    */   public java.awt.Canvas getCanvas() {
/* 44 */     return this.canvas;
/*    */   }
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Snake.jar!\dev\game\display\Display.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */