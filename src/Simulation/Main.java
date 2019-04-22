/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulation;

import support.MindView;
import support.NativeUtils;
import SoarBridge.SoarBridge;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Danilo
 */
public class Main
{
    Logger logger = Logger.getLogger(Main.class.getName());
        
    private void SilenceLoggers() {
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("org.jsoar")).setLevel(ch.qos.logback.classic.Level.OFF);
        Logger.getLogger("Simulation").setLevel(Level.SEVERE);
    }

    public Main(String args[]) {
        SilenceLoggers();
        try
        {
            NativeUtils.loadFileFromJar("/aula06.soar");
            String soarRulesPath = "aula06.soar";

            //Start enviroment data
            Environment e = new Environment(Boolean.FALSE,args);
            SoarBridge soarBridge = new SoarBridge(e,soarRulesPath,false);
            MindView mv = new MindView(soarBridge);
            mv.setVisible(true);

            // Run Simulation until some criteria was reached
            Thread.sleep(Long.parseLong(args[1]));
            boolean run = true;
            // Time count start
            Date dStart = new Date();
            while(run)
            {                           
                if (mv.getDebugState() == 0) {
                   soarBridge.step();
                   mv.set_input_link_text(soarBridge.input_link_string);
                   mv.set_output_link_text(soarBridge.output_link_string);
                }
                else e.c.updateState();
                Thread.sleep(100); // original 100
                if(soarBridge.getJewelRemainingTotal()==0 && run){
                    if (mv.getDebugState() == 0) {
                       soarBridge.step();
                       mv.set_input_link_text(soarBridge.input_link_string);
                       mv.set_output_link_text(soarBridge.output_link_string);
                    }
                    else e.c.updateState();
                    Date dEnd = new Date();
                    long dif = dEnd.getTime() - dStart.getTime();
                    int sec = (int) (dif / (1000));
                    System.out.println(">>>>Goal completed<<<< Creature: " + e.getCreature().getColor() + " - Seconds: " + sec);
                    e.getCreature().stop();
                    run = false;
                } 
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
            logger.severe("Unknown error"+ex);
        }
    }

    public static void main(String args[])
    {
        Main m = new Main(args);
    }


}
