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
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public class RobotHardware {
    /* Instantatiate motors and servos */
    public DcMotor frontL;
    public DcMotor backL;
    public DcMotor frontR;
    public DcMotor backR;
    public DcMotor mActuatorLeft;
    public DcMotor mActuatorRight;
    public HardwareDevice webcam_1;
    
    public Servo servoL;
    public Servo servoR;
    
    HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    
        
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
        
        /*
        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        */
        
        servoL.setPosition(0);
        servoR.setDirection(Servo.Direction.REVERSE);
        servoR.setPosition(0);
        
    }
    
    /*
    Start of Controller Movement Section
    */
    
    //Forward and Backward control with Controller
    public void frontBackControlled(double value) {
        //Sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
        frontL.setPower(value);
        backL.setPower(value);
        frontR.setPower(value);
        backR.setPower(value);
    }
    
    //moves robot diagonally with Controller
    public void diagonalControlled(double value, boolean bool) {
        //sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //When bool is true, robot will move forward left, backward right
        if (bool == true) {
            frontL.setPower(value);
            backR.setPower(value);
        }else {
            backL.setPower(value);
            frontR.setPower(value);
        }
    }
    
    //moves robot left and right with Controller
    public void leftRightControlled(double value) {
        //sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        frontL.setPower(-value);
        backL.setPower(value);
        frontR.setPower(value);
        backR.setPower(-value);
        
    }
    
    //rotates robot about an axis with Controller
    public void rotateControlled(double value) {
        //sets motors to run without encoder
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        frontL.setPower(value);
        backL.setPower(value);
        frontR.setPower(- value);
        backR.setPower(- value);
    }
    
    public void actuatorControlled(double value) {
        
        
        mActuatorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mActuatorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        mActuatorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mActuatorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        mActuatorLeft.setPower(value);
        mActuatorRight.setPower(- value);
        
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
       // mActuatorRight.setPower(1);
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
    public void frontBackAuto(int target, double powerLvl) {
        frontL.setTargetPosition(target);
        frontR.setTargetPosition(target);
        backL.setTargetPosition(target);
        backR.setTargetPosition(target);
        //sets motors to run using encoder
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontL.setPower(powerLvl);
        frontR.setPower(powerLvl);
        backL.setPower(powerLvl);
        backR.setPower(powerLvl);
    }
    
    //moves left and right autonomously
    public void leftRightAuto(int target, double powerLvl) {
        //sets target position
        frontL.setTargetPosition(target);
        frontR.setTargetPosition(target);
        backL.setTargetPosition(target);
        backR.setTargetPosition(target);
        //sets motors to run using encoder
        frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        frontL.setPower(powerLvl);
        frontR.setPower(-powerLvl);
        backL.setPower(-powerLvl);
        backR.setPower(powerLvl);
    }
    
    
}
