package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "avocado teleop")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {


        Servo clawServo = hardwareMap.servo.get("clawServo");

        DcMotor rotateMotor = hardwareMap.dcMotor.get("rotateMotor");

        DcMotor slideShiftMotor = hardwareMap.dcMotor.get("slideShiftMotor");

        DcMotor extendMotor = hardwareMap.dcMotor.get("extendMotor");

        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");

        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");

        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");

        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideShiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        slideShiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideShiftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double ticks = 2786.2;
        double turnage = 2;
        double Target = ticks/turnage;

        waitForStart();
        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            double frontLeftPower = (drive + strafe + rotate)/3;
            double frontRightPower = (drive - strafe - rotate)/3;
            double backLeftPower = (drive - strafe + rotate)/3;
            double backRightPower = (drive + strafe - rotate)/3;


            frontLeftMotor.setPower(frontLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backLeftMotor.setPower(backLeftPower);
            backRightMotor.setPower(backRightPower);
                while (-gamepad2.left_stick_y == 1) {
                    drive = -gamepad1.left_stick_y;
                    strafe = gamepad1.left_stick_x;
                    rotate = gamepad1.right_stick_x;

                    frontLeftPower = (drive + strafe + rotate)/3;
                    frontRightPower = (drive - strafe - rotate)/3;
                    backLeftPower = (drive - strafe + rotate)/3;

                    frontLeftMotor.setPower(frontLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backLeftMotor.setPower(backLeftPower);
                    backRightMotor.setPower(backRightPower);

                    extendMotor.setTargetPosition((int) Target);
                    extendMotor.setPower(0.3);
                    extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                }
                extendMotor.setPower(0);
                while (gamepad2.left_stick_y == 1) {
                    drive = -gamepad1.left_stick_y;
                    strafe = gamepad1.left_stick_x;
                    rotate = gamepad1.right_stick_x;

                    frontLeftPower = (drive + strafe + rotate)/3;
                    frontRightPower = (drive - strafe - rotate)/3;
                    backLeftPower = (drive - strafe + rotate)/3;

                    frontLeftMotor.setPower(frontLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backLeftMotor.setPower(backLeftPower);
                    backRightMotor.setPower(backRightPower);

                    extendMotor.setTargetPosition(-(int) Target);
                    extendMotor.setPower(-0.3);
                    extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                }
                extendMotor.setPower(0);
                while (-gamepad2.right_stick_y == 1) {
                    drive = -gamepad1.left_stick_y;
                    strafe = gamepad1.left_stick_x;
                    rotate = gamepad1.right_stick_x;

                    frontLeftPower = (drive + strafe + rotate)/3;
                    frontRightPower = (drive - strafe - rotate)/3;
                    backLeftPower = (drive - strafe + rotate)/3;

                    frontLeftMotor.setPower(frontLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backLeftMotor.setPower(backLeftPower);
                    backRightMotor.setPower(backRightPower);

                    slideShiftMotor.setTargetPosition((int) Target);
                    slideShiftMotor.setPower(0.27);
                    slideShiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                }
                slideShiftMotor.setPower(0);

                while (gamepad2.right_stick_y == 1) {
                    drive = -gamepad1.left_stick_y;
                    strafe = gamepad1.left_stick_x;
                    rotate = gamepad1.right_stick_x;

                    frontLeftPower = (drive + strafe + rotate)/3;
                    frontRightPower = (drive - strafe - rotate)/3;
                    backLeftPower = (drive - strafe + rotate)/3;

                    frontLeftMotor.setPower(frontLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backLeftMotor.setPower(backLeftPower);
                    backRightMotor.setPower(backRightPower);

                    slideShiftMotor.setTargetPosition(-(int) Target);
                    slideShiftMotor.setPower(-0.27);
                    slideShiftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }
                slideShiftMotor.setPower(0);
                if (gamepad2.right_bumper) {
                    clawServo.setPosition(1);
                }
                if (gamepad2.left_bumper) {
                    clawServo.setPosition(0);
                }
                while (gamepad2.left_trigger != 0) {
                    rotateMotor.setPower(1);
                }
                rotateMotor.setPower(0);
                if (gamepad2.right_trigger != 0) {
                    rotateMotor.setPower(-1);
                }
                rotateMotor.setPower(0);

            }
        }
        }

