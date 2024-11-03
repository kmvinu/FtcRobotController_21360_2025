// package org.firstinspires.ftc.teamcode;
// 
// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.ColorSensor;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.util.Range;
// import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.hardware.GyroSensor;
// import com.qualcomm.robotcore.hardware.ColorSensor;
// @Autonomous
// 
// public class Base2 {
// public DcMotor TopRight;
// public DcMotor BottomRight;
// public DcMotor BottomLeft;
// public DcMotor TopLeft;
// public DcMotor motor5;
// public Servo servoLeft;
// public Servo servoRight;
// public ColorSensor color;
// 
// @TeleOp
// public class JavaEncoderTest extends linearmode {
//      Dcmotorex motor;
//  
//  @Override
//  public void runOpMode() {
//      motor = hardwareMap.get(DcMotorEx.class, "Motor");
//      waitForStart();
//      while (opModeIsActive()) {
//          telemetry.addData("Encode value", "Motor");
//          waitForStart();
//          while (opModeIsaActive()) {
//               telemetry.addData("Encoder value", motor.getCurrentPosition());
//               telemetry.update();
//          }
//      }
//  }
//  DcMotorEx motor = hardwareMap.get(DcMotor.class, "Motor");
// "motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
// 
// DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "Motor");
// "motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
// //to run the motor forward at half power
// double motorPower = 0.5;
// "motor.setPower(motorPower);
// 
// DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "Motor");
// "motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //This will turn the motor at 200 ticks per second
// double motorVelocity = 200;
// "motor.setVelocity(motorVelocity);
// 
// "package org.firstinspires.ftc.teamcode;
// 
// @TeleOp
// "public clas JavaRunToPositionExample extends LinearOpMode {
//     DcMotorEx motor;
//     
//     @Override 
//     public void runOpMode() {
//         motor = hardwareMap.get(DcMotorEx.class, "Motor");
//         
//         motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         
//         waitForStart();
//         
//         motor.setTargetPosition(300);
//         
//         motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         
//         motor.setVelocity(200);
//         
//         while (opModeIsActive()) {
//             telemetry.addData("velocity", motor.getVelocity());
//             telemetry.addData("position", motor.getCurrentPosition());
//             telemetry.addData("is at taret", !motor.is.Busy());
//             telemetry.update();
//         }
//         
// }
// 
// while(motor.isBusy()) && !isStopRequested()){
//     telemetry.addData("Status","Waiting for the motor to reach its target");
//     telemetry.update();
// }
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
// 
//  
//  
// }  
// }