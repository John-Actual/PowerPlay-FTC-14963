/*
Copyright 2022 FIRST Tech Challenge Team 14963

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

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "DriverControlled", group = "TeleOp")
public class DriverControlled extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.mActuatorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.mActuatorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        double powerLvl = 1;
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Encoder Value:", robot.frontR.getCurrentPosition());
            telemetry.update();

            //Forward, back, left, right, and diagonal Movement
            if (this.gamepad1.dpad_up == true) {
                //forward
                robot.frontBackControlled(-powerLvl);
            }else if (this.gamepad1.dpad_down == true) {
                //backward
                robot.frontBackControlled(powerLvl);
            }else if (this.gamepad1.dpad_right == true) {
                //right
                robot.leftRightControlled(powerLvl);
            }else if (this.gamepad1.dpad_left == true) {
                //left
                robot.leftRightControlled(-powerLvl);
            }else if (this.gamepad1.dpad_up == true && this.gamepad1.dpad_left == true) {
                //forward left
                robot.diagonalControlled(powerLvl, true);
            }else if (this.gamepad1.dpad_down == true && this.gamepad1.dpad_right == true) {
                //backward left
                robot.diagonalControlled(-powerLvl, true);
            }else if (this.gamepad1.dpad_up == true && this.gamepad1.dpad_right == true ) {
                //forward right
                robot.diagonalControlled(powerLvl, false);
            }else if (this.gamepad1.dpad_down == true && this.gamepad1.dpad_left == true) {
                //backward left
                robot.diagonalControlled(-powerLvl, false);
            }else {
                //no movement
                robot.frontBackControlled(0);
                robot.leftRightControlled(0);
                robot.diagonalControlled(0, true);
                robot.diagonalControlled(0, false);
            }

            //Rotation
            if(this.gamepad1.right_stick_x != 0) {
                robot.rotateControlled(- this.gamepad1.right_stick_x);
            }else {
                robot.rotateControlled(0);
            }

            //moving the linear actuator to different positions
            if (this.gamepad1.x == true) {
                robot.linearActuator(1300);
            }else if (this.gamepad1.y == true) {
                robot.linearActuator(2265);
            }else if (this.gamepad1.b == true) {
                robot.linearActuator(3000);
            }else if (this.gamepad1.a == true) {
                robot.openClaw();
                robot.linearActuator(0);
            }

            //closing and opening the claw
            if (this.gamepad1.right_bumper == true) {
                //Closes claw by moving servos together
                robot.closeClaw();
            }else if (this.gamepad1.left_bumper == true) {
                //Opens claw by moving servos to zero position
                robot.openClaw();
            }

            if (this.gamepad1.right_trigger != 0) {
                powerLvl = 0.4;
            }else {
                powerLvl = 1;
            }


        }
    }
}
