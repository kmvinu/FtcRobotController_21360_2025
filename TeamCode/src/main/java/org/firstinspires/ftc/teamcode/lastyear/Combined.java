package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Combined", group="TeleOp")
public class Combined extends OpMode {

/*
    LinearOpMode Methods
    runOpMode(): Code inside this method will run exactly once after you press the INIT button. This is where you should put all code for the OpMode.
    waitForStart(): This method pauses the Op-Mode until you press the START button on the driver station.
    isStarted(): returns true if the START button has been pressed, otherwise it returns false.
    isStopRequested(): returns true if the STOP button has been pressed, otherwise it returns false.
    idle(): calls Thread.yield, allowing other threads at the same priority level to run.
    opModeIsActive(): returns isStarted() && !isStopRequested() and calls idle().
    opModeInInit(): returns !isStarted() && !isStopRequested() and does not call idle().
*/
/*
    OpMode Methods
    init(): Code inside this method will run exactly once after you press the INIT button on the driver station.
    init_loop(): Once the code in init() has been run, code inside this method will run continuously until the START button is pressed on the driver station.
    start(): Code inside this method will run exactly once after you press the START button on the driver station.
    loop(): Once the code in start() has been run, code inside this method will run continuously until the STOP button is pressed on the driver station.
    stop(): Code inside this method will run exactly once after you press the STOP button on the driver station.
    */


    //private Servo servoWR;//Right Wrist commented only one servo required for wrist
    private Servo servoWL;//Left Wrist
    private Servo servoCR; //Right Claw
    private Servo servoCL; //Left Claw

    private DcMotor TopRight; //TopRight, Motor1
    private DcMotor BottomRight; //BottomRight motor2
    private DcMotor BottomLeft; //BottomLeft motor3
    private DcMotor TopLeft; //TopLeft motor4
    private Servo servoDrone;


    private DcMotor arm;
    private double servoOnePickUpPosition = 0;
    private double servoOnePositionDefault = 0.5;
    private double servoOneBoardDropPosition = 0.2;
    private int servoLeftDelay = 10;
    int ARM_HALF_TURN = (int)ARM_MOTOR_TIC_COUNT / 2;
    int ARM_THIRD_TURN = (int)ARM_MOTOR_TIC_COUNT / 3;
    int ARM_QUARTER_TURN = (int)ARM_MOTOR_TIC_COUNT / 4;
    int ARM_FIFTH_TURN = (int)ARM_MOTOR_TIC_COUNT / 5;
    int ARM_TRAVEL = 20;
    private int armholdPosition = 100;
    int  ARM_PIXEL_PICKUP_POSITION = 71;
    int ARM_SAFE_POSITION = 70;

    // DEFINE robot
    //RobotHardware robot = new RobotHardware();
    // CREATING THE HARDWARE MAP

    //CONSTANTS AND VARIABLES
    static final double ARM_MOTOR_TIC_COUNT = 1440;


    // RUN ONCE ON INIT()
    @Override
    public void init() {
        telemetry.addData("STATUS", "Hardware check");
        telemetry.update();
        initAll();
        telemetry.addData("STATUS", "Initialized");
        telemetry.update();
    }

    // LOOP ON init()
    @Override
    public void init_loop() {
        clawOpen();


    }

    //RUNS ONCE ON PLAY()
    @Override
    public void start() {

        //Set counter of tics to 0
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set to target Position
        arm.setTargetPosition(ARM_THIRD_TURN);
        //set power max = 1
        arm.setPower(0.1);
        //Instruct motor to run to target position
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Do nothing while this arm is working
        while (arm.isBusy()) {
            telemetry.addData("Status", "Encoder Motor Working");
            telemetry.update();
        }

        wristServoLeftPickUp();
        armPickUpPosition();

        clawOpen();;

        wristServoLeftDefault();
    }

