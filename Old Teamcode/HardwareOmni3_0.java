package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


public class HardwareOmni3_0 {
    /* Public OpMode members. */
    public DcMotor FrontMotor = null;   // lf   rf
    public DcMotor BackMotor = null;   //
    public DcMotor LeftMotor = null;    // lb   rb
    public DcMotor RightMotor = null;    //

    public LightSensor FloorEye = null;
    public LightSensor BeaconLooker = null;
    public UltrasonicSensor DistanceLooker = null;

    public double TELEPOWER = 1.0;
    public final double AUTOPOWER = 0.5;
    public final double ONEFOOTDRIVETIME = 0.96;     // THis and NINETYDEGREEDRIVETIME are experimentally determined
    public final long NINETYDEGREEDRIVETIME = 600; // at AUTOPOWER power.

    public final String LICENSEKEY= "AdiNvu7/////AAAAGb6+m2UBfE+SodbKBmgDC7xOPWpHHVqzgv0d02rPS4AkjQ5" +
            "YFMn/0yMU50PgPc/WMaiQvY3D6b2l3vTmqA/3BeAKAWSY8yPsz4EKrMr2PA/ECG1MUOI7GnvXYGdEpXjKgDIw" +
            "TdGT0pTiEKfus3LBLQeT77/tcPgDepfOUT/tsoqx4Atg1Xz6XdbF+CGYatJL9oyLaLjMRSkAq+uERdSeOZTEAhtx" +
            "2MMGxc/HEwerezgg87IPoD1pcbSnOsLgmVrUMuqi0TIn/KtvsiJRVmG5dIO58WnbVUxlTWBxSNvcwQJ9E8NZXQZGSCb" +
            "QXHDPHrf2bmcNtSp39lYx+ZqaKaRzqoCS6soItsnaJrV6gbTb8zWA";
    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public HardwareOmni3_0() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        FrontMotor = hwMap.dcMotor.get("Front");   //LeftMotor FrontMotor
        BackMotor = hwMap.dcMotor.get("Back");   //RightMotor FrontMotor
        RightMotor = hwMap.dcMotor.get("Right");    //LeftMotor BackMotor
        LeftMotor = hwMap.dcMotor.get("Left");    //RightMotor


//        FloorEye = hwMap.lightSensor.get("floorEye");
//        BeaconLooker = hwMap.lightSensor.get("beaconEye");
//        DistanceLooker = hwMap.ultrasonicSensor.get("distanceEye");

        DcMotor[] driveMotors = {FrontMotor, BackMotor, RightMotor, LeftMotor};

        //Correct motor direction, set all motors to zero power, set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        for (DcMotor motor : driveMotors) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
            motor.setPower(0);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

//        FloorEye.enableLed(true);
//        BeaconLooker.enableLed(false);

        FrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)
        FrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        FrontMotor.setPower(0.0);
        BackMotor.setPower(0.0);
        RightMotor.setPower(0.0);
        LeftMotor.setPower(0.0);

    }
    public void goStraight(double power) {// goes forward/backward. just input a negative power to go backward.
        RightMotor.setPower(-power);
        LeftMotor.setPower(power);
    }

    public void strafe(double power) { //moves in a right or left. positive: right, negative: left
        FrontMotor.setPower(power);
        BackMotor.setPower(-power);
    }
    public void spin(double power) { //turns. positive: right. negative: left. Pivot turn.
        FrontMotor.setPower(power);
        BackMotor.setPower(power);
        RightMotor.setPower(power);
        LeftMotor.setPower(power);
    }
    public void moveUpRight(double power){
        RightMotor.setPower(-power);
        FrontMotor.setPower(-power);
        LeftMotor.setPower(power);
        BackMotor.setPower(power);
    }
    public void moveDownRight(double power){
        RightMotor.setPower(power);
        FrontMotor.setPower(-power);
        LeftMotor.setPower(-power);
        BackMotor.setPower(power);
    }
    public void moveDownLeft(double power){
        RightMotor.setPower(power);
        FrontMotor.setPower(power);
        LeftMotor.setPower(-power);
        BackMotor.setPower(-power);
    }
    public void moveUpLeft(double power){
        RightMotor.setPower(-power);
        FrontMotor.setPower(power);
        LeftMotor.setPower(power);
        BackMotor.setPower(-power);
    }
}

