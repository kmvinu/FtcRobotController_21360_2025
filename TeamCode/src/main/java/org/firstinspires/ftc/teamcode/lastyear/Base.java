// package org.firstinspires.ftc.teamcode;
// // importlines were omitted. onbotjava will automactically
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// 
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
// "public clas JavaRunToPositionExample extends LinearOpModde {
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
// "while(motor.isBusy()) && !isStopRequested()) {
//     // CODE
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