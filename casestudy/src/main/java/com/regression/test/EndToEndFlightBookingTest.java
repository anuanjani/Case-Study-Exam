package com.regression.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.process.fligthbooking.processUtility;

public class EndToEndFlightBookingTest extends processUtility{
	
	static ExtentTest test;
	static ExtentReports report;
	
	@Test
	public void testEndToEndFlightBooking() throws IOException, InterruptedException {
		processUtility utilityprocess = new processUtility();
		
		login();									//Step: 01.
		utilityprocess.itineraryDetails();			//Step: 02,03,04.
		utilityprocess.flightSelection();       	//Step: 05,06,07.
		utilityprocess.extrasBaggage();				//Step:	08,09,10.
		utilityprocess.passengerInformation();		//Step: 11.
		utilityprocess.validateBookingDetails();	//Step: 12,13,14,15.
		utilityprocess.paymentAndConfirmation();    //Step: 16,17,18.
	}
}