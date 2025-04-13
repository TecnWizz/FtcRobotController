package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Components.DriveTrain;
public class Arm {
    boolean ok = true;
    private DcMotorEx extendo, slideShift,front;
    private static final double rotations = 2786;
    private static  final double divisor = 0.5;
    private static final int targetPosition = (int)(rotations * divisor);
    private static int neutralPosition;
    public Arm(DcMotorEx extendo, DcMotorEx slideShift) {
        ok=true;
        this.extendo = extendo;
        this.slideShift = slideShift;

        extendo.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        slideShift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideShift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        neutralPosition=slideShift.getCurrentPosition();

    }
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    public void armControl(Gamepad currentGamepad,Gamepad lastGamepad) {

        switch (state){
            case UP:
                slideShift.setTargetPosition(targetPosition);
                slideShift.setPower(1);
                break;
            case DOWN:
                slideShift.setTargetPosition(neutralPosition);
                slideShift.setPower(1);
                break;
        }

        if (currentGamepad.circle){
            if (ok==true) {
                state = State.UP; ok=false;
            }
            else if (ok==false){
                state = State.DOWN;
            }
        }

    }


    }

