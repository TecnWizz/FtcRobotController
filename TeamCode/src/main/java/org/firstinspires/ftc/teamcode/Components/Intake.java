package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private CRServo intakeServo;
    private Servo rotateServo;
    public Intake(CRServo intakeMotor,Servo rotateServo) {
        this.intakeServo = intakeMotor;
        this.rotateServo = rotateServo;
        rotateServo.setPosition(0);
    }
    public enum State{
        neutralState,
        intake,
        outake,
        rotateIn,
        rotateOut,

    };
    public void aspirator(Gamepad currentGamepad) {
        State state = State.neutralState;
        switch(state){

            case intake:
                if (currentGamepad.right_trigger!=0){
                    intakeServo.setPower((currentGamepad.right_trigger) * 2);
                    state=State.intake;
                }
                break;

            case outake:
                if (currentGamepad.left_trigger!=0){
                    intakeServo.setPower((currentGamepad.left_trigger) * 2);
                    state=State.outake;
                }

            case rotateIn:
                if (currentGamepad.right_bumper){
                    state=State.rotateIn;
                    rotateServo.setPosition(1);
                }

            case rotateOut:
                if (currentGamepad.left_bumper){
                    state=State.rotateOut;
                    rotateServo.setPosition(0);
                }

            }

        }

    }

