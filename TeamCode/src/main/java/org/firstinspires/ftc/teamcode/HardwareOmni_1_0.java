package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  Up   drive motor:        "motor1"
 * Motor channel:  Right Up   drive motor:        "motor2"
 * Motor channel:  Left  Down drive motor:        "motor3"
 * Motor channel:  Right Down drive motor:        "motor4"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwareOmni_1_0
{
    /* Public OpMode members. */
    public DcMotor  leftUp  = null;
    public DcMotor  rightUp  = null;
    public DcMotor  leftDown    = null;
    public DcMotor  rightDown = null;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareOmni_1_0(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftUp      = hwMap.dcMotor.get("motor1");
        rightUp     = hwMap.dcMotor.get("motor2");
        leftDown    = hwMap.dcMotor.get("motor3");
        rightDown   = hwMap.dcMotor.get("motor4");
        leftUp.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightUp.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        leftDown.setDirection(DcMotor.Direction.FORWARD);
        rightDown.setDirection(DcMotor.Direction.FORWARD);



        // Set all motors to zero power
        leftUp.setPower(0);
        rightUp.setPower(0);
        leftDown.setPower(0);
        rightDown.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightUp.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDown.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDown.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    public void goForward(float power) {
        leftUp.setPower(power);
        leftDown.setPower(power);
        rightUp.setPower(-power);
        rightDown.setPower(-power);
    }
    public void goForward(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(power);
            leftDown.setPower(power);
            rightUp.setPower(-power);
            rightDown.setPower(-power);
        }
    }
    public void goLeft(float power) {
        leftUp.setPower(-power);
        leftDown.setPower(power);
        rightUp.setPower(-power);
        rightDown.setPower(power);
    }
    public void goLeft(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(-power);
            leftDown.setPower(power);
            rightUp.setPower(-power);
            rightDown.setPower(power);
        }
    }
    public void goRight(float power) {
    leftUp.setPower(power);
    leftDown.setPower(-power);
    rightUp.setPower(power);
    rightDown.setPower(-power);
}
    public void goRight(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(power);
            leftDown.setPower(-power);
            rightUp.setPower(power);
            rightDown.setPower(-power);
        }
    }
    public void goBackward(float power) {
        leftUp.setPower(-power);
        leftDown.setPower(-power);
        rightUp.setPower(power);
        rightDown.setPower(power);
    }
    public void goBackward(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(-power);
            leftDown.setPower(-power);
            rightUp.setPower(power);
            rightDown.setPower(power);
        }
    }
    public void spinRight(float power) {
        leftUp.setPower(power);
        leftDown.setPower(power);
        rightUp.setPower(power);
        rightDown.setPower(power);
    }
    public void spinRight(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(power);
            leftDown.setPower(power);
            rightUp.setPower(power);
            rightDown.setPower(power);
        }
    }
    public void spinLeft(float power) {
        leftUp.setPower(power);
        leftDown.setPower(power);
        rightUp.setPower(power);
        rightDown.setPower(power);
    }
    public void spinLeft(float power, double time){ // goes forward for set time in seconds
        ElapsedTime clock = new ElapsedTime();
        while (clock.time() <= time) {
            leftUp.setPower(power);
            leftDown.setPower(power);
            rightUp.setPower(power);
            rightDown.setPower(power);
        }
    }

}