    // LOOPS ON start()
    @Override
    public void loop() {

        double G1rightStickY = -gamepad1.right_stick_y;
        double G1leftStickY = -gamepad1.left_stick_y;
        //double G1rightStickX = -gamepad1.right_stick_x;
        // double G1leftStickX = -gamepad1.left_stick_x;
        boolean G1rightBumper = gamepad1.right_bumper;
        boolean G1leftBumper = gamepad1.left_bumper;
        boolean G1y = gamepad1.y;
        boolean G1x = gamepad1.x;
        boolean G2a = gamepad1.a;
        boolean G2b = gamepad1.b;
        boolean leftTrigger = gamepad1.left_trigger != 0;
        boolean rightTrigger = gamepad1.right_trigger != 0;
        boolean slowMode = false;

        //changed all motor power. Change back to 1 and -1
        if (G2a) {
            if (slowMode == false) {
                slowMode = true;
                telemetry.log().clear();
                telemetry.addData("Slow Mode Enabled",slowMode);
                telemetry.update();
            } else {
                slowMode = false;
                telemetry.log().clear();
                telemetry.addData("Slow Mode Disabled",slowMode);
                telemetry.update();
            }

        }

        if (!slowMode) {
            if (G1rightBumper) {
                TopRight.setPower(-0.5); //-
                BottomRight.setPower(0.5); // +
                BottomLeft.setPower(-0.5);//-
                TopLeft.setPower(0.5); //+

            } else if (G1leftBumper) {
                TopRight.setPower(-0.5);
                BottomRight.setPower(0.5);
                BottomLeft.setPower(-0.5);
                TopLeft.setPower(0.5);
            } else {
                TopRight.setPower(-G1rightStickY/2);
                BottomRight.setPower(-G1rightStickY/2);
                BottomLeft.setPower(G1leftStickY/2);
                TopLeft.setPower(-G1leftStickY/2);
            }
        } else if (slowMode) {
            if (G1rightBumper) {
                TopRight.setPower(-0.5);
                BottomRight.setPower(0.5);
                BottomLeft.setPower(-0.5);
                TopLeft.setPower(0.5);

            } else if (G1leftBumper) {
                TopRight.setPower(-0.5);
                BottomRight.setPower(0.5);
                BottomLeft.setPower(-0.5);
                TopLeft.setPower(0.5);
            } else {
                TopRight.setPower(-G1rightStickY / 3);
                BottomRight.setPower(-G1rightStickY / 3);
                BottomLeft.setPower(G1leftStickY / 3);
                TopLeft.setPower(G1leftStickY / 3);
            }
        }

        if (leftTrigger) { //closed
            clawClose();
        } else { //open
            clawOpen();
        }

        if ( rightTrigger){
            armPickUpPosition();
        }

        if (G1x){
            servoDrone.setPosition(0);
        } else {
            servoDrone.setPosition(1);
        }

        if ( G2a){
            wristServoLeftDefault();
            armTravelPosition();
        }
        if ( G2b){
            wristServoLeftPlaceOnBoard();
            armSearchPosition();
            
        }

        if ( G1y){
            wristServoLeftPlaceOnBoard();
            armHighDropPosition();
        }

          

    }


    public void initAll() {

        //servoWR = hardwareMap.get(Servo.class, "servoWristRight");
        servoWL = hardwareMap.get(Servo.class, "servoWristLeft");
        servoCL = hardwareMap.get(Servo.class, "servoLeft");
        servoCR = hardwareMap.get(Servo.class, "servoRight");
        servoDrone = hardwareMap.get(Servo.class, "servoDrone");
        TopRight = hardwareMap.get(DcMotor.class, "TopRight");
        BottomRight = hardwareMap.get(DcMotor.class, "BottomRight");
        BottomLeft = hardwareMap.get(DcMotor.class, "BottomLeft");
        TopLeft = hardwareMap.get(DcMotor.class, "TopLeft");

        arm = hardwareMap.get(DcMotor.class, "motor5");
        servoCR.setPosition(0.1);
        servoCL.setPosition(0.9);
        //servoDrone.setPosition(0.9);
        TopRight.setPower(0);
        BottomRight.setPower(0);
        BottomLeft.setPower(0);
        TopLeft.setPower(0);

        TopRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        TopRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void telemetryUpdate() {
        telemetry.log().clear();
        telemetry.addData("Direction", servoWL.getDirection());
        telemetry.addData("Controller", servoWL.getController());
        telemetry.addData("Port Number", servoWL.getPortNumber());
        telemetry.addData("Device Name", servoWL.getDeviceName());
        telemetry.addData("Version", servoWL.getVersion());
        telemetry.update();
    }

    public void teleOpControl() {


    }


    // RUN ONCE ON STOP
    @Override
    public void stop() {
        //Raise arm to SAFE POSITION
        arm.setPower(0.1);
        arm.setTargetPosition(ARM_SAFE_POSITION);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //Open Claws
        clawOpen();
    }
    public void  clawOpen(){
        servoCR.setPosition(0.1);
        servoCL.setPosition(0.9);
    }

    public void  clawClose(){
        servoCR.setPosition(0.6);
        servoCL.setPosition(0.4);
    }
    
    public void armHighDropPosition(){
        arm.setPower(0.3);
        arm.setTargetPosition(ARM_THIRD_TURN);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void armPickUpPosition(){
        arm.setPower(0.1);
        arm.setTargetPosition(ARM_PIXEL_PICKUP_POSITION);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void armSearchPosition(){
        arm.setPower(0.1);
        arm.setTargetPosition(ARM_FIFTH_TURN);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    
    public void armTravelPosition(){
        arm.setPower(0.1);
        arm.setTargetPosition(ARM_TRAVEL);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void wristServoLeftPickUp(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePickUpPosition);
    }


    public void wristServoLeftDefault(){

        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePositionDefault);
    }


    public void wristServoLeftPlaceOnBoard(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOneBoardDropPosition);
    }



}
