package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous(name="Auton_Test")
public class Auton extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive (hardwareMap , new Pose2d(0,0,0));
        waitForStart();
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0,0,0))
                        .lineToX(8)
                        .lineToY(16)
                        .lineToX(32)
                        .lineToX(0)
                        .lineToY(0)
                        .build());
    }

}
