// /*
//  * Copyright (c) 2023 FIRST
//  *
//  * All rights reserved.
//  *
//  * Redistribution and use in source and binary forms, with or without modification,
//  * are permitted (subject to the limitations in the disclaimer below) provided that
//  * the following conditions are met:
//  *
//  * Redistributions of source code must retain the above copyright notice, this list
//  * of conditions and the following disclaimer.
//  *
//  * Redistributions in binary form must reproduce the above copyright notice, this
//  * list of conditions and the following disclaimer in the documentation and/or
//  * other materials provided with the distribution.
//  *
//  * Neither the name of FIRST nor the names of its contributors may be used to
//  * endorse or promote products derived from this software without specific prior
//  * written permission.
//  *
//  * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
//  * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
//  * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
//  * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
//  * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
//  * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
//  * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//  * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//  * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
//  * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
//  * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//  */
// 
// package org.firstinspires.ftc.teamcode;
// 
// import android.util.Size;
// import com.qualcomm.robotcore.hardware.ColorSensor;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
// import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
// import org.firstinspires.ftc.vision.VisionPortal;
// 
// import java.util.Locale;
// 
// /*
//  * This OpMode helps calibrate a webcam or RC phone camera, useful for AprilTag pose estimation
//  * with the FTC VisionPortal.   It captures a camera frame (image) and stores it on the Robot Controller
//  * (Control Hub or RC phone), with each press of the gamepad button X (or Square).
//  * Full calibration instructions are here:
//  *
//  *  https://ftc-docs.firstinspires.org/camera-calibration
//  *
//  * In Android Studio, copy this class into your "teamcode" folder with a new name.
//  * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
//  *
//  * In OnBot Java, use "Add File" to add this OpMode from the list of Samples.
//  */
// 
// 
// public class WebCam1 extends LinearOpMode
// {
//     /*
//      * EDIT THESE PARAMETERS AS NEEDED
//      */
//     final boolean USING_WEBCAM = false;
//     final BuiltinCameraDirection INTERNAL_CAM_DIR = BuiltinCameraDirection.BACK;
//     final int RESOLUTION_WIDTH = 640;
//     final int RESOLUTION_HEIGHT = 640;
// 
//     // Internal state
//     boolean lastX;
//     int frameCount;
//     long capReqTime;
// 
//     @Override
//     public void runOpMode()
//     {
//         VisionPortal portal;
// 
//         if (USING_WEBCAM)
//         {
//             portal = new VisionPortal.Builder() 
//                     .setCamera(hardwareMap.get(WebcamName.class, "WebCam1")) // dont know name yet
//                     .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
//                     .build();
//         }
//         else
//         {
//             portal = new VisionPortal.Builder()
//                     .setCamera(INTERNAL_CAM_DIR)
//                     .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
//                     .build();
//         }
// 
//         while (!isStopRequested())
//         {
//             boolean x = gamepad1.x;
// 
//             if (x && !lastX)
//             {
//                 portal.saveNextFrameRaw(String.format(Locale.US, "CameraFrameCapture-%06d", frameCount++));
//                 capReqTime = System.currentTimeMillis();
//             }
// 
//             lastX = x;
// 
//             telemetry.addLine("######## Camera Capture Utility ########");
//             telemetry.addLine(String.format(Locale.US, " > Resolution: %dx%d", RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
//             telemetry.addLine(" > Press X (or Square) to capture a frame");
//             telemetry.addData(" > Camera Status", portal.getCameraState());
// 
//             if (capReqTime != 0)
//             {
//                 telemetry.addLine("\nCaptured Frame!");
//             }
// 
//             if (capReqTime != 0 && System.currentTimeMillis() - capReqTime > 1000)
//             {
//                 capReqTime = 0;
//             }
// 
//             telemetry.update();
//         }
//     }
//     
//     
// 
// 
// 
// public class ColorDemo
// {
//     public static void main(String[] args)
//     {
//     color = new ColorSensor(SensorPort.S3);
// 
//         System.out.println("Color Demo");
//         Lcd.print(2, "Press to start");
//         
//         Button.LEDPattern(4);    // flash green led and
//         Sound.beepSequenceUp();    // make sound when ready.
// 
//         Button.waitForAnyPress();
//         Button.LEDPattern(0);
//         
//         // run until escape button pressed.
//         
//         while (Button.ESCAPE.isUp())
//         {
//             Lcd.clear(4);
//             Lcd.print(4, "ambient=%.3f", color.getAmbient());
//             Delay.msDelay(250);
//         }
// 
//         Delay.msDelay(1000);
// 
//         color.setRedMode();
//         color.setFloodLight(Color.RED);
//         color.setFloodLight(true);
//         
//         while (Button.ESCAPE.isUp())
//         {
//             Lcd.clear(5);
//             Lcd.print(5, "red=%.3f", color.getRed());
//             Delay.msDelay(250);
//         }
// 
//         Delay.msDelay(1000);
// 
//         color.setRGBMode();
//         color.setFloodLight(Color.WHITE);
//         
//         Color rgb;
//         
//         while (Button.ESCAPE.isUp())
//         {
//             rgb = color.getColor();
//             
//             Lcd.clear(6);
//             Lcd.print(6, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
//             Delay.msDelay(250);
//         }
// 
//         Delay.msDelay(1000);
// 
//         color.setColorIdMode();
//         color.setFloodLight(false);
//         
//         while (Button.ESCAPE.isUp())
//         {
//             Lcd.clear(7);
//             Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
//             Delay.msDelay(250);
//         }
// 
//         // free up resources.
//         color.close();
//         
//         Sound.beepSequence();    // we are done.
// 
//         Button.LEDPattern(4);
//         Button.waitForAnyPress();
//     }
// 
// }
//     
//     
// }