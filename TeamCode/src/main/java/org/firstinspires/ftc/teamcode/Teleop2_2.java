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

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Okey dokey kiddos here's a wild ride for you
 * We're using HardwareMech_2_0
 * Uhh this one's controls are the left joystick y and right joystick y
 * that's for the left side and right side respectively
 * lit amirite
 */

@TeleOp(name="Teleop2_2", group="Teleop")
//@Disabled
public class Teleop2_2 extends OpMode{

    /* Declare OpMode members. */
    HardwareMech_2_0 robot = new HardwareMech_2_0(); // use the hardware file bc it's radical
                                                         // could also use HardwarePushbotMatrix clas
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
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
        /*
         so you take the forward component and do the tilty thing by subtracting the x.
         ofc this makes it impossible to go like left = -1 and right = 1.
         that sucks
         but turning should naturally be slower.
         so how bad is it?
         im open to ideas.
         */
        double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;
        telemetry.addLine("left: " + left + "\nright: " + right);

        robot.lBack.setPower(left);
        robot.lFront.setPower(left);
        robot.rBack.setPower(right);
        robot.rFront.setPower(right);
//        if (gamepad2.a) // if a, then turn on the launchy thing
//            robot.launch.setPower(0.4);
//        else
//            robot.launch.setPower(0.0); //otherwise, stop launch thing
//        if  (gamepad2.x)
//            robot.lift.setPower(0.2);
//        else if (gamepad2.y)
//            robot.lift.setPower(-0.2);
//        else
//            robot.lift.setPower(0.0);

        // Send telemetry message to signify robot running;

        updateTelemetry(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        //robot.eye.close();
    }

}
