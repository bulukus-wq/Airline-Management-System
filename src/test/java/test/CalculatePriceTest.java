package test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import manager.CalculatePrice;
import model.Bagage;
import model.Flight;
import model.Passenger;
import model.Plane;
import model.Reservation;
import model.Route;
import model.Seat;
import model.Seat.SeatClass;
import model.Ticket;

class CalculatePriceTest {

    private CalculatePrice calculator;
    private Bagage bagageNormal;
    private Bagage bagageOverweight;
    private Seat seatEco;
    private Passenger pas;
    private Flight flight;
    private Reservation resEcoOver;


    @BeforeEach
    void setUp() {
        calculator = new CalculatePrice();


        pas = new Passenger("111", "Test", "User", "555-1234");
        Plane plane = new Plane("PLA-101", "Boeing 737");
        Route route = new Route("Istanbul", "Ankara");
        flight = new Flight("FLI-101", plane, LocalDate.now().plusDays(1), LocalTime.of(10, 30), route, "IST", "ANK", 60);
        seatEco = new Seat("A6", Seat.SeatClass.ECONOMY, false);
        bagageNormal = new Bagage(10);
        bagageOverweight = new Bagage(25); 
        resEcoOver = new Reservation("RES-101", LocalDate.now(), flight, seatEco, pas, (int)bagageOverweight.getWeight());

    }


    @Test
    @DisplayName("Test: Business - Limit Alti Bagaj (Standart Fiyat)")
    void testBusiness_UnderLimit() {
       
        double expected = CalculatePrice.BUSINESS_PRICE;
        double actual = calculator.calculateTotalPrice(SeatClass.BUSINESS, 10);

        assertEquals(expected, actual, 0.001, "Limit alti bagajda sadece taban fiyat alinmalidir.");
    }

    @Test
    @DisplayName("Test: Business - Limit Ustu Bagaj (Cezali Fiyat)")
    void testBusiness_OverLimit() {
        // Beklenen: 1000 + (5 * 50) = 1250 TL
        double expected = 1000.0 + (5 * 50.0); 
        double actual = calculator.calculateTotalPrice(SeatClass.BUSINESS, 25);

        assertEquals(expected, actual, 0.001, "Limit asiminda 50 TL/kg fark eklenmelidir.");
    }


    @Test
    @DisplayName("Test: Economy - Limit Alti Bagaj (Standart Fiyat)")
    void testEconomy_UnderLimit() {
        double expected = CalculatePrice.ECONOMY_PRICE;
        double actual = calculator.calculateTotalPrice(SeatClass.ECONOMY, 10);

        assertEquals(expected, actual, 0.001);
    }

    @Test
    @DisplayName("Test: Economy - Limit Ustu Bagaj (Cezali Fiyat)")
    void testEconomy_OverLimit() {
        // Beklenen: 450 + (2 * 50) = 550 TL
        double expected = 450.0 + (2 * 50.0);
        double actual = calculator.calculateTotalPrice(SeatClass.ECONOMY, 17);

        assertEquals(expected, actual, 0.001);
    }


    @Test
    @DisplayName("Test: Null Koltuk Tipi Hatasi")
    void testCalculate_NullSeat() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTotalPrice(null, 10);
        }, "Null koltuk tipi girildiginde hata firlatilmalidir.");
    }

    
    @Test
    @DisplayName("Test: Ticket Sinifinin Otomatik Fiyat Hesaplamasi")
    void testTicket_AutoCalculation() {

        // Ekstra: 10 * 50 = 500 TL
        // Beklenen Toplam: 450 + 500 = 950 TL
        Ticket ticket = new Ticket("TIC-TEST", resEcoOver, bagageOverweight); 
        
        double expectedPrice = 450.0 + (10 * 50.0); 
        
        assertEquals(expectedPrice, ticket.getPrice(), 0.001, "Ticket nesnesi olusturulurken fiyat otomatik hesaplanmalidir.");
        assertEquals(CalculatePrice.ECONOMY_BAGGAGE_LIMIT, ticket.getBagageAllowance(), "Ticket nesnesi dogru bagaj limitini atamalidir.");
    }
}