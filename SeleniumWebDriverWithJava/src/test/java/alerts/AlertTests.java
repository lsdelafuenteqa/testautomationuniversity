package alerts;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.AlertsPage;

import static org.testng.Assert.assertEquals;

public class AlertTests extends BaseTest {

    @Test (priority = 0)
    public void testAcceptAlert(){

        AlertsPage alertsPage = homePage.clickJSAlert();
        alertsPage.triggerAlert();
        alertsPage.alert_clickToAccept();

        assertEquals(alertsPage.getResult(), "You successfuly clicked an alert", "Results text incorrect");

    }

    @Test (priority = 1)
    public void testGetTextFromAlert(){
        AlertsPage alertsPage = homePage.clickJSAlert();
        alertsPage.triggerConfirm();

        String text = alertsPage.alert_getText();
        alertsPage.alert_clickToDismiss();

        assertEquals(text, "I am a JS Confirm", "Alert text incorrect");

    }

    @Test (priority = 2)
    public void testSetInputInAlert(){
        AlertsPage alertsPage = homePage.clickJSAlert();
        alertsPage.triggerPrompt();

        String text = "TAU rocks";
        alertsPage.alert_setInput(text);
        alertsPage.alert_clickToAccept();

        assertEquals(alertsPage.getResult(), "You entered: " + text, "Results text incorrect");
    }

}
