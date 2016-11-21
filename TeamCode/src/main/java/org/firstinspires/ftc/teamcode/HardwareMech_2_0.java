package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 * <p/>
 * This class can be used to define all the specific hardware for a single karel.
 * In this case that karel is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p/>
 * This hardware class assumes the following device names have been configured on the karel:
 * Note:  All names are lower case and some have single spaces between words.
 * <p/>
 * Motor channel:  Left  Up   drive motor:        "motor1"
 * Motor channel:  Right Up   drive motor:        "motor2"
 * Motor channel:  Left  Down drive motor:        "motor3"
 * Motor channel:  Right Down drive motor:        "motor4"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 * <p/>
 * also the power module is on the back
 */
public class HardwareMech_2_0 {
    /* Public OpMode members. */
    public DcMotor lFront = null;   // lf   rf
    public DcMotor rFront = null;   //
    public DcMotor lBack = null;    // lb   rb
    public DcMotor rBack = null;    //

    public DcMotor lift = null;
    public DcMotor launch = null;

    //public LightSensor eye = null;

    public double TELEPOWER = 0.5;
    public final double AUTOPOWER = 0.5;
    public final double ONEFOOTDRIVETIME = 0.96;     // THis and NINETYDEGREEDRIVETIME are experimentally determined
    public final long NINETYDEGREEDRIVETIME = 600; // at AUTOPOWER power. TODO what is NINETYDEGREEDRIVETIME


    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public HardwareMech_2_0() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        lFront = hwMap.dcMotor.get("motor1");   //Left Front
        rFront = hwMap.dcMotor.get("motor2");   //Right Front
        lBack = hwMap.dcMotor.get("motor3");    //Left Back
        rBack = hwMap.dcMotor.get("motor4");    //Right

        lift = hwMap.dcMotor.get("lift");
        launch = hwMap.dcMotor.get("launch");

        //eye = hwMap.lightSensor.get("eye");

        DcMotor[] driveMotors = {lFront, rFront, lBack, rBack};

        //Correct motor direction, set all motors to zero power, set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        for (DcMotor motor : driveMotors) {
            motor.setDirection(DcMotor.Direction.REVERSE);
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        rBack.setDirection(DcMotor.Direction.FORWARD);
        lFront.setDirection(DcMotor.Direction.FORWARD);
        lBack.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        launch.setDirection(DcMotor.Direction.FORWARD);

        lift.setPower(0);
        launch.setPower(0);

        //eye.enableLed(true);

    }

    /***
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    public void stopDrive() { //stop the drive motors
        lFront.setPower(0);
        lBack.setPower(0);
        rFront.setPower(0);
        rBack.setPower(0);

    }

    public void stopSpecial(){ // stop launch and lift. will stop other motors as we add more
        launch.setPower(0.0);
        lift.setPower(0.0);
    }
    public void goStraight(double power) {// goes forward/backward. just input a negative power to go backward.
        lBack.setPower(power);
        lFront.setPower(power);
        rBack.setPower(power);
        rFront.setPower(power);
    }

    public void turn(double power) { //turns. positive: right. negative: left. Pivot turn.
        lBack.setPower(power);
        lFront.setPower(power);
        rBack.setPower(-power);
        rFront.setPower(-power);
    }


}

