// package org.firstinspires.ftc.teamcode;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.ColorSensor;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// @TeleOp
// // 
// // 
// public class Colorsen {
// // 
// 
//   ColorSensor color;
//   @Override
//   public void runOpMode() {
// 
//     color = hardwareMap.get(ColorSensor.class, "Color");
//     waitForStart();
// 
//     while (opModeIsActive()) {
//       int blue = color.blue();
//       int red = color.red();
//       int green = color.green();
//       if (blue>red && blue>green){
//         System.out.println("Blue");
//       }
//       else if (green>red && green>blue){
//         System.out.println("Green");
//       }
//       else if (red>green && red>blue){
//         System.out.println("Red");
//       }
//     }
// //             
// //         
//   }
// 
// // 
// // 
// // 
// }
// // 
// // 
// // 
// // 
// // 
// //         
// // 