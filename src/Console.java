import java.io.BufferedReader;
import java.util.StringTokenizer;

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
 * var [AssetsVars.variable] [value]
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
    protected Thread th;
    protected boolean running = false;

    protected String read()
    {
        BufferedReader stdin = new BufferedReader(
            new java.io.InputStreamReader(System.in));
        try {
            return stdin.readLine();
        } catch (java.io.IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    protected String readCommand()
    {
        BufferedReader stdin = new BufferedReader(
            new java.io.InputStreamReader(System.in));
        try {
            System.out.print("\nLRS shell> ");
            try {
                return stdin.readLine().trim();
            } catch (NullPointerException ex) {
                System.out.println();
                System.exit(0);
            }
        } catch (java.io.IOException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    protected void write(String str)
    {
        System.out.println(str);
    }

    /*private void evaluate(String command) throws Exception {
        StringTokenizer sT = new StringTokenizer(command, " \n");
        String cmd = sT.nextToken();
        if (cmd.equalsIgnoreCase("SELECT")) {
            AssetsVars.engON = Byte.parseByte(sT.nextToken());
            switch(AssetsVars.engON) {
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
                    System.out.println("\r[E] unknown error\n");
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
                    System.out.println("\r[E] unknown error\n");
            }
        }
        else if (cmd.equalsIgnoreCase("TIME")) {
            if (sT.nextToken().equalsIgnoreCase("WARP")) {
                AssetsVars.warpF = Integer.parseInt(sT.nextToken());
            }
            else throw new Exception ("incomplete command: expected WARP after TIME");
        }
        else if (cmd.equalsIgnoreCase("STATUS")) {
            System.out.println();
            System.out.println("\rFuel:       " + AssetsVars.fuel + " %");
            System.out.println("\rThrust:     " + AssetsVars.throttle + " %");
            System.out.println();
            System.out.println("\rVelocity:   " + AssetsVars.rktVt + " m/s");
            System.out.println("\rAltitude:   " + AssetsVars.altitude + " m from nearest body");
            System.out.println();
            System.out.println("\rFuel:       " + AssetsVars.Mfuel_t + " kg");
            System.out.println("\rMass:       " + AssetsVars.Mt + " kg");
            System.out.println();
            System.out.println("\rEngine:     " + AssetsVars.engON + " is ON");
            System.out.println("\rLast stage: " + (int)(AssetsVars.stageDumped + 1));
            System.out.println("\rTime warp:  " + AssetsVars.warpF + " times");
        }
        else if (cmd.equals ("var")) {
            String var = sT.nextToken();
            if (var.equalsIgnoreCase("help")) {
                Console.write("\nList of variables:\n" +
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
                Console.write("\nList of variables:\n" +
                               "    activity     = " + AssetsVars.activity + "\n" +
                               "    engON        = " + AssetsVars.engON + "\n" +
                               "    throttle     = " + AssetsVars.throttle + "\n" +
                               "    fuel         = " + AssetsVars.fuel + "\n" +
                               "    stageDumped  = " + AssetsVars.stageDumped + "\n" +
                               "    yFlame       = " + AssetsVars.yFlame + "\n" +
                               "    FPS          = " + AssetsVars.FPS + "\n" +
                               "    UPS          = " + AssetsVars.UPS + "\n" +
                               "    upTime       = " + AssetsVars.upTime + "\n" +
                               "    warpF        = " + AssetsVars.warpF + "\n" +
                               "    accG         = " + AssetsVars.accG + "\n" +
                               "    Isp          = " + AssetsVars.Isp + "\n" +
                               "    dM           = " + AssetsVars.dM + "\n" +
                               "    Mfuel_t      = " + AssetsVars.Mfuel_t + "\n" +
                               "    Mt           = " + AssetsVars.Mt + "\n" +
                               "    scaleF       = " + AssetsVars.scaleF + "\n" +
                               "    totalAcc     = " + AssetsVars.totalAcc + "\n" +
                               "    rktVt        = " + AssetsVars.rktVt + "\n" +
                               "    altitude     = " + AssetsVars.altitude);
            }
            else if (var.equalsIgnoreCase("activity")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.activity = Byte.parseByte(sT.nextToken());
                }
                else System.out.println("    activity = " + AssetsVars.activity);
            }
            else if (var.equalsIgnoreCase("engON")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.engON = Byte.parseByte(sT.nextToken());
                }
                else System.out.println("    engON = " + AssetsVars.engON);
            }
            else if (var.equalsIgnoreCase("throttle")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.throttle = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    throttle = " + AssetsVars.throttle);
            }
            else if (var.equalsIgnoreCase("fuel")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.fuel = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    fuel = " + AssetsVars.fuel);
            }
            else if (var.equalsIgnoreCase("stageDumped")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.stageDumped = Byte.parseByte(sT.nextToken());
                }
                else System.out.println("    stageDumped = " + AssetsVars.stageDumped);
            }
            else if (var.equalsIgnoreCase("yFlame")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.yFlame = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    yFlame = " + AssetsVars.yFlame);
            }
            else if (var.equalsIgnoreCase("FPS")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.FPS = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    FPS = " + AssetsVars.FPS);
            }
            else if (var.equalsIgnoreCase("UPS")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.UPS = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    UPS = " + AssetsVars.UPS);
            }
            else if (var.equalsIgnoreCase("upTime")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.upTime = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    upTime = " + AssetsVars.upTime);
            }
            else if (var.equalsIgnoreCase("warpF")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.warpF = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    warpF = " + AssetsVars.warpF);
            }
            else if (var.equalsIgnoreCase("accG")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.accG = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    accG = " + AssetsVars.accG);
            }
            else if (var.equalsIgnoreCase("Isp")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.Isp = Integer.parseInt(sT.nextToken());
                }
                else System.out.println("    Isp = " + AssetsVars.Isp);
            }
            else if (var.equalsIgnoreCase("dM")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.dM = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    dM = " + AssetsVars.dM);
            }
            else if (var.equalsIgnoreCase("Mfuel_t")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.Mfuel_t = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    Mfuel_t = " + AssetsVars.Mfuel_t);
            }
            else if (var.equalsIgnoreCase("Mt")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.Mt = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    Mt = " + AssetsVars.Mt);
            }
            else if (var.equalsIgnoreCase("scaleF")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.scaleF = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    scaleF = " + AssetsVars.scaleF);
            }
            else if (var.equalsIgnoreCase("totalAcc")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.totalAcc = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    totalAcc = " + AssetsVars.totalAcc);
            }
            else if (var.equalsIgnoreCase("rktVt")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.rktVt = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    rktVt = " + AssetsVars.rktVt);
            }
            else if (var.equalsIgnoreCase("altitude")) {
                if (sT.hasMoreTokens()) {
                    AssetsVars.altitude = Double.parseDouble (sT.nextToken());
                }
                else System.out.println("    altitude = " + AssetsVars.altitude);
            }
            else {
                System.out.println("[E] No such variable");
            }
        }
        else if (cmd.equalsIgnoreCase("HELP")) {
            System.out.println("\rCommands\n" +
                                " 1.  select [ENGINE NO]\n" +
                                " 2.  thrust [% VALUE without % sign]\n" +
                                " 3.  drop [STAGE NO]\n" +
                                " 4.  time warp [WARP factor]\n" +
                                " 5.  status");
        }
        else if (cmd.equalsIgnoreCase("EXIT")) {
            running = false;
            AssetsVars.running = false;
        }
        else {
            System.out.println("\r[E] no such command: " + command);
            System.out.println("\rEnter HELP for commands list and syntax");
        }
    }*/

    /**
     * Abstract method: Runs the code required to be run on thread
     */
    @Override
    public void run()
    {
        while (running) {
            String command = readCommand();
            if (!command.equalsIgnoreCase("")) try {
                // evaluate(AssetsVars.command);
                if (command.equalsIgnoreCase("exit")) {
                    AssetsVars.quit = true;
                    running = false;
                    th.stop();
                }
            } catch (Exception e) {
                System.out.println("\r[E] " + e);
                System.out.println("\r    User input error. Please try again");
            }
        }
        th.stop();
    }

    //Method responsible for starting thread.
    public synchronized void start()
    {
        if (running)
            return;
        running = true;
        th = new Thread(this);
        th.start();
    }

    //Method responsible for stopping thread
    public synchronized void stop()
    {
        if (!running)
            return;
        running = false;
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //Event actions
    /*private void inputThrust(int thrust) {
        if ((AssetsVars.engON == 1 && AssetsVars.stageDumped == 0) || (AssetsVars.engON == 2 && AssetsVars.stageDumped == 1) || (AssetsVars.engON == 3 && AssetsVars.stageDumped == 2)) {
            if (!(AssetsVars.activity >= Activities.LAUNCH)) {
                try {
                    AssetsVars.throttle = thrust;
                } catch (Exception ex) {
                    System.out.println("\r[E] INVALID ENTRY FOR THRUST");
                }
                if (AssetsVars.throttle < 0) {
                    System.out.println("\r[E] NO ENGINE FOR ANTI THRUST");
                } else if (AssetsVars.throttle < 10) {
                    System.out.println("\r> Analyzing thrust...");
                    System.out.println("\r[W] LOW THRUST");
                } else if (AssetsVars.throttle >= 101) {
                    System.out.println("\r> Analyzing thrust...");
                    System.out.println("\r[E] INVALID COMMAND FOR: ");
                    System.out.println("\rTHRUST BEYOND 100");
                } else if (AssetsVars.throttle >= 51) {
                    AssetsVars.activity = Activities.LAUNCH;
                    System.out.println("\r> Analyzing thrust...");
                    System.out.println("\r[W] THRUST TOO HIGH\n[W] CREW WON'T SURVIVE");
                } else {
                    AssetsVars.activity = Activities.LAUNCH;
                    System.out.println("\r> Analyzing thrust...");
                    System.out.println("\rTHRUST OPTIMUM");
                    System.out.println("\rRocket launched thrusting at: " + AssetsVars.throttle);
                }
            } else {
                tmp = AssetsVars.throttle;
                AssetsVars.throttle = thrust;
                if (AssetsVars.throttle < 0) {
                    AssetsVars.throttle = tmp;
                    System.out.println("\r[E] INVALID ENTRY FOR THRUST");
                } else if (AssetsVars.throttle >= 101) {
                    AssetsVars.throttle = tmp;
                    System.out.println("\r> Analyzing thrust...");
                    System.out.println("\r[E] INVALID COMMAND FOR: ");
                    System.out.println("\rTHRUST BEYOND 100");
                } else {
                    System.out.println("\rSTAGE " + (AssetsVars.stageDumped + 1) + ": Thrust entered: " + AssetsVars.throttle);
                }
            }
        } else {
            System.out.println("\rStage " + (AssetsVars.stageDumped + 1) + " Engine offline");
        }
    }

    private void dumpStg1ActionPerformed() {
        if (AssetsVars.stageDumped == 0) {
            AssetsVars.stageDumped = 1;
            isOn_engS1 = false;
            System.out.println("\rDumped stage 1");
            AssetsVars.throttle = 0;
            AssetsVars.yFlame -= 128;
        } else {
            System.out.println("\r[E] STAGE DOESN'T EXIST");
        }
    }

    private void dumpStg2ActionPerformed() {
        if (AssetsVars.stageDumped == 0 || AssetsVars.stageDumped == 1) {
            isOn_engS1 = false;
            isOn_engS2 = false;
            System.out.println("\rDumped stage 2");
            AssetsVars.throttle = 0;
            AssetsVars.yFlame -= 128;
            if (AssetsVars.stageDumped == 0) {
                AssetsVars.yFlame -= 128;
            }
            AssetsVars.stageDumped = 2;
        } else {
            System.out.println("\r[E] STAGE DOESN'T EXIST");
        }
    }

    private void ejectPayloadActionPerformed() {
        if (AssetsVars.activity >= Activities.BEYOND_KARMAN) {
            if (AssetsVars.stageDumped == 0 || AssetsVars.stageDumped == 1 || AssetsVars.stageDumped == 2) {
                isOn_engS1 = false;
                isOn_engS2 = false;
                isOn_engS3 = false;
                System.out.println("\rDumped stage 3");
                System.out.println("\rEjecting Module....");
                AssetsVars.throttle = 0;
                AssetsVars.activity = Activities.RELEASE_PAYLOAD;
                if (AssetsVars.stageDumped == 0) {
                    AssetsVars.yFlame -= 128;
                    AssetsVars.stageDumped = 1;
                }
                if (AssetsVars.stageDumped == 1) {
                    AssetsVars.yFlame -= 128;
                    AssetsVars.stageDumped = 2;
                }
                if (AssetsVars.stageDumped == 2) {
                    AssetsVars.yFlame -= 128;
                }
                AssetsVars.stageDumped = 3;
            } else {
                System.out.println("\r[E] COMMAND INVALID");
            }
        } else {
            System.out.println("\r[E] CAN'T REMOVE FAIRINGS");
        }
    }*/

    private void warpTimeActionPerformed(int warpF)
    {
        AssetsVars.warpF = warpF;
    }
}
