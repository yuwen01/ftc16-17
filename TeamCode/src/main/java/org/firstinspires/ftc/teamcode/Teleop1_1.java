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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This file provides basic Telop driving for a Pushbot karel.
 * The code is structured as an Iterative OpMode
 *
 * All device access is managed through the HardwareOmni1_0 class.
 *
 * This particular OpMode executes a basic Omni Wheel Teleop
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Teleop1_1", group="Teleop")
@Disabled
public class Teleop1_1 extends OpMode{

    /* Declare OpMode members. */
    HardwareOmni_1_1 robot       = new HardwareOmni_1_1(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class
    public final double JOYTHRESHOLDY = 0.4;
    public final double JOYTHRESHOLDX = 0.4;

    public double JoydriveX;
    public double JoydriveY;
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
        updateTelemetry(telemetry);
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
            robot.goLeft(robot.TELEPOWER);
            telemetry.addData("Movement: ", "Forward");
        }
        else if (gamepad1.dpad_down) { //when down on dpad is pressed, go backward
            robot.goRight(robot.TELEPOWER);
            telemetry.addData("Movement: ", "Backward");
        }
        else if (gamepad1.dpad_right) { // when right on dpad is pressed, go right
            robot.goForward(robot.TELEPOWER);
            telemetry.addData("Movement: ", "Right");
        }
        else if (gamepad1.dpad_left) { // when left on dpad is pressed, go left
            robot.goBackward(robot.TELEPOWER);
            telemetry.addData("Movement: ", "Left");
        }
        else if (gamepad1.left_bumper) { // when lbumper on dpad is pressed, spin left
            robot.spinLeft(robot.TELEPOWER);
            telemetry.addData("Movement: ", "CCL");
        }
        else if (gamepad1.right_bumper) { // when rbumper on dpad is pressed, spin right
            robot.spinRight(robot.TELEPOWER);
            telemetry.addData("Movement: ", "CC");
        }
        else{ //otherwise stop moving around!
            robot.stopDrive();
            telemetry.addData("Movement: ", "Stop");
        }
        if (gamepad1.a){
            robot.TELEPOWER -= 0.2;
        }
        else if (gamepad1.b){
            robot.TELEPOWER += 0.2;
        }
        if (gamepad2.a) // if a, then turn on the launchy thing
            robot.launch.setPower(0.4);
        else
            robot.launch.setPower(0.0); //otherwise, stop launch thing
        if  (gamepad2.x)
            robot.lift.setPower(0.2);
        else if (gamepad2.y)
            robot.lift.setPower(-0.2);
        else
            robot.lift.setPower(0.0);

        // Send telemetry message to signify karel running;

        updateTelemetry(telemetry);
        // Send telemetry message t signify karel running;

        updateTelemetry(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
