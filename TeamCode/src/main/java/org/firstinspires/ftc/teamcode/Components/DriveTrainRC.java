package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;


public class DriveTrainRC {
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;
    private double powerMode = 1;
    private double currentPower = powerMode;
    boolean ok = true;
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    public DriveTrainRC(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight) {
        ok=true;
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void goGoVrumVrumRC(Gamepad lastGamepad, Gamepad currentGamepad) {

        double y = currentGamepad.left_stick_y;
        double x = currentGamepad.left_stick_x * 1.1;
        double rx = -currentGamepad.right_trigger+currentGamepad.left_trigger;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx)/ denominator * powerMode;
        double backLeftPower = (y - x + rx) / denominator * powerMode;
        double frontRightPower = (y - x - rx) / denominator * powerMode;
        double backRightPower = (y + x - rx) / denominator * powerMode;

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

