/*    */ package dev.game.input;
/*    */ 
/*    */ import dev.game.Game;
/*    */ import dev.game.Launcher;
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ public class KeyManager implements java.awt.event.KeyListener
/*    */ {
/*    */   public void keyPressed(KeyEvent e)
/*    */   {
/* 11 */     if (e.getKeyCode() == 40) {
/* 12 */       Launcher.test.changeSnakeSpeed(0, 1);
/* 13 */     } else if (e.getKeyCode() == 38) {
/* 14 */       Launcher.test.changeSnakeSpeed(0, -1);
/* 15 */     } else if (e.getKeyCode() == 37) {
/* 16 */       Launcher.test.changeSnakeSpeed(-1, 0);
/* 17 */     } else if (e.getKeyCode() == 39) {
/* 18 */       Launcher.test.changeSnakeSpeed(1, 0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void keyReleased(KeyEvent e) {}
/*    */   
/*    */   public void keyTyped(KeyEvent e) {}
/*    */ }


/* Location:              D:\SAVED PROJECTS FROM OLD WINDOWS V2\Desktop\Game2\Snake.jar!\dev\game\input\KeyManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */