package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class MultiThreadAuto extends Thread {
    private DcMotor motor;
    private double power;

    public void load(DcMotor motor, double power) {
        this.motor = motor;
        this.power = power;
    }



    @Override
    public  void run() {
        int i = 0;
        while (i < motor.getTargetPosition() / 4) {
            motor.setPower(power);
            i = motor.getCurrentPosition();
        }
        while (i > motor.getTargetPosition() / 4) {
            motor.setPower(Range.clip(motor.getPower() - .01, 0, 1));
            i = motor.getCurrentPosition();
        }

    }
}
