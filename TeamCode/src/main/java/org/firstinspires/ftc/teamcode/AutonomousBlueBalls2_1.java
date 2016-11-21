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

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO this entire thing
 * Important: Only on blue alliance team
 * Should align robot parallel to the beacon wall close to the corner vortex
 * Move slowly along the wall, scanning for the image
 * Turn 90 right, then go forward to the beacon
 * Find out what color is seen with color sensor, then move adjust as necessary
 * Press the button
 * Move backwards, orient robot parallel to wall again, then move to the second beacon
 * Repeat.
 * Turn around and knock off the cap ball.
 */

@Autonomous(name="AutoBalls")
//@Disabled
public class AutonomousBlueBalls2_1 extends LinearOpMode {

    public static final String TAG = "Vuforia Sample";

    OpenGLMatrix lastLocation = null;

    private final double SLOWPWR = 0.3;

    private final double LENGTHS[] = {1400, 1400, 1400, 1400, 1400, 1400};

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    @Override public void runOpMode() {
        /**
         * Start up Vuforia, telling it the id of the view that we wish to use as the parent for
         * the camera monitor feedback; if no camera monitor feedback is desired, use the parameterless
         * constructor instead. We also indicate which camera on the RC that we wish to use. For illustration
         * purposes here, we choose the back camera; for a competition karel, the front camera might
         * prove to be more convenient.
         *
         * Note that in addition to indicating which camera is in use, we also need to tell the system
         * the location of the phone on the karel; see phoneLocationOnRobot below
         *
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AdiNvu7/////AAAAGb6+m2UBfE+SodbKBmgDC7xOPWpHHVqzgv0d02rPS4AkjQ5" +
                "YFMn/0yMU50PgPc/WMaiQvY3D6b2l3vTmqA/3BeAKAWSY8yPsz4EKrMr2PA/ECG1MUOI7GnvXYGdEpXjKgDIw" +
                "TdGT0pTiEKfus3LBLQeT77/tcPgDepfOUT/tsoqx4Atg1Xz6XdbF+CGYatJL9oyLaLjMRSkAq+uERdSeOZTEAhtx" +
                "2MMGxc/HEwerezgg87IPoD1pcbSnOsLgmVrUMuqi0TIn/KtvsiJRVmG5dIO58WnbVUxlTWBxSNvcwQJ9E8NZXQZGSCb" +
                "QXHDPHrf2bmcNtSp39lYx+ZqaKaRzqoCS6soItsnaJrV6gbTb8zWA";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * load the targets
         * I am loading all of them, even though I'm only using the blue ones. I just don't want to make two data thing files.
         * TODO separate the red and blue images
         */
        VuforiaTrackables imageTargets= this.vuforia.loadTrackablesFromAsset("ImageMarkers");
        VuforiaTrackable bWheels = imageTargets.get(0);
        bWheels.setName("Wheels");  // Wheels

        VuforiaTrackable rTools = imageTargets.get(1);
        rTools.setName("Tools");  // Tools

        VuforiaTrackable bLegos = imageTargets.get(2);
        bLegos.setName("Legos");  // Legos

        VuforiaTrackable rGears = imageTargets.get(3);
        rGears.setName("Gears");  // Gears

        /** For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(imageTargets);

        /**
         * I'm using mm because apparently vuforia likes it when you do
         * some of these values are supposed to be used for when I find out the
         * location of the robot. I'll get rid of them when I finalize the ideas. Or something.
         */
        final float mmPerInch        = 25.4f;
        final float mmBotWidth       = 370;            // 370 mm
        final float mmFTCFieldWidth  = (5*12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels


        HardwareMech_2_0 robot = new HardwareMech_2_0();

        robot.init(hardwareMap);
        waitForStart();
        telemetry.addData("Path", "Started");
        telemetry.update();
        /** Start tracking the data sets we care about. */
        imageTargets.activate();

        robot.goStraight(robot.AUTOPOWER);
        telemetry.addData("Path", "Move Along Corner Vortex");
        telemetry.update();

        double tmpStart = getRuntime();
        while (opModeIsActive() && getRuntime() < tmpStart + LENGTHS[0]){
        }

        tmpStart = getRuntime();
        robot.turn(-robot.AUTOPOWER);
        telemetry.addData("Path", "Turn to be parallel with wall");
        telemetry.update();
        while (opModeIsActive() && getRuntime() < tmpStart + LENGTHS[1]){
        }

        robot.goStraight(SLOWPWR);
        telemetry.addData("Path", "Go until i see the paper");
        telemetry.update();
        while (opModeIsActive() && ((VuforiaTrackableDefaultListener)bLegos.getListener()).isVisible()){
        }

        tmpStart = getRuntime();
        robot.turn(robot.AUTOPOWER);
        telemetry.addData("Path", "Turn towards the beacon");
        telemetry.update();
        while (opModeIsActive() && getRuntime() < tmpStart + LENGTHS[2]){
        }

        tmpStart = getRuntime();
        robot.goStraight(robot.AUTOPOWER);
        telemetry.addData("Path", "Go to the beacon");
        telemetry.update();
        while (opModeIsActive() && getRuntime() < tmpStart + LENGTHS[3]){
        }
    }

    /**
     * A simple utility that extracts positioning information from a transformation matrix
     * and formats it in a form palatable to a human being.
     */
    String format(OpenGLMatrix transformationMatrix) {
        return transformationMatrix.formatAsTransform();
    }
}
