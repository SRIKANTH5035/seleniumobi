package pageobjects;

import Utils.ChromeDriverManager;

import Utils.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;



public class RHQuote {
    private static final String homeUrl = "https://tst.k8s.devlabs/hobby-farm/";
    private static final String outputPath = "\\\\\\\\sdc1as1350\\\\TestPro\\\\GIS\\\\Output\\\\GIS_OBI_Output\\\\";
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
        WebElement t;

        //Less than 54 Years Old
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.ageCategoryLessThan54Years));
        t.click();

        //Property Address
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.addressToggle));
        t.click();

        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.addressStreet));
        t.sendKeys("3 GREYSTANES RD");

        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.addressPostcode));
        t.sendKeys("2145");

        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.addressPostcodeSelect));
        t.click();

        //Annual Income
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.annualIncome));
        t.sendKeys("50000");

        //Property Size
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.farmSizeInHectares));
        t.sendKeys("11");

        //Farm Stay
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.propertyFarmStayNo));
        t.click();

        //Stamp Duty
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.stampDutyExemptionNo));
        t.click();

        //About Your Business Continue Button
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.aboutBusinessContinue));
        t.click();
    }

    public void chooseCover() throws Exception {
        WebElement t;

        //Residence
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.primaryResidence));
        t.click();

        //Age of Property
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.houseAge));
        t.click();

        //Construction Type
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.wallType));
        Select wt = new Select(t);
        wt.selectByVisibleText("Cement");

        //Replacement Value of Home
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.replacementValue));
        t.sendKeys("100000");

        //Accidental Damage
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.accidentalDamageYes));
        t.click();

        //Property Condition
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.propertyConditionYes));
        t.click();

        //Submit
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.propertyConditionYes));
        t.click();

        //Continue
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.submit));
        t.click();

        //Cover Amount
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.coverAmount10Million));
        t.click();

        //Submit
        t = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.finalSubmit));
        t.click();

        WebElement output = w.until(ExpectedConditions.presenceOfElementLocated(RHLocators.output));

        if (output.getText().contains("Quote no.")) {
            quoteNo.add(output.getText().split("\\|")[0].replace("Quote no. ", "").trim());
            policyStartDate.add(output.getText().split("\\|")[1].replace("Policy start date: ", "").trim());
            errorMessage.add("No");
        } else {
            quoteNo.add("");
            policyStartDate.add("");
            errorMessage.add(output.getText());
        }

        if (!output.getText().isEmpty())
            System.out.println("OBI Complete: Quote Number [" + quoteNo.get(quoteNo.size() - 1) + "], Start Date [" + policyStartDate.get(policyStartDate.size() - 1) + "]");
        else throw new Exception("[OBI Error]: Could not complete execution");
    }

    public void saveToExcel() throws Exception {
        ArrayList<String> headerList = new ArrayList<>();

        headerList.add("Quote Number");
        headerList.add("Policy Start Date");
        headerList.add("Has Error");

        ExcelUtil.addRowToExcel(headerList, outputPath, "WebGis");

        for (int i = 0; i < errorMessage.size(); i++) {
            ArrayList<String> outputRow = new ArrayList<>();

            outputRow.add(quoteNo.get(i));
            outputRow.add(policyStartDate.get(i));
            outputRow.add(errorMessage.get(i));

            ExcelUtil.addRowToExcel(outputRow, outputPath, "WebGis");

        }
    }
}