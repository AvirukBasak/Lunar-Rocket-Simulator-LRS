package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import assets.StateVars;

/*
 * Entire console will be CASE-INSENSITIVE
 *
 * Commands
 * 1.  SELECT [ENGINE NO]
 * 2.  THRUST [% VALUE without % sign]
 * 3.  DROP STAGE [STAGE NO]
 * 4.  TIME WARP [WARP factor]
 * 5.  STATUS
 * 6.  var <var> <val>
 * 7.  var list <var>
 *
 * Developer command
 * var help
 * var [StateVars.variable] [value]
 * List of variables:
 *      activity
 *      engON
 *      throttle
 *      fuel
 *      stageDumped
 *      yFlame
 *      FPS
 *      UPS
 *      upTime
 *      warpF
 *      accG
 *      Isp
 *      dM
 *      Mfuel_t
 *      Mt
 *      scaleF
 *      totalAcc
 *      rktVt
 *      altitude
 */

public class Console implements Runnable
{
    private Thread thread;
    private boolean running = false;

    public String read()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public String readCommand()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            this.write("\nLRS shell> ");
            try {
                return br.readLine().trim();
            } catch (NullPointerException ex) {
                System.out.println();
                return "exit";
            }
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return "";
    }

    public void write(String str)
    {
        System.out.print(str);
    }

    public void writeln(String str)
    {
        System.out.println("\r" + str);
    }

    /*private void evaluate(String command) throws Exception {
        StringTokenizer sT = new StringTokenizer(command, " \n");
        String cmd = sT.nextToken();
        if (cmd.equalsIgnoreCase("SELECT")) {
            StateVars.engON = Byte.parseByte(sT.nextToken());
            switch(StateVars.engON) {
                case 1:
                    isOn_engS1 = true;
                    break;
                case 2:
                    isOn_engS2 = true;
                    break;
                case 3:
                    isOn_engS3 = true;
                    break;
                default:
                    this.writeln("[E] unknown error\n");
            }
        }
        else if (cmd.equalsIgnoreCase("THRUST")) {
            inputThrust(Integer.parseInt(sT.nextToken()));
        }
        else if (cmd.equalsIgnoreCase("DROP")) {
            byte stageNo = (byte) Integer.parseInt(sT.nextToken());
            switch(stageNo) {
                case 1:
                    dumpStg1ActionPerformed();
                    break;
                case 2:
                    dumpStg2ActionPerformed();
                    break;
                case 3:
                    ejectPayloadActionPerformed();
                    break;
                default:
                    this.writeln("[E] unknown error\n");
            }
        }
        else if (cmd.equalsIgnoreCase("TIME")) {
            if (sT.nextToken().equalsIgnoreCase("WARP")) {
                StateVars.warpF = Integer.parseInt(sT.nextToken());
            }
            else throw new Exception ("incomplete command: expected WARP after TIME");
        }
        else if (cmd.equalsIgnoreCase("STATUS")) {
            this.writeln("");
            this.writeln("Fuel:       " + StateVars.fuel + " %");
            this.writeln("Thrust:     " + StateVars.throttle + " %");
            this.writeln("");
            this.writeln("Velocity:   " + StateVars.rktVt + " m/s");
            this.writeln("Altitude:   " + StateVars.altitude + " m from nearest body");
            this.writeln("");
            this.writeln("Fuel:       " + StateVars.Mfuel_t + " kg");
            this.writeln("Mass:       " + StateVars.Mt + " kg");
            this.writeln("");
            this.writeln("Engine:     " + StateVars.engON + " is ON");
            this.writeln("Last stage: " + (int)(StateVars.stageDumped + 1));
            this.writeln("Time warp:  " + StateVars.warpF + " times");
        }
        else if (cmd.equals ("var")) {
            String var = sT.nextToken();
            if (var.equalsIgnoreCase("help")) {
                this.writeln("\nList of variables:\n" +
                             "    activity\n" +
                             "    engON\n" +
                             "    throttle\n" +
                             "    fuel\n" +
                             "    stageDumped\n" +
                             "    yFlame\n" +
                             "    FPS\n" +
                             "    UPS\n" +
                             "    upTime\n" +
                             "    warpF\n" +
                             "    accG\n" +
                             "    Isp\n" +
                             "    dM\n" +
                             "    Mfuel_t\n" +
                             "    Mt\n" +
                             "    scaleF\n" +
                             "    totalAcc\n" +
                             "    rktVt\n" +
                             "    altitude");
            }
            else if (var.equalsIgnoreCase("list")) {
                this.writeln("\nList of variables:\n" +
                             "    activity     = " + StateVars.activity + "\n" +
                             "    engON        = " + StateVars.engON + "\n" +
                             "    throttle     = " + StateVars.throttle + "\n" +
                             "    fuel         = " + StateVars.fuel + "\n" +
                             "    stageDumped  = " + StateVars.stageDumped + "\n" +
                             "    yFlame       = " + StateVars.yFlame + "\n" +
                             "    FPS          = " + StateVars.FPS + "\n" +
                             "    UPS          = " + StateVars.UPS + "\n" +
                             "    upTime       = " + StateVars.upTime + "\n" +
                             "    warpF        = " + StateVars.warpF + "\n" +
                             "    accG         = " + StateVars.accG + "\n" +
                             "    Isp          = " + StateVars.Isp + "\n" +
                             "    dM           = " + StateVars.dM + "\n" +
                             "    Mfuel_t      = " + StateVars.Mfuel_t + "\n" +
                             "    Mt           = " + StateVars.Mt + "\n" +
                             "    scaleF       = " + StateVars.scaleF + "\n" +
                             "    totalAcc     = " + StateVars.totalAcc + "\n" +
                             "    rktVt        = " + StateVars.rktVt + "\n" +
                             "    altitude     = " + StateVars.altitude);
            }
            else if (var.equalsIgnoreCase("activity")) {
                if (sT.hasMoreTokens()) {
                    StateVars.activity = Byte.parseByte(sT.nextToken());
                }
                else this.writeln("    activity = " + StateVars.activity);
            }
            else if (var.equalsIgnoreCase("engON")) {
                if (sT.hasMoreTokens()) {
                    StateVars.engON = Byte.parseByte(sT.nextToken());
                }
                else this.writeln("    engON = " + StateVars.engON);
            }
            else if (var.equalsIgnoreCase("throttle")) {
                if (sT.hasMoreTokens()) {
                    StateVars.throttle = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    throttle = " + StateVars.throttle);
            }
            else if (var.equalsIgnoreCase("fuel")) {
                if (sT.hasMoreTokens()) {
                    StateVars.fuel = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    fuel = " + StateVars.fuel);
            }
            else if (var.equalsIgnoreCase("stageDumped")) {
                if (sT.hasMoreTokens()) {
                    StateVars.stageDumped = Byte.parseByte(sT.nextToken());
                }
                else this.writeln("    stageDumped = " + StateVars.stageDumped);
            }
            else if (var.equalsIgnoreCase("yFlame")) {
                if (sT.hasMoreTokens()) {
                    StateVars.yFlame = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    yFlame = " + StateVars.yFlame);
            }
            else if (var.equalsIgnoreCase("FPS")) {
                if (sT.hasMoreTokens()) {
                    StateVars.FPS = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    FPS = " + StateVars.FPS);
            }
            else if (var.equalsIgnoreCase("UPS")) {
                if (sT.hasMoreTokens()) {
                    StateVars.UPS = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    UPS = " + StateVars.UPS);
            }
            else if (var.equalsIgnoreCase("upTime")) {
                if (sT.hasMoreTokens()) {
                    StateVars.upTime = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    upTime = " + StateVars.upTime);
            }
            else if (var.equalsIgnoreCase("warpF")) {
                if (sT.hasMoreTokens()) {
                    StateVars.warpF = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    warpF = " + StateVars.warpF);
            }
            else if (var.equalsIgnoreCase("accG")) {
                if (sT.hasMoreTokens()) {
                    StateVars.accG = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    accG = " + StateVars.accG);
            }
            else if (var.equalsIgnoreCase("Isp")) {
                if (sT.hasMoreTokens()) {
                    StateVars.Isp = Integer.parseInt(sT.nextToken());
                }
                else this.writeln("    Isp = " + StateVars.Isp);
            }
            else if (var.equalsIgnoreCase("dM")) {
                if (sT.hasMoreTokens()) {
                    StateVars.dM = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    dM = " + StateVars.dM);
            }
            else if (var.equalsIgnoreCase("Mfuel_t")) {
                if (sT.hasMoreTokens()) {
                    StateVars.Mfuel_t = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    Mfuel_t = " + StateVars.Mfuel_t);
            }
            else if (var.equalsIgnoreCase("Mt")) {
                if (sT.hasMoreTokens()) {
                    StateVars.Mt = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    Mt = " + StateVars.Mt);
            }
            else if (var.equalsIgnoreCase("scaleF")) {
                if (sT.hasMoreTokens()) {
                    StateVars.scaleF = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    scaleF = " + StateVars.scaleF);
            }
            else if (var.equalsIgnoreCase("totalAcc")) {
                if (sT.hasMoreTokens()) {
                    StateVars.totalAcc = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    totalAcc = " + StateVars.totalAcc);
            }
            else if (var.equalsIgnoreCase("rktVt")) {
                if (sT.hasMoreTokens()) {
                    StateVars.rktVt = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    rktVt = " + StateVars.rktVt);
            }
            else if (var.equalsIgnoreCase("altitude")) {
                if (sT.hasMoreTokens()) {
                    StateVars.altitude = Double.parseDouble (sT.nextToken());
                }
                else this.writeln("    altitude = " + StateVars.altitude);
            }
            else {
                this.writeln("[E] No such variable");
            }
        }
        else if (cmd.equalsIgnoreCase("HELP")) {
            this.writeln("Commands\n" +
                         " 1.  select [ENGINE NO]\n" +
                         " 2.  thrust [% VALUE without % sign]\n" +
                         " 3.  drop [STAGE NO]\n" +
                         " 4.  time warp [WARP factor]\n" +
                         " 5.  status");
        }
        else {
            this.writeln("[E] no such command: " + command);
            this.writeln("Enter HELP for commands list and syntax");
        }
    }*/

    /**
     * Abstract method: Runs the code required to be run on thread
     */
    @Override
    public void run()
    {
        while (running && StateVars.running) {
            String command = readCommand();
            if (!command.equalsIgnoreCase("")) try {
                // evaluate(command);
                if (command.equalsIgnoreCase("exit")) {
                    StateVars.running = false;
                    break;
                }
            } catch (Exception ex) {
                this.writeln("[E] " + ex);
                this.writeln("    User input error. Please try again");
            }
        }
    }

    // Method responsible for starting thread.
    public synchronized void start()
    {
        if (running) return;
        StateVars.running = running = true;
        thread = new Thread(this);
        thread.start();
    }

    // Method responsible for stopping thread
    public synchronized void stop()
    {
        if (!running) return;
        StateVars.running = running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    //Event actions
    /*private void inputThrust(int thrust) {
        if ((StateVars.engON == 1 && StateVars.stageDumped == 0) || (StateVars.engON == 2 && StateVars.stageDumped == 1) || (StateVars.engON == 3 && StateVars.stageDumped == 2)) {
            if (!(StateVars.activity >= Activities.LAUNCH)) {
                try {
                    StateVars.throttle = thrust;
                } catch (Exception ex) {
                    this.writeln("[E] INVALID ENTRY FOR THRUST");
                }
                if (StateVars.throttle < 0) {
                    this.writeln("[E] NO ENGINE FOR ANTI THRUST");
                } else if (StateVars.throttle < 10) {
                    this.writeln("> Analyzing thrust...");
                    this.writeln("[W] LOW THRUST");
                } else if (StateVars.throttle >= 101) {
                    this.writeln("> Analyzing thrust...");
                    this.writeln("[E] INVALID COMMAND FOR: ");
                    this.writeln("THRUST BEYOND 100");
                } else if (StateVars.throttle >= 51) {
                    StateVars.activity = Activities.LAUNCH;
                    this.writeln("> Analyzing thrust...");
                    this.writeln("[W] THRUST TOO HIGH\n[W] CREW WON'T SURVIVE");
                } else {
                    StateVars.activity = Activities.LAUNCH;
                    this.writeln("> Analyzing thrust...");
                    this.writeln("THRUST OPTIMUM");
                    this.writeln("Rocket launched thrusting at: " + StateVars.throttle);
                }
            } else {
                tmp = StateVars.throttle;
                StateVars.throttle = thrust;
                if (StateVars.throttle < 0) {
                    StateVars.throttle = tmp;
                    this.writeln("[E] INVALID ENTRY FOR THRUST");
                } else if (StateVars.throttle >= 101) {
                    StateVars.throttle = tmp;
                    this.writeln("> Analyzing thrust...");
                    this.writeln("[E] INVALID COMMAND FOR: ");
                    this.writeln("THRUST BEYOND 100");
                } else {
                    this.writeln("STAGE " + (StateVars.stageDumped + 1) + ": Thrust entered: " + StateVars.throttle);
                }
            }
        } else {
            this.writeln("Stage " + (StateVars.stageDumped + 1) + " Engine offline");
        }
    }

    private void dumpStg1ActionPerformed() {
        if (StateVars.stageDumped == 0) {
            StateVars.stageDumped = 1;
            isOn_engS1 = false;
            this.writeln("Dumped stage 1");
            StateVars.throttle = 0;
            StateVars.yFlame -= 128;
        } else {
            this.writeln("[E] STAGE DOESN'T EXIST");
        }
    }

    private void dumpStg2ActionPerformed() {
        if (StateVars.stageDumped == 0 || StateVars.stageDumped == 1) {
            isOn_engS1 = false;
            isOn_engS2 = false;
            this.writeln("Dumped stage 2");
            StateVars.throttle = 0;
            StateVars.yFlame -= 128;
            if (StateVars.stageDumped == 0) {
                StateVars.yFlame -= 128;
            }
            StateVars.stageDumped = 2;
        } else {
            this.writeln("[E] STAGE DOESN'T EXIST");
        }
    }

    private void ejectPayloadActionPerformed() {
        if (StateVars.activity >= Activities.BEYOND_KARMAN) {
            if (StateVars.stageDumped == 0 || StateVars.stageDumped == 1 || StateVars.stageDumped == 2) {
                isOn_engS1 = false;
                isOn_engS2 = false;
                isOn_engS3 = false;
                this.writeln("Dumped stage 3");
                this.writeln("Ejecting Module....");
                StateVars.throttle = 0;
                StateVars.activity = Activities.RELEASE_PAYLOAD;
                if (StateVars.stageDumped == 0) {
                    StateVars.yFlame -= 128;
                    StateVars.stageDumped = 1;
                }
                if (StateVars.stageDumped == 1) {
                    StateVars.yFlame -= 128;
                    StateVars.stageDumped = 2;
                }
                if (StateVars.stageDumped == 2) {
                    StateVars.yFlame -= 128;
                }
                StateVars.stageDumped = 3;
            } else {
                this.writeln("[E] COMMAND INVALID");
            }
        } else {
            this.writeln("[E] CAN'T REMOVE FAIRINGS");
        }
    }*/

    private void warpTimeActionPerformed(int warpF)
    {
        StateVars.warpF = warpF;
    }
}
