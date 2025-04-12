package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class  DriveTrain {
    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private double powerMode = 1;
    private double currentPower = powerMode;

    public DriveTrain(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        this.frontLeftMotor = frontLeft;
        this.frontRightMotor = frontRight;
        this.backLeftMotor = backLeft;
        this.backRightMotor = backRight;

        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        frontLeftMotor.setZeroPowerBehavior(behavior);
        frontRightMotor.setZeroPowerBehavior(behavior);
        backLeftMotor.setZeroPowerBehavior(behavior);
        backRightMotor.setZeroPowerBehavior(behavior);
    }

    public void goGoVrumVrum(Gamepad lastGamepad, Gamepad currentGamepad) {
        double drive = -currentGamepad.left_stick_y;
        double strafe = -currentGamepad.left_stick_x;
        double rotate = -currentGamepad.left_trigger + currentGamepad.right_trigger;
        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(rotate), 1);

        double frontRightPower = ((drive + strafe + rotate)*powerMode) / denominator;
        double frontLeftPower = ((drive - strafe - rotate)*powerMode) / denominator;
        double backRightPower = ((drive - strafe + rotate)*powerMode) / denominator;
        double backLeftPower = ((drive + strafe - rotate)*powerMode) / denominator;

        setMotorPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);


    }
    private void setMotorPowers(double frontLeft, double frontRight, double backLeft, double backRight) {
        frontLeftMotor.setPower(frontLeft);
        frontRightMotor.setPower(frontRight);
        backLeftMotor.setPower(backLeft);
        backRightMotor.setPower(backRight);
    }
}
