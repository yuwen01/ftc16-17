/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.nio.ByteBuffer;

/**
 * This file is a simple autonomous that launches the balls we have, then
 * pushes over the cap ball and parks on the base thing.
 */

@Autonomous(name = "Autonomous_Simple")  // @Autonomous(...) is the other common choice
//@Disabled
public class Autonomous3_0 extends LinearOpMode {

    /* Declare OpMode members. */
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;
    HardwareOmni3_0 robot = new HardwareOmni3_0();

    private final double LINETHRESHOLD = 0.5;
    private final double SLOW = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.vuforiaLicenseKey = robot.LICENSEKEY;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;


        VuforiaLocalizer locale = ClassFactory.createVuforiaLocalizer(params);
        locale.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);


        robot.init(hardwareMap);// initialize hardware variables
        waitForStart(); // wait for play button
        telemetry.addData("Path", "Start");
        telemetry.update();// send telemetry to DS that robot has started routine

        double tmpStart = getRuntime();//get current run time in MS
        robot.stopDrive();
        while (opModeIsActive() && getRuntime() < tmpStart + 10.0){
        }

        tmpStart = getRuntime();
        robot.goStraight(-robot.AUTOPOWER);
        while (opModeIsActive() && getRuntime() < tmpStart + 7.0){
        }

        tmpStart = getRuntime();
        robot.spin(robot.AUTOPOWER);
        while (opModeIsActive() && getRuntime() < tmpStart + 2.0){}

        robot.stopDrive();
        telemetry.addData("Path", "Done");
        telemetry.update();// stop, tell DS the robot's don

    }
    public void encoderDriveRight(double targetFeet){

    }
    @Nullable
    public static Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int format) {

        long numImgs = frame.getNumImages();
        for (int i = 0; i < numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }//if
        }//for

        return null;
    }

}

