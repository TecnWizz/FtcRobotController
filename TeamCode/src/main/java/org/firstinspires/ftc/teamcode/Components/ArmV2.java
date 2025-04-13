package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Components.DriveTrainV2;

public class ArmV2{
    boolean ok = true;
    private DcMotorEx extendo, slideShift;
    private static final double rotations = 2786;
    private static  final double divisor = 0.5;
    private static final int targetPosition = (int)(rotations * divisor);
    private static int neutralPosition;
    public ArmV2(DcMotorEx extendo, DcMotorEx slideShift) {
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
    public void slideShiftV2(Gamepad currentGamepad) {

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

