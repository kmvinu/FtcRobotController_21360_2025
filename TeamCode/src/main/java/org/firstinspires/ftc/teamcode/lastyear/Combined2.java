package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Combined2", group="TeleOp")
public class Combined2 extends OpMode {

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

        //CONSTANTS AND VARIABLES
    static final double ARM_MOTOR_TIC_COUNT = 1440;

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
    private double servoOnePickUpPosition = 0.7;
    private double servoOneDragPosition = 0.75;
    private double servoOnePositionDefault = 0.5;
    private double servoOnePositionSafe = 0.1; // 0 is vertical 1 is horizontal
    private double servoOneBoardDropPosition = 0.9;//horizontal

    int ARM_HALF_TURN = (int)(ARM_MOTOR_TIC_COUNT / 2)-220;//Subtract to lower the arm G1Y
    //int ARM_THIRD_TURN = ((int)ARM_MOTOR_TIC_COUNT / 3);
    //int ARM_QUARTER_TURN = (int)ARM_MOTOR_TIC_COUNT / 4;
    int ARM_FIFTH_TURN = ((int)ARM_MOTOR_TIC_COUNT / 5)+50; // G1b
    int ARM_STARTING = 20 ; //used only during initial run to set claw safely  & reset encoder

    int  ARM_PIXEL_PICKUP_POSITION = 68;
    int ARM_SAFE_POSITION = 70;





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
        
        //Raise Wrist 
        wristServoLeftPlaceSafe();
        //Set counter of tics to 0
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set to target Position close to support.
        arm.setTargetPosition(ARM_STARTING);
        //set power max = 1
        arm.setPower(0.1);
        //Instruct motor to run to target position
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Do nothing while this arm is working
        while (arm.isBusy()) {
            telemetry.addData("Status", "Encoder Motor Working");
            telemetry.update();
        }

        //wristServoLeftPickUp();
        //armPickUpPosition();

        clawOpen();;

        //wristServoLeftDefault();
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
        boolean G1a = gamepad1.a;
        boolean G1b = gamepad1.b;
        boolean G2y = gamepad2.y;
        boolean G2x = gamepad2.x;
        boolean G2a = gamepad2.a;
        boolean G2b = gamepad2.b;
        boolean leftTrigger = gamepad1.left_trigger != 0;
        boolean rightTrigger = gamepad1.right_trigger != 0;
        boolean leftTrigger2 = gamepad2.left_trigger != 0;
        boolean rightTrigger2 = gamepad2.right_trigger != 0;
        boolean slowMode = false;

        //changed all motor power. Change back to 1 and -1
        if (G1a) {
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
             TopLeft.setPower(1);
                BottomLeft.setPower(1);
                TopRight.setPower(1);
                BottomRight.setPower(-1);


            } else if (G1leftBumper) {
                TopLeft.setPower(-1);
                BottomLeft.setPower(-1);
                TopRight.setPower(-1);
                BottomRight.setPower(1);
            } else {
                TopRight.setPower(-G1rightStickY/1.25);
                BottomRight.setPower(-G1rightStickY/1.25);
                BottomLeft.setPower(G1leftStickY/1.25);
                TopLeft.setPower(-G1leftStickY/1.25);
            }
        } else if (slowMode) {
            if (G1rightBumper) {
                TopLeft.setPower(1);
                BottomLeft.setPower(1);
                TopRight.setPower(1);
                BottomRight.setPower(-1);

            } else if (G1leftBumper) {
                TopLeft.setPower(-1);
                BottomLeft.setPower(-1);
                TopRight.setPower(-1);
                BottomRight.setPower(1);
            } else {
                TopRight.setPower(-G1rightStickY / 2);
                BottomRight.setPower(-G1rightStickY / 2);
                BottomLeft.setPower(G1leftStickY / 2);
                TopLeft.setPower(G1leftStickY / 2);
            }
        }

        if (leftTrigger) { //closed
            clawClose();
        } else { //open
            clawOpen();
        }

        if ( rightTrigger){
            armPickUpPosition();
            //wristServoLeftDragAndPickUp();
            wristServoLeftPickUp();
        }

        if (G1x){
            servoDrone.setPosition(1);
        } else {
            servoDrone.setPosition(0);
        }

        if ( G1a){
            //wristServoLeftDefault();
            wristServoLeftPlaceSafe();
            armTravelPosition();
        }
        if ( G1b){
            armSearchPosition();
            wristServoLeftPlaceOnBoard();
        }

        if ( G1y){
            wristServoLeftPlaceOnBoard();
            armHighDropPosition();
        }
        
        /**** Second GamePad Controller **/
        //if ( G2b){
           // armSearchPosition();
            //wristServoLeftPickUp();
      //  }
        

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
        arm.setTargetPosition(ARM_HALF_TURN);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (arm.isBusy()) {
            telemetry.addData("Status", "Encoder Motor Working:SearchPosition");
            telemetry.update();
        }

    }

    public void armPickUpPosition(){
        arm.setPower(0.2);
        arm.setTargetPosition(ARM_PIXEL_PICKUP_POSITION);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void armSearchPosition(){
        arm.setPower(0.2);
        arm.setTargetPosition(ARM_FIFTH_TURN);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (arm.isBusy()) {
            telemetry.addData("Status", "Encoder Motor Working:SearchPosition");
            telemetry.update();
        }
    }
    
    public void armTravelPosition(){
        arm.setPower(0.1);
        arm.setTargetPosition(ARM_STARTING); 
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void wristServoLeftPickUp(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePickUpPosition);
    }
    
      public void wristServoLeftDragAndPickUp(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOneDragPosition);
    }


    public void wristServoLeftDefault(){

        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePositionDefault);
    }


    public void wristServoLeftPlaceOnBoard(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOneBoardDropPosition);
    }
    
    public void wristServoLeftPlaceSafe(){
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePositionSafe);
    }
    
    public void sweepPickup(){
        clawOpen();
        armPickUpPosition();
        wristServoLeftPickUp();
    }



}
