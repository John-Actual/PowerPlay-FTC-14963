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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


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
        robot.frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("frontL", robot.frontL.getCurrentPosition());
            telemetry.addData("frontR", robot.frontR.getCurrentPosition());
            telemetry.addData("backR", robot.backR.getCurrentPosition());
            telemetry.addData("backL", robot.backL.getCurrentPosition());
            telemetry.addData("actuatorR", robot.mActuatorRight.getCurrentPosition());
            telemetry.addData("actuatorL", robot.mActuatorLeft.getCurrentPosition());
            telemetry.update();

            //Forward, back, left, right, rotational, and diagonal Movement
            // uses the inputs of the controller to fill in the values of the movement method
            //if the robots arm is at full height it will go at half speed, or if the right trigger is pushed down
            if (this.gamepad1.right_trigger != 0) {
                robot.movement(this.gamepad1.left_stick_x/2, this.gamepad1.left_stick_y/2, this.gamepad1.right_stick_x / 2);
            } else if (this.gamepad1.left_trigger != 0 || robot.mActuatorLeft.getCurrentPosition() > 3500) {
                robot.movement(this.gamepad1.left_stick_x/4, this.gamepad1.left_stick_y/4, this.gamepad1.right_stick_x / 4);
            } else {
                robot.movement(this.gamepad1.left_stick_x, this.gamepad1.left_stick_y, this.gamepad1.right_stick_x/ 1.333333333);
            }

            //moving the linear actuator to different positions
            if (this.gamepad1.x) {
                robot.linearActuator(robot.firstJunction);
            }else if (this.gamepad1.y) {
                robot.linearActuator(robot.secondJunction);
            }else if (this.gamepad1.b) {
                robot.linearActuator(4000);
            }else if (this.gamepad1.a) {
                if (robot.mActuatorLeft.getCurrentPosition() < 100 || robot.mActuatorLeft.getCurrentPosition() > 1000) {
                    robot.linearActuator(400);
                    sleep(200);
                    robot.openClaw(0.165, 0.1);
                } else if (robot.mActuatorLeft.getCurrentPosition() > 100 || robot.mActuatorLeft.getCurrentPosition() < 500) {
                    robot.openClaw(0.3, 0.25);
                    sleep(200);
                    robot.linearActuator(0);

                }
            }

            //closing and opening the claw
            if (this.gamepad1.right_bumper) {
                //Closes claw by moving servos together
                robot.closeClaw();
                sleep(200);
            }else if (this.gamepad1.left_bumper) {
                if (robot.mActuatorLeft.getCurrentPosition() < 100 || robot.mActuatorLeft.getCurrentPosition() > 1000) {
                    robot.openClaw(0.3, 0.25);
                } else if (robot.mActuatorLeft.getCurrentPosition() > 100 || robot.mActuatorLeft.getCurrentPosition() < 500) {
                    robot.openClaw(0.165, 0.1);
                }
            }

        }
    }
}
