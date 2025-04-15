package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeV2 {

    private CRServo intake1, intake2;
    private Servo rotate;
    boolean ok = false;
    public IntakeV2(CRServo intake1 , CRServo intake2 , Servo rotate) {
        this.intake1 = intake1;
        this.intake2 = intake2;
        this.rotate = rotate;
        rotate.setPosition(0);
    }
    public enum State{
        neutralState,
        intake,
        outake,
        rotateIn,
        rotateOut,

    };
    State state = State.neutralState;

    public void aspiratorV2(Gamepad currentGamepad,Gamepad lastGamepad) {
        switch(state){
            case intake:
                intake1.setPower(currentGamepad.right_trigger);
                intake2.setPower(currentGamepad.right_trigger);
                break;
            case outake:
                intake1.setPower(-currentGamepad.left_trigger);
                intake2.setPower(-currentGamepad.left_trigger);
                break;
            case rotateIn:
                    rotate.setPosition(1);
                    break;
            case rotateOut:
                    rotate.setPosition(0);
                    break;

        }
        if (currentGamepad.square){
            if (ok == false) {
                state = State.rotateIn;
                ok = true;
            }
            if (ok == true){
                state = State.rotateOut;
                ok = false;
            }
        }
        if (currentGamepad.right_trigger != 0){
            state = State.intake;
        }
        if (currentGamepad.left_trigger != 0){
            state = State.outake;
        }

    }

}

