package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This is the new robot, as of 12/13/2016
 * Omniwheels on the corners is the shtick here
 * //TODO Exactly what kind of operation are we running here?
 * //TODO Find what kind of values the sensors return, gyro in particular
 *
 * Legacy sensors here
 */
public class HardwareMR4_0
{
    /* Public OpMode members. */
    public DcMotor lFront = null;
    public DcMotor rFront = null;
    public DcMotor lBack = null;
    public DcMotor rBack = null;

    public GyroSensor Gyro = null;
    public OpticalDistanceSensor FloorEye = null;
    public ColorSensor BeaconEye= null;

    public final double TELEPOWER = 0.6;
    public final double AUTOPOWER = 0.3;
    public final double ONEFOOTDRIVETIME = 1.4;

    private final double KPGYRO = 1.0/180.0;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMR4_0(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        lFront = hwMap.dcMotor.get("lFront");   //LeftMotor FrontMotor
        rFront = hwMap.dcMotor.get("rFront");   //RightMotor FrontMotor
        lBack = hwMap.dcMotor.get("lBack");    //LeftMotor BackMotor
        rBack = hwMap.dcMotor.get("rBack");    //RightMotor BackMotor

        Gyro = hwMap.gyroSensor.get("Gyro");
        FloorEye = hwMap.opticalDistanceSensor.get("FloorEye");
        BeaconEye = hwMap.colorSensor.get("BeaconEye");

        DcMotor[] driveMotors = {lFront, rFront, lBack, rBack};

        //Correct motor direction, set all motors to zero power, set all motors to run without encoders.
        for(DcMotor motor : driveMotors) {
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        rFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rBack.setDirection(DcMotorSimple.Direction.REVERSE);
        lFront.setDirection(DcMotorSimple.Direction.REVERSE);
        lBack.setDirection(DcMotorSimple.Direction.REVERSE);

        FloorEye.enableLed(true);
        BeaconEye.enableLed(false);
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
    public void stopDrive() {
        lFront.setPower(0);
        lBack.setPower(0);
        rFront.setPower(0);
        rBack.setPower(0);

    }
    public void goStraight(double power) {
        lFront.setPower(power);
        lBack.setPower(power);
        rFront.setPower(power);
        rBack.setPower(power);
    }
    public void strafe(double power){ // positive: right, negative: left
        lFront.setPower(power);
        lBack.setPower(-power);
        rFront.setPower(-power);
        rBack.setPower(power);
    }
    public void spin(double power){ // positive: right, negative: left
        lFront.setPower(-power);
        lBack.setPower(-power);
        rFront.setPower(power);
        rBack.setPower(power);
    }

    //TODO PID straight movement with the gyro
    //RN just tryna use proportional drive, but may or may not integrate some dumbshit later
    //always calibrate gyro first
    public double[] goStraightGyro(double power){ //use raw heading values?\
        double[] sidePowers = new double[2];
        int currentHeading = this.Gyro.getHeading();
        if (this.Gyro.getHeading() > 180)// turn gyro heading from 0 to 360 to -180 to 180
            currentHeading -= 360;

        sidePowers[0] = power + ((1.0-power) * (currentHeading)/180.0);
        sidePowers[1] = power - ((1.0-power) * (currentHeading)/180.0);
        lFront.setPower(sidePowers[0]);
        lBack.setPower(sidePowers[0]);
        rFront.setPower(sidePowers[1]);
        rBack.setPower(sidePowers[1]);
//        if (currentHeading < 0) {
//            lFront.setPower(power - 0.1);
//            lBack.setPower(power - 0.1);
//            rFront.setPower(power + 0.1);
//            rBack.setPower(power + 0.1);
//        }
        return sidePowers;
    }
}

