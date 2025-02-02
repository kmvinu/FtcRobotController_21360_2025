package org.firstinspires.ftc.teamcode.current;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Robot: Auto Drive By Encoder Left", group="Robot")


public class AutoDriveLeft extends LinearOpMode {

    /* Declare OpMode members. */
    private DcMotor rightFrontDrive    = null;
    private DcMotor rightBackDrive  = null;
    private DcMotor leftBackDrive  = null;
    private DcMotor leftFrontDrive  = null;
    private DcMotor arm = null;



    //OmniDrive driveObject = new OmniDrive();

    private ElapsedTime     runtime = new ElapsedTime();

    // Calculate the COUNTS_PER_INCH for your specific drive train.
    // Go to your motor vendor website to determine your motor's COUNTS_PER_MOTOR_REV
    // For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
    // For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
    // This is gearing DOWN for less speed and more torque.
    // For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation.
    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 3.8 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.1;
    static final double     TURN_SPEED              = 0.1;

    @Override
    public void runOpMode() {

        // Initialize the drive system variables.
        //driveObject.init(hardwareMap);
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "TopLeft");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "TopRight");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "BottomLeft");
        rightBackDrive = hardwareMap.get(DcMotor.class, "BottomRight");
        arm = hardwareMap.get(DcMotor.class, "arm");



        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Starting at",  "%7d :%7d",
                leftFrontDrive.getCurrentPosition(),
                leftBackDrive.getCurrentPosition(),
                rightFrontDrive.getCurrentPosition(),rightBackDrive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //movespecimen
        sleep(1000);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(200);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(0.5);

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        encoderDrive(DRIVE_SPEED,  -3, -3, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   24, -24, 6.0);  // S2: Turn Left 12 Inches with 4 Sec timeout
        encoderDrive(DRIVE_SPEED, -46, -46, 7.0);  // S3: Reverse 24 Inches with 4 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the OpMode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newleftFrontDriveTarget;
        int newleftBackDriveTarget;
        int newTopRightTarget;
        int newrightBackDriveTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newleftFrontDriveTarget = leftFrontDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newleftBackDriveTarget = leftBackDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newTopRightTarget = rightFrontDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newrightBackDriveTarget = rightBackDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            rightFrontDrive.setTargetPosition(newTopRightTarget);
            rightBackDrive.setTargetPosition(newrightBackDriveTarget);
            leftFrontDrive.setTargetPosition(newleftFrontDriveTarget);
            leftBackDrive.setTargetPosition(newleftBackDriveTarget);

            // Turn On RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFrontDrive.setPower(Math.abs(speed));
            rightBackDrive.setPower(Math.abs(speed));
            leftBackDrive.setPower(Math.abs(speed));
            rightFrontDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFrontDrive.isBusy() && rightBackDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d", newleftFrontDriveTarget,  newTopRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d",
                        leftFrontDrive.getCurrentPosition(), rightBackDrive.getCurrentPosition());
                telemetry.addData("LeftBackDrive Stats power",leftBackDrive.getPower());
                telemetry.addData("LeftBackDrive Stats Mode",leftBackDrive.getMode());
                telemetry.addData("rightBackDrive Stats power",rightBackDrive.getPower());
                telemetry.addData("rightBackDrive Stats Mode",rightBackDrive.getMode());
                telemetry.update();
            }

            // Stop all motion;
            leftFrontDrive.setPower(0);
            rightBackDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }
}
