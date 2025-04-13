package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class  DriveTrainV2 {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private double powerMode = 1;
    private double currentPower = powerMode;
    boolean ok = true;
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    public DriveTrainV2(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight) {
        ok=true;
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        setZeroPowerBehaviorV2(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setZeroPowerBehaviorV2(DcMotor.ZeroPowerBehavior behavior) {
        frontLeft.setZeroPowerBehavior(behavior);
        frontRight.setZeroPowerBehavior(behavior);
        backLeft.setZeroPowerBehavior(behavior);
        backRight.setZeroPowerBehavior(behavior);
    }

    public void goGoVrumVrumV2(Gamepad lastGamepad, Gamepad currentGamepad) {

        double y = -currentGamepad.left_stick_y;
        double x = currentGamepad.left_stick_x * 1.1;
        double rx = currentGamepad.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) * powerMode / denominator;
        double backLeftPower = (y - x + rx) * powerMode / denominator;
        double frontRightPower = (y - x - rx) * powerMode / denominator;
        double backRightPower = (y + x - rx) * powerMode / denominator;

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        switch (state){
            case UP:
                powerMode=0.5;
                break;
            case DOWN:
                powerMode=1;
                break;
        }

        if (currentGamepad.circle){
            if (ok==true) {
                state = State.UP;
                ok=false;
            }
            else if (ok==false){
                state = State.DOWN;
                ok=true;
            }

        }

    }


    }

