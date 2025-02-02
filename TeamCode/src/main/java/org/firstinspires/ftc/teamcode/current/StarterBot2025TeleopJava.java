package org.firstinspires.ftc.teamcode.current;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="2025intoTheDeep", group="Linear Opmode")

public class StarterBot2025TeleopJava extends LinearOpMode{


    OmniDrive driveObject = new OmniDrive();

    // Declare OpMode members.
    private DcMotor arm = null;
    private DcMotor wrist = null;
    private Servo claw = null;
    private CRServo intake = null;


    // Arm and Wrist target positions for each state
    private static final int ARM_POSITION_INIT = 200;//300;
    private static final int ARM_POSITION_INTAKE = 810;//450;
    private static final int ARM_POSITION_WALL_GRAB = 1100;//1100;
    private static final int ARM_POSITION_WALL_UNHOOK = 1700;//1700;
    private static final int ARM_POSITION_HOVER_HIGH = 2600;//2600;
    private static final int ARM_POSITION_CLIP_HIGH = 2200;//2100;
    private static final int ARM_POSITION_LOW_BASKET = 2271;//2500;
    private static final int ARM_POSITION_HIGH_BASKET = 2500;

    private static final int WRIST_POSITION_INIT = 6;
    private static final int WRIST_POSITION_SAMPLE = 290;
    private static final int WRIST_POSITION_SPEC = 30;
    private static final int WRIST_POSITION_LOW_BSKT = 280;
    private static final int WRIST_POSITION_HIGH_BSKT = 280;



    // Claw positions
    private static final double CLAW_OPEN_POSITION = 0.15;
    private static final double CLAW_CLOSED_POSITION = 0.26;

    // Enum for state machine
    private enum RobotState {
        INIT,
        INTAKE,
        WALL_GRAB,
        WALL_UNHOOK,
        HOVER_HIGH,
        CLIP_HIGH,
        LOW_BASKET,
        HIGH_BASKET,
        MANUAL
    }

    // Initial state
    private RobotState currentState = RobotState.INIT;

    // Claw toggle state
    private boolean clawOpen = false;
    private boolean lastBump = false;
    private boolean lastHook = false;
    private boolean lastGrab = false;

    //target position
    private int targetArm = 0;
    private int targetWrist = 0;

    private static final boolean USE_WEBCAM = true;  // Set true to use a webcam, or false for a phone camera
    private static final int DESIRED_TAG_ID = -1;     // Choose the tag you want to approach or set to -1 for ANY tag.

