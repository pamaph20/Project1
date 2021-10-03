/**
 * conifguration ovject for storing the
 * settings for running our maze solver
 */

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;


public class Config {
        //store mode
        private char mode = 0; //null character
        //store the output mode
        private char outputMode = 'm';


        //keep track of checkpoint mode
        private boolean checkpoint1 = false;
        private boolean checkpoint2 = false;

        //GetOpt options
        private static LongOpt[] longOptions =  {
                new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
                new LongOpt("stack", LongOpt.NO_ARGUMENT,null,'s'),
                new LongOpt("queue", LongOpt.NO_ARGUMENT,null,'q'),
                new LongOpt("output", LongOpt.REQUIRED_ARGUMENT,null,'o'),
                new LongOpt("checkpoint1", LongOpt.NO_ARGUMENT,null,'x'),
                new LongOpt("checkpoint2", LongOpt.NO_ARGUMENT,null,'y')
        };

        /**
         * construct our configuration object using GetOpt
         * @param args string array of command line arguments
         */
        public  Config(String[] args){
            //we will do all getopt parsing

            //made a getopt object
            Getopt g = new Getopt("mazeSolver", args,"hsqo:xy", longOptions );
            g.setOpterr(true);

            int choice;

            //process each arg from the command line in turn
            while(( choice = g.getopt()) != -1){
                //check which arg we are processing
                switch (choice) {
                    case 'h':
                        printHelp();
                        break;
                    case 's':
                    case 'q':
                        //verify this was provided once!!
                        //if mode is not "NULL" character (int value 0), we already set the mode --> error!
                        if (mode != 0){
                            System.err.println("Mode was already specified");
                            System.exit(1);
                    }
                        mode = (char) choice;
                        break;
                    case 'o':
                        String reqOutput = g.getOptarg();
                        if(reqOutput.equals("map") || reqOutput.equals("list")){
                            outputMode = reqOutput.charAt(0);
                        }else{
                            System.err.println("Unknown output type -" + (char) choice);
                        }

                        break;
                    case 'x':
                        checkpoint1 = true;


                        break;
                    case 'y':
                        checkpoint2 = true;


                        break;
                    default:
                        //none of the above
                        System.err.println("Unknown command line option -" +(char) choice);
                        System.exit(1);
                }//switch
            }//while

            //do some final error checking
            //check if --stack or --queue mode specified

            if(mode == 0) {
                System.err.println("You must specify --stack or --queue mode");
            }



        }

        public boolean isQueueMode(){
            return mode == 'q';
        }

        public boolean isMapOutputMode(){
            return outputMode == 'm';
        }

        public boolean isCheckpoint1() {
            return checkpoint1;
        }

        public boolean isCheckpoint2() {
            return checkpoint2;
        }


    private void printHelp() {
        //TODO make a better help message
        System.out.println("");
        System.out.println("This program gives a solution to solve an inputted maze in java");
        System.exit(1);

    }

    public static void main(String[] args) {

    }}




