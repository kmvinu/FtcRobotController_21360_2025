package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="DriverWithOneController", group="TeleOp")
public class DriverWithOneController extends OpMode{

    // DEFINE robot
    RobotHardware robot = new RobotHardware();
       // CREATING THE HARDWARE MAP
    
    //CONSTANTS AND VARIABLES
    static final double ARM_MOTOR_TIC_COUNT=1440;
   
  
   // RUN ONCE ON INIT()
    @Override
    public void init() {
        telemetry.addData("STATUS", "Hardware check");
        telemetry.update();
        robot.init(hardwareMap);
        telemetry.addData("STATUS", "Initialized");
        telemetry.update();
} 

   // LOOP ON init()
    @Override
    public void init_loop() {
      robot.servoRight.setPosition(0.6);
      robot.servoLeft.setPosition(0.4);

    }

    //RUNS ONCE ON PLAY()
    @Override
    public void start() {
        double half_turn = ARM_MOTOR_TIC_COUNT/2;
        double quarter_turn = ARM_MOTOR_TIC_COUNT/4;
        //Set counter of tics to 0
        robot.motor5.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set to target Position
        robot.motor5.setTargetPosition((int)quarter_turn);
        //set power max = 1
        robot.motor5.setPower(0.1);
        //Instruct motor to run to target position
        robot.motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Do nothing while this arm is working
      //robot.servoWristLeft.setDirection(Servo.Direction.FORWARD);
      //robot.servoWristLeft.setPosition(0.9);
        while( robot.motor5.isBusy()){
            telemetry.addData("Status", "Encoder Motor Working");
            telemetry.update();
        }
        
    }
  
    

    // LOOPS ON start()
    @Override
    public void loop() {
        double G1rightStickY = -gamepad1.right_stick_y;
        double G1leftStickY = -gamepad1.left_stick_y;
        //double G1rightStickX = -gamepad1.right_stick_x;
       // double G1leftStickX = -gamepad1.left_stick_x;
        boolean G1rightBumper = gamepad1.right_bumper;
        boolean G1leftBumper = gamepad1.left_bumper;
        boolean G2y = gamepad1.y;
        boolean G1x = gamepad1.x;
        boolean G2a = gamepad1.a; 
        boolean G2b = gamepad1.b; 
        boolean leftTrigger = gamepad1.left_trigger != 0;
        boolean rightTrigger = gamepad1.right_trigger !=0;
        boolean slowMode = false;
        
//changed all motor power. Change back to 1 and -1
        if (rightTrigger){
            if (slowMode == false){
                slowMode = true;
            }  else { 
                slowMode = false;
            }
            
        }
        
        if(!slowMode) {
        if (G1rightBumper) {
            robot.TopRight.setPower(0.5); //-
            robot.BottomRight.setPower(-0.5); // +
            robot.BottomLeft.setPower(0.5);//-
            robot.TopLeft.setPower(-0.5); //+
        
        } else if (G1leftBumper) {
            robot.TopRight.setPower(-0.5);
            robot.BottomRight.setPower(0.5);
            robot.BottomLeft.setPower(-0.5);
            robot.TopLeft.setPower(0.5);
        } else {
            robot.TopRight.setPower(G1rightStickY);
            robot.BottomRight.setPower(G1rightStickY);
            robot.BottomLeft.setPower(-G1leftStickY);
            robot.TopLeft.setPower(-G1leftStickY);
        }
        } else if (slowMode){
         if (G1rightBumper) {
            robot.TopRight.setPower(-0.5);
            robot.BottomRight.setPower(0.5);
            robot.BottomLeft.setPower(-0.5);
            robot.TopLeft.setPower(0.5);
        
        } else if (G1leftBumper) {
            robot.TopRight.setPower(0.5);
            robot.BottomRight.setPower(-0.5);
            robot.BottomLeft.setPower(0.5);
            robot.TopLeft.setPower(-0.5);
        } else {
            robot.TopRight.setPower(-G1rightStickY/4);
            robot.BottomRight.setPower(-G1rightStickY/4);
            robot.BottomLeft.setPower(G1leftStickY/4);
            robot.TopLeft.setPower(G1leftStickY/4);
        }           
        }

        
        if (leftTrigger) {
           //robot.servo0.setPosition(0);
           robot.servoLeft.setPosition(0.1);
           //robot.servoRight.setPosition(0.5);
        } else  {
           //robot.servo0.setPosition(0.2)
           //optimum =0.4
          robot.servoLeft.setPosition(0.4);
          //robot.servoRight.setPosition(0.4);
        }
        
        if (rightTrigger) {
            //robot.servoLeft.setPosition(0.9);
            robot.servoRight.setPosition(0.9);
               ;
        } else {
            //optimum = 0.6
           robot.servoRight.setPosition(0.6);
        }
        
            if (G2a) {
//                armLeft.setTargetPosition(armHomePosition);
//                armRight.setTargetPosition(armHomePosition);
//                armLeft.setPower(1.0);
//                armRight.setPower(1.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                wrist.setPosition(wristUpPosition);
                  robot.motor5.setTargetPosition(25);
                  robot.motor5.setPower(0.1);
                  robot.motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
           if (G2y) {
       // telemetry.addData("G2y", robot.TopRight.getPower());
                 robot.motor5.setTargetPosition(200);
                  robot.motor5.setPower(0.1);
                  robot.motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
        if (G1x) {
       //           robot.servoRight.setPosition(0.3);
      //robot.servoLeft.setPosition(0.3);
        //robot.servoWristLeft.getDeviceName();
        //robot.servoWristRight.getDeviceName();
          //ServoController ctrl = robot.servoWristLeft.getController();
          //double srvpos = robot.servoWristLeft.getPosition();
          //telemetry.addData("Servo Details",srpos);
        //Double pos1 = robot.servoWristLeft.getPosition();
       // robot.servoWristRight.setPosition(0.8);
            }
    } 

    // RUN ONCE ON STOP 
    @Override
    public void stop() {
    } 
}
