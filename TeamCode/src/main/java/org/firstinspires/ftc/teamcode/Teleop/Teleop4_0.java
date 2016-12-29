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
package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.teamcode.HardwareLegacy4_0;

/**
 * Standard omni wheel teleop
 *
 * gamepad1: dpad for movement, bumpers for spinning
 * gamepad2: a: collector, b: launch //TODO I still need to know what kind of operation is going on here
 *
 */

@TeleOp(name="Teleop4_0", group="Teleop")
@Disabled
public class Teleop4_0 extends OpMode{

    /* Declare OpMode members. */
    HardwareLegacy4_0 robot       = new HardwareLegacy4_0(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify karel waiting;
        telemetry.addData("Status", "Ready to Rumble");    //
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (gamepad1.dpad_up) { // when up on dpad is pressed, go forward
            robot.goStraight(robot.TELEPOWER);
            telemetry.addData("Movement: ", "Forward");
        }
        else if (gamepad1.dpad_down) { //when down on dpad is pressed, go backward
            robot.goStraight(-robot.TELEPOWER);
            telemetry.addData("Movement: ", "Backward");
        }
        else if (gamepad1.dpad_right) { // when right on dpad is pressed, go right
            robot.strafe(robot.TELEPOWER);
            telemetry.addData("Movement: ", "RightMotor");
        }
        else if (gamepad1.dpad_left) { // when left on dpad is pressed, go left
            robot.strafe(-robot.TELEPOWER);
            telemetry.addData("Movement: ", "LeftMotor");
        }
        else if (gamepad1.left_bumper) { // when lbumper on dpad is pressed, spin left
            robot.spin(-robot.TELEPOWER);
            telemetry.addData("Movement: ", "CCL");
        }
        else if (gamepad1.right_bumper) { // when rbumper on dpad is pressed, spin right
            robot.spin(robot.TELEPOWER);
            telemetry.addData("Movement: ", "CC");
        }
        else{ //otherwise stop moving around!
            robot.stopDrive();
            telemetry.addData("Movement: ", "Stop");
        }
        //TODO add launchy things when it gets to that time

        updateTelemetry(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        //karel.eye.close();
    }

}