/*
Copyright 2022 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous

public class MotorTest extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");

        robot.init(hardwareMap);
        telemetry.addData("Encoder", robot.frontR);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.closeClaw();
        sleep(200);
        robot.linearActuator(700);
        robot.movementAuto(0.3, 196, 196, -196,-196);
        sleep(1000);
        robot.movementAuto(0.3,-2948,2901,2929,-2966);
        sleep(5000);
        robot.movementAuto(0.3,200,-200,-200,200);//was 200 on all
        robot.linearActuator(robot.thirdJunction);
        sleep(2000);
        robot.movementAuto(0.3, 434,447,-462,-481);
        sleep(2000);
        robot.openClaw(0.165, 0.1);
        sleep(5000);
        robot.movementAuto(0.3, 200,-200,-200,200);
        robot.linearActuator(400);
        sleep(2000);



        // run until the end of the match (driver presses STOP)
    }
}