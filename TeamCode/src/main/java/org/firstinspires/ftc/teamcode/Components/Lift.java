package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift{
    boolean ok = true;
    private DcMotorEx extendo, slideShift1,slideShift2;
    private static final double rotations = 2786;
    private static  final double divisor = 0.5;
    private static final int targetPosition = (int)(rotations * divisor);
    private static int neutralPosition;
    public Lift(DcMotorEx slideShift1, DcMotorEx slideShift2) {
        ok=true;
        this.extendo = extendo;
        this.slideShift1 = slideShift1;
        this.slideShift2 = slideShift2;

        slideShift1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideShift1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        slideShift2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slideShift2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        neutralPosition=slideShift1.getCurrentPosition();

    }
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    public void DropDown(Gamepad currentGamepad) {

        switch (state){
            case UP:
                slideShift1.setTargetPosition(targetPosition);
                slideShift2.setTargetPosition(targetPosition);
                slideShift1.setPower(1);
                slideShift2.setPower(1);
                break;
            case DOWN:
                slideShift1.setTargetPosition(neutralPosition);
                slideShift2.setTargetPosition(neutralPosition);
                slideShift1.setPower(1);
                slideShift2.setPower(1);

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

