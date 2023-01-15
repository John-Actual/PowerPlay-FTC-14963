package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MultiThreadControl extends Thread {
    private DcMotor motor;
    private double power;
    public void load(DcMotor motor, double power) {
        this.motor = motor;
        this.power = power;
    }

    @Override
    public void run() {
        motor.setPower(power);
    }
}
