/**
 * @name Lunar Rocket Simulator LRS
 * @version 2021.5.25
 */
class Main
{
    //main method that begins software execution.
    public static void main (String[] args)
    {
        /* Create new Sim object and start thread,
         * Thread is used to allow running multiple codes together.
         * This will aid in reducing code execution wait time
         * and also aids in programming logic.
         */
        if (args.length >= 1)
            Sim.noGUI = args[0].equalsIgnoreCase("-noGUI");
        new Sim("Lunar Space Simulator LRS", 768, 582).start();
    }
}
