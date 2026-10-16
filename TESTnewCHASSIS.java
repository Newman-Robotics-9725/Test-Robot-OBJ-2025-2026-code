package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Thread;



/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Test kek", group="Linear OpMode")

public class TESTnewCHASSIS extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive   = null;
    private DcMotor rightFrontDrive  = null;
    private DcMotor leftBackDrive  = null;
    private DcMotor rightBackDrive  = null;
    
    
    public void setDrivePower(double leftFrontPower, double rightFrontPower, double rightBackPower, double leftBackPower) {
            // Output the values to the motor drives.
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            rightBackDrive.setPower(rightBackPower);
            leftBackDrive.setPower(leftBackPower);
        }   
        
        public void driveRobot(double axial, double lateral, double yaw) {
            // Combine drive and turn for blended motion.
            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;
            
        setDrivePower(leftFrontPower, rightFrontPower, rightBackPower, leftBackPower);
        }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "drivefl");//port 0
        rightFrontDrive = hardwareMap.get(DcMotor.class, "drivefr");//port 1
        leftBackDrive = hardwareMap.get(DcMotor.class, "drivebl");//port 2
        rightBackDrive = hardwareMap.get(DcMotor.class, "drivebr");//port 3
        

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);//FORWARD
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //driveRobot(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            driveRobot(gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

            // Show the elapsed game time and wheel power.
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            //telemetry.addData("Launch Power (%.2f)", launchPower);
            telemetry.update();
        }
    }
}
//.update();
 //       }
  //  }
//}
