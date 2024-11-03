// package org.firstinspires.ftc.teamcode;
//  
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
// import com.qualcomm.robotcore.hardware.DistanceSensor;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//  
// @TeleOp
// public class Distsen extends LinearOpMode {
//     DistanceSensor distance;
//     DcMotor TopRight; //TopRight, Motor1
//     DcMotor BottomRight; //BottomRight motor2
//     DcMotor BottomLeft; //BottomLeft motor3
//     DcMotor TopLeft;
//     int n = 10;
//     @Override
//     public void runOpMode() {
//         // Get the distance sensor and motor from hardwareMap
//         distance = hardwareMap.get(DistanceSensor.class, "Distance");
//         TopRight  = hardwareMap.get(DcMotor.class, "TopRight");
//         BottomRight = hardwareMap.get(DcMotor.class, "BottomRight");
//         BottomLeft = hardwareMap.get(DcMotor.class, "BottomLeft");
//         TopLeft = hardwareMap.get(DcMotor.class, "TopLeft");
//         
//         // Loop while the Op Mode is running
//         waitForStart();
//         while (opModeIsActive()) {
//             // If the distance in centimeters is less than 10, set the power to 0.3
//             if (distance.getDistance(DistanceUnit.CM)>n ) {
//                 motor0.setPower(0.3);
//             } else {  // Otherwise, stop the motor
//                 motor0.setPower(0);
//             }
//         }
//     }
// }
// 