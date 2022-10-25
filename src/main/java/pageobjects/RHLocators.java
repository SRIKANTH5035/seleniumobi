package pageobjects;

import org.openqa.selenium.By;

public class RHLocators {
    //About Your Business
    static final By ageCategoryLessThan54Years = By.xpath("//label[@id='dateOfBirth_18_TO_54_YEARS_label']");

    static final By addressToggle = By.xpath("//span[@id='address-toggle']");
    static final By addressStreet = By.xpath("//input[@id='riskAddress-streetAddress']");
    static final By addressPostcode = By.xpath("//input[@id='riskAddress-suburbPostcode']");
    static final By addressPostcodeSelect = By.xpath("//button[@id='riskAddress-suburbPostcode_result_1']");

    static final By annualIncome = By.xpath("//input[@id='OCC001_INCOME']");
    static final By farmSizeInHectares = By.xpath("//input[@id='OCC001_PROPERTY_SIZE']");
    static final By propertyFarmStayNo = By.xpath("//label[@id='OCC001_PROPERTY_USE_false_label']");
    static final By stampDutyExemptionNo = By.xpath("//label[@id='stampDutyExemption_false_label']");
    static final By aboutBusinessContinue = By.xpath("//button[@id='aboutYourBusiness_continue']");

    //Choose Cover
    static final By primaryResidence = By.xpath("//label[@id='CSDH_1_CSDH_USE_BU01_label']");
    static final By houseAge = By.xpath("//label[@id='CSDH_1_CSDH_CONSTRUCTION_YEAR_CY01_label']");
    static final By wallType = By.xpath("//select[@id='CSDH_1_CSDH_MATERIALS']");
    static final By replacementValue = By.xpath("//input[@id='CSDH_1_CSDH_SUM_INSURED']");
    static final By accidentalDamageYes = By.xpath("//label[@id='CSDH_1_CSDH_ACCIDENTAL_DAMAGE_true_label']");
    static final By propertyConditionYes = By.xpath("//label[@id='CSDH_1_CSDH_BUILDING_STATE_true_label']");
    static final By submit = By.xpath("//button[@id='CSDH_continue']");
    static final By coverAmount10Million = By.xpath("//label[@id='CSL_SUM_INSURED_10000000_label']");
    static final By finalSubmit = By.xpath("//button[@id='CSL_continue']");
    static final By output = By.xpath("//*[@id=\"nrma\"]/div[3]/span/div[2]/div[1]/div/div/div/span");
}