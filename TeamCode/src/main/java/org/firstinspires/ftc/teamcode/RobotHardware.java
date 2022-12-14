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




import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class RobotHardware {
    /* Instantatiate motors and servos */
    public DcMotor frontL;
    public DcMotor backL;
    public DcMotor frontR;
    public DcMotor backR;
    public DcMotor mActuatorLeft;
    public DcMotor mActuatorRight;

    public Servo servoL;
    public Servo servoR;

    public int firstJunction = 1300;
    public int secondJunction = 2265;
    public int thirdJunction = 3000;



    //initalizes all hardware, must be run before movement
    public void init(HardwareMap hardwareMap) {

        frontL = hardwareMap.get(DcMotor.class, "frontmotorL");
        backL = hardwareMap.get(DcMotor.class, "backmotorL");
        frontR = hardwareMap.get(DcMotor.class, "frontmotorR");
        backR = hardwareMap.get(DcMotor.class, "backmotorR");
        mActuatorLeft = hardwareMap.get(DcMotor.class, "actuatorL");
        mActuatorRight = hardwareMap.get(DcMotor.class, "actuatorR");
        servoL = hardwareMap.get(Servo.class, "servoL");
        servoR = hardwareMap.get(Servo.class, "servoR");

        frontL.setPower(0);
        frontR.setPower(0);
        backL.setPower(0);
        backR.setPower(0);
        mActuatorLeft.setPower(0);
        mActuatorRight.setPower(0);



        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        servoL.setPosition(0);
        servoR.setDirection(Servo.Direction.REVERSE);
        servoR.setPosition(0);

    }
    //Math
    public int math(int numOfInches) {
        final int ticks_per_rotation = 550;
        final double inches_per_rotation = 3.54331 * Math.PI;
        double ticks_per_inch = ticks_per_rotation / inches_per_rotation;

        return numOfInches * (int) ticks_per_inch;

    }

    /*
    Start of Controller Movement Section
    */

    public void movement(double x, double y) {
        //Sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontL.setPower(y - x);
        backL.setPower(y + x);
        frontR.setPower((-y) + x);
        backR.setPower((-y) - x);
    }

    //rotates robot about an axis with Controller
    public void rotateControlled(double value) {
        //sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontL.setPower(-value);
        backL.setPower(-value);
        frontR.setPower(-value);
        backR.setPower(-value);
    }

    /*
    End of Controller Movement Section
    Start of Actuator and Claw Section
    */

    public void linearActuator(int targetValue) {


        mActuatorRight.setTargetPosition(- targetValue);
        mActuatorLeft.setTargetPosition(targetValue);

        mActuatorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mActuatorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        mActuatorLeft.setPower(1);
        mActuatorRight.setPower(1);
    }

    public void openClaw() {
        servoL.setPosition(0);
        servoR.setPosition(0);
    }

    public void closeClaw() {
        servoL.setPosition(0.12);
        servoR.setPosition(0.17);
    }

    /*
    End of Actuator and Claw Section
    Start of Autonomous Section
    */

    //moves forward and back autonomously
    public void frontBackAuto(int inches, double powerLvl) {
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontR.setTargetPosition(math(inches));
        frontL.setTargetPosition(- math(inches));
        backL.setTargetPosition(- math(inches));
        backR.setTargetPosition(math(inches));
        //sets motors to run using encoder
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontR.setPower(powerLvl);
        frontL.setPower(powerLvl);
        backL.setPower(powerLvl);
        backR.setPower(powerLvl);
    }



    //moves left and right autonomously
    public void leftRightAuto(int inches, double powerLvl) {
        //Negative inches is left
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sets target position
        frontL.setTargetPosition(- math(inches));
        frontR.setTargetPosition(math(inches));
        backL.setTargetPosition(math(inches));
        backR.setTargetPosition(- math(inches));
        //sets motors to run using encoder
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontL.setPower(powerLvl);
        backL.setPower(powerLvl);
        frontR.setPower(powerLvl);
        backR.setPower(powerLvl);
    }

    public void rotateAuto(int degrees, double powerLvl) {
        //negative degrees goes to the right
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        final double degree = 1080 / 90;

        frontL.setTargetPosition((int) (degree * degrees));
        frontR.setTargetPosition((int) (degree * degrees));
        backL.setTargetPosition((int) (degree * degrees));
        backR.setTargetPosition((int) (degree * degrees));

        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontL.setPower(powerLvl);
        backL.setPower(powerLvl);
        frontR.setPower(powerLvl);
        backR.setPower(powerLvl);
    }

}
