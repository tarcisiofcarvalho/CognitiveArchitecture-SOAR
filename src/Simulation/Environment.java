/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import java.util.logging.Logger;
import ws3dproxy.CommandUtility;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.World;

/**
 *
 * @author Danilo
 */
public class Environment
{
    Logger logger = Logger.getLogger(Environment.class.getName());
    WS3DProxy proxy = null;
    //SoarBridge soarBridge = null;
    Creature c = null;
    private World w = null;

    private int SPOT_X = 750;
    private int SPOT_Y = 550;
    
    public Environment(Boolean prepareEnviromentAndStartGame,String args[])
    {
        int creatureColor = 1;
        if(args != null){
            creatureColor = Integer.parseInt(args[0]);
        }
        proxy = new WS3DProxy();
        //proxy.connect();
        try {
        w = proxy.getWorld();
        //w.reset();
        c = proxy.createCreature(Double.parseDouble(args[3]),Double.parseDouble(args[4]),0,creatureColor);
        c.start();
        c.genLeaflet();
        if(args != null){
           if(args[2].equals("grow")){
               w.grow(1);
               if(args[5].equals("one")){
                    w.createJewel(1, 200, 500);
                    w.createJewel(1, 300, 400);
                    w.createJewel(1, 400, 300);                
                    w.createJewel(2, 400, 100);
                    w.createJewel(2, 500, 240);
                    w.createJewel(2, 200, 300);                

                    w.createJewel(3, 250, 350);
                    w.createJewel(3, 350, 100);
                    w.createJewel(4, 450, 300);
                    w.createJewel(4, 550, 200);
                    w.createJewel(4, 200, 550);                

                    w.createJewel(5, 300, 120);
                    w.createJewel(5, 400, 550);
                    w.createJewel(1, 500, 250);
                    w.createJewel(3, 600, 350);
               }
           
//                CommandUtility.sendNewJewel(1, 200, 500);
//                CommandUtility.sendNewJewel(1, 300, 400);
//                CommandUtility.sendNewJewel(1, 400, 300);                
//                CommandUtility.sendNewJewel(2, 400, 100);
//                CommandUtility.sendNewJewel(2, 500, 240);
//                CommandUtility.sendNewJewel(2, 200, 300);                
//
//                CommandUtility.sendNewJewel(3, 250, 350);
//                CommandUtility.sendNewJewel(3, 350, 100);
//                CommandUtility.sendNewJewel(4, 450, 300);
//                CommandUtility.sendNewJewel(4, 550, 200);
//                CommandUtility.sendNewJewel(4, 200, 550);                
//
//                CommandUtility.sendNewJewel(5, 300, 120);
//                CommandUtility.sendNewJewel(5, 400, 550);
//                CommandUtility.sendNewJewel(1, 500, 250);
//                CommandUtility.sendNewJewel(3, 600, 350);               
           }
        }

        // Delivery Spot
        if(creatureColor==1){
            this.SPOT_X = 700;
            this.SPOT_Y = 100;
            System.out.println("Spot created for Red");
            CommandUtility.sendNewWaypoint(this.SPOT_X,this.SPOT_Y);            
        }else{
            this.SPOT_X = 700;
            this.SPOT_Y = 500;
            System.out.println("Spot created for Yellow");
            CommandUtility.sendNewWaypoint(this.SPOT_X,this.SPOT_Y);
        }
        
        if (prepareEnviromentAndStartGame)
        {
            // Create Simulation Enviroment - Bricks
            CommandUtility.sendNewBrick(4,747.0,2.0,800.0,567.0);
            CommandUtility.sendNewBrick(4,50.0,-4.0,747.0,47.0);
            CommandUtility.sendNewBrick(4,49.0,562.0,796.0,599.0);
            CommandUtility.sendNewBrick(4,-2.0,6.0,50.0,599.0);            
        }
        } catch (Exception e) {
            logger.severe("Error in starting the Environment ");
            e.printStackTrace();
        }
    }
    
    public Creature getCreature() {
        return(c);
    }
    
    public World getWorld(){
        return w;
    }
    
    public int getSpotX(){
        return this.SPOT_X;       
    }   
    
    public int getSpotY(){
        return this.SPOT_Y;
    }
}