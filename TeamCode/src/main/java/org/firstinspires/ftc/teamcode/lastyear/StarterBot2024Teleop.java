/* Copyright (c) 2017 FIRST. All rights reserved.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Starter Bot 2024", group="Iterative Opmode")

public class StarterBot2024Teleop extends OpMode
{
    // Declare OpMode members.

//    private ElapsedTime runtime = new ElapsedTime();
//    private DcMotor leftDrive = null;
//    private DcMotor rightDrive = null;
//    private DcMotor armLeft = null;
//    private DcMotor armRight = null;
//    private Servo gripper = null;
//    private Servo wrist = null;


    private DcMotor motor5 = null;
    static final double ARM_MOTOR_TIC_COUNT=1440;
    
    private boolean manualMode = false;
//    private double armSetpoint = 0.0;
//
    private final double armManualDeadband = 0.03;
    private final int heavyArmHomePosition = 2;
//
//    private final double gripperClosedPosition = 1.0;
//    private final double gripperOpenPosition = 0.5;
//    private final double wristUpPosition = 1.0;
//    private final double wristDownPosition = 0.0;
//
//    private final int armHomePosition = 0;
//    private final int armIntakePosition = 10;
//    private final int armScorePosition = 600;
      private final int armShutdownThreshold = 5;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing heavyDutyArm");
        motor5 = hardwareMap.get(DcMotor.class,"motor5");
//        /*
//        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
//        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
//        armLeft  = hardwareMap.get(DcMotor.class, "armLeft");
//        armRight = hardwareMap.get(DcMotor.class, "armRight");
//        gripper = hardwareMap.get(Servo.class, "gripper");
//        wrist = hardwareMap.get(Servo.class, "wrist");
//
//         */



//        leftDrive.setDirection(DcMotor.Direction.FORWARD);
//        rightDrive.setDirection(DcMotor.Direction.REVERSE);
//        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//
//        armLeft.setDirection(DcMotor.Direction.FORWARD);
//        armRight.setDirection(DcMotor.Direction.REVERSE);
//        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        armLeft.setPower(0.0);
//        armRight.setPower(0.0);
        motor5.setPower(0.0);
        motor5.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
//        runtime.reset();
        
//        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armLeft.setTargetPosition(armHomePosition);
//        armRight.setTargetPosition(armHomePosition);
//        armLeft.setPower(1.0);
//        armRight.setPower(1.0);
//        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double half_turn = ARM_MOTOR_TIC_COUNT/2;
        double quarter_turn = ARM_MOTOR_TIC_COUNT/4;
        //Set counter of tics to 0
        motor5.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set to target Position
        motor5.setTargetPosition((int)quarter_turn);
        //set power max = 1
        motor5.setPower(0.1);
        //Instruct motor to run to target position
        motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Do nothing while this arm is working
        while( motor5.isBusy()){
            telemetry.addData("Status", "Encoder Motor Working");
            telemetry.update();
        }
        //Once motor no longer working stop and reset
        //heavyDutyArm.setPower(0);
        //set the Motor back to reset positon
        //heavyDutyArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftPower;
        double rightPower;
        double manualArmPower;
        
        //DRIVE
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

//        leftDrive.setPower(leftPower);
//        rightDrive.setPower(rightPower);
        
        //ARM & WRIST
        manualArmPower = gamepad1.right_trigger - gamepad1.left_trigger;
        if (Math.abs(manualArmPower) > armManualDeadband) {
            if (!manualMode) {
//                armLeft.setPower(0.0);
//                armRight.setPower(0.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motor5.setPower(0.0);
                motor5.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                manualMode = true;
            }
//            armLeft.setPower(manualArmPower);
//            armRight.setPower(manualArmPower);
            motor5.setPower(manualArmPower);
        }
        else {
            if (manualMode) {
//                armLeft.setTargetPosition(armLeft.getCurrentPosition());
//                armRight.setTargetPosition(armRight.getCurrentPosition());
//                armLeft.setPower(1.0);
//                armRight.setPower(1.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor5.setTargetPosition(motor5.getCurrentPosition());
                motor5.setPower(0.1);
                motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                manualMode = false;
            }
            
            //preset buttons
            if (gamepad1.a) {
//                armLeft.setTargetPosition(armHomePosition);
//                armRight.setTargetPosition(armHomePosition);
//                armLeft.setPower(1.0);
//                armRight.setPower(1.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                wrist.setPosition(wristUpPosition);
                  double base_position = ARM_MOTOR_TIC_COUNT/6;
                  motor5.setTargetPosition(25);
                  motor5.setPower(0.1);
                  motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if (gamepad1.b) {
//                armLeft.setTargetPosition(armIntakePosition);
//                armRight.setTargetPosition(armIntakePosition);
//                armLeft.setPower(1.0);
//                armRight.setPower(1.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                wrist.setPosition(wristDownPosition);
                motor5.setTargetPosition(heavyArmHomePosition);
                motor5.setPower(0.8);
                motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if (gamepad1.y) {
//                armLeft.setTargetPosition(armScorePosition);
//                armRight.setTargetPosition(armScorePosition);
//                armLeft.setPower(1.0);
//                armRight.setPower(1.0);
//                armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                wrist.setPosition(wristUpPosition);
                motor5.setTargetPosition(heavyArmHomePosition);
                motor5.setPower(0.1);
                motor5.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
        }
        
        //Re-zero encoder button
        if (gamepad1.start) {
//            armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            armLeft.setPower(0.0);
//            armRight.setPower(0.0);
//            manualMode = false;
            motor5.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor5.setPower(0.0);
            manualMode = false;
        }
        
        //Watchdog to shut down motor once the arm reaches the home position
        if (!manualMode &&
        motor5.getMode() == DcMotor.RunMode.RUN_TO_POSITION &&
        motor5.getTargetPosition() <= armShutdownThreshold &&
        motor5.getCurrentPosition() <= armShutdownThreshold
        ) {
//            armLeft.setPower(0.0);
//            armRight.setPower(0.0);
//            armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor5.setPower(0.0);
            motor5.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        
        //GRIPPER
//        if (gamepad1.left_bumper || gamepad1.right_bumper) {
//            gripper.setPosition(gripperOpenPosition);
//        }
//        else {
//            gripper.setPosition(gripperClosedPosition);
//        }

//        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Gamepad", "drive (%.2f), turn (%.2f)", drive, turn);
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Manual Power", manualArmPower);
//        telemetry.addData("Arm Pos:",
//            "left = " +
//            ((Integer)armLeft.getCurrentPosition()).toString() +
//            ", right = " +
//            ((Integer)armRight.getCurrentPosition()).toString());
//        telemetry.addData("Arm Pos:",
//            "left = " +
//            ((Integer)armLeft.getTargetPosition()).toString() +
//            ", right = " +
//            ((Integer)armRight.getTargetPosition()).toString());
                telemetry.addData("Heavy Arm Pos:",
            "heavy Arm Position = " +
            ((Integer)motor5.getTargetPosition()).toString() +
            "::");
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

} 