package pageobjects;



import Utils.ChromeDriverManager;
import Utils.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class BPQuote {
    private static final String homeUrl = "https://tst.k8s.devlabs/business-insurance-quote/";
    private ChromeDriverManager d;
    WebDriverWait w;

    private static final ArrayList<String> quoteNo = new ArrayList<>();
    private static final ArrayList<String> policyStartDate = new ArrayList<>();
    private static final ArrayList<String> errorMessage = new ArrayList<>();

    public void execute() throws Exception {
        try {
            d = new ChromeDriverManager();
            w = new WebDriverWait(d.getDriver(), Duration.ofSeconds(10));
            d.getDriver().get(homeUrl);

            aboutYourBusiness();
            chooseCover();

            d.closeDriver();
        } catch (Exception e) {
            d.closeDriver();
            throw e;
        }
    }

    private void aboutYourBusiness() {
        List<WebElement> l;
        WebElement t;

        //Business Type
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.businessType));
        t.sendKeys("Leather belt manufacturer");

        l = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(BPLocators.getBusinessTypeList));
        for (WebElement e : l) {
            if (e.getText().contains("Leather belt manufacturer")) {
                e.click();
                break;
            }
        }

        //Business Established
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.businessEstablished));
        t.sendKeys("2017");

        //Suburb
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.suburbTown));
        t.sendKeys("AUSTRAL, NSW, 2179");

        l = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(BPLocators.getSuburbTownList));
        for (WebElement e : l) {
            if (e.getText().contains("AUSTRAL, NSW, 2179")) {
                e.click();
                break;
            }
        }

        //Email
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.saveEmail));
        t.sendKeys("sss@gmail.com");

        //Stamp Duty
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.stampDutyExemptionYes));
        t.click();

        //About Your Business Continue Button
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.aboutBusinessContinue));
        t.click();

        //Cover Selection Continue Button
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.coverSelectionContinue));
        t.click();
    }

    public void chooseCover() throws Exception {
        WebElement t;

        //State
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.stateBusinessList));
        t.click();

        //Operate overseas in next 12 months
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.operateOverseasNo));
        t.click();

        //Estimated Turnover
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.estimatedTurnoverState));
        t.sendKeys("43333");

        //Employee Count
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.employeeCount));
        t.sendKeys("1");

        //Product Liability
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.liabilityCover));
        Select pl = new Select(t);
        pl.selectByVisibleText("$20 million");

        //Contractors/Sub Contractors
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.contractorsSubContractorsNo));
        t.click();

        //Labour Hire
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.labourHireNo));
        t.click();

        //Additional Cover
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.additionalCoverNo));
        t.click();

        //General Product Liability
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.generalProductLibilityNo));
        t.click();

        //Submit
        t = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.submit));
        t.click();

        WebElement outputQuote = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.outputQuote));
        WebElement outputDate = w.until(ExpectedConditions.presenceOfElementLocated(BPLocators.outputDate));

        if (outputQuote.isDisplayed()) {
            quoteNo.add(outputQuote.getText().replace("Quote number: ", ""));
            policyStartDate.add(outputDate.getText().replace("Policy start date: ", ""));
            errorMessage.add("No");
        } else {
            quoteNo.add("");
            policyStartDate.add("");
            errorMessage.add(outputQuote.getText());
        }

        if (!outputQuote.getText().isEmpty())
            System.out.println("OBI Complete: Quote Number [" + quoteNo.get(quoteNo.size() - 1) + "], Start Date [" + policyStartDate.get(policyStartDate.size() - 1) + "]");
        else throw new Exception("[OBI Error]: Could not complete execution");
    }

    public void saveToExcel(String path) throws Exception {
        ArrayList<String> headerList = new ArrayList<>();

        headerList.add("Quote Number");
        headerList.add("Policy Start Date");
        headerList.add("Has Error");

        ExcelUtil.addRowToExcel(headerList, path, "WebGis");

        for (int i = 0; i < errorMessage.size(); i++) {
            ArrayList<String> outputRow = new ArrayList<>();

            outputRow.add(quoteNo.get(i));
            outputRow.add(policyStartDate.get(i));
            outputRow.add(errorMessage.get(i));

            ExcelUtil.addRowToExcel(outputRow, path, "WebGis");

        }
    }
}