package steps;


import org.openqa.selenium.WebDriver;
import pages.FarePage;

public class FarePageSteps extends AbstractSteps{

    private FarePage farePage;
    public FarePageSteps(WebDriver driver) {
        super(driver);
        farePage = new FarePage(driver);
    }

    public FarePageSteps selectFarePlus(){
        farePage.clickSelectButton();
        return this;
    }

    public double getTotalPrice(){
       return farePage.getTotalPrice();
    }
    public boolean compareTicketsPrice(){

        return getTotalPrice() == getPriceForPerson() * 3 ;
    }

    public double getPriceForPerson(){return  farePage.getPriceForPerson();}
}
