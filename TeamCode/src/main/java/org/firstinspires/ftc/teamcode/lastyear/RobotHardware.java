package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.ColorSensor; 

public class RobotHardware
{
    
    public HardwareMap hardwareMap;

    /* Declare OpMode members. */
    private LinearOpMode myOpMode = null;   // gain access to methods in the calling OpMode.

   //ISTANTIATE MOTORS AND SERVOS//
    public DcMotor TopRight; //TopRight, Motor1
    public DcMotor BottomRight; //BottomRight motor2
    public DcMotor BottomLeft; //BottomLeft motor3
    public DcMotor TopLeft; //TopLeft motor4
    
    public DcMotor motor5;
    
    public Servo servoLeft; //claw Left
    public Servo servoRight; //claw Right
    public ColorSensor color;
    public Servo servoWL;
    public Servo servoWR;
    public Servo servoDrone;
 
    
    public void init(HardwareMap hardwareMap) {
        
        // DEFINIE MOTORS AND SERVOS
        motor5 = hardwareMap.get(DcMotor.class, "motor5");
        TopRight  = hardwareMap.get(DcMotor.class, "TopRight");
        BottomRight = hardwareMap.get(DcMotor.class, "BottomRight");
        BottomLeft = hardwareMap.get(DcMotor.class, "BottomLeft");
        TopLeft = hardwareMap.get(DcMotor.class, "TopLeft");
        servoLeft  = hardwareMap.get(Servo.class, "servoLeft");
        servoRight  = hardwareMap.get(Servo.class, "servoRight");
        color = hardwareMap.get(ColorSensor.class, "Color");
        servoWL  = hardwareMap.get(Servo.class, "servoWristLeft");
        servoWR  = hardwareMap.get(Servo.class, "servoWristRight");
        servoDrone  = hardwareMap.get(Servo.class, "servoDrone");
        //DEFINE SENSORS
        //gyroSensor = hardwareMap.get(GyroSensor.class, "gyroSensor");
    
        //SET MOTOR POWER
     //   motor0.setPower(0);
        TopRight.setPower(0);
        BottomRight.setPower(0);
        BottomLeft.setPower(0);
        TopLeft.setPower(0);
        motor5.setPower(0);
      //  servo0.setDirection(REVERSE);
      //  servo1.setDirection(REVERSE);
        
        
        //SET MOTOR MODE
     //   motor0.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motor5.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motor5.setDirection(DcMotor.Direction.REVERSE);
        
        
        //SET MOTOR TO zeroPowerBehavior
      //  motor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //motor5.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        
        //SET SERVO POSITION
        servoLeft.setPosition(0.3); //don't set to extremes for init fuction. EX. 0 or 1
        servoRight.setPosition(0.3);
        //servoWristLeft.setPosition(0.5);
        //servoWristRight.setPosition(0.5);
   
        // CALIBRATE SENSORS
       //gyroSensor.calibrate();
       
       
    }
    

}

