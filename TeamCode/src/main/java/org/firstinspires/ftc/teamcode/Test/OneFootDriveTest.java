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
package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.HardwareLegacy4_0;

/**
 * This file is a simple autonomous that launches the balls we have, then
 * pushes over the cap ball and parks on the base thing.
 */

@Autonomous(name = "1pUNCH")  // @Autonomous(...) is the other common choice
//@Disabled
public class OneFootDriveTest extends LinearOpMode {

    /* Declare OpMode members. */
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;
    HardwareLegacy4_0 robot = new HardwareLegacy4_0();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the karel configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        robot.init(hardwareMap);// initialize hardware variables
        waitForStart(); // wait for play button
        telemetry.addData("Path", "Start");
        telemetry.update();// send telemetry to DS that robot has started routine

        double tmpStart = getRuntime();//get current run time in MS (I Think)
        robot.goStraight(robot.AUTOPOWER);// turn on motors to go forward.
        while (opModeIsActive() && getRuntime() < tmpStart + robot.ONEFOOTDRIVETIME){
            telemetry.addData("Path", "1");// tell DS what stage of movement the robot's in
            telemetry.update();
        }
        robot.stopDrive();

        telemetry.addData("Path", "Done");
        telemetry.update();// stop, tell DS the robot's done
    }
}
