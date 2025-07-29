package org.firstinspires.ftc.teamcode.Components;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
public class Intake {

    private Servo extend,claw,rclaw,rotation,clipClaw,clipArm;
    public enum State{
        start,
        extend,
        score,
        clip,

    };
    State state = State.start;
    public Intake (Servo extend, Servo claw, Servo rclaw, Servo rotation,Servo clipArm, Servo clipClaw){
        this.extend=extend;
        this.claw=claw;
        this.rclaw=rclaw;
        this.rotation=rotation;
        this.clipClaw = clipClaw;
        this.clipArm = clipArm;

        extend.setPosition(0);
        claw.setPosition(0);
        rclaw.setPosition(0);
        rotation.setPosition(0);
        clipArm.setPosition(0);
        clipClaw.setPosition(0);

    }

    public void intake (Gamepad Gamepad2){
        int x = 1;
         switch (state){
             case start:
                 rotation.setPosition(0);
                 claw.setPosition(0);
                 rclaw.setPosition(0);
                 extend.setPosition(0);
                 break;
             case extend:
                 extend.setPosition(0.5);
                 break;
             case score:
                 claw.setPosition(1);
                 break;
             case clip:
                 extend.setPosition(0);
                 rotation.setPosition(0.1);
                 clipClaw.setPosition(1);
                 claw.setPosition(0);
                 clipArm.setPosition(1);

         }
         if (Gamepad2.circle){
             x ++;
             if (x == 2) {
                 state = State.extend;
             }
             else if (x == 3) {
                 state = State.score;
             }
             else if (x == 4){
                 state = State.clip;
             }
             else if (x==5){
                 state=State.start;
                 x = 1;
             }

         }
    }
}

