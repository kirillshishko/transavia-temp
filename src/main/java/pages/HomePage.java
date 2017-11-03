package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;
import wrapper.CustomElement;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[@class='primary-navigation_item']/a[text()[contains(.,'Manage your booking')]]")
    private WebElement manageYourBookingDropDown;
    @FindBy(xpath = "//span[text()[contains(.,'View your booking')]]")
    private WebElement viewYourBookingDropDownItem;
    @FindBy(xpath = "//li[@class='primary-navigation_item']/a[text()[contains(.,'Service')]]")
    private WebElement serviceDropDownMenu;
    @FindBy(xpath = "//li[1]/a[@href='/en-EU/service/hand-luggage/']" )
    private WebElement handluggageDropDownitem;
    @FindBy(xpath = "//li[@class='primary-navigation_item']/a[text()[contains(.,'Plan and book')]]")
    private WebElement planAndBookDropDownMenu;
    @FindBy(xpath = "//ul[contains(@class,'sub-navigation-level-2_list')]//a[@href='/en-EU/book-a-flight/advanced-search/search/']")
    private WebElement advancedSearchDropDownItem;
    @FindBy(css = ".header_bar .secondary-navigation_link[href='/en-EU/destinations/']")
    private WebElement destinationsLink;
    // Airports
    @FindBy(id = "routeSelection_DepartureStation-input")
    private WebElement departureAirportTextbox;
    @FindBy(id = "routeSelection_ArrivalStation-input")
    private WebElement destinationAirportTextbox;
    @FindBy(css = ".item")
    private WebElement autocompleteResult;
    @FindBy(css = ".label--bold--bp0")
    // Dates
    private WebElement returnOnCheckbox;
    @FindBy(id = "dateSelection_OutboundDate-datepicker")
    private WebElement departureDateTextbox;
    @FindBy(id = "dateSelection_IsReturnFlight-datepicker")
    private WebElement returnDateTextbox;
    @FindBy(id = "booking-passengers-input")
    private WebElement passengersInput;
    @FindBy(css = ".icon-passenger")
    private WebElement passengerIcon;
    // Passengers form
    @FindBy(css = ".selectfield.adults .decrease")
    private WebElement adultsDecreaseButton;
    @FindBy(css = ".selectfield.adults .increase")
    private WebElement adultsIncreaseButton;
    @FindBy(css = ".selectfield.children .decrease")
    private WebElement childrensDecreaseButton;
    @FindBy(css = ".selectfield.children .increase")
    private WebElement childrensIncreaseButton;
    @FindBy(css = ".button-secondary.close")
    private WebElement savePassengersCountButton;

    @FindBy(css = ".desktop .button-primary")
    private WebElement searchButton;
    @FindBy(css = "[ data-initialized='ui/CombinationFlightHelper']")
    private WebElement addMultipleDestinationsLink;



    @FindBy(xpath = "//*[@id='dateSelection_OutboundDate-datepicker']/following-sibling::span")
    private WebElement outboundDatePicker;

    public void setDepartureAirport(String city){
        CustomElement departureAirportTextbox = new CustomElement(this.departureAirportTextbox);
        waiter.waitForPageLoaded(driver, TIMEOUT);
        waiter.waitForElementIsClickable(departureAirportTextbox);
        Waiter.pause(1500);
        logger.info("input departure airport");
        departureAirportTextbox.sendKeys(city);
        waiter.waitForElementIsClickable(autocompleteResult);
        autocompleteResult.click();

    }

    public void setDestinationAirport(String city){
        CustomElement destinationAirportTextbox = new CustomElement(this.destinationAirportTextbox);
        waiter.waitForElementIsClickable(destinationAirportTextbox);
        logger.info("input destination airport");
        destinationAirportTextbox.sendKeys(city);
        waiter.waitForElementIsClickable(autocompleteResult);
        autocompleteResult.click();

    }

    public void setAirports(String cityFrom,String cityTo){
        setDepartureAirport(cityFrom);
        setDestinationAirport(cityTo);
    }

    public void uncheckReturnOnCheckbox(){
        CustomElement returnOnCheckbox = new CustomElement(this.returnOnCheckbox);
        returnOnCheckbox.click();
        logger.info("Click checkbox 'ReturnOn' ");
    }

    public void setDepartureDate(String date){
        CustomElement departureDateTextbox = new CustomElement(this.departureDateTextbox);
        waiter.waitForElementIsClickable(departureDateTextbox);
        logger.info("set departure date");
        departureDateTextbox.clear();
        departureDateTextbox.sendKeys(date);
    }

    public void setReturnDate(String date){
        CustomElement returnDateTextbox = new CustomElement(this.returnDateTextbox);
        waiter.waitForElementIsClickable(returnDateTextbox);
        logger.info("set return date");
        returnDateTextbox.clear();
        returnDateTextbox.sendKeys(date);
    }

    public void setDates(boolean isReturnOn,String date,String returnDate){
        if(isReturnOn){
            setDepartureDate(date);
            setReturnDate(returnDate);
        }else {
            uncheckReturnOnCheckbox();
            setDepartureDate(date);
        }
    }


   public void addAdultPassengers(){
        CustomElement adultsIncreaseButton = new CustomElement(this.adultsIncreaseButton);
       waiter.waitForElementIsClickable(adultsIncreaseButton);
       logger.info("click adults increase button");
       adultsIncreaseButton.click();
   }

   public void addChildrensPassengers(){
       CustomElement childrensIncreaseButton = new CustomElement(this.childrensIncreaseButton);
       waiter.waitForElementIsClickable(childrensIncreaseButton);
       logger.info("click children increase button");
       childrensIncreaseButton.click();
   }

   public void clickPassengersInput(){
       CustomElement passengersInput = new CustomElement(this.passengersInput);
       waiter.waitForElementIsClickable(passengersInput);
       logger.info("click passengers input");
       passengersInput.click();

   }

    public void setPassengersNumber(String adults,String children) throws NumberFormatException {

       long adultsNumber = Integer.parseInt(adults);
       long childrenNumber = Integer.parseInt(children);

        if( (adultsNumber > 1 ) || (childrenNumber > 0) ){
           clickPassengersInput();

           for (int i = 1;i<adultsNumber;i++){
               addAdultPassengers();
           }

           for (int i = 0;i < childrenNumber;i++){
                addChildrensPassengers();
           }

           clickSavePassengersCountButton();
        }

    }


   public void clickSavePassengersCountButton(){
        CustomElement savePassengersCountButton = new CustomElement(this.savePassengersCountButton);
       waiter.waitForElementIsClickable(savePassengersCountButton);
       logger.info("click 'Save' passengers count  button");
       savePassengersCountButton.click();
   }

   public void clickSearchButton(){
       CustomElement searchButton = new CustomElement(this.searchButton);
       waiter.waitForElementIsClickable(searchButton);
       logger.info("click 'Search' button");
       searchButton.click();
   }

   public void clickManageYourBooking(){
       waiter.waitForPageLoaded(driver, TIMEOUT);
       waiter.waitForjQuery();
       CustomElement manageYourBookingDropDown = new CustomElement(this.manageYourBookingDropDown);
       waiter.waitForElementIsClickable(manageYourBookingDropDown);
       logger.info("click 'Manage your booking' drop down");
       Waiter.pause(1500);
       manageYourBookingDropDown.click();
   }

   public void clickViewYourBooking(){
       CustomElement viewYourBookingDropDownItem = new CustomElement(this.viewYourBookingDropDownItem);
       waiter.waitForElementIsClickable(viewYourBookingDropDownItem);
       viewYourBookingDropDownItem.click();
       logger.info("click 'View your booking' ");
   }

   public void clickAddMultipleDestinationsLink(){

       waiter.waitForPageLoaded(driver, TIMEOUT);
       CustomElement addMultipleDestinationsLink = new CustomElement(this.addMultipleDestinationsLink);
       waiter.waitForElementIsClickable(addMultipleDestinationsLink);
       logger.info("click 'Add multiple destinations link'");
       addMultipleDestinationsLink.click();
   }

    public void clickServiceDropDownMenu(){

        waiter.waitForPageLoaded(driver,TIMEOUT);
        CustomElement serviceDropDownMenu = new CustomElement(this.serviceDropDownMenu);
        Waiter.pause(2000);
        waiter.waitForElementIsClickable(serviceDropDownMenu);
        logger.info("click 'Service' drop down");
        serviceDropDownMenu.click();
    }

    public void clickHandLuggageDropDownItem(){
        CustomElement handluggageDropDownitem = new CustomElement(this.handluggageDropDownitem);
        waiter.waitForElementIsClickable(handluggageDropDownitem);
        handluggageDropDownitem.click();
        logger.info("click 'Handluggage' drop down item");
    }

    public void clickPlanAndBookDropDownMenu(){

        waiter.waitForPageLoaded(driver,TIMEOUT);
        CustomElement planAndBookDropDownMenu = new CustomElement(this.planAndBookDropDownMenu);
        Waiter.pause(2000);
        waiter.waitForElementIsClickable(planAndBookDropDownMenu);
        planAndBookDropDownMenu.click();
        logger.info("click 'Plan and book' drop down item");

    }

    public void clickAdvancedSearchDropDownItem(){
        CustomElement advancedSearchDropDownItem = new CustomElement(this.advancedSearchDropDownItem);
        waiter.waitForElementIsClickable(advancedSearchDropDownItem);
        advancedSearchDropDownItem.click();
        logger.info("click 'Advanced search' drop down item");
    }

    public void clickDestinationsLink(){

        waiter.waitForPageLoaded(driver,TIMEOUT);
        waiter.waitForjQuery();
        CustomElement destinationsLink = new CustomElement(this.destinationsLink);
        waiter.waitForElementIsClickable(destinationsLink);
        destinationsLink.click();
        logger.info("click 'Destinations link' ");
    }


    public void clickOutboundDatePicker(){
        waiter.waitForPageLoaded(driver,TIMEOUT);
        waiter.waitForjQuery();
        CustomElement outboundDatePicker = new CustomElement(this.outboundDatePicker);
        Waiter.pause(5000);
        waiter.waitForElementIsClickable(outboundDatePicker);
        outboundDatePicker.click();
        logger.info("click date picker");
    }

}
