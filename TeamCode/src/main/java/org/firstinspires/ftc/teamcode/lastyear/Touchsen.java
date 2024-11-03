// package org.firstinspires.ftc.teamcode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.TouchSensor;
// import com.qualcomm.robotcore.hardware.DcMotor;
// 
// 
// public class Touchsen extends LinearOpMode{
//     TouchSensor touch;
//     DcMotor motor;
//     public void runOpMode(){
//         
//         touch = hardwareMap.get(TouchSensor.class,"touch");
//         motor = hardwareMap.get(DcMotor.class,"motor");
//         
//         waitForStart();
//     
//         while(opModeIsActive()){
//             if(touch.isPressed());{
//                 System.out.println("Yessssssssssss");
//                 motor.setPower(0.6);
//             if(touch.isPressed() == false);{
//                 motor.setPower(0);
//             
//            
//         
//             }          
//             } 
//     }
//     
//     }
//     
// }