package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
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
public class HardwareMRSensor4_0
{
    /* Public OpMode members. */

    public GyroSensor Gyro = null;
    public OpticalDistanceSensor FloorEye = null;
    public ColorSensor BeaconEye= null;

    public final double TELEPOWER = 0.6;
    public final double AUTOPOWER = 0.3;
    public final double ONEFOOTDRIVETIME = 1.4;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMRSensor4_0(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        Gyro = hwMap.gyroSensor.get("Gyro");
        FloorEye = hwMap.opticalDistanceSensor.get("FloorEye");
        BeaconEye = hwMap.colorSensor.get("BeaconEye");

        //Correct motor direction, set all motors to zero power, set all motors to run without encoders.

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
    //TODO PID straight movement with the gyro,
}

