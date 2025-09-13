package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.VoltageSensor;


public class DriveTrain {
    private final DcMotorEx leftFront, rightFront, leftBack, rightBack;
    private double powerMode = 1;
    boolean ok;
    public DriveTrain(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack) {

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack=leftBack;
        this.rightBack = rightBack;

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
    public void test (Gamepad Gamepad1){

        if (Gamepad1.triangle)
            leftFront.setPower(1);

        if (Gamepad1.cross)
            rightFront.setPower(1);

        if (Gamepad1.square)
            leftBack.setPower(1);

        if (Gamepad1.circle)
            rightBack.setPower(1);
    }

    public void drive(Gamepad Gamepad1) {

        double y = -Gamepad1.left_stick_y;
        double x = Gamepad1.left_stick_x * 1.1;
        double rx = Gamepad1.right_trigger-Gamepad1.left_trigger;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx)/ denominator;
        double backLeftPower = (y + x - rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y - x + rx) / denominator;

        leftFront.setPower(frontLeftPower);
        leftBack.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);

    }


    }

