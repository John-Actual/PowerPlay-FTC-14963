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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous

public class NoCameraAuto extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");

        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
/*
        telemetry.addLine(String.format("encoderFR: %.2f ", robot.frontR.getCurrentPosition()));y
        telemetry.addData("encoderFL: %.2f ", robot.frontL.getCurrentPosition());
        telemetry.addData("encoderBR: %.2f ", robot.backR.getCurrentPosition());
        telemetry.addData("encoderBL: %.2f ", robot.backL.getCurrentPosition());
        robot.closeClaw();
        sleep(1000);

 */
        robot.MoveVerticle(33*26,0.5);
        while (opModeIsActive() && robot.frontL.isBusy() && robot.frontR.isBusy() ) {sleep(10);}
        robot.MoveHoritzontal(60*25, 0.5);
        sleep(10000);
        //sleep(1000);
        //robot.frontBackAuto(-4500, 1);
        //while (opModeIsActive() && robot.frontL.isBusy() && robot.frontR.isBusy() ) {sleep(10);}
        //sleep(10000);


        // run until the end of the match (driver presses STOP)
    }
}