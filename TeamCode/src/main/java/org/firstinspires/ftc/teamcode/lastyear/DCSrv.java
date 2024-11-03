package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="DCSrv", group="TeleOp")
public class DCSrv extends OpMode {

    private Servo servoWR;
    private Servo servoWL;
    private Servo servoCR;
    private Servo servoCL;


    private DcMotor arm;
    private double servoOneInitPosition = 0.5;
    private double servoOnePositionOne = 0.1;
    private double servoOnePickUpPosn = 0.7;
    private int servoLeftDelay = 10;

    // DEFINE robot
    //RobotHardware robot = new RobotHardware();
    // CREATING THE HARDWARE MAP

    //CONSTANTS AND VARIABLES
    static final double ARM_MOTOR_TIC_COUNT=1440;



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
        //initAll();
        servoCR.setPosition(0.1);
        servoCL.setPosition(0.9);


    }

    //RUNS ONCE ON PLAY()
    @Override
    public void start() {
        //initAll();
        double half_turn = ARM_MOTOR_TIC_COUNT/2;
        double third_turn = ARM_MOTOR_TIC_COUNT/3;
        double quarter_turn = ARM_MOTOR_TIC_COUNT/4;
        
        servoCR.setPosition(0.1);
        servoCL.setPosition(0.9);
        
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePositionOne);
        
        arm.setPower(0.1);
        arm.setTargetPosition(107);
        //Instruct motor to run to target position
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // Do nothing while this arm is working

        doNothing(2000);
        
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePickUpPosn);
    

        
        doNothing(2000);
        
        servoCR.setPosition(0.5);
        servoCL.setPosition(0.5);
        
        doNothing(2000);
        
        servoWL.setDirection(Servo.Direction.FORWARD);
        servoWL.setPosition(servoOnePositionOne);
        

    }


    public void doNothing(int milliseconds){
        double currTime = getRuntime();
        double waitUntil = currTime + (double)(milliseconds/1000);
        while (getRuntime() < waitUntil){
            
        }
         
    }
    // LOOPS ON start()
    @Override
    public void loop() {

    }



    public  void initAll() {

        servoWR = hardwareMap.get(Servo.class,"servoWristRight");
        servoWL = hardwareMap.get(Servo.class,"servoWristLeft");
        servoCL  = hardwareMap.get(Servo.class, "servoLeft");
        servoCR  = hardwareMap.get(Servo.class, "servoRight");
        arm = hardwareMap.get(DcMotor.class, "motor5");
        //servoWL.setDirection(Servo.Direction.FORWARD);
        //servoWL.setPosition(servoOneInitPosition);
        //servoWR.setDirection(Servo.Direction.FORWARD);
        //servoWR.setPosition(servoOneInitPosition);
        servoCR.setPosition(0.1);
        servoCL.setPosition(0.9);

    }

    public void telemetryUpdate(){
        telemetry.log().clear();
        telemetry.addData("Position",servoWR.getPosition());
        telemetry.addData("Direction",servoWR.getDirection());
        telemetry.addData("Controller",servoWR.getController());
        telemetry.addData("Port Number",servoWR.getPortNumber());
        telemetry.addData("Device Name",servoWR.getDeviceName());
        telemetry.addData("Version",servoWR.getVersion());
        telemetry.update();
    }

    public void teleOpControl() {
        if (gamepad1.a) {
            servoWL.setPosition(0.9);
                
           arm.setTargetPosition(100);
            //set power max = 1
            arm.setPower(0.1);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        
        if (gamepad1.b) {
            servoWL.setPosition(0.1);
                
           arm.setTargetPosition(50);
            //set power max = 1
            arm.setPower(0.1);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

    }


        // RUN ONCE ON STOP
       @Override
        public void stop() {

    }
}