    @Override
    public void runOpMode() {

        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
        double  turn            = 0;        // Desired turning power/speed (-1 to +1)
        driveObject.init(hardwareMap);
        arm = hardwareMap.get(DcMotor.class, "arm");
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        claw = hardwareMap.get(Servo.class, "claw");
        intake = hardwareMap.get(CRServo.class, "intake");

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set zero power behavior
        wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // State machine logic
            switch (currentState) {
                case INIT:
                    /*targetWrist = WRIST_POSITION_INIT;
                    targetArm = ARM_POSITION_INIT;
                    telemetry.addData("State", "INIT");*/
                    break;
                case INTAKE:
                    targetArm = ARM_POSITION_INTAKE;
                    targetWrist = WRIST_POSITION_SAMPLE;
                    telemetry.addData("State", "INTAKE");
                    break;

                case WALL_GRAB:
                    targetArm = ARM_POSITION_WALL_GRAB;
                    targetWrist = WRIST_POSITION_SPEC;
                    telemetry.addData("State", "WALL_GRAB");
                    break;

                case WALL_UNHOOK:
                    targetArm = ARM_POSITION_WALL_UNHOOK;
                    targetWrist = WRIST_POSITION_SPEC;
                    telemetry.addData("State", "WALL_UNHOOK");
                    break;

                case HOVER_HIGH:
                    targetArm = ARM_POSITION_HOVER_HIGH;
                    targetWrist = WRIST_POSITION_SPEC;
                    telemetry.addData("State", "HOVER_HIGH");
                    break;

                case CLIP_HIGH:
                    targetArm = ARM_POSITION_CLIP_HIGH;
                    targetWrist = WRIST_POSITION_SPEC;
                    telemetry.addData("State", "CLIP_HIGH");
                    break;
                case LOW_BASKET:
                    targetArm = ARM_POSITION_LOW_BASKET;
                    targetWrist = WRIST_POSITION_LOW_BSKT;
                    telemetry.addData("State", "LOW_BASKET");
                    break;
                case HIGH_BASKET:
                    targetArm = ARM_POSITION_HIGH_BASKET;
                    targetWrist = WRIST_POSITION_HIGH_BSKT;
                    telemetry.addData("State", "HIGH_BASKET");
                    break;
                case MANUAL:
                    telemetry.addData("State", "MANUAL");
                    break;
            }

            // Handle state transitions based on gamepad input
            if (gamepad1.a) {
                currentState = RobotState.INTAKE;
            } else if (gamepad1.b && !lastGrab) {
                if(currentState == RobotState.WALL_GRAB){
                    currentState = RobotState.WALL_UNHOOK;
                }else{
                    currentState = RobotState.WALL_GRAB;
                }
                /* USED to CLIP and UNCLIP  on HIGH BAR */
            } else if (gamepad1.y && !lastHook) {
                if(currentState == RobotState.HOVER_HIGH){
                    currentState = RobotState.CLIP_HIGH;
                }else{
                    currentState = RobotState.HOVER_HIGH;
                }
            } else if (gamepad1.x) {
                currentState = RobotState.LOW_BASKET;
            } else if (gamepad1.left_bumper) {
                currentState = RobotState.INIT;
            } else if (gamepad1.dpad_up && arm.getCurrentPosition() < 2600){ //manual control
                currentState = RobotState.MANUAL;
                targetArm += 5;
            } else if (gamepad1.dpad_down){
                currentState = RobotState.MANUAL;
                targetArm -= 5;
            } else if (gamepad1.dpad_left && wrist.getCurrentPosition()< WRIST_POSITION_HIGH_BSKT ){
                //currentState = RobotState.MANUAL;
                //targetWrist += 1;
            } else if (gamepad1.dpad_right && wrist.getCurrentPosition() > WRIST_POSITION_SPEC){
                //currentState = RobotState.MANUAL;
                //targetWrist -= 1;
            }

            lastGrab = gamepad1.b;
            lastHook = gamepad1.y;

            // Toggle claw position when right_bumper is pressed
            if (gamepad1.right_bumper && !lastBump) {
                clawOpen = !clawOpen;
                if (clawOpen && wrist.getCurrentPosition() < 40) {
                    claw.setPosition(CLAW_OPEN_POSITION);
                } else {
                    claw.setPosition(CLAW_CLOSED_POSITION);
                }
            }
            lastBump = gamepad1.right_bumper;

            // Control intake servo with triggers
            if (gamepad1.right_trigger>0.1) {
                intake.setPower(1.0);
            } else if (gamepad1.left_trigger>0.1) {
                intake.setPower(-1.0);
            } else {
                intake.setPower(0);
            }
            telemetry.addData("Manual Drive1","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
            //DRIVE Split Arcade
            // drive using manual POV Joystick mode.  Slow things down to make the robot more controlable.
            drive  = -gamepad1.left_stick_y  / 2.0;  // Reduce drive rate to 50%.
            strafe =  -gamepad1.left_stick_x  / 2.0;  // Reduce strafe rate to 50%.
            turn   = -gamepad1.right_stick_x / 2.0;  // Reduce turn rate to 33%.
            telemetry.addData("Manual Drive","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);

            if (!clawOpen )
                setWrist(targetWrist);
            arm.setTargetPosition(targetArm);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(0.6);


            // Send telemetry data to the driver station
            telemetry.addData("Claw Position", clawOpen ? "Open" : "Closed");
            telemetry.addData("Arm Position", arm.getCurrentPosition());
            telemetry.addData("Arm Power", arm.getPower());
            telemetry.addData("Wrist Position", wrist.getCurrentPosition());
            telemetry.addData("Wrist Power", wrist.getPower());
            telemetry.update();


            // Apply desired axes motions to the drivetrain.
            driveObject.moveRobot(drive, strafe, turn);
        }
    }

    public void setWrist(Integer targetWrist){
        //claw.setPosition(CLAW_CLOSED_POSITION);
        wrist.setTargetPosition(targetWrist);
        wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wrist.setPower(0.4);
    }
}