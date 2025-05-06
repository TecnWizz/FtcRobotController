package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;


public class DriveTrainRC {
    private final DcMotorEx leftFront, rightFront, leftBack, rightBack;
    private double powerMode = 1;
    boolean ok;
    public enum State{
        UP,
        DOWN,
    }
    State state = State.DOWN;
    public DriveTrainRC(DcMotorEx leftFront, DcMotorEx rightFront, DcMotorEx leftBack, DcMotorEx rightBack) {
        ok=true;
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.leftBack=leftBack;
        this.rightBack = rightBack;

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void goGoVrumVrumRC(Gamepad aGamepad) {

        double y = -aGamepad.left_stick_y;
        double x = aGamepad.left_stick_x * 1.1;
        double rx = aGamepad.right_trigger-aGamepad.left_trigger;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx)/ denominator * powerMode;
        double backLeftPower = (y - x + rx) / denominator * powerMode;
        double frontRightPower = (y - x - rx) / denominator * powerMode;
        double backRightPower = (y + x - rx) / denominator * powerMode;

        leftFront.setPower(frontLeftPower);
        leftBack.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);

        switch (state){
            case UP:
                powerMode=0.5;
                break;
            case DOWN:
                powerMode=1;
                break;
        }

        if (aGamepad.circle){
            if (ok) {
                state = State.UP;
                ok=false;
            }
            else {
                state = State.DOWN;
                ok=true;
            }

        }

    }


    }

