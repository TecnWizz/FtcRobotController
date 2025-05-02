package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Extendo {
    private static final double rotation = 2786;
    private static final double divisor = 0.5;
    private final DcMotorEx extendMotor;
    int balancePos =(int)(rotation * divisor);
    int targetPos = (int)rotation;
    int neutralPos;
    public enum State{
        Neutral,
        Retract,
        Balance,
        Extend,
    }
    State state = State.Neutral;
    public Extendo (DcMotorEx extendMotor){
        this.extendMotor = extendMotor;
        neutralPos = extendMotor.getCurrentPosition();
        extendMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void extend (Gamepad currentGamepad){

        switch (state){
            case Retract:
                  extendMotor.setTargetPosition(neutralPos);
                  extendMotor.setPower(1);
                  break;
            case Balance:
                  extendMotor.setTargetPosition(balancePos);
                  extendMotor.setPower(1);
                  break;
            case Extend:
                  extendMotor.setTargetPosition(targetPos);
                  extendMotor.setPower(1);
                  break;
        }
        if (currentGamepad.circle)
            state=State.Retract;
        if (currentGamepad.right_bumper)
            state=State.Balance;
        if (currentGamepad.left_bumper)
            state=State.Extend;

        extendMotor.setPower(currentGamepad.right_stick_x);
    }
}
