package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Extendo {
    private static final double rotation = 2786;
    private static final double divizor = 0.5;
    private DcMotorEx extendo;
    int balancePos =(int)(rotation * divizor);
    int targetPos = (int)rotation;
    int neutralPos;
    private boolean ok;
    public enum State{
        Neutral,
        Retract,
        Balance,
        Extend,
    };
    State state = State.Neutral;
    public Extendo (DcMotorEx extendo){
        this.extendo = extendo;
        neutralPos = extendo.getCurrentPosition();
        extendo.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        extendo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    private void extend (Gamepad currentGamepad, Gamepad lastGamepad){

        switch (state){
            case Retract:
                  extendo.setTargetPosition(neutralPos);
                  extendo.setPower(1);
                  break;
            case Balance:
                  extendo.setTargetPosition(balancePos);
                  extendo.setPower(1);
                  break;
            case Extend:
                  extendo.setTargetPosition(targetPos);
                  extendo.setPower(1);
                  break;
        }
        if (currentGamepad.circle)
            state=State.Retract;
        if (currentGamepad.right_bumper)
            state=State.Balance;
        if (currentGamepad.left_bumper)
            state=State.Extend;

        extendo.setPower(currentGamepad.right_stick_x);
    }
}
