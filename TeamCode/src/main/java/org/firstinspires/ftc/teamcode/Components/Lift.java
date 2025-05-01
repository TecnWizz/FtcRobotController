package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Lift{
    boolean ok = true;
    private DcMotorEx liftMotor1,liftMotor2;
    private static final double rotations = 2786;
    private static  final double divisor = 0.5;
    private static final int targetPosition = (int)(rotations * divisor);
    private static int neutralPosition;
    public Lift(DcMotorEx liftMotor1, DcMotorEx liftMotor2) {
        ok=true;
        this.liftMotor1 = liftMotor1;
        this.liftMotor2 = liftMotor2;

        liftMotor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        liftMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        neutralPosition=liftMotor1.getCurrentPosition();

    }
    public enum State{
        UP,
        DOWN,
    };
    State state = State.DOWN;
    public void dropDown(Gamepad currentGamepad) {

        switch (state){
            case UP:
                liftMotor1.setTargetPosition(targetPosition);
                liftMotor2.setTargetPosition(targetPosition);
                liftMotor1.setPower(1);
                liftMotor2.setPower(1);
                break;
            case DOWN:
                liftMotor1.setTargetPosition(neutralPosition);
                liftMotor2.setTargetPosition(neutralPosition);
                liftMotor1.setPower(1);
                liftMotor2.setPower(1);

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

