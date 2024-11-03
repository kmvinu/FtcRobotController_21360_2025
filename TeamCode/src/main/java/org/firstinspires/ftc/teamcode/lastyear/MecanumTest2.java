package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumTest2", group = "TeleOp")
public class MecanumTest2 extends OpMode {

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

    private DcMotor TopRight; //TopRight, Motor1
    private DcMotor BottomRight; //BottomRight motor2
    private DcMotor BottomLeft; //BottomLeft motor3
    private DcMotor TopLeft; //TopLeft motor4

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
    }

    //RUNS ONCE ON PLAY()
    @Override
    public void start() {

    }

    // LOOPS ON start()
    @Override
    public void loop() {

        double G1rightStickY = -gamepad1.right_stick_y;
        double G1leftStickY = gamepad1.left_stick_y;
        //double G1rightStickX = -gamepad1.right_stick_x;
        // double G1leftStickX = -gamepad1.left_stick_x;
        boolean G1rightBumper = gamepad1.right_bumper;
        boolean G1leftBumper = gamepad1.left_bumper;

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
            
            TopLeft.setPower(G1leftStickY / 2);
            BottomLeft.setPower(-G1leftStickY / 2);
            TopRight.setPower(-G1rightStickY / 2);
            BottomRight.setPower(-G1rightStickY / 2);
            
              

        }

    }

    @Override
    public void stop() {

    }

    public void initAll() {
        TopRight = hardwareMap.get(DcMotor.class, "TopRight");
        BottomRight = hardwareMap.get(DcMotor.class, "BottomRight");
        BottomLeft = hardwareMap.get(DcMotor.class, "BottomLeft");
        TopLeft = hardwareMap.get(DcMotor.class, "TopLeft");

        TopRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


}
