package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveL {

    private DcMotor drivel;
    private DcMotor driver;

    public DriveL(HardwareMap hardwareMap) {

        drivel = hardwareMap.get(DcMotor.class, "drivel");
        driver = hardwareMap.get(DcMotor.class, "driver");

        drivel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        driver.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drivel.setDirection(DcMotorSimple.Direction.REVERSE);
        driver.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void drive(double forward, double turn) {
        double leftPower  = forward + turn;
        double rightPower = forward - turn;

        leftPower  = Math.max(-1, Math.min(1, leftPower));
        rightPower = Math.max(-1, Math.min(1, rightPower));

        drivel.setPower(leftPower);
        driver.setPower(rightPower);
    }

    public void driveForTime(double leftPower, double rightPower, long timeMs) {
        drivel.setPower(leftPower);
        driver.setPower(rightPower);

        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            // ignore
        }

        drivel.setPower(0);
        driver.setPower(0);
    }
}
