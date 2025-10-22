package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.VoltageSensor;


public class DriveTrain {
    private final DcMotorEx leftFront, rightFront, leftBack, rightBack;
    VoltageSensor battery;
    double voltage;
    boolean ok;
    public DriveTrain(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack) {

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack=leftBack;
        this.rightBack = rightBack;

        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

    }

    public void test(Gamepad Gamepad1){
        boolean x = Gamepad1.square;
        boolean x1 = Gamepad1.circle;
        boolean x2 = Gamepad1.cross;
        boolean x3 = Gamepad1.triangle;

        int y = x? 1:0;
        int y1= x1? 1:0;
        int y2 = x2? 1:0;
        int y3= x3? 1:0;

        leftFront.setPower(y);
        leftBack.setPower(y1);
        rightFront.setPower(y2);
        rightBack.setPower(y3);
    }
    public void drive(Gamepad Gamepad1) {
        voltage = battery.getVoltage();
        double y = -Gamepad1.left_stick_y;
        double x = Gamepad1.left_stick_x*1.1;
        double rx = Gamepad1.right_trigger-Gamepad1.left_trigger;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx)/ denominator * voltage;
        double backLeftPower = (y - x + rx) / denominator * voltage;
        double frontRightPower = (y - x - rx) / denominator * voltage;
        double backRightPower = (y + x - rx) / denominator *voltage;
        leftFront.setPower(frontLeftPower);
        leftBack.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);

    }

    }

