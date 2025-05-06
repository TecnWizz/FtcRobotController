package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private final CRServo intake1, intake2;
    private final Servo rotate;
    boolean ok = false;

    public Intake(CRServo intake1, CRServo intake2, Servo rotateServo) {
        this.intake2 = intake2;
        this.intake1 = intake1;
        this.rotate = rotateServo;
        rotateServo.setPosition(0);
    }

    public enum State {
        neutralState,
        intake,
        outake,
        rotateIn,
        rotateOut,

    }
    State state = State.neutralState;

    public void aspirator(Gamepad bGamepad) {

        switch (state) {
            case intake:
                intake1.setPower(bGamepad.right_trigger);
                intake2.setPower(bGamepad.right_trigger);
                break;
            case outake:
                intake1.setPower(-bGamepad.left_trigger);
                intake2.setPower(-bGamepad.left_trigger);
                break;
            case rotateIn:
                rotate.setPosition(1);
                break;
            case rotateOut:
                rotate.setPosition(0);
                break;

        }
        if (bGamepad.square) {
            if (!ok) {
                state = State.rotateIn;
                ok = true;
            }
            else{
                state = State.rotateOut;
                ok = false;
            }
        }
        if (bGamepad.right_trigger != 0) {
            state = State.intake;
        }
        if (bGamepad.left_trigger != 0) {
            state = State.outake;
        }


    }
}

