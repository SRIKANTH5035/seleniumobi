package stepDefination;

import Utils.ExcelUtil;
import pageobjects.BPQuote;
import pageobjects.RHQuote;

public class Main {


    public static void main(String[] Args) {
        BPQuote bp;
        RHQuote rh;

        String input = "\\\\sdc1as1350\\TestPro\\GIS\\Output\\GIS_OBI_Output\\";
        String output = ExcelUtil.createExcelOutputResults(input, "WebGis", "");

        for (int i = 1; i <= Integer.parseInt(Args[0]); i++) {
            System.out.println("\nTest Case [" + i + "] started");

            try {
                if (Args[1].equalsIgnoreCase("BP")) {
                    bp = new BPQuote();
                    bp.execute();
                    bp.saveToExcel(output);

                } else {
                    rh = new RHQuote();
                    rh.execute();
                    rh.saveToExcel(output);
                }

                System.out.println("Test Case [" + i + "] successfully completed");
            } catch (Exception e) {

                System.err.println("\n[Selenium Failure]: " + e);
            }
        }
    }
}
