package pageobjects;

import org.openqa.selenium.By;

public class BPLocators {
    //About Your Business
    static final By businessType = By.xpath("//input[@id='occupation']");
    static final By getBusinessTypeList = By.xpath("//div[@role='listitem']");
    static final By businessEstablished = By.xpath("//select[@id='businessStartDate']");
    static final By suburbTown = By.xpath("//input[@id='riskAddress']");
    static final By getSuburbTownList = By.xpath("//div[@id='riskAddress_autocomplete_option_group']");
    static final By saveEmail = By.xpath("//input[@id='reminderEmail']");
    static final By stampDutyExemptionYes = By.xpath("//label[@id='stampDutyExemption_true_label']");
    static final By aboutBusinessContinue = By.xpath("//button[@id='aboutYourBusiness_continue']");
    static final By coverSelectionContinue = By.xpath("//button[@id='coverSelection_next']");

    //Choose Cover
    static final By stateBusinessList = By.xpath("//*[@id='CSGPL_EMPLOYEES_TURNOVERS_DETAILS-checkbox-NSW']");
    static final By operateOverseasNo = By.xpath("//label[@id='CSGPL_EMPLOYEES_TURNOVERS_DETAILS-radio-overseas_unchecked_label']");
    static final By estimatedTurnoverState = By.xpath("//input[@name='CSGPL_EMPLOYEES_TURNOVERS_DETAILS_details[0].turnover']");
    static final By employeeCount = By.xpath("//*[@id='CSGPL_FORM']/div[2]/section[1]/div[2]/div/div[3]/div[3]/div/div[2]/input");
    static final By liabilityCover = By.xpath("//select[@id='CSGPL_SUM_INSURED']");
    static final By contractorsSubContractorsNo = By.xpath("//label[@id='CSGPL_CONTRACTORS_ENGAGED_false_label']");
    static final By labourHireNo = By.xpath("//*[@id='CSGPL_LABOUR_HIRE_ENGAGED_false_label']");
    static final By additionalCoverNo = By.xpath("//*[@id='CSGPL_ADDITIONAL_COVER_PHYSICAL_LEGAL_CONTROL_LIMIT_false_label']");
    static final By generalProductLibilityNo = By.xpath("//label[@id='CSGPL_ANY_CLAIMS_IN_LAST_5_YEARS_false_label']");
    static final By submit = By.xpath("//button[@type='submit']");
    static final By outputQuote = By.xpath("//*[@id=\"newPriceHeader\"]/div[1]/p[1]");
    static final By outputDate = By.xpath("//*[@id=\"newPriceHeader\"]/div[1]/p[2]");
}