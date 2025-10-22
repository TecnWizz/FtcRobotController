package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class DualSwerve {
    Servo ch1,ch2;
    DcMotorEx motor1,motor2;
    public DualSwerve (Servo ch1, Servo ch2, DcMotorEx motor1, DcMotorEx motor2){
        this.ch1=ch1;
        this.ch2=ch2;
        this.motor1=motor1;
        this.motor2=motor2;
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
    public void drive (Gamepad Gamepad1){
        double x = Gamepad1.left_stick_x;
        double y = -Gamepad1.left_stick_y;
        double rx = Gamepad1.right_trigger-Gamepad1.left_trigger;
        double q=Math.toDegrees(Math.atan2(x,y))/180;
        double q1 = Math.abs(q);
        double mp = Math.sqrt(x*x+y*y)*(q/q1);
        ch1.setPosition(Math.max(q1,rx));
        ch2.setPosition(Math.max(q1,rx));
        motor1.setPower(mp);
        motor2.setPower(mp);
    }
}
