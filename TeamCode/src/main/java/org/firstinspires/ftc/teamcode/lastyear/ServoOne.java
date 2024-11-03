package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class ServoOne extends LinearOpMode{

    
    private Servo servoWR;
    private Servo servoWL;
    private Servo servoCR;
    private Servo servoCL;
    private Servo servoL;
    private Servo servoR;
    private double servoOneInitPosition = 0.9;
    private double servoOnePositionOne = 0.1;
    private double servoOnePositionTwo = 0.9;
    private int servoLeftDelay = 10;
    
    

    @Override
    public void runOpMode() throws InterruptedException {
        initHardware();
        while(!isStarted()){
            //Waiting for Play
            telemetryUpdate();

        }
        waitForStart();
        while(isStarted()){}
        teleOpControl();
        telemetryUpdate();
    }

    public void initHardware(){
        initServoOne();
    }

    public  void initServoOne() {

        servoWR = hardwareMap.get(Servo.class,"servoWristRight");
        servoWL = hardwareMap.get(Servo.class,"servoWristLeft");
        servoL  = hardwareMap.get(Servo.class, "servoLeft");
        servoR  = hardwareMap.get(Servo.class, "servoRight");
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOneInitPosition);
        servoL.setPosition(0.4);
        servoR.setPosition(0.6);
        //servoWR.setDirection(Servo.Direction.FORWARD);
        //servoWR.setPosition(servoOneInitPosition);

    }

    public void telemetryUpdate(){
        telemetry.log().clear();
        telemetry.addData("Position",servoWR.getPosition());
        telemetry.addData("Direction",servoWR.getDirection());
        telemetry.addData("Controller",servoWR.getController());
        telemetry.addData("Port Number",servoWR.getPortNumber());
        telemetry.addData("Device Name",servoWR.getDeviceName());
        telemetry.addData("Version",servoWR.getVersion());
        telemetry.update();
    }
    
    public void teleOpControl(){
        if(gamepad1.a){
            servoWR.setPosition(servoOnePositionOne);
        }
        if(gamepad1.b){
            servoWR.setPosition(servoOnePositionTwo);
        }
    }
    

}