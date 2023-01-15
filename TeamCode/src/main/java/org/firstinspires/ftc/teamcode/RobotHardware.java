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

    public int firstJunction = 1200;
    public int secondJunction = 2265;
    public int thirdJunction = 3000;

    final int frontLRotation = 537;
    final int frontRRotation = 539;
    final int backLRotation = 536;
    final int backRRotation = 538;

    MultiThreadControl FLT = new MultiThreadControl();
    MultiThreadControl FRT = new MultiThreadControl();
    MultiThreadControl BLT = new MultiThreadControl();
    MultiThreadControl BRT = new MultiThreadControl();

    MultiThreadAuto FLTA = new MultiThreadAuto();
    MultiThreadAuto FRTA = new MultiThreadAuto();
    MultiThreadAuto BLTA = new MultiThreadAuto();
    MultiThreadAuto BRTA = new MultiThreadAuto();

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
    public double linear(double numOfInches,int rotationTicks) {
        final double inches_per_rotation = 3.54331 * Math.PI;
        double ticks_per_inch = rotationTicks / inches_per_rotation;

        return numOfInches * ticks_per_inch;

    }

    public double angular(double degrees, int rotationTicks) {
        final double w = 14;
        final double l = 15.5;

        double diagonal = Math.sqrt(Math.pow(w, 2) + Math.pow(l, 2));
        double circumfrence = diagonal * Math.PI;
        double oneDegree = circumfrence / 360;

        return linear(oneDegree, rotationTicks) * degrees;
    }

    /*
    Start of Controller Movement Section
    */

    // x is x axis, y is y axis, and r is rotational value
    public void movement(double x, double y, double r) {
        FRT.load(frontR, (-y) - x - r);
        FLT.load(frontL, y - x - r);
        BLT.load(backL, y + x - r);
        BRT.load(backR, (-y) + x - r);

        FRT.start();
        FLT.start();
        BRT.start();
        BLT.start();
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

    public void movementAuto(double xInches, double yInches, double rDegrees, double power) {
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontR.setTargetPosition((int) (linear(yInches, frontRRotation) -  linear(xInches, frontRRotation) + angular(rDegrees, frontRRotation)));
        frontL.setTargetPosition((int) (- linear(yInches, frontLRotation) - linear(xInches, frontLRotation) + angular(rDegrees, frontLRotation)));
        backL.setTargetPosition((int) (- linear(yInches, backLRotation) + linear(xInches, backLRotation) + angular(rDegrees, backLRotation)));
        backR.setTargetPosition((int) (linear(yInches, backRRotation) + linear(xInches, backRRotation) + angular(rDegrees, backRRotation)));

        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FRTA.load(frontR, power);
        FLTA.load(frontL, power);
        BLTA.load(backL, power);
        BRTA.load(backR, power);

        FRTA.start();
        FLTA.start();
        BRTA.start();
        BLTA.start();
    }
}
