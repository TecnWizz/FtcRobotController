package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(name = "avocado teleop cu pid")
public class Teleop_with_PID extends LinearOpMode {
    boolean toggleState = false;
    double kP = 0;
    double kI = 0;
    double kD = 0;
    double kF = 0;
    PIDFCoefficients pidf = new PIDFCoefficients(kP,kI,kD,kF);
    double powerMode = 3;
    public static double clawRotationPos = 0.69;

    private static final double TICKS_PER_ROTATION = 2786.2;
    private static final double ROTATION_DIVISOR = 2;
    public static double DEFAULT_POWER_MODE = 1.5;
    public static double ALTERNATE_POWER_MODE = 3;

    public static double SLOW_POWER_MODE = 4;

    public static  double VERY_SLOW_POWER_MODE = 5.5;

    private DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private DcMotor intakeMotor, slideShiftMotor, extendMotor;
    private Servo rotateServo, clawServo;
    Gamepad lastGamepad1 = new Gamepad();
    Gamepad lastGamepad2 = new Gamepad();
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        initializeHardware();
        double targetPosition = TICKS_PER_ROTATION / ROTATION_DIVISOR;
        waitForStart();

        while (opModeIsActive()) {
            lastGamepad1.copy(currentGamepad1);
            lastGamepad2.copy(currentGamepad2);


            handleDriving(lastGamepad1);
            handleExtendMotorControl(currentGamepad2);
            handleSlideShiftMotorControl(currentGamepad2);
            handleServoControl(currentGamepad2);
            handleIntakeMotorControl(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);
            telemetry.update();

        }
    }

    private void initializeHardware() {
        rotateServo = hardwareMap.servo.get("rotateServo");
        clawServo = hardwareMap.servo.get("clawServo");
        intakeMotor = hardwareMap.dcMotor.get("intakeMotor");
        slideShiftMotor = hardwareMap.dcMotor.get("slideShiftMotor");
        extendMotor = hardwareMap.dcMotor.get("extendMotor");

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideShiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        resetEncoders(slideShiftMotor, extendMotor);

        rotateServo.setPosition(1);
        clawServo.setPosition(1);
    }



    private void resetEncoders(DcMotor... motors) {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    private void handleDriving(Gamepad lastGamepad1) {
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = Math.abs(-gamepad1.right_stick_x-gamepad1.right_stick_x);
        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(rotate), 1);

        if (gamepad1.dpad_up && !lastGamepad1.dpad_up) {
            powerMode = DEFAULT_POWER_MODE;
        }

        if (gamepad1.dpad_down && !lastGamepad1.dpad_down) {
            powerMode = ALTERNATE_POWER_MODE;
        }

        if (gamepad1.dpad_right && !lastGamepad1.dpad_right) {
            powerMode = SLOW_POWER_MODE;
        }

        if (gamepad1.dpad_left && !lastGamepad1.dpad_left) {
            powerMode = VERY_SLOW_POWER_MODE;
        }
        double frontLeftPower = ((drive + strafe + rotate) / powerMode)/denominator;
        double frontRightPower = ((drive - strafe - rotate) / powerMode)/denominator;
        double backLeftPower = ((drive - strafe + rotate) / powerMode)/denominator;
        double backRightPower = ((drive + strafe - rotate) / powerMode)/denominator;

        setMotorPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

        telemetry.addData("PowerMode", powerMode);
    }

    private void setMotorPowers(double frontLeft, double frontRight, double backLeft, double backRight) {
        frontLeftMotor.setPower(frontLeft);
        frontRightMotor.setPower(frontRight);
        backLeftMotor.setPower(backLeft);
        backRightMotor.setPower(backRight);
    }

    private void handleExtendMotorControl(Gamepad currentGamepad2){
        extendMotor.setPower(currentGamepad2.left_stick_y);
    }

    private void handleSlideShiftMotorControl(Gamepad currentGamepad2) {
        slideShiftMotor.setPower(currentGamepad2.right_stick_y);
    }

    private void handleServoControl(Gamepad currentGamepad2) {
        if (currentGamepad2.square) {
            rotateServo.setPosition(0);
        } else if (currentGamepad2.circle) {
            rotateServo.setPosition(clawRotationPos);
        }

        if (currentGamepad2.right_bumper) {
            clawServo.setPosition(1);
        } else if (currentGamepad2.left_bumper) {
            clawServo.setPosition(0);
        }
    }

    private void handleIntakeMotorControl(Gamepad currentGamepad2) {
        if (currentGamepad2.right_trigger > 0) {
            intakeMotor.setPower(currentGamepad2.right_trigger / 2.5);
        } else if (gamepad2.left_trigger > 0) {
            intakeMotor.setPower(-currentGamepad2.left_trigger / 2.5);
        } else {
            intakeMotor.setPower(0);
        }
    }
}
