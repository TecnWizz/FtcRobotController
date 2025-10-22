package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class MonoSwerve {
    Servo ch1;
    DcMotorEx motor;
    public MonoSwerve(Servo ch1, DcMotorEx motor){
        this.ch1=ch1;
        this.motor=motor;

        ch1.setPosition(1);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
    public void drive (Gamepad Gamepad1){
        double x = Gamepad1.left_stick_x;
        double y = Gamepad1.left_stick_y;
        double rx = Gamepad1.right_trigger-Gamepad1.left_trigger;
        double mp = Math.sqrt(x*x+y*y);
        double q = Math.toDegrees(Math.atan2(x,y))/180;

        motor.setPower(mp);
        ch1.setPosition(Math.max(q,rx));

    }
}
