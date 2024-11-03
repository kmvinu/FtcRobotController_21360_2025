package org.firstinspires.ftc.teamcode.lastyear;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;


@TeleOp

public class ArmTest extends LinearOpMode {
    private Blinker control_Hub;
    private DcMotor wrist;
    private DcMotor arm;
    private Servo claw;



    @Override
public void runOpMode() {
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        //leftmotor = hardwareMap.get(DcMotor.class, "leftmotor");
        //rightmotor = hardwareMap.get(DcMotor.class, "rightmotor");
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        claw.setPosition(0.11);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.y){
                //move to position 0
                claw.setPosition(0.13);//apart
    
            } else if (gamepad1.x) {
                //move to position 0.5
                claw.setPosition(0.25);//together
            arm.setTargetPosition(450);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(1);

            } else if (gamepad1.a) {
                //move to position 1
                claw.setPosition(0.3);
            wrist.setTargetPosition(270);
            wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wrist.setPower(1);
                        }
            else if (gamepad1.b){
            wrist.setTargetPosition(100);
            wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wrist.setPower(1);

            }
            telemetry.addData("Servo Position", claw.getPosition());
            telemetry.addData("Wrist Position", wrist.getCurrentPosition());
            telemetry.addData("Status", "Running");
            telemetry.addData("Wrist Power", wrist.getPower());
            telemetry.addData("Arm Position", arm.getCurrentPosition());
            telemetry.addData("Arm Power", arm.getPower());
            telemetry.update();

        }
    }
}